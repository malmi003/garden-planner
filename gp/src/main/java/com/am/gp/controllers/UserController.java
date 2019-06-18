/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.am.gp.controllers;

import com.am.gp.entities.user.HardinessZone;
import com.am.gp.entities.user.User;
import com.am.gp.service.GardenService;
import com.am.gp.service.PlantService;
import com.am.gp.service.Result;
import com.am.gp.service.UserService;
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
@RequestMapping("/api/user")
public class UserController {
    private final PlantService pS;
    private final GardenService gS;
    private final UserService uS;

    public UserController(PlantService pS, GardenService gS, UserService uS) {
        this.pS = pS;
        this.gS = gS;
        this.uS = uS;
    }
     @PostMapping("/create")
     public ResponseEntity<User> createUser(@RequestBody User user) {
         int userZip= user.getZipCode();
         HardinessZone hz = uS.findHZById(userZip);
         user.setHardinessZone_zipcode(hz);
         
         Result<User> u = uS.addUser(user);
         HttpStatus status = HttpStatus.OK;
         
         if (u.hasError()) {
             status = HttpStatus.UNPROCESSABLE_ENTITY;
             
             return ResponseEntity.status(status).body(null);
         }
         
         return ResponseEntity.status(status).body(u.getValue());
        
     }
         @PostMapping("/getbyid")
     public ResponseEntity<User> getUserById(@RequestBody User user) {         
        user = uS.findUserById(user.getId());
         HttpStatus status = HttpStatus.OK;
         
         if (user == null) {
             status = HttpStatus.UNPROCESSABLE_ENTITY;
             
             return ResponseEntity.status(status).body(null);
         }
         
         return ResponseEntity.status(status).body(user);
     }
     
}
