/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.am.gp.entities.user;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author amandamalmin
 */
@Entity
public class HardinessZone {
    @Id
    private int zipcode;
    
    @Column(nullable = false)
    @NotBlank	
    @Size(min = 1, max = 45, message = "Zone must be between 1-45 characters")
    private String zone;
    
    @Size(min = 0, max = 45, message = "Trange must be less than 45 characters")
    private String trange;
    
    @Size(min = 0, max = 45, message = "Zonetitle must be less than 45 characters")
    private String zonetitle;
    
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate lastSpringFrost;
    
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate firstFallFrost;

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getTrange() {
        return trange;
    }

    public void setTrange(String trange) {
        this.trange = trange;
    }

    public String getZonetitle() {
        return zonetitle;
    }

    public void setZonetitle(String zonetitle) {
        this.zonetitle = zonetitle;
    }

    public LocalDate getLastSpringFrost() {
        return lastSpringFrost;
    }

    public void setLastSpringFrost(LocalDate lastSpringFrost) {
        this.lastSpringFrost = lastSpringFrost;
    }

    public LocalDate getFirstFallFrost() {
        return firstFallFrost;
    }

    public void setFirstFallFrost(LocalDate firstFallFrost) {
        this.firstFallFrost = firstFallFrost;
    }
    
    
}
