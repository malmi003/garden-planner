/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.am.gp.entities.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author amandamalmin
 */
@Component
public class Downloader {
    String url = "https://www.almanac.com/gardening/frostdates/zipcode/";
    public List<String> getFrostDate(int zipcode) throws MalformedURLException, IOException {
        // Make a URL to the web page
        URL target = new URL(url + zipcode);
        // Get the input stream through URL Connection
        URLConnection con = target.openConnection();
        InputStream is = con.getInputStream();
        // Once you have the Input Stream, it's just plain old Java IO stuff.
        // For this case, since you are interested in getting plain-text web page
        // I'll use a reader and output the text content to System.out.
        // For binary content, it's better to directly read the bytes from stream and write
        // to the target file.
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        // read each line and write to System.out
        List<String> dates = new ArrayList<>();
        while ((line = br.readLine()) != null) {
             int found = line.indexOf("Last Spring Frost", 0);
             if (found != -1) {
                 String[] suspects = line.split("<td>");
                 dates.add(suspects[3]);
                 dates.add(suspects[4]);
             }
        }
        for (int i=0;i<dates.size();i++) {
            String date = dates.get(i).replace("</td>", "").replace("  ", " ");
            dates.set(i,date);
        }
        
        for (String date:dates) {
            System.out.println(date);
        }
        return dates;
    }
}
