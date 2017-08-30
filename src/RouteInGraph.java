import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class RouteInGraph {

    enum States {UNVISITED, VISITING, VISITED}

    private static class Node<E> {
        String name;
        States state;
        E data;
        ArrayList<Node> children = new ArrayList<Node>();

        Node(String name, E data) {
            this.name = name;
            this.data = data;
            this.state = States.UNVISITED;
        }
    }

    private static class Graph<E> {

        private ArrayList<Node> vertices = new ArrayList<Node>();

        Graph() {
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

        Node[] getAdjacentNodes(Node n) {
            if (!vertices.contains(n)) {
                throw new NoSuchElementException();
            }
            return (Node[]) n.children.toArray(new Node[n.children.size()]);
        }

    }

    private static boolean isPath(Graph g, Node start, Node end) {
        if (start == end) {
            return true;
        }
        LinkedList<Node> queue = new LinkedList<Node>();

        start.state = States.VISITING;
        queue.add(start);
        Node n;
        while (!queue.isEmpty()) {
            n = queue.remove();
            for (Node u : g.getAdjacentNodes(n)) {
                if (u.state == States.UNVISITED) {
                    if (u == end) {
                        return true;
                    } else {
                        u.state = States.VISITING;
                        queue.add(u);
                    }
                }
            }
            n.state = States.VISITED;
        }
        return false;
    }

    public static void main(String[] args) {

        Graph g = new Graph();

        Node one = new Node("one", 1);
        Node two = new Node("two", 2);
        Node three = new Node("three", 3);
        Node four = new Node("four", 4);
        Node five = new Node("five", 5);
        Node six = new Node("six", 6);
        Node seven = new Node("six", 7);

        g.createNode(one, g.getRootNode());
        g.createNode(four, g.getRootNode());

        g.createNode(two, one);
        g.createNode(five, one);
        g.createNode(three, two);
        g.createNode(six, five);
        g.createNode(seven, two);

        System.out.println("Is there a path from " + three.name + " to " + seven.name + ": " + isPath(g, three, seven));

    }

}
