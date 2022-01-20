package com.cardservice.jparepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cardservice.entity.CardInfoEntity;


@Repository
public interface CardRepository extends JpaRepository<CardInfoEntity, Long> {
}
