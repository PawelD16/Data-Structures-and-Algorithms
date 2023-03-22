package com.company;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<T> {
    private final Comparator<T> _comparator;
    private Node _root;

    public BinarySearchTree(Comparator<T> comp) {
        _comparator = comp;
        _root = null;
    }

    public void delete(T key) {
        _root = deleteRec(_root, key);
    }


    private Node deleteRec(Node root, T key) {

        if (root == null)
            return root;

        if (_comparator.compare(root.value, key) > 0)
            root.left = deleteRec(root.left, key);
        else if (_comparator.compare(root.value, key) < 0)
            root.right = deleteRec(root.right, key);


        else {

            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            root.value = minValue(root.right);

            root.right = deleteRec(root.right, root.value);
        }

        return root;
    }

    private T minValue(Node root) {
        T minv = root.value;
        while (root.left != null) {
            minv = root.left.value;
            root = root.left;
        }
        return minv;
    }

    private Node detachMin(Node del, Node node) {
        if (node.left != null) node.left = detachMin(del, node.left);
        else {
            del.value = node.value;
            node = node.right;
        }
        return node;
    }


    public boolean add(T key) {
        if (key == null) throw new NullPointerException();
        Node newNode = new Node(key);
        Node searcher = _root;
        Node temp = null;

        while (searcher != null) {
            temp = searcher;

            if (_comparator.compare(searcher.value, key) > 0) searcher = searcher.left;
            else if (_comparator.compare(searcher.value, key) < 0) searcher = searcher.right;
            else {
                return false;
            }
        }
        if (temp == null) {
            _root = newNode;
            return true;
        } else if (_comparator.compare(temp.value, key) > 0) temp.left = newNode;
        else temp.right = newNode;

        return true;
    }




    public T find(T elem) {
        Node node = search(elem);
        return node == null ? null : node.value;
    }

    private Node search(T elem) {
        Node node = _root;
        int cmp = 0;
        while (node != null && (cmp = _comparator.compare(elem, node.value)) != 0)
            node = cmp < 0 ? node.left : node.right;
        return node;
    }

    public void print() {
        if (_root == null) return;

        Queue<Node> queue = new LinkedList<>();
        queue.add(_root);

        while (!queue.isEmpty()) {
            int counter = queue.size();

            for (int i = 0; i < counter; i++) {
                Node tempNode = queue.poll();
                System.out.print(tempNode + "\t");

                if (tempNode != null && tempNode.left != null) queue.add(tempNode.left);
                if (tempNode != null && tempNode.right != null) queue.add(tempNode.right);
            }
            System.out.println();
        }
    }


    class Node {
        T value; // element
        Node left;
        Node right;

        Node(T obj) {
            value = obj;
        }

        Node(T obj, Node leftNode, Node rightNode) {
            value = obj;
            left = leftNode;
            right = rightNode;
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }
}
