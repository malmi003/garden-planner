/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.am.gp.data.user;

import com.am.gp.entities.garden.GardenDiagram;
import com.am.gp.entities.user.User;
import com.am.gp.entities.user.UserPlant;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author amandamalmin
 */
@Repository
public interface UserPlantRepo extends JpaRepository<UserPlant, Integer>{
    List<UserPlant> findByUserId(User userId);
    void deleteByUserId(User userId);
}
