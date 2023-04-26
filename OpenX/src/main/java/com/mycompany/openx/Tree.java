/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.openx;

public class Tree {
    private static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }

        Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        boolean isLeaf() {
            return left == null && right == null;
        }

        int maxDepth() {
            if (isLeaf()) {
                return 0;
            } else {
                int leftDepth = left == null ? 0 : left.maxDepth();
                int rightDepth = right == null ? 0 : right.maxDepth();
                return 1 + Math.max(leftDepth, rightDepth);
            }
        }

        boolean equals(Node other) {
            if (other == null) {
                return false;
            } else if (value != other.value) {
                return false;
            } else if (left == null && other.left != null) {
                return false;
            } else if (right == null && other.right != null) {
                return false;
            } else if (left != null && !left.equals(other.left)) {
                return false;
            } else if (right != null && !right.equals(other.right)) {
                return false;
            } else {
                return true;
            }
        }
    }

    private Node root;

    public Tree(int value) {
        root = new Node(value);
    }

    public Tree(int value, Tree left, Tree right) {
        root = new Node(value, left.root, right.root);
    }

    public int countLeaves() {
        return countLeaves(root);
    }

    private int countLeaves(Node node) {
        if (node == null) {
            return 0;
        } else if (node.isLeaf()) {
            return 1;
        } else {
            return countLeaves(node.left) + countLeaves(node.right);
        }
    }

    public int maxDepth() {
        return root.maxDepth();
    }

    public boolean equals(Tree other) {
        return root.equals(other.root);
    }

    public static void main(String[] args) {
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

        // Calculate the number of leaf nodes
        int numLeaves = tree1.countLeaves();
        System.out.println("The number of leaf nodes is: " + numLeaves);

        // Calculate the max depth
        int maxDepth = tree1.maxDepth();
        System.out.println("The max depth of the tree is: " + maxDepth);

        // Check equivalence with another instance of the same tree
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
        boolean isEqual = tree1.equals(tree2);
        System.out.println("The two trees are equivalent: " + isEqual);
    }
}

