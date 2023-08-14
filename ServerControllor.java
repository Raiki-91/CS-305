package com.snhu.sslserver;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



@RestController
public class ServerControllor {
    @RequestMapping("/hash")
    public String myHash() {
        String data = "Hello World Check Sum!";
        String checksum = null;
        String cipherAlg = "SHA-256";

        // Create an object of MessageDigest class with the SHA-256 algorithm
        try {
            MessageDigest digest = MessageDigest.getInstance(cipherAlg);
            // Generate the hash value of byte type from my first and last name
            digest.update(data.getBytes());

            byte[] hashValue = digest.digest();

            // convert hash to hex w/ bytesToHex function
            checksum = bytesToHex(hashValue);

            // create RESTful route using @RequestMapping method to generate and return the info
            // for web browser, with the hash value.
            return "<p> data: " + data + " Name of Cipher Algorithm Used: " + cipherAlg + " Check Sum Value: " + checksum;
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Exception thrown: " + e);
            return "Error occurred: " + e.getMessage();
        }
    }


    // converts pass hash value to byte array to hex and returns it as a string
    private String bytesToHex(byte[] hashArray) {
        StringBuilder strBuilder = new StringBuilder();
        for (byte b : hashArray) {
            strBuilder.append(String.format("%02x", b & 0xff));
        }
        return strBuilder.toString();}
    }


