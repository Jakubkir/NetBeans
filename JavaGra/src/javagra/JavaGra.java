/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javagra;

import javax.swing.*;

/**
 *
 * @author patsz
 */
public class JavaGra {

    public static void main(String[] args) {
        Gra gra = new Gra();
        JFrame jf = new JFrame("Prosta Gra");
        jf.setBounds(200, 200, 700, 600);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setResizable(false);
        jf.setVisible(true);
        jf.add(gra);

    }

}
