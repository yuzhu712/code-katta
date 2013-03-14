package com.ashishpaliwal.codekatta.algos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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

    public int getTreeDepth() {
        return getTreeDepth(root);
    }

    private int getTreeDepth(Node node) {
        if(node == null) {
            return 0;
        }

        int leftDepth = getTreeDepth(node.left);
        int rightDepth = getTreeDepth(node.right);
        return Math.max(leftDepth, rightDepth) + 1;
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

    private void printTree(List<Node> nodes, int level, int treeDepth) {

        if(nodes.size() == 0) {
            return;
        }

        int indent = (int) Math.pow(2, treeDepth - level) - 1;
        int spacing = (int) (Math.pow(2, treeDepth - level + 1 ) - 1);

        List<Node> nextList = new ArrayList<Node>();
        for (int i = 0; i < indent; i++) {
            System.out.print(" ");
        }
        for (Node next : nodes) {
            System.out.print(next.data);
            for (int i = 0; i < spacing; i++) {
                System.out.print(" ");
            }
            if(next.left != null) {
                nextList.add(next.left);
            }

            if(next.right != null) {
                nextList.add(next.right);
            }
        }
        System.out.println();
        printTree(nextList, level + 1, treeDepth);
    }

    public void prettyPrint() {
        List<Node> node = new ArrayList<Node>();
        node.add(root);

        printTree(node, 0, getTreeDepth());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BinaryTree bTree = new BinaryTree();
        int input;
        while((input = scanner.nextInt()) != -1) {
            bTree.insert(input);
        }
        bTree.printTree();
        System.out.println("Depth = "+bTree.getTreeDepth());
        bTree.prettyPrint();
    }



}
