/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.am.gp.entities.garden;

import com.am.gp.entities.plant.Plant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

/**
 *
 * @author amandamalmin
 */
@Entity
public class PlantGardenObject {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    
    @Column(nullable = false)
    private int x;
    
    @Column(nullable = false)
    private int y;
    
    @Column(nullable = false)
    private int w;
    
    @Column(nullable = false)
    private int h;
    
    @ManyToOne
//    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plantid", nullable = false)
    private Plant plantId;
    
    @Transient
    Integer plantIdInt;
    @Transient
    Integer i;
    
    @ManyToOne
    @JoinColumn(name = "gardenDiagramid", nullable = false)
    private GardenDiagram gardenDiagramId;
    
     @Transient
    Integer gardenDiagramIdInt;
    
    @Column
    @Size(min = 0, max = 200, message = "Icon must be between 1-200 characters")
    private String icon;
    
    @Column
    private Double minH;
    
    @Column
    private Double minW;

    public Double getMinH() {
        return minH;
    }

    public void setMinH(Double minH) {
        this.minH = minH;
    }

    public Double getMinW() {
        return minW;
    }

    public void setMinW(Double minW) {
        this.minW = minW;
    }

    public Integer getI() {
        return id;
    }

    public void setI(Integer i) {
        this.i = i;
    }
    
    public Integer getPlantIdInt() {
        return plantIdInt;
    }

    public void setPlantIdInt(Integer plantIdInt) {
        this.plantIdInt = plantIdInt;
    }

    public Integer getGardenDiagramIdInt() {
        return gardenDiagramIdInt;
    }

    public void setGardenDiagramIdInt(Integer gardenDiagramIdInt) {
        this.gardenDiagramIdInt = gardenDiagramIdInt;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Plant getPlantId() {
        return plantId;
    }

    public void setPlantId(Plant plantId) {
        this.plantId = plantId;
    }

    public GardenDiagram getGardenDiagramId() {
        return gardenDiagramId;
    }

    public void setGardenDiagramId(GardenDiagram gardenDiagramId) {
        this.gardenDiagramId = gardenDiagramId;
    }
    
    

    
}
