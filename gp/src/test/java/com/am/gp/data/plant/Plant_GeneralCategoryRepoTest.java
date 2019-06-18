/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.am.gp.data.plant;

import com.am.gp.entities.plant.Plant_GeneralCategory;
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
public class Plant_GeneralCategoryRepoTest {
    
    public Plant_GeneralCategoryRepoTest() {
    }

    @Autowired
    private Plant_GeneralCategoryRepo repo;
    
    @Test
    public void testFindAll() {
        List<Plant_GeneralCategory> x = repo.findAll();
        
        assertNotNull(x);
    }
    
//    NEED TO UPDATE TEST ONCE DATA IN DB
    
    @Test
    public void testFindById() {
        Plant_GeneralCategory x = repo.findById(1).orElse(null);
        
        assertNull(x);
    }
    
}
