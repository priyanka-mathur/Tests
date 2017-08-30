import java.util.ArrayList;
import java.util.NoSuchElementException;

public class isBST {

    private static class Node<E> {
        String name;
        E data;
        Node<E> leftChild, rightChild;

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

    private static boolean checkBST(Node<Integer> root, Integer min, Integer max) {
        if (root == null) {
            return true;
        }

        if ((min != null && root.data < min) || (max != null && root.data >= max)) {
            return false;
        }

        if (!checkBST(root.leftChild, min, root.data) || !checkBST(root.rightChild, root.data, max)) {
            return false;
        }
        return true;
    }

    private static Integer last_printed = null;
    private static boolean checkBSTTwo(Node<Integer> root) {
        if(root == null) {
            return true;
        }

        boolean left = checkBSTTwo(root.leftChild);

        if(last_printed!=null && root.data<last_printed) {
            return false;
        }
        last_printed = root.data;

        boolean right = checkBSTTwo(root.rightChild);

        return (left&&right);
    }

    public static void main(String[] args) {

        Tree<Integer> t = new Tree<Integer>(20);

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


        System.out.println("is BST? " + checkBST(t.getRootNode(), null, null));

        System.out.println("is BST? " + checkBSTTwo(t.getRootNode()));


    }

}
