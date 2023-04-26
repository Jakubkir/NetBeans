/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.openx;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TreeTest {

    @Test
    public void testCountLeaves() {
        Tree tree = new Tree(5,
                new Tree(3,
                        new Tree(2),
                        new Tree(7)),
                new Tree(7,
                        new Tree(1),
                        new Tree(0,
                                new Tree(2),
                                new Tree(8,
                                        null,
                                        new Tree(5)))));
        int numLeaves = tree.countLeaves();
        assertEquals(5, numLeaves);
    }

    @Test
    public void testMaxDepth() {
        Tree tree = new Tree(5,
                new Tree(3,
                        new Tree(2),
                        new Tree(7)),
                new Tree(7,
                        new Tree(1),
                        new Tree(0,
                                new Tree(2),
                                new Tree(8,
                                        null,
                                        new Tree(5)))));
        int maxDepth = tree.maxDepth();
        assertEquals(4, maxDepth);
    }

    @Test
    public void testEquals() {
        Tree tree1 = new Tree(5,
                new Tree(3,
                        new Tree(2),
                        new Tree(7)),
                new Tree(7,
                        new Tree(1),
                        new Tree(0,
                                new Tree(2),
                                new Tree(8,
                                        null,
                                        new Tree(5)))));
        Tree tree2 = new Tree(5,
                new Tree(3,
                        new Tree(2),
                        new Tree(7)),
                new Tree(7,
                        new Tree(1),
                        new Tree(0,
                                new Tree(2),
                                new Tree(8,
                                        null,
                                        new Tree(5)))));
        assertTrue(tree1.equals(tree2));
    }
}


