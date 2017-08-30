import sun.awt.image.ImageWatched;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class ListOfDepthsDFS {

    private static class Node<E> {
        String name;
        E data;
        Node leftChild, rightChild;

        Node(String name, E data) {
            this.name = name;
            this.data = data;
            leftChild = null;
            rightChild = null;
        }

        Node[] getAdjacentNodes() {
            return new Node[]{leftChild, rightChild};
        }
    }

    private static class Tree<E> {

        private ArrayList<Node> vertices = new ArrayList<Node>();

        Tree(E data) {
            vertices.add(new Node<E>("first", data));
        }

        Node getRootNode() {
            return vertices.get(0);
        }

        void addLeftChildOf(Node n, Node parent) {
            if (!vertices.contains(parent)) {
                throw new NoSuchElementException();
            } else {
                vertices.add(n);
                parent.leftChild = n;
            }
        }

        void addRightChildOf(Node n, Node parent) {
            if (!vertices.contains(parent)) {
                throw new NoSuchElementException();
            } else {
                vertices.add(n);
                parent.rightChild = n;
            }
        }
    }

    private static void createListOfTreeNodes(ArrayList<LinkedList<Node>> lists, Node root, int level) {
        if(root == null) {
            return;
        }

        LinkedList<Node> list;
        if(lists.size() == level) {
            list = new LinkedList<Node>();
            lists.add(list);
        } else {
            list = lists.get(level);
        }

        list.add(root);
        createListOfTreeNodes(lists, root.leftChild, level+1);
        createListOfTreeNodes(lists, root.rightChild, level+1);
    }

    public static void main(String[] args) {

        Tree<Integer> t = new Tree<Integer>(0);

        Node<Integer> one = new Node<Integer>("one", 1);
        Node<Integer> two = new Node<Integer>("two", 2);
        Node<Integer> three = new Node<Integer>("three", 3);
        Node<Integer> four = new Node<Integer>("four", 4);
        Node<Integer> five = new Node<Integer>("five", 5);
        Node<Integer> six = new Node<Integer>("six", 6);
        Node<Integer> seven = new Node<Integer>("seven", 7);

        t.addLeftChildOf(one, t.getRootNode());
        t.addRightChildOf(four, t.getRootNode());

        t.addLeftChildOf(two, one);
        t.addRightChildOf(five, one);

        t.addLeftChildOf(three, two);

        t.addLeftChildOf(six, four);
        t.addRightChildOf(seven, four);

        ArrayList<LinkedList<Node>> lists = new ArrayList<LinkedList<Node>>();

        createListOfTreeNodes(lists, t.getRootNode(), 0);

        System.out.println("List of Depths:");
        for(int i = 0; i < lists.size(); i++) {
            for(int j = 0; j < lists.get(i).size(); j++) {
                System.out.print(lists.get(i).get(j).name + " ");
            }
            System.out.println();
        }
    }

}
