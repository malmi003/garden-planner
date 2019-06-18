/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.am.gp.entities.plant;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author amandamalmin
 */
@Entity
public class Plant {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    
    @Column(nullable = false)
    @Size(min = 0, max = 100, message = "Name must be be less than 100 characters")
    private String commonName;
    
    @Column
    @Size(min = 0, max = 100, message = "Species must be be less than 100 characters")
    private String species;
    
    @Column
    @Size(min = 0, max = 100, message = "Genus must be be less than 100 characters")
    private String genus;
    @Column
    private Double plantingDepth;
    @Column
    private Double distanceBetweenRows;
    @Column
    private Double distanceBetweenRowsAfterThinning;
    @Column
    private Boolean RR;
    @Column
    private Boolean H;
    
    @Column
    @Size(min = 0, max = 45, message = "PlantsPerRow must be be less than 45 characters")
    private String plantsPerRow;
    @Column
    private Boolean plantsPerRowOnces;
    @Column
    private Double startIndoorsWeekCountS;
    @Column
    private Double startOutdoorsWeekCountS;
    @Column
    private Double startIndoorsWeekCountF;
    @Column
    private Double startOutdoorsWeekCountF;
    @Column
    private Integer firstHarvestDateAfterStartOutdoors;
    @Column
    private Integer lastHarvestDateBeforeFreeze;
    @Column
    private Boolean protectFromHeat;
    
    @Column
    private Double averageYield;
    
    @ManyToOne
    @JoinColumn(name = "plantHeight_id", nullable = false)
    private PlantHeight plantHeight_id;
    
    @ManyToOne
    @JoinColumn(name = "plantSunRequirement_id", nullable = false)
    private PlantSunRequirement plantSunRequirement_id;
    
    @Column
    private Boolean poleTrellis;
    
    @Column
    private Boolean ladderTrellis;
    
    @Column
    @Size(min = 0, max = 200, message = "Icon must be be less than 200 characters")
    private String icon;
    
    @ManyToOne
    @JoinColumn(name = "lifecycle_id", nullable = false)
    private Lifecycle lifecycle_id;
    
    @ManyToOne
    @JoinColumn(name = "waterPreference_id", nullable = false)      
    private WaterPreference waterPreference_id;
    
    @Column
    @Size(min = 0, max = 500, message = "ImageUrl must be be less than 500 characters")
    private String imageUrl;
    
    @Column
    private Double plantsPerSquareFoot;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getGenus() {
        return genus;
    }

    public void setGenus(String genus) {
        this.genus = genus;
    }

    public Double getPlantingDepth() {
        return plantingDepth;
    }

    public void setPlantingDepth(Double plantingDepth) {
        this.plantingDepth = plantingDepth;
    }

    public Double getDistanceBetweenRows() {
        return distanceBetweenRows;
    }

    public void setDistanceBetweenRows(Double distanceBetweenRows) {
        this.distanceBetweenRows = distanceBetweenRows;
    }

    public Double getDistanceBetweenRowsAfterThinning() {
        return distanceBetweenRowsAfterThinning;
    }

    public void setDistanceBetweenRowsAfterThinning(Double distanceBetweenRowsAfterThinning) {
        this.distanceBetweenRowsAfterThinning = distanceBetweenRowsAfterThinning;
    }

    public Boolean getRR() {
        return RR;
    }

    public void setRR(Boolean RR) {
        this.RR = RR;
    }

    public Boolean getH() {
        return H;
    }

    public void setH(Boolean H) {
        this.H = H;
    }

    public String getPlantsPerRow() {
        return plantsPerRow;
    }

    public void setPlantsPerRow(String plantsPerRow) {
        this.plantsPerRow = plantsPerRow;
    }

    public Boolean getPlantsPerRowOnces() {
        return plantsPerRowOnces;
    }

    public void setPlantsPerRowOnces(Boolean plantsPerRowOnces) {
        this.plantsPerRowOnces = plantsPerRowOnces;
    }

    public Double getStartIndoorsWeekCountS() {
        return startIndoorsWeekCountS;
    }

    public void setStartIndoorsWeekCountS(Double startIndoorsWeekCountS) {
        this.startIndoorsWeekCountS = startIndoorsWeekCountS;
    }

    public Double getStartOutdoorsWeekCountS() {
        return startOutdoorsWeekCountS;
    }

    public void setStartOutdoorsWeekCountS(Double startOutdoorsWeekCountS) {
        this.startOutdoorsWeekCountS = startOutdoorsWeekCountS;
    }

    public Double getStartIndoorsWeekCountF() {
        return startIndoorsWeekCountF;
    }

    public void setStartIndoorsWeekCountF(Double startIndoorsWeekCountF) {
        this.startIndoorsWeekCountF = startIndoorsWeekCountF;
    }

    public Double getStartOutdoorsWeekCountF() {
        return startOutdoorsWeekCountF;
    }

    public void setStartOutdoorsWeekCountF(Double startOutdoorsWeekCountF) {
        this.startOutdoorsWeekCountF = startOutdoorsWeekCountF;
    }

    public Integer getFirstHarvestDateAfterStartOutdoors() {
        return firstHarvestDateAfterStartOutdoors;
    }

    public void setFirstHarvestDateAfterStartOutdoors(Integer firstHarvestDateAfterStartOutdoors) {
        this.firstHarvestDateAfterStartOutdoors = firstHarvestDateAfterStartOutdoors;
    }

    public Integer getLastHarvestDateBeforeFreeze() {
        return lastHarvestDateBeforeFreeze;
    }

    public void setLastHarvestDateBeforeFreeze(Integer lastHarvestDateBeforeFreeze) {
        this.lastHarvestDateBeforeFreeze = lastHarvestDateBeforeFreeze;
    }

    public Boolean getProtectFromHeat() {
        return protectFromHeat;
    }

    public void setProtectFromHeat(Boolean protectFromHeat) {
        this.protectFromHeat = protectFromHeat;
    }

    public Double getAverageYield() {
        return averageYield;
    }

    public void setAverageYield(Double averageYield) {
        this.averageYield = averageYield;
    }

    public PlantHeight getPlantHeight_id() {
        return plantHeight_id;
    }

    public void setPlantHeight_id(PlantHeight plantHeight_id) {
        this.plantHeight_id = plantHeight_id;
    }

    public PlantSunRequirement getPlantSunRequirement_id() {
        return plantSunRequirement_id;
    }

    public void setPlantSunRequirement_id(PlantSunRequirement plantSunRequirement_id) {
        this.plantSunRequirement_id = plantSunRequirement_id;
    }

    public Boolean getPoleTrellis() {
        return poleTrellis;
    }

    public void setPoleTrellis(Boolean poleTrellis) {
        this.poleTrellis = poleTrellis;
    }

    public Boolean getLadderTrellis() {
        return ladderTrellis;
    }

    public void setLadderTrellis(Boolean ladderTrellis) {
        this.ladderTrellis = ladderTrellis;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Lifecycle getLifecycle_id() {
        return lifecycle_id;
    }

    public void setLifecycle_id(Lifecycle lifecycle_id) {
        this.lifecycle_id = lifecycle_id;
    }

    public WaterPreference getWaterPreference_id() {
        return waterPreference_id;
    }

    public void setWaterPreference_id(WaterPreference waterPreference_id) {
        this.waterPreference_id = waterPreference_id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Double getPlantsPerSquareFoot() {
        return plantsPerSquareFoot;
    }

    public void setPlantsPerSquareFoot(Double plantsPerSquareFoot) {
        this.plantsPerSquareFoot = plantsPerSquareFoot;
    }

   
    
    
}
