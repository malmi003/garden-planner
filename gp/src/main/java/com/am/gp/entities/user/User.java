/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.am.gp.entities.user;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

/**
 *
 * @author amandamalmin
 */@Entity
public class User {
    @Id
    private String id;
    
    @Pattern(regexp="^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$")
    private String phone;
    
    @Email
    private String notificationEmail;
    
    @ManyToOne
    @JoinColumn(name = "hardinessZone_zipcode", nullable = false)
    private HardinessZone hardinessZone_zipcode;
    
    @Transient
    int zipCode;

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNotificationEmail() {
        return notificationEmail;
    }

    public void setNotificationEmail(String notificationEmail) {
        this.notificationEmail = notificationEmail;
    }

    public HardinessZone getHardinessZone_zipcode() {
        return hardinessZone_zipcode;
    }

    public void setHardinessZone_zipcode(HardinessZone hardinessZone_zipcode) {
        this.hardinessZone_zipcode = hardinessZone_zipcode;
    }
    
    
}
