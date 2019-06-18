/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.am.gp.data.user;

import com.am.gp.entities.user.HardinessZone;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author amandamalmin
 */
@Repository
public interface HardinessZoneRepo extends JpaRepository<HardinessZone, Integer>{
    @Query("select z.zone from HardinessZone z group by zone")
 public List<String> findAllGroupByZone();
}
