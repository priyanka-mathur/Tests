import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class CheckBalanced {

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

    private static int checkHeight(Node root) {
        if (root == null) {
            return -1;
        }

        int leftHeight = checkHeight(root.leftChild);
        if(leftHeight == Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }

        int rightHeight = checkHeight(root.rightChild);
        if(rightHeight == Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }

        int heightDiff = leftHeight - rightHeight;
        if(Math.abs(heightDiff) > 1) {
            return Integer.MIN_VALUE;
        }
        System.out.println(heightDiff);

        return Math.max(leftHeight, rightHeight) +1;
    }

    private static boolean isBalanced(Node root) {
        return checkHeight(root)!=Integer.MIN_VALUE;
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

        t.addLeftChildOf(three, four);
        t.addRightChildOf(six, four);

        t.addRightChildOf(seven, three);


        System.out.println("Balanced: " + isBalanced(t.getRootNode()));
    }

}
