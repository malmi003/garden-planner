/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.am.gp.helpers;

import com.am.gp.entities.garden.GardenDiagram;
import com.am.gp.entities.garden.PlantGardenObject;
import com.am.gp.entities.plant.Plant;
import com.am.gp.entities.user.HardinessZone;
import com.am.gp.entities.user.User;
import java.time.LocalDate;
import java.time.Month;

/**
 *
 * @author amandamalmin
 */
public class Helpers {
    
    public LocalDate createNewDate(int d, Month m, int y) {
        return LocalDate.of(d, m, y);
    }
    
    public HardinessZone createNewHZ(int zip, String z, LocalDate d1, LocalDate d2) {
        HardinessZone hz = new HardinessZone();
        hz.setZipcode(zip);
        hz.setZone(z);
        hz.setLastSpringFrost(d1);
        hz.setFirstFallFrost(d2);
        
        return hz;
    }
    
    public User createNewUser(String id, String p, String e, HardinessZone h) {
        User u = new User();
        u.setId(id);
        u.setPhone(p);
        u.setNotificationEmail(e);
        u.setHardinessZone_zipcode(h);
        return u;
    }
    
    public Plant createNewPlant(int id, String cn ) {
        Plant p = new Plant();
        p.setId(id);
        p.setCommonName(cn);
        
        return p;
    }
    
    public PlantGardenObject createNewPGO(int id, int x, int y, int w, int h, Plant p, GardenDiagram gd) {
        PlantGardenObject pgo = new PlantGardenObject();
        pgo.setId(id);
        pgo.setX(x);
        pgo.setY(y);
        pgo.setW(w);
        pgo.setH(h);
        pgo.setPlantId(p);
        pgo.setGardenDiagramId(gd);
        
        return pgo;
    }
    
    public GardenDiagram createNewGD(int id, User u, int gridCount) {
        GardenDiagram gd = new GardenDiagram();
        gd.setId(id);
        gd.setGridCount(gridCount);
        gd.setUserId(u);
        
        return gd;
    }
    
}
