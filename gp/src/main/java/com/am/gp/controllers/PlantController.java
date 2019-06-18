/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.am.gp.controllers;

import com.am.gp.entities.plant.Plant;
import com.am.gp.entities.user.User;
import com.am.gp.service.GardenService;
import com.am.gp.service.PlantService;
import com.am.gp.service.Result;
import com.am.gp.service.UserService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author amandamalmin
 */
@CrossOrigin
@RestController
@RequestMapping("/api/plant")
public class PlantController {

    private final PlantService pS;
    private final GardenService gS;
    private final UserService uS;

    public PlantController(PlantService pS, GardenService gS, UserService uS) {
        this.pS = pS;
        this.gS = gS;
        this.uS = uS;
    }

    @GetMapping("/getallplants")
    public ResponseEntity<List<Plant>> getPlants() {
        HttpStatus status = HttpStatus.OK;

        List<Plant> r = pS.getAllPlants();

        if (r == null || r.size() < 1) {
            status = HttpStatus.UNPROCESSABLE_ENTITY;

            return ResponseEntity.status(status).body(null);
        }

        return ResponseEntity.status(status).body(r);
    }

    @PostMapping("/getplantbyid")
    public ResponseEntity<Plant> getPlantById(@RequestBody Plant p) {
        HttpStatus status = HttpStatus.OK;

        p = pS.getPlantById(p.getId());

        if (p == null) {
            status = HttpStatus.UNPROCESSABLE_ENTITY;

            return ResponseEntity.status(status).body(null);
        }

        return ResponseEntity.status(status).body(p);
    }

    @PostMapping("/getcompanionplants")
    public ResponseEntity<List<Plant>> getCompanionPlants(@RequestBody Plant p) {
        HttpStatus status = HttpStatus.OK;

        p = pS.getPlantById(p.getId());
        Result<List<Plant>> r = pS.getPlantsByCompanion(p);
        if (r.hasError()) {
            status = HttpStatus.UNPROCESSABLE_ENTITY;

            return ResponseEntity.status(status).body(null);
        }

        return ResponseEntity.status(status).body(r.getValue());
    }

    @PostMapping("/getincompatableplants")
    public ResponseEntity<List<Plant>> getIncompatablePlants(@RequestBody Plant p) {
        HttpStatus status = HttpStatus.OK;

        p = pS.getPlantById(p.getId());
        Result<List<Plant>> r = pS.getPlantsByIncompatable(p);
        if (r.hasError()) {
            status = HttpStatus.UNPROCESSABLE_ENTITY;

            return ResponseEntity.status(status).body(null);
        }

        return ResponseEntity.status(status).body(r.getValue());
    }

    @PostMapping("getplantsbyuserid")
    public ResponseEntity<List<Plant>> getPlantsByUserId(@RequestBody User u) {
        HttpStatus status = HttpStatus.OK;
        u = uS.findUserById(u.getId());
        
        if (u == null) {
            status = HttpStatus.UNPROCESSABLE_ENTITY;

            return ResponseEntity.status(status).body(null);
        }
        
        Result<List<Plant>> r = pS.getPlantsByUser(u);
        if (r.hasError()) {
            status = HttpStatus.UNPROCESSABLE_ENTITY;

            return ResponseEntity.status(status).body(null);
        }

        return ResponseEntity.status(status).body(r.getValue());
    }
}
