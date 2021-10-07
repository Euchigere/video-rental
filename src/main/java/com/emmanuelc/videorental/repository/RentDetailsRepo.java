package com.emmanuelc.videorental.repository;

import com.emmanuelc.videorental.domain.models.RentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentDetailsRepo extends JpaRepository<RentDetails, Long> {
}
