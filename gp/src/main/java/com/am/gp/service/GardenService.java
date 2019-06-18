/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.am.gp.service;

import com.am.gp.data.garden.GardenDiagramRepo;
import com.am.gp.data.garden.PlantGardenObjectRepo;
import com.am.gp.data.user.UserRepo;
import com.am.gp.entities.garden.GardenDiagram;
import com.am.gp.entities.garden.PlantGardenObject;
import com.am.gp.entities.user.User;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 *
 * @author amandamalmin
 */
@Service
public class GardenService {

    private final GardenDiagramRepo gdRepo;
    private final PlantGardenObjectRepo pgRepo;
    private final UserRepo uRepo;

    public GardenService(GardenDiagramRepo gpRepo, PlantGardenObjectRepo pgRepo, UserRepo uServ) {
        this.gdRepo = gpRepo;
        this.pgRepo = pgRepo;
        this.uRepo = uServ;
    }

    public Result<List<GardenDiagram>> findGDByUserId(String id) {
        Result r = new Result();

        if (!Validations.isRealUser(id, uRepo)) {
            r.addError("invalid user");
        }
        if (r.hasError()) {
            return r;
        }
        User u = uRepo.findById(id).orElse(null);
        List<GardenDiagram> dgs = gdRepo.findByUserId(u);

        r.setValue(dgs);
        return r;
    }

    public GardenDiagram findGDById(int id) {
        return gdRepo.findById(id).orElse(null);
    }

    public Result<GardenDiagram> updateGD(GardenDiagram gd) {
        Result r = new Result();

        if (Validations.isNull(gd)) {
            r.addError("null garden diagram");
        }

        if (Validations.isNull(gd.getUserId())) {
            r.addError("garden diagram must have a user");
        } else if (!Validations.isRealUser(gd.getUserId().getId(), uRepo)) {
            r.addError("user does not exist");
        }

        if (!Validations.inRange(gd.getGridCount(), 0, 100)) {
            r.addError("gridCount must be between 1-100");
        }

        if (gd.getName() != null) {
            if (!Validations.inRange(gd.getName(), 0, 45)) {
                r.addError("name must be less than 45 characters");
            }
        }
        if (r.hasError()) {
            return r;
        }

        gd = gdRepo.save(gd);
        r.setValue(gd);

        return r;

    }

    public Result<GardenDiagram> addGD(GardenDiagram newGD) {
        Result r = new Result();
        GardenDiagram gd = findGDById(newGD.getId());

        if (!Validations.isNull(gd)) {
            r.addError("garden diagram already exists");
        }

        return updateGD(newGD);
    }

    @Transactional
    public void deleteGDByUserId(String userId) {
        User u = uRepo.findById(userId).orElse(null);
        List<GardenDiagram> dgs = gdRepo.findByUserId(u);
        dgs.forEach(dg -> {
            deleteGDById(dg.getId());
        });

        gdRepo.deleteByUserId(u);
    }

    @Transactional
    public void deleteGDById(int id) {
//        delete PGOs first, then GD
        pgRepo.deleteByGardenDiagramId(findGDById(id));
        gdRepo.deleteById(id);
    }

    public void deletePGObyId(int id) {
        pgRepo.deleteById(id);
    }

    public Result<List<PlantGardenObject>> findPGOByGDId(int id) {
        Result r = new Result();

        if (!Validations.isRealGD(id, gdRepo)) {
            r.addError("invalid garden diagram");
        }
        if (r.hasError()) {
            return r;
        }
        GardenDiagram gd = gdRepo.findById(id).orElse(null);
        List<PlantGardenObject> pgos = pgRepo.findByGardenDiagramId(gd);

        r.setValue(pgos);
        return r;
    }
    
    public PlantGardenObject findPGOById(int id) {
        return pgRepo.findById(id).orElse(null);
    }
    
       public Result<PlantGardenObject> updatePGO(PlantGardenObject pgo) {
        Result r = new Result();

        if (Validations.isNull(pgo)) {
            r.addError("null plant garden object");
        }
        if (!Validations.greaterThanX(pgo.getX(), -1)) {
            r.addError("x out of bounds");
        } 
        if (!Validations.greaterThanX(pgo.getY(),-1)) {
            r.addError("y out of bounds");
        } 
        if (!Validations.greaterThanX(pgo.getW(), -1)) {
            r.addError("w out of bounds");
        } 
        if (!Validations.greaterThanX(pgo.getH(), -1)) {
            r.addError("h out of bounds");
        } 
        
        if(Validations.isNull(pgo.getPlantId())) {
            r.addError("plant is null");
        }
        if(Validations.isNull(pgo.getGardenDiagramId())) {
            r.addError("garden diagram is null");
        }
        
        if(!Validations.isNullOrWhitespace(pgo.getIcon())) {
            if (!Validations.inRange(pgo.getIcon(), 0, 200)) {
                r.addError("icon must be less than 200 characters");
            }
        }
        
        if (r.hasError()) {
            return r;
        }

        pgo = pgRepo.save(pgo);
        r.setValue(pgo);

        return r;
    }
       
        public Result<PlantGardenObject> addPGO(PlantGardenObject newPGO) {
        Result r = new Result();
        PlantGardenObject pgo = findPGOById(newPGO.getId());

        if (!Validations.isNull(pgo)) {
            r.addError("plant garden object already exists");
        }
        
        if (r.hasError()) {
            return r;
        }

        return updatePGO(newPGO);
    }

}
