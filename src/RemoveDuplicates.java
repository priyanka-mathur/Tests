import java.util.HashSet;

public class RemoveDuplicates {

    private static class Node {
        int d;
        Node next;

        Node(int d) {
            this.d = d;
            this.next = null;
        }

        void appendNode(int data) {
            Node add = new Node(data);
            Node head = this;
            while (head.next != null) {
                head = head.next;
            }
            head.next = add;
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
        Node h1 = new Node(1);
        h1.appendNode(3);
        h1.appendNode(3);
        h1.appendNode(1);
        h1.appendNode(1);

        System.out.print("Linked List before: ");
        h1.printLinkedList();

        removeDuplicates(h1);

        System.out.print("\n\nLinked List after removing duplicates: ");
        h1.printLinkedList();
    }

    static void removeDuplicates(Node h) {
        if(h==null || h.next == null) {
            return;
        }
        HashSet<Integer> hashSet = new HashSet<Integer>();
        Node n = h;
        hashSet.add(n.d);
        while (n.next != null) {
            if(!hashSet.contains(n.next.d)) {
                hashSet.add(n.next.d);
                n = n.next;
            } else {
                n.next = n.next.next;
            }
        }
    }
}
