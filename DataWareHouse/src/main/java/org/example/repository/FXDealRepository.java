package org.example.repository;

import org.example.model.FXDeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FXDealRepository extends JpaRepository<FXDeal, String> {
}
