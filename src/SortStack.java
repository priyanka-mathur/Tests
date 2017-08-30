import java.util.EmptyStackException;
import java.util.Stack;

public class SortStack {

    private static class SortedStack{

        Stack<Integer> stack;
        Stack<Integer> temp;

        SortedStack() {
            stack = new Stack<Integer>();

        }

        void push(int value) {
            if(stack.capacity() == stack.size()) {
                throw new StackOverflowError();
            }
            temp = new Stack<Integer>();
            while (!stack.isEmpty() && peek()<value) {
                temp.push(pop());
            }
            stack.push(value);
            while (!temp.isEmpty()) {
                stack.push(temp.pop());
            }
        }

        int pop() {
            if(stack.isEmpty()) {
                throw new EmptyStackException();
            }
            return stack.pop();
        }

        int peek() {
            return stack.peek();
        }
    }

    public static void main(String[] args) {

        SortedStack s = new SortedStack();

        s.push(3);
        s.push(1);
        s.push(2);
        s.push(2);
        s.push(7);
        s.push(8);
        s.push(3);
        s.pop();

        System.out.println(s.stack);

    }

}
