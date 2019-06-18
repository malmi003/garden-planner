/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.am.gp.entities.plant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author amandamalmin
 */
@Entity
public class PlantIncompatable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name = "firstPlantId", nullable = false)
    private Plant firstPlantId;

    @ManyToOne
    @JoinColumn(name = "secondPlantId", nullable = false)
    private Plant secondPlantId;

    @ManyToOne
    @JoinColumn(name = "incompatabilityTypeid", nullable = false)
    private IncompatabilityType incompatabilityTypeId;

    public Plant getFirstPlantId() {
        return firstPlantId;
    }

    public void setFirstPlantId(Plant firstPlantId) {
        this.firstPlantId = firstPlantId;
    }

    public Plant getSecondPlantId() {
        return secondPlantId;
    }

    public void setSecondPlantId(Plant secondPlantId) {
        this.secondPlantId = secondPlantId;
    }

    public IncompatabilityType getIncompatabilityTypeId() {
        return incompatabilityTypeId;
    }

    public void setIncompatabilityTypeId(IncompatabilityType incompatabilityTypeId) {
        this.incompatabilityTypeId = incompatabilityTypeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
