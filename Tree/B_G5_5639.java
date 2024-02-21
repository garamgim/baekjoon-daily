package Tree;

import java.io.IOException;
import java.util.Scanner;

public class B_G5_5639 {
    static Node root;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            int n = sc.nextInt();

            Node newNode = new Node(n);
            enq(newNode);
        }

        postorder(root);
    }

    static void postorder(Node node) {
        if (node.left != null) postorder(node.left);
        if (node.right != null) postorder(node.right);
        System.out.println(node.data);
    }

    static void enq(Node node) {
        if (root == null) {
            root = node;
        } else {
            Node currParent = root;
            while (true) {
                if (node.data < currParent.data) {
                    if (currParent.left == null) {
                        currParent.left = node;
                        break;
                    } else {
                        currParent = currParent.left;
                    }
                } else {
                    if (currParent.right == null) {
                        currParent.right = node;
                        break;
                    } else {
                        currParent = currParent.right;
                    }
                }
            }
            node.parent = currParent;
        }
    }
}

class Node {
    Node parent;
    Node left;
    Node right;
    int data;

    public Node(int data) {
        this.data = data;
        this.parent = null;
        this.left = null;
        this.right = null;
    }
}


