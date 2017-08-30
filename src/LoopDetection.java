public class LoopDetection {

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
    }

    public static void main(String[] args) {
        Node num1 = new Node(1);
        Node num2 = new Node(2);

        num1.next = num2;
        num2.next = num1;

        Node fast = isLoop(num1);
        if(fast != null) {
            System.out.println("\nnum1 has a loop!");
            System.out.println("\nData @ startOfLoop: " + startOfLoop(num1, fast).d);
        } else {
            System.out.println("\nnum1 does not have a loop");
        }
    }

    static Node isLoop(Node n) {
        if(n == null || n.next == null) {
            return null;
        }
        Node slow  = n, fast = n;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) {
                return fast;
            }
        }
        return null;
    }

    static Node startOfLoop(Node n, Node fast) {
        Node start = n;

        while (start != fast) {
            start = start.next;
            fast = fast.next;
        }
        return start;
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
