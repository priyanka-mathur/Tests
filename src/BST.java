
public class BST {

    enum States {UNVISITED, VISITING, VISITED}

    private static class TNode<E> {
        E data;
        TNode left, right;

        TNode() {
            this.left = null;
            this.right = null;
        }

        TNode(E data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }

        TNode getLeftChild() {
            return this.left;
        }

        TNode getRightChild() {
            return this.right;
        }

        void addLeftChild(TNode n) {
            this.left = n;
        }

        void addRightChild(TNode n) {
            this.right = n;
        }
    }

    private static class Tree<E> {

        public TNode<E> root;

        Tree() {
            this.root = new TNode<E>();
        }

        void createMinimalBST(E[] arr) {
            int len = arr.length;
            this.root = createMinimalBST(arr, 0, len-1);
        }

        TNode<E> createMinimalBST(E[] arr, int start, int end) {

            if(end < start) {
                return null;
            }

            int mid = (start + end)/2;
            TNode<E> t = new TNode<E>(arr[mid]);

            t.left = createMinimalBST(arr, start, mid-1);
            t.right = createMinimalBST(arr, mid+1, end);

            return t;
        }


    }





    public static void main(String[] args) {

        Tree t = new Tree<Integer>();

        Integer[] array = {0, 1, 2, 3, 4, 5, 6, 7};

        t.createMinimalBST(array);

        System.out.println(t.root.data);


    }

}
