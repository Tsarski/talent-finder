package com.talentfinder.repository;

import com.talentfinder.model.ServiceCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<ServiceCategory, Integer> {
    public Optional<ServiceCategory> findByName(String name);
}
