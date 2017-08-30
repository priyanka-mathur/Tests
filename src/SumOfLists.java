import sun.invoke.empty.Empty;

import java.util.HashSet;

public class SumOfLists {

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
        num1.appendNodeAtEnd(2);
        num1.appendNodeAtEnd(7);
        num1.appendNodeAtEnd(8);
        num1.appendNodeAtEnd(8);

        System.out.print("Num1: ");
        num1.printLinkedList();

        Node num2 = new Node();
        num2.appendNodeAtEnd(0);
        num2.appendNodeAtEnd(5);
        num2.appendNodeAtEnd(6);
        System.out.print("\nNum2: ");
        num2.printLinkedList();

        Node sum = sumOfLists(num1, num2);
        System.out.print("\nSum: ");
        sum.printLinkedList();

    }

    static Node sumOfLists(Node num1, Node num2) {
        int s, carry=0, units;
        Node sum = new Node();
        while (num1!=null && num2!=null) {
            s = carry + (num1.d + num2.d);
            if(s>9) {
                units = s - 10;
                carry = s/10;
            } else {
                units = s;
                carry = 0;
            }

            sum.appendNodeAtEnd(units);

            num1 = num1.next;
            num2 = num2.next;
        }

        if(num1==null && num2!=null) {
            while (num2!=null) {
                s = carry + num2.d;
                if(s>9) {
                    units = s - 10;
                    carry = s - units;
                } else {
                    units = s;
                    carry = 0;
                }

                sum.appendNodeAtEnd(units);
                num2 = num2.next;
            }
        } else if(num2==null && num1!=null) {
            while (num1!=null) {
                s = carry + num1.d;

                if(s>9) {
                    units = s - 10;
                    carry = s - units;
                } else {
                    units = s;
                    carry = 0;
                }

                sum.appendNodeAtEnd(units);
                num1 = num1.next;
            }
        }

        return sum;
    }


}
