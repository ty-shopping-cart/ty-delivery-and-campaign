package com.trendyol.tydeliveryandcampaign.repository;

import com.trendyol.tydeliveryandcampaign.model.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParameterRepository extends JpaRepository<Parameter,Long> {
    Parameter findByKey(String key);
}
