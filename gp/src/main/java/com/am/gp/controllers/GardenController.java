/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.am.gp.controllers;

import com.am.gp.entities.garden.GardenDiagram;
import com.am.gp.entities.garden.PlantGardenObject;
import com.am.gp.entities.plant.Plant;
import com.am.gp.entities.user.User;
import com.am.gp.service.GardenService;
import com.am.gp.service.PlantService;
import com.am.gp.service.Result;
import com.am.gp.service.UserService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("/api/garden")
public class GardenController {
    private final PlantService pS;
    private final GardenService gS;
    private final UserService uS;

    public GardenController(PlantService pS, GardenService gS, UserService uS) {
        this.pS = pS;
        this.gS = gS;
        this.uS = uS;
    }
     @PostMapping("/creategarden")
     public ResponseEntity<GardenDiagram> createGarden(@RequestBody GardenDiagram gd) {
         String userIdString = gd.getUserIdString();
         User u = uS.findUserById(userIdString);
         gd.setYear(LocalDate.of(2019, 5, 2));
         gd.setUserId(u);
         
         Result<GardenDiagram> r = gS.addGD(gd);
         HttpStatus status = HttpStatus.OK;
         
         if (r.hasError()) {
             status = HttpStatus.UNPROCESSABLE_ENTITY;
             
             return ResponseEntity.status(status).body(null);
         }
         
         return ResponseEntity.status(status).body(r.getValue());
     }
     
     @PostMapping("/deletegarden")
     public ResponseEntity<GardenDiagram> deleteGarden(@RequestBody GardenDiagram gd) {
         gS.deleteGDById(gd.getId());
         HttpStatus status = HttpStatus.OK;
         
         return ResponseEntity.status(status).body(gd);
     }
     
     @PostMapping("/updategarden")
     public ResponseEntity<GardenDiagram> updateGarden(@RequestBody GardenDiagram gd) {
         int id =gd.getId();
         GardenDiagram dbGd = gS.findGDById(id);
         dbGd.setName(gd.getName());
         HttpStatus status = HttpStatus.OK;
         
         Result<GardenDiagram> r = gS.updateGD(dbGd);
         
         if (r.hasError()) {
             status = HttpStatus.UNPROCESSABLE_ENTITY;
             
             return ResponseEntity.status(status).body(null);
         }
         
         return ResponseEntity.status(status).body(r.getValue());
     }
     @PostMapping("/getgardensbyuserid")
     public ResponseEntity<List<GardenDiagram>> getGardensById(@RequestBody User u) {
        HttpStatus status = HttpStatus.OK;
        
         Result<List<GardenDiagram>> r = gS.findGDByUserId(u.getId());
         
          if (r.hasError()) {
             status = HttpStatus.UNPROCESSABLE_ENTITY;
             
             return ResponseEntity.status(status).body(null);
         }
         
         return ResponseEntity.status(status).body(r.getValue());
     }
     
     @PostMapping("/createpgo")
     public ResponseEntity<PlantGardenObject> createPGO(@RequestBody PlantGardenObject pgo) {
         GardenDiagram gd = gS.findGDById(pgo.getGardenDiagramIdInt());
         Plant p = pS.getPlantById(pgo.getPlantIdInt());
         
         pgo.setGardenDiagramId(gd);
         pgo.setPlantId(p);
         
         Result<PlantGardenObject> r = gS.addPGO(pgo);
         HttpStatus status = HttpStatus.OK;
         
         if (r.hasError()) {
             status = HttpStatus.UNPROCESSABLE_ENTITY;
             
             return ResponseEntity.status(status).body(null);
         }
         
         return ResponseEntity.status(status).body(r.getValue());
     }
     
        @PostMapping("/deletepgo")
     public ResponseEntity<PlantGardenObject> deletePGO(@RequestBody PlantGardenObject pgo) {
         gS.deletePGObyId(pgo.getId());
         HttpStatus status = HttpStatus.OK;
         
         return ResponseEntity.status(status).body(pgo);
     }
     
     @PostMapping("/getpgosbygardenid")
     public ResponseEntity<List<PlantGardenObject>> getPGOsByGardenId(@RequestBody GardenDiagram gd) {
        HttpStatus status = HttpStatus.OK;
        
         Result<List<PlantGardenObject>> r = gS.findPGOByGDId(gd.getId());
         
          if (r.hasError()) {
             status = HttpStatus.UNPROCESSABLE_ENTITY;
             
             return ResponseEntity.status(status).body(null);
         }
         
         return ResponseEntity.status(status).body(r.getValue());
     }
     
          @PostMapping("/updatepgo")
     public ResponseEntity<PlantGardenObject> updatePGO(@RequestBody PlantGardenObject pgo) {
         int id = pgo.getId();
         PlantGardenObject newPGO = gS.findPGOById(id);
         HttpStatus status = HttpStatus.OK;
         newPGO.setX(pgo.getX());
         newPGO.setY(pgo.getY());
         newPGO.setH(pgo.getH());
         newPGO.setW(pgo.getW());
         
         Result<PlantGardenObject> r = gS.updatePGO(newPGO);
         
         if (r.hasError()) {
             status = HttpStatus.UNPROCESSABLE_ENTITY;
             
             return ResponseEntity.status(status).body(null);
         }
         
         return ResponseEntity.status(status).body(r.getValue());
     }
     @PostMapping("/getpgo")
     public ResponseEntity<PlantGardenObject> getPGO(@RequestBody PlantGardenObject pgo) {
         HttpStatus status = HttpStatus.OK;
         PlantGardenObject r = gS.findPGOById(pgo.getId());
         
         if (r==null) {
             status = HttpStatus.UNPROCESSABLE_ENTITY;
             
             return ResponseEntity.status(status).body(null);
         }
         
         return ResponseEntity.status(status).body(r);
     }

}
