public class Palindrome {

    private static class Node {
        int d = 0;
        int numOfElements = 0;
        Node next;

        Node() {
            this.next = null;
        }

        void appendNodeAtEnd(int data) {
            if(this.numOfElements==0) {
                this.d = data;
                this.numOfElements++;
                return;
            }
            Node add = new Node();
            add.d = data;
            this.numOfElements++;
            Node head = this;
            while (head.next != null) {
                head = head.next;
            }
            head.next = add;
        }

        Node addNodeBefore(int data) {
            if(this.numOfElements==0) {
                this.d = data;
                this.numOfElements++;
                return this;
            }
            Node add = new Node();
            add.d = data;
            add.numOfElements = ++this.numOfElements;
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
        Node num1 = new Node();
        num1.appendNodeAtEnd(9);
        num1.appendNodeAtEnd(9);
        num1.appendNodeAtEnd(8);
        num1.appendNodeAtEnd(9);
        num1.appendNodeAtEnd(8);

        num1.printLinkedList();
        boolean b = isPalindrome(num1);

        System.out.println("\nnum1 is a palindrome: " + b );
    }

    static boolean isPalindrome(Node n) {
        if(n == null) {
            return false;
        }
        Node num2 = new Node();
        Node head = n;
        while(head!=null) {
            num2 = num2.addNodeBefore(head.d);
            head=head.next;
        }

        while (num2 != null) {
            if(n.d != num2.d) {
                return false;
            }
            num2 = num2.next;
            n = n.next;
        }
        return true;
    }

}
