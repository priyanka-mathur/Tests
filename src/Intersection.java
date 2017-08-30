public class Intersection {

    private static class Node {
        int d;
        Node next;

        Node() {
            this.next = null;
        }

        Node(int data) {
            this.d = data;
            this.next = null;
        }

        void appendNodeAtEnd(int data) {
            Node add = new Node();
            add.d = data;
            Node head = this;
            while (head.next != null) {
                head = head.next;
            }
            head.next = add;
        }

        void addNode(Node n) {

            Node head = this;
            while (head.next != null) {
                head = head.next;
            }
            head.next = n;
        }

        Node addNodeBefore(int data) {
            Node add = new Node();
            add.d = data;
            add.next = this;
            return add;
        }

        void printLinkedList() {
            Node head = this;
            while (head != null) {
                System.out.print(head.d + "  ");
                head = head.next;
            }
        }
    }

    public static void main(String[] args) {
        Node num1 = new Node(1);
        num1.appendNodeAtEnd(9);
        num1.appendNodeAtEnd(9);

        Node num2 = new Node(2);
        num2.appendNodeAtEnd(8);
        num2.appendNodeAtEnd(9);
        num2.appendNodeAtEnd(8);
        num2.appendNodeAtEnd(8);
        num2.appendNodeAtEnd(9);

        Node n = new Node(11);
        num1.addNode(n);
        num2.addNode(n);

        Node n1 = new Node(2);
        num1.addNode(n1);

        Node n2 = new Node(0);
        num1.addNode(n2);
        Node n3 = new Node(6);
        num1.addNode(n3);

        System.out.println("Num1: ");
        num1.printLinkedList();

        System.out.println("\nNum2: ");
        num2.printLinkedList();
        boolean b = doIntersect(num1, num2);
        System.out.println("\nnum1 and num2 intersect: " + b );

        if(b) {
            System.out.println("\nData @ node of startOfLoop: " + intersection(num1, num2).d);
        }
    }

    static boolean doIntersect(Node n1, Node n2) {
        if(n1 == null || n2 == null) {
            return false;
        }
        while (n1.next!=null) {
            n1 = n1.next;
        }
        while ((n2.next!=null)) {
            n2 = n2.next;
        }
        return n1 == n2;
    }

    static Node intersection(Node n1, Node n2) {
        if(length(n1) > length(n2)) {
            n1 = chopLinkedList(n1, length(n1)-length(n2));
        } else if(length(n2) > length(n1)) {
            n2 = chopLinkedList(n2, length(n2)- length(n1));
        }
        while (n1 != n2) {
            n1 = n1.next;
            n2 = n2.next;
        }
        return n1;
    }

    static Node chopLinkedList(Node n, int diff) {
        while (diff > 0) {
            n=n.next;
            diff--;
        }
        return n;
    }

    static int length(Node n) {
        int c = 0;
        while(n != null) {
            n = n.next;
            c++;
        }
        return c;
    }

}
