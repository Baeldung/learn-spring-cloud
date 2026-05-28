package com.baeldung.lsc.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.baeldung.lsc.persistence.model.Campaign;
import com.baeldung.lsc.persistence.repository.CampaignRepository;
import com.baeldung.lsc.service.CampaignService;
import com.baeldung.lsc.web.dto.NotificationDto;

@Service
public class DefaultCampaignService implements CampaignService {
    private static final Logger log = LoggerFactory.getLogger(DefaultCampaignService.class);
    private static final String NOTIFICATION_SERVICE_URL = "http://localhost:8081";

    private CampaignRepository campaignRepository;
    private RestClient restClient;
    private CircuitBreakerFactory<?, ?> circuitBreakerFactory;

    public DefaultCampaignService(
      CampaignRepository campaignRepository,
      RestClient.Builder restClientBuilder,
      CircuitBreakerFactory<?, ?> circuitBreakerFactory) {
        this.campaignRepository = campaignRepository;
        this.restClient = restClientBuilder.baseUrl(NOTIFICATION_SERVICE_URL).build();
        this.circuitBreakerFactory = circuitBreakerFactory;
    }

    @Override
    public Optional<Campaign> findById(Long id) {
        return campaignRepository.findById(id);
    }

    @Override
    public Campaign save(Campaign campaign) {
        Campaign saved = campaignRepository.save(campaign);
        NotificationDto notification = new NotificationDto(
            "task-service@baeldung.com",
            "admin@example.com",
            "Campaign created: " + saved.getName(),
            "Campaign with code " + saved.getCode() + " has been created.");
        circuitBreakerFactory.create("campaign-cb")
            .run(() -> restClient.post()
                .uri("/notification/send")
                .contentType(MediaType.APPLICATION_JSON)
                .body(notification)
                .retrieve()
                .toBodilessEntity(),
              throwable -> {
                  log.warn("Notification call skipped: {}", throwable.getMessage());
                  return null;
              });
        return saved;
    }

    @Override
    public Iterable<Campaign> findAll() {
        return campaignRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        campaignRepository.deleteById(id);
    }

    @Override
    public Iterable<Campaign> findByName(String name) {
        return campaignRepository.findByNameContaining(name);
    }
}
