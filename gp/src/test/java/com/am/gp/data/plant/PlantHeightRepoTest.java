/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.am.gp.data.plant;

import com.am.gp.entities.plant.PlantHeight;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
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
public class PlantHeightRepoTest {
    
    public PlantHeightRepoTest() {
    }

    @Autowired
    private PlantHeightRepo repo;
    
    @Test
    public void testFindAll() {
        List<PlantHeight> x = repo.findAll();
        
        assertNotNull(x);
    }
    
    @Test
    public void testFindById() {
        PlantHeight x = repo.findById(1).orElse(null);
        
        assertTrue(x.getName().equals("short"));
    }
    
}
