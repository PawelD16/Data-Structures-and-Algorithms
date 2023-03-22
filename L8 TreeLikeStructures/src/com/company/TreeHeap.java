package com.company;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class TreeHeap<T> implements IHeap<T> {

    private Node<T> root;
    private Node<T> latestNode;
    private int size;
    private final Comparator<T> comparator;

    public TreeHeap(Comparator<T> comparator) {
        this.comparator = comparator;
        this.root = null;
        this.latestNode = null;
        this.size = 0;
    }

    @Override
    public void clear() {
        this.root = null;
        this.size = 0;
        this.latestNode = null;
    }

    @Override
    public void add(T element) {
        if (element == null) throw new IllegalArgumentException();
        int tempInt = findNumberInLevel(size);
        latestNode = new Node<>(element, ++size);

        if (root == null) {
            root = latestNode;
            return;
        }

        if (size == 2) {
            root.leftChild = latestNode;
            if (comparator.compare(root.getKey(), latestNode.getKey()) > 0) swap(root, latestNode);
            return;
        }

        if (size == 3) {
            root.rightChild = latestNode;
            if (comparator.compare(root.getKey(), latestNode.getKey()) > 0) swap(root, latestNode);
            return;
        }

        int level = findLevel(size);
        ArrayList<Node<T>> nodePath = new ArrayList<>();

        Node<T> searcher = root;

        while (level > 0) {
            if (isLeftNext(tempInt, level)) searcher = searcher.leftChild;
            else searcher = searcher.rightChild;

            nodePath.add(searcher);
            level--;
        }

        if (isPowerOfTwo(size)) nodePath.add(searcher.leftChild);

        if (nodePath.get(nodePath.size() - 2).getLeftChild() == null)
            nodePath.get(nodePath.size() - 2).setLeftChild(latestNode);
        else nodePath.get(nodePath.size() - 2).setRightChild(latestNode);


        nodePath.add(0, root);
        nodePath.set(nodePath.size() - 1, latestNode);
        for (Node<T> temp = nodePath.remove(nodePath.size() - 1); nodePath.size() > 0; temp = nodePath.remove(nodePath.size() - 1)) {
            if (comparator.compare(temp.key, nodePath.get(nodePath.size() - 1).getKey()) < 0)
                swap(temp, nodePath.get(nodePath.size() - 1));
        }
    }

    @Override
    public T minimum() {
        T temp = null;
        if (size > 0) temp = root.getKey();

        if (size > 1) {
            Node<T> tempNode = findNewLatestNodeAndGiveItsParent();
            root.setKey(latestNode.getKey());

            if (tempNode != null) {
                if (tempNode.getRightChild() != null) tempNode.setRightChild(null);
                else tempNode.setLeftChild(null);
            }
            remakeHeapAt(root);
        } else {
            root = null;
            latestNode = null;
        }

        size--;
        return temp;
    }

    @Override
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

    private Node<T> findNewLatestNodeAndGiveItsParent() {
        if (root == null) {
            root = latestNode;
            return null;
        }

        if (size == 1) {
            root.leftChild = latestNode;
            return root;
        }

        if (size == 2) {
            root.rightChild = latestNode;
            return root;
        }

        int tempInt = findNumberInLevel(size - 1);
        int level = findLevel(size);
        Node<T> latestNodeParent = root;
        latestNode = root;

        while (level > 0) {
            latestNodeParent = latestNode;
            if (isLeftNext(tempInt, level)) latestNode = latestNode.leftChild;
            else latestNode = latestNode.rightChild;
            level--;
        }

        if (isPowerOfTwo(size)) {
            latestNodeParent = latestNode;
            latestNode = latestNode.leftChild;
        }
        return latestNodeParent;
    }

    private void remakeHeapAt(Node<T> node) {
        if (node != null) {

            if (node.leftChild != null && node.rightChild != null) {
                if (comparator.compare(node.getKey(), node.leftChild.getKey()) > 0 || comparator.compare(node.getKey(), node.rightChild.getKey()) > 0) {

                    if (comparator.compare(node.leftChild.getKey(), node.rightChild.getKey()) < 0) {
                        swap(node, node.leftChild);
                        remakeHeapAt(node.leftChild);
                    } else {
                        swap(node, node.rightChild);
                        remakeHeapAt(node.rightChild);
                    }
                }
            }
        }
    }

    private void swap(Node<T> left, Node<T> right) {
        T temp = left.getKey();
        left.setKey(right.getKey());
        right.setKey(temp);
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public Comparator<T> getComparator() {
        return comparator;
    }

    private boolean isPowerOfTwo(int x) {
        return (x != 0) && ((x & (x - 1)) == 0);
    }

    private int findLevel(int n) {
        return (int) (Math.ceil(Math.log(n) / Math.log(2)) - 1);
    }

    private int findNumberInLevel(int n) {
        for (int i = 0; n - (int) Math.pow(2, i) > 0; i++) n = n - (int) Math.pow(2, i);
        return n;
    }

    private boolean isLeftNext(int n, int level) {
        return (n % Math.pow(2, level) < Math.pow(2, level - 1));
    }

    private class Node<E> {
        private Node<E> rightChild;
        private Node<E> leftChild;
        private E key;
        private final int numberOfNode;

        public Node(E key, int numberOfNode) {
            this.key = key;
            this.numberOfNode = numberOfNode;
        }

        public int getNumberOfNode() {
            return numberOfNode;
        }

        public Node<E> getRightChild() {
            return rightChild;
        }

        public void setRightChild(Node<E> rightChild) {
            this.rightChild = rightChild;
        }

        public Node<E> getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(Node<E> leftChild) {
            this.leftChild = leftChild;
        }

        public E getKey() {
            return key;
        }

        public void setKey(E key) {
            this.key = key;
        }

        @Override
        public String toString() {
            return key.toString();
        }
    }
}
