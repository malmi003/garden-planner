/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.am.gp.service;

import com.am.gp.data.user.HardinessZoneRepo;
import com.am.gp.data.user.UserPlantRepo;
import com.am.gp.data.user.UserRepo;
import com.am.gp.entities.user.Downloader;
import com.am.gp.entities.user.HardinessZone;
import com.am.gp.entities.user.User;
import com.am.gp.entities.user.UserPlant;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 *
 * @author amandamalmin
 */
@Service
public class UserService {

    private final UserRepo uRepo;
    private final HardinessZoneRepo hRepo;
    private final UserPlantRepo upRepo;
    private final GardenService gService;
    private final Downloader downloader;

    public UserService(UserRepo uRepo, HardinessZoneRepo hRepo, UserPlantRepo upRepo, GardenService gService, Downloader downloader) {
        this.uRepo = uRepo;
        this.hRepo = hRepo;
        this.upRepo = upRepo;
        this.gService = gService;
        this.downloader = downloader;
    }

//    USER INFO
    public User findUserById(String id) {
        return uRepo.findById(id).orElse(null);
    }

    public Result<User> updateUser(User u) {
        Result r = new Result();

        if (!Validations.isPhone(u.getPhone())) {
            r.addError("invalid phone number");
        }

        if (!Validations.isEmail(u.getNotificationEmail())) {
            r.addError("invalid email address");
        }

        if (Validations.isNull(u.getHardinessZone_zipcode())) {
            r.addError("invalid zone");
        }

        if (r.hasError()) {
            return r;
        }

        User newU = uRepo.save(u);
        r.setValue(newU);

        return r;
    }

    public Result<User> addUser(User newU) {
        Result r = new Result();
        User u = findUserById(newU.getId());

        if (!Validations.isNull(u)) {
            r.addError("user already exists");
        }

        return updateUser(newU);
    }

    @Transactional
    public void deleteUserById(String id) {
        gService.deleteGDByUserId(id);
        List<UserPlant> ups = findUPByUserId(id).getValue();
        ups.forEach(up -> {
            deleteUPById(up.getId());
        });

        uRepo.deleteById(id);
    }

//    HARDINESS INFO
    public HardinessZone findHZById(int id) {
        HardinessZone z = hRepo.findById(id).orElse(null);
        if (z == null) {
            setZipFrostDates(id);
        }

        z = hRepo.findById(id).orElse(null);
        return z;
    }

    public Result<List<String>> findAllHZs() {
        Result r = new Result();

        List<String> zones = hRepo.findAllGroupByZone();
        if (zones == null) {
            r.addError("no zones available");
            return r;
        }

        r.setValue(zones);
        return r;
    }

    private void setZipFrostDates(int userZip) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy MMM d");

        try {
            List<String> dates = downloader.getFrostDate(userZip);
            LocalDate sd = LocalDate.parse("2000 " + dates.get(0), dtf);
            LocalDate fd = LocalDate.parse("2000 " + dates.get(1), dtf);
            HardinessZone hz = new HardinessZone();
            hz.setZipcode(userZip);
            hz.setZone("unknown");
            hz.setLastSpringFrost(sd);
            hz.setFirstFallFrost(fd);
            hRepo.save(hz);

        } catch (DateTimeParseException | IOException e) {
            HardinessZone hz = new HardinessZone();
            hz.setZipcode(userZip);
            hz.setZone("unknown");
            hz.setLastSpringFrost(LocalDate.of(2000, 5, 13));
            hz.setFirstFallFrost(LocalDate.of(2000, 10, 1));
            hRepo.save(hz);
        }
    }

    public UserPlant findUserPlantById(int id) {
        return upRepo.findById(id).orElse(null);
    }

    public Result<List<UserPlant>> findUPByUserId(String userId) {
        Result r = new Result();

        if (!Validations.isRealUser(userId, uRepo)) {
            r.addError("invalid user");
        }
        if (r.hasError()) {
            return r;
        }
        User u = findUserById(userId);
        List<UserPlant> ups = upRepo.findByUserId(u);

        r.setValue(ups);
        return r;
    }

    public void deleteUPById(int id) {
        upRepo.deleteById(id);
    }

}
