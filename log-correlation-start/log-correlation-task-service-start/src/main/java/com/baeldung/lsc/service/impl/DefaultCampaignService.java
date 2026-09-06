package com.baeldung.lsc.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.baeldung.lsc.persistence.model.Campaign;
import com.baeldung.lsc.persistence.repository.CampaignRepository;
import com.baeldung.lsc.service.CampaignService;

@Service
public class DefaultCampaignService implements CampaignService {

    private CampaignRepository campaignRepository;

    public DefaultCampaignService(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    @Override
    public Optional<Campaign> findById(Long id) {
        return campaignRepository.findById(id);
    }

    @Override
    public Campaign save(Campaign campaign) {
        return campaignRepository.save(campaign);
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
