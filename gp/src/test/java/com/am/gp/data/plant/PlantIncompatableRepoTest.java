/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.am.gp.data.plant;

import com.am.gp.entities.plant.PlantIncompatable;
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
public class PlantIncompatableRepoTest {
    
    public PlantIncompatableRepoTest() {
    }

    @Autowired
    private PlantIncompatableRepo repo;
    
    @Test
    public void testFindAll() {
        List<PlantIncompatable> x = repo.findAll();
        
        assertNotNull(x);
    }
    
    
//    UPDATE THIS TEST WHEN DATA IN DB
    @Test
    public void testFindById() {
        PlantIncompatable x = repo.findById(1).orElse(null);
        
        assertNull(x);
    }
    
}
