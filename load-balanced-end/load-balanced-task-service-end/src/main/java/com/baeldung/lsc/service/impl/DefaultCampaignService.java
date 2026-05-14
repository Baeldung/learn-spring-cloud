package com.baeldung.lsc.service.impl;

import java.util.Optional;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.baeldung.lsc.persistence.model.Campaign;
import com.baeldung.lsc.persistence.repository.CampaignRepository;
import com.baeldung.lsc.service.CampaignService;
import com.baeldung.lsc.web.dto.NotificationDto;

@Service
public class DefaultCampaignService implements CampaignService {
    private CampaignRepository campaignRepository;
    private final RestClient restClient;

    public DefaultCampaignService(CampaignRepository campaignRepository, @LoadBalanced RestClient.Builder restClientBuilder) {
        this.campaignRepository = campaignRepository;
        this.restClient = restClientBuilder.build();
    }

    @Override
    public Optional<Campaign> findById(Long id) {
        return campaignRepository.findById(id);
    }

    @Override
    public Campaign save(Campaign campaign) {
        Campaign savedCampaign = campaignRepository.save(campaign);
        NotificationDto notificationDto = new NotificationDto("task-service@baeldung.com", "admin@baeldung.com", "Campaign Saved", "Campaign '" + savedCampaign.getName() + "' has been saved.");
        restClient.post()
                .uri("http://NOTIFICATION-SERVICE/notification/send")
                .body(notificationDto)
                .retrieve()
                .toBodilessEntity();
        return savedCampaign;
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
