/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.am.gp.entities.plant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author amandamalmin
 */

@Entity
public class Plant_GeneralCategory {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "generalCategory_id", nullable = false)
    private GeneralCategory generalCategory;
    
    @ManyToOne
    @JoinColumn(name = "plant_id", nullable = false)
    private Plant plant_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GeneralCategory getGeneralCategory() {
        return generalCategory;
    }

    public void setGeneralCategory(GeneralCategory generalCategory) {
        this.generalCategory = generalCategory;
    }

    public Plant getPlant_id() {
        return plant_id;
    }

    public void setPlant_id(Plant plant_id) {
        this.plant_id = plant_id;
    }

    
    
}
