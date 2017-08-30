public class SumOfListsReverse {

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

        Node num2 = new Node();
        num2.appendNodeAtEnd(9);

        Node[] numbers = {num1, num2};
        numbers = padWithZeroes(numbers);

        System.out.print("Num1: ");
        numbers[0].printLinkedList();
        System.out.print("\nNum2: ");
        numbers[1].printLinkedList();

        Node sum = partialSum(numbers);
        int c = sumOfListsReverse(sum);
        if(c > 0) {
            sum = sum.addNodeBefore(c);
        }
        System.out.print("\nSum: ");
        sum.printLinkedList();

    }

    static int sumOfListsReverse(Node sum) {
        if(sum == null) {
            return 0;
        }
        int carry = sumOfListsReverse(sum.next);
        if(sum.d+carry > 9) {
            sum.d = (sum.d+carry)%10;
            carry = 1;
        } else {
            sum.d = sum.d + carry;
            carry = 0;
        }
        return carry;
    }

    static Node partialSum(Node[] numbers) {
        Node n1 = numbers[0], n2 = numbers[1];
        if(n1 == null || n2 == null) {
            return null;
        }
        int s;
        Node sum = new Node();
        while (n1 != null) {
            s = n1.d + n2.d;
            sum.appendNodeAtEnd(s);

            n1 = n1.next;
            n2 = n2.next;
        }
        return sum;
    }

    static Node[] padWithZeroes(Node[] numbers) {
        int diff = numbers[0].numOfElements - numbers[1].numOfElements;
        if(diff>0) {
            while (diff>0) {
                numbers[1] = numbers[1].addNodeBefore(0);
                diff--;
            }
        } else if(diff<0) {
            diff = -diff;
            while (diff>0) {
                numbers[0] = numbers[0].addNodeBefore(0);
                diff--;
            }
        }
        return numbers;
    }

}
