/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.am.gp.data.plant;

import com.am.gp.entities.plant.Plant;
import com.am.gp.entities.plant.PlantCompanion;
import com.am.gp.entities.plant.PlantIncompatable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author amandamalmin
 */
@Repository
public interface PlantCompanionRepo extends JpaRepository<PlantCompanion, Integer>{
    List<PlantCompanion> findByFirstPlantId(Plant firstPlantId);
    List<PlantCompanion> findBySecondPlantId(Plant secondPlantId);
}
