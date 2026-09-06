package com.baeldung.lsc.persistence.repository;

import com.baeldung.lsc.persistence.model.Campaign;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CampaignRepository extends PagingAndSortingRepository<Campaign, Long>, CrudRepository<Campaign, Long> {

    Iterable<Campaign> findByNameContaining(String name);

}
