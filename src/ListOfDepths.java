import sun.awt.image.ImageWatched;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class ListOfDepths {

    private static class Node<E> {
        String name;
        E data;
        ArrayList<Node> children = new ArrayList<Node>();

        Node(String name, E data) {
            this.name = name;
            this.data = data;
        }

        Node[] getAdjacentNodes() {
            return (Node[]) this.children.toArray(new Node[this.children.size()]);
        }
    }

    private static class Tree<E> {

        private ArrayList<Node> vertices = new ArrayList<Node>();

        Tree() {
            vertices.add(new Node("first", 0));
        }

        Node getRootNode() {
            return vertices.get(0);
        }

        void createNode(Node n, Node parent) {
            if (!vertices.contains(parent)) {
                throw new NoSuchElementException();
            } else {
                vertices.add(n);
                parent.children.add(n);
            }
        }



    }

    private static ArrayList<LinkedList<Node>> createListOfTreeNodes(Tree t) {
        Node root = t.getRootNode();

        ArrayList<LinkedList<Node>> resultLists = new ArrayList<LinkedList<Node>>();
        LinkedList<Node> current = new LinkedList<Node>();
        current.add(root);
        while (current.size() > 0) {
            resultLists.add(current);
            LinkedList<Node> parent = current;
            current = new LinkedList<Node>();
            for(Node p: parent) {
                for(Node c : p.getAdjacentNodes()){
                    current.add(c);
                }
            }
        }
        return resultLists;
    }

    public static void main(String[] args) {

        Tree t = new Tree();

        Node one = new Node("one", 1);
        Node two = new Node("two", 2);
        Node three = new Node("three", 3);
        Node four = new Node("four", 4);
        Node five = new Node("five", 5);
        Node six = new Node("six", 6);
        Node seven = new Node("seven", 7);

        t.createNode(one, t.getRootNode());
        t.createNode(four, t.getRootNode());

        t.createNode(two, one);
        t.createNode(five, one);
        t.createNode(three, two);
        t.createNode(six, five);
        t.createNode(seven, two);

        ArrayList<LinkedList<Node>> lists = createListOfTreeNodes(t);

        System.out.println("List of Depths:");
        for(int i =0; i<lists.size(); i++) {
            for(int j =0; j<lists.get(i).size(); j++) {
                System.out.print(lists.get(i).get(j).name + " ");
            }
            System.out.println();
        }


    }

}
