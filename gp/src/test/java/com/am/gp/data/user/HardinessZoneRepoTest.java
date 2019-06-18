/*
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.am.gp.data.user;

import com.am.gp.entities.user.HardinessZone;
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
public class HardinessZoneRepoTest {
    
    public HardinessZoneRepoTest() {
    }

    @Autowired
    private HardinessZoneRepo repo;
    
    @Test
    public void testFindAll() {
        List<HardinessZone> x = repo.findAll();
        
        assertNotNull(x);
    }
    
//    UPDATE TEST ONCE DATA IN DB
    @Test
    public void testFindById() {
        HardinessZone x = repo.findById(1).orElse(null);
        
        assertNull(x);
    }
    
    @Test
    public void testFindZones() {
        List<String> zones = repo.findAllGroupByZone();
        
        assertNotNull(zones);
    }
    
}
