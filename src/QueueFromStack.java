import java.util.EmptyStackException;
import java.util.Stack;

public class QueueFromStack {

    private static class MyQueue<E>{

        private Stack<E> stackToPush;
        private Stack<E> stackToPop;

        MyQueue() {
            stackToPush = new Stack<E>();
            stackToPop = new Stack<E>();
        }

        void add(E e){
            if(stackToPush.size() == stackToPush.capacity()) {
                throw new StackOverflowError();
            }
            stackToPush.push(e);
        }

        E remove() {
            if(stackToPop.isEmpty() && stackToPush.isEmpty()) {
                throw new EmptyStackException();
            }
            else if(stackToPop.isEmpty()) {
                moveStackElements(stackToPush, stackToPop);
            }
            return stackToPop.pop();
        }

        void moveStackElements(Stack from, Stack to) {
            while (!from.isEmpty()) {
                if(to.size()!=to.capacity())
                    to.push(from.pop());
            }
        }

        void printQueue() {
            System.out.println("Stack to Push" + stackToPush);
            System.out.println("Stack to Pop" + stackToPop);

        }

    }


    public static void main(String[] args) {

        MyQueue queue = new MyQueue<Integer>();

        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);
        queue.add(6);

        queue.remove();

        queue.add(7);
        queue.add(8);
        queue.remove();
        queue.remove();
        queue.remove();

        queue.add(9);
        queue.add(10);
        queue.add(11);
        queue.add(12);
        queue.remove();
        queue.remove();
        queue.remove();
        queue.remove();
        queue.remove();
        queue.remove();
        queue.remove();
        queue.remove();
        queue.remove();



        queue.printQueue();


    }

}
