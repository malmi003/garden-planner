/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.am.gp.data.user;

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
public class UserRepoTest {

    public UserRepoTest() {
    }

    @Autowired
    private UserRepo repo;
    private final Helpers help = new Helpers();
    @Autowired
    private HardinessZoneRepo hRepo;
    
    @Before
    public void setUp() {
        repo.deleteAll();
        hRepo.deleteAll();
        
    HardinessZone z = help.createNewHZ(55655, "string", LocalDate.of(2000,Month.APRIL,2), LocalDate.of(2000,Month.APRIL,2));
        
        hRepo.save(z);
    
        User u1 = help.createNewUser("1", "507-560-0000", "e@s.com", z);
        User u2 = help.createNewUser("2", "121-122-1111", "email@a.com", z);
        User u3 = help.createNewUser("3", "111-110-1111", "e@s.com", z);
        
        
        repo.save(u1);
        repo.save(u2);
        repo.save(u3);
    }

//    update w/ login credentials
    @Test
    public void testAdd() {
        HardinessZone z = help.createNewHZ(55655, "string", LocalDate.of(2000,Month.APRIL,2), LocalDate.of(2000,Month.APRIL,2));
        User u4 = help.createNewUser("8", "111-110-1111", "e@s.zed", z);
        User result = repo.save(u4);
        
        assertNotNull(result);
        
    }

    @Test
    public void testDelete() {
        repo.delete(repo.findById("2").orElse(null));
        
        assertNull(repo.findById("2").orElse(null));
    }

    @Test
    public void testUpdate() {
         HardinessZone z = help.createNewHZ(55655, "string", LocalDate.of(2000,Month.APRIL,2), LocalDate.of(2000,Month.APRIL,2));
        User u4 = help.createNewUser("4", "111-110-1111", "e@s.zed", z);
        repo.save(u4);
        
        u4.setNotificationEmail("second@email.com");
        User result = repo.save(u4);
        assertTrue(result.getNotificationEmail().equals("second@email.com"));
    }

    @Test
    public void testFindAll() {
        List<User> x = repo.findAll();

        assertNotNull(x);
    }

    @Test
    public void testFindById() {
        User x = repo.findById("1").orElse(null);

        assertNotNull(x);
    }
}
