package com.yifan.settlement.repository;

import com.yifan.settlement.entity.SettlementMsg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SettlementRepository extends JpaRepository<SettlementMsg, String> {
}
