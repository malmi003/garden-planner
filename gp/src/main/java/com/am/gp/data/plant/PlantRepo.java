/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.am.gp.data.plant;

import com.am.gp.entities.plant.Plant;
import com.am.gp.entities.user.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author amandamalmin
 */
@Repository
public interface PlantRepo extends JpaRepository<Plant, Integer>{
    @Query(value="select p.* from Plant p "
            +"join plantGardenObject pgo on pgo.plantId = p.id "
            +"join GardenDiagram gd on gd.id=pgo.gardendiagramid "
            +"join user u on u.id = gd.userid "
            +"where u.id = ?1 "
            +"group by p.commonName", nativeQuery=true)
    public List<Plant> findPlantsByUser(String id);
}
