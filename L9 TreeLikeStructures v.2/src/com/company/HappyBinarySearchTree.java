package com.company;

import java.util.*;

public class HappyBinarySearchTree<T> {
    private final Comparator<T> comparator;
    private Node<T> root;
    private Node<T> deletionAssistant;
    private int size;

    public HappyBinarySearchTree(Comparator<T> comparator) {
        this.comparator = comparator;
        this.root = null;
        this.deletionAssistant = null;
        this.size = 0;
    }

    public boolean add(T key) {
        if (key == null) throw new NullPointerException();

        ArrayList<Node<T>> arrayListOfNodes = new ArrayList<>();
        Node<T> newNode = new Node<>(key, ++size);
        Node<T> searcher = root;
        Node<T> temp = null;

        while (searcher != null) {
            temp = searcher;
            arrayListOfNodes.add(temp);
            if (comparator.compare(searcher.getKey(), key) > 0) searcher = searcher.getLeftChild();
            else if (comparator.compare(searcher.getKey(), key) < 0) searcher = searcher.getRightChild();
            else return false;

        }
        if (temp == null) {
            root = newNode;
            return true;
        } else if (comparator.compare(temp.getKey(), key) > 0) temp.setLeftChild(newNode);
        else temp.setRightChild(newNode);

        arrayListOfNodes.add(newNode);
        fixPriorities(arrayListOfNodes);
        return true;
    }

    private void fixPriorities(ArrayList<Node<T>> arrayListOfNodes) {

        for (int i = arrayListOfNodes.size() - 2; i >= 0; i--) {
            Node<T> grandParentNode = root;
            if (i != 0) grandParentNode = arrayListOfNodes.get(i - 1);
            Node<T> parentTemp = arrayListOfNodes.get(i);
            Node<T> childTemp = arrayListOfNodes.get(i + 1);
            if (parentTemp.getLeftChild() == childTemp && parentTemp.getPriority() < childTemp.getPriority())
                arrayListOfNodes.set(i, rightRotate(parentTemp, grandParentNode));
            else if (parentTemp.getRightChild() == childTemp && parentTemp.getPriority() < childTemp.getPriority())
                arrayListOfNodes.set(i, leftRotate(parentTemp, grandParentNode));
        }
    }

    private Node<T> rightRotate(Node<T> node, Node<T> parentNode) {
        Node<T> leftChildTemp = node.leftChild;
        Node<T> rightChildTemp = leftChildTemp.rightChild;

        if (node == root) root = leftChildTemp;
        else {
            if (parentNode.getRightChild() == node) parentNode.setRightChild(leftChildTemp);
            else parentNode.setLeftChild(leftChildTemp);
        }

        leftChildTemp.setRightChild(node);
        node.setLeftChild(rightChildTemp);

        deletionAssistant = leftChildTemp;

        return leftChildTemp;
    }

    private Node<T> leftRotate(Node<T> node, Node<T> parentNode) {
        Node<T> rightChildTemp = node.rightChild;
        Node<T> leftChildTemp = rightChildTemp.leftChild;

        if (node == root) root = rightChildTemp;
        else {
            if (parentNode.getRightChild() == node) parentNode.setRightChild(rightChildTemp);
            else parentNode.setLeftChild(rightChildTemp);
        }

        rightChildTemp.setLeftChild(node);
        node.setRightChild(leftChildTemp);

        deletionAssistant = rightChildTemp;

        return rightChildTemp;
    }

    public int getSize() {
        return size;
    }

    public boolean find(T key) {
        return findANode(key) != null;
    }

    private Node<T> findANode(T key) {
        Node<T> searcher = root;
        deletionAssistant = root;
        int temp;

        while (searcher != null && (temp = comparator.compare(searcher.getKey(), key)) != 0) {
            deletionAssistant = searcher;
            if (temp > 0) searcher = searcher.leftChild;
            else searcher = searcher.rightChild;
        }
        return searcher;
    }

    public boolean delete(T key) {
        if (size <= 0) throw new NullPointerException();
        Node<T> searcher = findANode(key);

        size--;
        if (searcher == null) return false;
        leafify(searcher);
        if (deletionAssistant.getLeftChild() == searcher) deletionAssistant.setLeftChild(null);
        else deletionAssistant.setRightChild(null);
        return true;
    }

    private void leafify(Node<T> node) {
        while (!(node.getRightChild() == null && node.getLeftChild() == null)) {
            if (node.getLeftChild() == null) leftRotate(node, deletionAssistant);
            else if (node.getRightChild() == null) rightRotate(node, deletionAssistant);
            else {
                Node<T> temp = deletionAssistant;
                deletionAssistant = node;
                if (node.getLeftChild().getPriority() > node.getRightChild().getPriority()) rightRotate(node, temp);
                else leftRotate(node, temp);
            }
        }
    }

    public void print() {
        if (root == null) return;

        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int counter = queue.size();

            for (int i = 0; i < counter; i++) {
                Node<T> tempNode = queue.poll();
                System.out.print(tempNode + "\t");

                if (tempNode != null && tempNode.leftChild != null) queue.add(tempNode.leftChild);
                if (tempNode != null && tempNode.rightChild != null) queue.add(tempNode.rightChild);
            }
            System.out.println();
        }
    }


    private class Node<E> {
        private Node<E> leftChild, rightChild;
        private final int priority;
        private final E key;

        public Node(E key, int priorityRange) {
            this.key = key;
            this.priority = new Random().nextInt((priorityRange + 1) * 2);
        }

        public Node<E> getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(Node<E> leftChild) {
            this.leftChild = leftChild;
        }

        public Node<E> getRightChild() {
            return rightChild;
        }

        public void setRightChild(Node<E> rightChild) {
            this.rightChild = rightChild;
        }

        public int getPriority() {
            return priority;
        }

        public E getKey() {
            return key;
        }

        @Override
        public String toString() {
            return key.toString() + "," + priority;
        }
    }
}
