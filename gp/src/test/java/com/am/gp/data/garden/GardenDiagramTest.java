/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.am.gp.data.garden;

import com.am.gp.data.plant.PlantRepo;
import com.am.gp.data.user.HardinessZoneRepo;
import com.am.gp.data.user.UserRepo;
import com.am.gp.entities.garden.GardenDiagram;
import com.am.gp.entities.user.HardinessZone;
import com.am.gp.entities.user.User;
import com.am.gp.helpers.Helpers;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author amandamalmin
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class GardenDiagramTest {

    public GardenDiagramTest() {
    }

    @Autowired
    private UserRepo uRepo;
    @Autowired
    private GardenDiagramRepo gdRepo;
    @Autowired
    private PlantGardenObjectRepo pgRepo;
    @Autowired
    private HardinessZoneRepo hRepo;
    @Autowired
    private PlantRepo pRepo;

    private final Helpers help = new Helpers();
    int id1, id2, id3;
    String uid;

    @Before
    public void setUp() {
        pgRepo.deleteAll();
        gdRepo.deleteAll();
        pRepo.deleteAll(); 
        uRepo.deleteAll();
        hRepo.deleteAll();

        HardinessZone z = help.createNewHZ(55422, "string", LocalDate.of(2000, Month.APRIL, 2), LocalDate.of(2000, Month.APRIL, 2));
        hRepo.save(z);
        User u1 = help.createNewUser("1", "507-560-0000", "e@s.com", z);
        u1 = uRepo.save(u1);
        uid=u1.getId();

        GardenDiagram gd1 = help.createNewGD(1, u1, 1);
        GardenDiagram gd2 = help.createNewGD(2, u1, 1);
        GardenDiagram gd3 = help.createNewGD(3, u1, 1);
        gd1 = gdRepo.save(gd1);
        id1=gd1.getId();
        gd2 = gdRepo.save(gd2);
        id2=gd2.getId();
        gd3 = gdRepo.save(gd3);
        id3=gd3.getId();

    }

    @Test
    public void testFindAll() {
        List<GardenDiagram> x = gdRepo.findAll();

        assertNotNull(x);
    }

    @Test
    public void testFindById() {
        GardenDiagram x = gdRepo.findById(id1).orElse(null);

        assertNotNull(x);
    }

    @Test
    public void testDelete() {
        gdRepo.delete(gdRepo.findById(id2).orElse(null));
        
        assertNull(gdRepo.findById(id2).orElse(null));
    }

    @Test
    public void testUpdate() {
        GardenDiagram x = gdRepo.findById(id3).orElse(null);
        x.setGridCount(10);
        gdRepo.save(x);
        
        GardenDiagram result = gdRepo.findById(id3).orElse(null);
        
        assertTrue(result.getGridCount()==10);
    }
    
    @Test 
    public void testGetByUser() {
        List<GardenDiagram> ds = gdRepo.findByUserId(uRepo.findById(uid).orElse(null));
        
        assertNotNull(ds);
    }
}
