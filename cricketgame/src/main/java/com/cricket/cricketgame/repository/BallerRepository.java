package com.cricket.cricketgame.repository;

import com.cricket.cricketgame.entity.Baller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BallerRepository extends JpaRepository<Baller,Long> {
    public Baller findByBallerName(String ballerName);
    public Baller findByBallerNameIgnoreCase(String ballerName);
}
