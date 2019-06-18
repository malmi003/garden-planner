/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.am.gp.service;

import com.am.gp.data.garden.GardenDiagramRepo;
import com.am.gp.data.garden.PlantGardenObjectRepo;
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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
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
public class GardenServiceTest {
    
    public GardenServiceTest() {
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
    
    @Autowired
    private GardenService gServ;
    @Autowired
    private PlantService pServ;
    @Autowired
    private UserService uServ;

    private final Helpers help = new Helpers();
    int gid1, gid2, gid3;
    String userId;
    int poid1, poid2, poid3;

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
        userId = u1.getId();

        GardenDiagram gd1 = help.createNewGD(1, u1, 1);
        GardenDiagram gd2 = help.createNewGD(2, u1, 1);
        GardenDiagram gd3 = help.createNewGD(3, u1, 1);
        gd1 = gdRepo.save(gd1);
        gid1=gd1.getId();
        gd2 = gdRepo.save(gd2);
        gid2=gd2.getId();
        gd3 = gdRepo.save(gd3);
        gid3=gd3.getId();

    }
    
    @Test
    public void testFindGDByUserId() {
        List<GardenDiagram> gds = gServ.findGDByUserId(userId).getValue();
        
        assertNotNull(gds);
        
    }

    @Test
    public void testFindGDById() {
        GardenDiagram gd = gServ.findGDById(gid1);
        assertNotNull(gd);
    }

    @Test
    public void testUpdateGD() {
        GardenDiagram gd = gServ.findGDById(gid2);
        gd.setGridCount(30);
        
        gServ.updateGD(gd);
        GardenDiagram r = gServ.findGDById(gid2);
        assertTrue(r.getGridCount()==30);
    }

    @Test
    public void testAddGD() {
        GardenDiagram newGd = gServ.findGDById(gid1);
        newGd.setId(200);

        List<GardenDiagram> gds = gServ.findGDByUserId(userId).getValue();
        
        gServ.addGD(newGd);
        List<GardenDiagram> gds2 = gServ.findGDByUserId(userId).getValue();
        
        assertTrue(gds2.size()>gds.size());
    }

    @Test
    public void testDeleteGDByUserId() {
    }

    @Test
    public void testDeleteGDById() {
    }

    @Test
    public void testDeletePGObyId() {
    }

    @Test
    public void testFindPGOByGDId() {
    }

    @Test
    public void testFindPGOById() {
    }

    @Test
    public void testUpdatePGO() {
    }

    @Test
    public void testAddPGO() {
    }
    
}
