import java.util.Stack;

public class StackMin {

    private static class StackWithMin extends Stack<NodeWithMin>{

        void push(int value) {
            int newMin = Math.min(value, min());
            super.push(new NodeWithMin(value, newMin));
        }

        int min() {
            if(this.isEmpty()) {
                return Integer.MAX_VALUE;
            } else {
                return peek().min;
            }
        }
    }

    static class NodeWithMin {
        int value;
        int min;

        NodeWithMin(int v, int m) {
            this.value = v;
            this.min = m;
        }
    }

    public static void main(String[] args) {

    }


}
