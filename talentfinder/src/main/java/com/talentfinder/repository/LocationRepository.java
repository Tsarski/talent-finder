package com.talentfinder.repository;

import com.talentfinder.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
    public Optional<Location> findByLocationName(String name);
}
