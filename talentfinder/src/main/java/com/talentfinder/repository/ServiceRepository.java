package com.talentfinder.repository;

import com.talentfinder.model.BusinessService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<BusinessService, Long> {
    public List<BusinessService> findAllByTitleContaining(String title);
}
