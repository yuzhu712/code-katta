package com.ashishpaliwal.codekatta.algos;

import java.util.Scanner;

/**
 *
 */
public class BinaryTree {

    // root node
    private Node root;

    class Node {
        Node left;
        Node right;
        int data;

        Node(int data) {
             this.data = data;
        }
    }

    public boolean lookup(int data) {
        return lookup(root, data);
    }

    private boolean lookup(Node node, int data) {
        if(root == null) {
            return false;
        }

        if(data == node.data) {
            return true;
        } else if(data < node.data) {
            return lookup(node.left, data);
        } else {
            return lookup(node.right, data);
        }
    }

    private void insert(int data) {
        root = insert(root, data);
    }

    private Node insert(Node node, int data) {
        if (node==null) {
            node = new Node(data);
        }
        else {
            if (data <= node.data) {
                node.left = insert(node.left, data);
            }
            else {
                node.right = insert(node.right, data);
            }
        }

        return node;
    }

    public void printTree() {
        printTree(root);
        System.out.println();
    }

    private void printTree(Node node) {
        if(node == null) {
            return;
        }
        printTree(node.left);
        System.out.println(node.data + " ");
        printTree(node.right);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BinaryTree bTree = new BinaryTree();
        int input;
        while((input = scanner.nextInt()) != -1) {
            bTree.insert(input);
        }
        bTree.printTree();
    }

}
