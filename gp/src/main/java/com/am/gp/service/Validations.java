package com.am.gp.service;

import com.am.gp.data.garden.GardenDiagramRepo;
import com.am.gp.data.plant.PlantRepo;
import com.am.gp.data.user.UserRepo;
import com.am.gp.entities.garden.GardenDiagram;
import com.am.gp.entities.garden.PlantGardenObject;
import com.am.gp.entities.plant.Plant;
import com.am.gp.entities.user.HardinessZone;
import com.am.gp.entities.user.User;
import java.util.List;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public final class Validations {
    
    public static boolean inRange(int value, int min, int max) {
        return value >= min && value <= max;
    }

    public static boolean isNullOrWhitespace(String value) {
        return value == null || value.trim().length() == 0;
    }
    
    public static boolean greaterThanX(int num, int x) {
        return num>x;
    }
    
     public static boolean isNull(HardinessZone z) {
        return z == null;
    }
     public static boolean isNull(User u) {
        return u == null;
    }
     public static boolean isNull(Plant p) {
        return p == null;
    }
     public static boolean isNull(PlantGardenObject p) {
        return p == null;
    }
     public static boolean isNull(GardenDiagram gd) {
        return gd == null;
    }

    public static boolean isEmail(String emailAddress) {

        if (isNullOrWhitespace(emailAddress)) {
            return false;
        }

        try {
            InternetAddress addr = new InternetAddress(emailAddress);
            return true;
        } catch (AddressException ex) {
            return false;
        }
    }

    public static boolean isDuplicate(List<Integer> picks) {
        if (picks.size() > 0) {
            int lastAddition = picks.get(picks.size() - 1);
            if (picks.indexOf(lastAddition) != picks.size() - 1) {
                return true;
            }
        }
        return false;
    }

    public static boolean isZipCode(String zipCode) {
        if (isNullOrWhitespace(zipCode)) {
            return false;
        }
        return zipCode.matches("\\d{5}");
    }

    public static boolean isPhone(String phoneNo) {
        if (isNullOrWhitespace(phoneNo)) {
            return false;
        }
        //validate phone numbers of format "1234567890"
        if (phoneNo.matches("\\d{10}")) {
            return true;
        } //validating phone number with -, . or spaces
        else if (phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) {
            return true;
        } //validating phone number with extension length from 3 to 5
        else if (phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) {
            return true;
        } //validating phone number where area code is in braces ()
        else if (phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) {
            return true;
        } //return false if nothing matches the input
        else {
            return false;
        }
    }

    public static boolean isRealUser(String id, UserRepo uRepo) {
        User u = uRepo.findById(id).orElse(null);
        return !(u == null);
    }
    public static boolean isRealGD(int id, GardenDiagramRepo gRepo) {
        GardenDiagram gd = gRepo.findById(id).orElse(null);
        return !(gd == null);
    }
    
    public static boolean isRealPlant(int id, PlantRepo pRepo) {
        Plant p = pRepo.findById(id).orElse(null);
        return !(p == null);
    }
    
    public static boolean inRange(String s, int min, int max) {
        return s.length() >= min && s.length() <= max;
    }
}
