/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.am.gp.entities.user;

import com.am.gp.entities.plant.GeneralCategory;
import com.am.gp.entities.plant.Plant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author amandamalmin
 */
@Entity
public class UserPlant {
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private User userId;
    
    @Transient
    String userIdString;
    
    @ManyToOne
    @JoinColumn(name = "plantid", nullable = false)
    private Plant plantId;
    
    @Transient
    String plantIdString;

    public String getUserIdString() {
        return userIdString;
    }

    public void setUserIdString(String userIdString) {
        this.userIdString = userIdString;
    }

    public String getPlantIdString() {
        return plantIdString;
    }

    public void setPlantIdString(String plantIdString) {
        this.plantIdString = plantIdString;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Plant getPlantId() {
        return plantId;
    }

    public void setPlantId(Plant plantId) {
        this.plantId = plantId;
    }
    
}
