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
import com.am.gp.entities.garden.PlantGardenObject;
import com.am.gp.entities.plant.Plant;
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
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author amandamalmin
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PlantGardenObjectRepoTest {

    public PlantGardenObjectRepoTest() {
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
        
        
    @Before
    public void setUp() {
        pgRepo.deleteAll();
        gdRepo.deleteAll();
        pRepo.deleteAll(); 
        uRepo.deleteAll();
        hRepo.deleteAll();
        
    HardinessZone z = help.createNewHZ(55655, "string", LocalDate.of(2000,Month.APRIL,2), LocalDate.of(2000,Month.APRIL,2));
        hRepo.save(z);
        User u1 = help.createNewUser("1", "507-560-0000", "e@s.com", z);
        u1=uRepo.save(u1);
 
        Plant p = help.createNewPlant(1,"plant1");
        p=pRepo.save(p);
        
        GardenDiagram gd = help.createNewGD(1, u1, 1);
        gd = gdRepo.save(gd);
        
        PlantGardenObject p1 = help.createNewPGO(1, 1, 0, 2, 2, p, gd);
        PlantGardenObject p2 = help.createNewPGO(2, 1, 0, 2, 2, p, gd);
        PlantGardenObject p3 = help.createNewPGO(3, 1, 0, 2, 2, p, gd);
        
        PlantGardenObject r1= pgRepo.save(p1);
        id1 = r1.getId();
       PlantGardenObject r2= pgRepo.save(p2);
       id2 = r2.getId();
        PlantGardenObject r3=pgRepo.save(p3);
        id3 = r3.getId();
       
    }


@Test
    public void testFindAll() {
        List<PlantGardenObject> x = pgRepo.findAll();

        assertNotNull(x);
    }

    @Test
    public void testFindById() {
        PlantGardenObject x = pgRepo.findById(id1).orElse(null);

        assertNotNull(x);
    }

    @Test
    public void testDelete() {
        pgRepo.delete(pgRepo.findById(id2).orElse(null));
        
        assertNull(pgRepo.findById(id2).orElse(null));
    }

    @Test
    public void testUpdate() {
        PlantGardenObject x = pgRepo.findById(id3).orElse(null);
        x.setX(5);
        pgRepo.save(x);
        
        PlantGardenObject result = pgRepo.findById(id3).orElse(null);
        
        assertTrue(result.getX()==5);
    }
 
}
