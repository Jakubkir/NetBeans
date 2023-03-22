/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginsystem;

import java.util.HashMap;

/**
 *
 * @author patsz
 */
public class IDandpasswords {

    HashMap<String, String> logininfo = new HashMap<String, String>();

    IDandpasswords() {

        logininfo.put("Bro", "pizza");
        logininfo.put("Brometheus", "PASSWORD");
        logininfo.put("BroCode", "abc123");
    }

    HashMap getLoginInfo() {
        return logininfo;
    }
}
