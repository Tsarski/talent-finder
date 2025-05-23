package com.talentfinder.repository;

import com.talentfinder.model.BusinessService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<BusinessService, Long> {
}
