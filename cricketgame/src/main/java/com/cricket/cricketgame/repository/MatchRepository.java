package com.cricket.cricketgame.repository;

import com.cricket.cricketgame.entity.MatchDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<MatchDetails,Long> {
}
