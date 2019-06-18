/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.am.gp.data.garden;

import com.am.gp.entities.garden.GardenDiagram;
import com.am.gp.entities.user.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author amandamalmin
 */
@Repository
public interface GardenDiagramRepo extends JpaRepository<GardenDiagram, Integer>{
    List<GardenDiagram> findByUserId(User userId);
    void deleteByUserId(User userId);
}
