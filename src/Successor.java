import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Successor {

    private static class Node<E> {
        String name;
        E data;
        Node<E> leftChild, rightChild, parent;

        Node(String name, E data) {
            this.name = name;
            this.data = data;
            leftChild = null;
            rightChild = null;
            parent = null;
        }

        Node[] getAdjacentNodes() {
            return new Node[]{leftChild, rightChild};
        }
    }

    private static class Tree<E> {

        private ArrayList<Node> vertices = new ArrayList<Node>();

        Tree(String name, E data) {
            vertices.add(new Node<E>(name, data));
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
                n.parent = parent;
            }
        }

        void addRightChildOf(Node n, Node parent) {
            if (!vertices.contains(parent)) {
                throw new NoSuchElementException();
            } else {
                vertices.add(n);
                parent.rightChild = n;
                n.parent = parent;
            }
        }
    }

    private static Node<Integer> getInorderSuccessor(Node<Integer> n) {
        if (n == null) {
            return null;
        }

        if (n.rightChild != null) {
            return getLeftMostChild(n.rightChild);
        }
        Node<Integer> q = n;
        Node<Integer> x = q.parent;
        while (x != null && x.leftChild != q) {
            q = x;
            x = x.parent;
        }
        return x;
    }

    private static Node<Integer> getLeftMostChild(Node<Integer> n) {
        if (n == null) {
            return null;
        }

        while (n.leftChild != null) {
            n = n.leftChild;
        }
        return n;
    }

    private static Integer last_printed = null;


    public static void main(String[] args) {

        Tree<Integer> t = new Tree<Integer>("twenty", 20);

        Node<Integer> ten = new Node<Integer>("ten", 10);
        Node<Integer> thirty = new Node<Integer>("thirty", 30);
        Node<Integer> five = new Node<Integer>("five", 5);
        Node<Integer> fifteen = new Node<Integer>("fifteen", 15);
        Node<Integer> three = new Node<Integer>("three", 3);
        Node<Integer> seventeen = new Node<Integer>("seventeen", 17);
        Node<Integer> seven = new Node<Integer>("seven", 7);
        Node<Integer> twentyfive = new Node<Integer>("twentyfive", 25);

        t.addLeftChildOf(ten, t.getRootNode());
        t.addRightChildOf(thirty, t.getRootNode());

        t.addLeftChildOf(five, ten);
        t.addRightChildOf(fifteen, ten);

        t.addLeftChildOf(three, five);
        t.addRightChildOf(seven, five);

        t.addRightChildOf(seventeen, fifteen);


        System.out.println("in order successor: " + getInorderSuccessor(ten).name);


    }

}
