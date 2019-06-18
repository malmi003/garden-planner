/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.am.gp.service;

import com.am.gp.data.plant.PlantCompanionRepo;
import com.am.gp.data.plant.PlantIncompatableRepo;
import com.am.gp.data.plant.PlantRepo;
import com.am.gp.data.user.UserRepo;
import com.am.gp.entities.plant.Plant;
import com.am.gp.entities.plant.PlantCompanion;
import com.am.gp.entities.plant.PlantIncompatable;
import com.am.gp.entities.user.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author amandamalmin
 */
@Service
public class PlantService {
//    get plant by id
//    get all plants
//    get plantCompanion by plantid
//    get plantIncompatabl by plant id

//    get companion benefit from plantCampanionId
//    get companion benefit from plantCampanionId
    private final PlantRepo pRepo;
    private final PlantIncompatableRepo piRepo;
    private final PlantCompanionRepo pcRepo;
    private final UserRepo uRepo;

    public PlantService(PlantRepo pRepo, PlantIncompatableRepo piRepo, PlantCompanionRepo pcRepo, UserRepo uRepo) {
        this.pRepo = pRepo;
        this.piRepo = piRepo;
        this.pcRepo = pcRepo;
        this.uRepo = uRepo;
    }

   

    public Plant getPlantById(int id) {
        return pRepo.findById(id).orElse(null);
    }

    public List<Plant> getAllPlants() {
        return pRepo.findAll();
    }

    public Result<List<Plant>> getPlantsByCompanion(Plant p) {
        Result r = new Result();

        if (!Validations.isRealPlant(p.getId(), pRepo)) {
            r.addError("plant does not exist");
            return r;
        }
        List<Plant> companions = new ArrayList<>();
        List<PlantCompanion> c1 = pcRepo.findByFirstPlantId(p);
        c1.stream()
                .forEach(pl -> {
                    companions.add(pl.getSecondPlantId());
                });
        List<PlantCompanion> c2 = pcRepo.findBySecondPlantId(p);
        c2.stream()
                .forEach(pl -> {
                    companions.add(pl.getFirstPlantId());
                });
        
        
        r.setValue(companions);
        return r;
    }

    public Result<List<Plant>> getPlantsByIncompatable(Plant p) {
        Result r = new Result();

        if (!Validations.isRealPlant(p.getId(), pRepo)) {
            r.addError("plant does not exist");
            return r;
        }
        List<Plant> incomps = new ArrayList<>();
        List<PlantIncompatable> c1 = piRepo.findByFirstPlantId(p);
        c1.stream()
                .forEach(pl -> {
                    incomps.add(pl.getSecondPlantId());
                });
        List<PlantIncompatable> c2 = piRepo.findBySecondPlantId(p);
        c2.stream()
                .forEach(pl -> {
                    incomps.add(pl.getFirstPlantId());
                });
        
        
        r.setValue(incomps);
        return r;
    }
    
     public Result<List<Plant>> getPlantsByUser(User u) {
        Result r = new Result();

         if (!Validations.isRealUser(u.getId(), uRepo)) {
            r.addError("user does not exist");
            return r;
        }
         
         List<Plant> plants= pRepo.findPlantsByUser(u.getId());
         
         r.setValue(plants);
         return r;
     }

}
