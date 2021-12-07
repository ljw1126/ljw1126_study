package algorithm.Tree.recommandEx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*

    이진 검색 트리 - 실버 1
    https://www.acmicpc.net/problem/5639

    해당 블로그 참고 -- 이게 기본 BST 알고리즘 활용한거라 쉬움
    https://girawhale.tistory.com/59

    ※ 솔루션 너무 어려움.. (궁금하면 공부해보자..)

    5 28 24 45 30 60 52 98 50       // 후위 순회 - 왼쪽 오른쪽 - 가운데 
    50 30 24 5 28 45 98 52 60      // 전위 순회 - 가운데 왼쪽 오른쪽
    5 24 28 30 45 50 52 60 98      // 중위 순회 - 왼쪽 가운데 오른쪽
*/
public class ex2_5639practice {
    
    static StringBuilder sb = new StringBuilder();
    
    static class Node{
        int num;
        Node left;
        Node right;

        Node(int num){
            this.num = num;
            this.left = null;
            this.right = null;
        }

        Node(int num, Node left, Node right){
            this.num = num;
            this.left = left;
            this.right = right;
        }

        void insert(int n){
            if(n < this.num){ // 왼쪽인 경우
                if(this.left == null) this.left = new Node(n);
                else this.left.insert(n);
            }else{ // this.num < n  오른쪽인 경우
                if(this.right == null) this.right = new Node(n);
                else this.right.insert(n);
            }
        }

    }


    // 전위 순회 한 값이 주어질대, 후위 순회한 값을 구하라 

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Node root = new Node(Integer.parseInt(br.readLine()));
        String input ="";
        while(true){
            input = br.readLine();
            if(input == null || "".equals(input)) break;
            root.insert(Integer.parseInt(input));
        }
       
        postOrder(root);
        sb.append('\n');
        preOrder(root);
        sb.append('\n');
        inOrder(root);
        System.out.println(sb);
    }

    static void postOrder(Node node){
        if(node == null) return;

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.num);
        sb.append(node.num).append(' ');
    }
    // 나머지는 궁금해서 추가해봄  
    // 중위 순회 - 왼 가운데 오른
    static void inOrder(Node node){
        if(node == null) return;

        inOrder(node.left);
        sb.append(node.num).append(' ');
        inOrder(node.right);
    }

    // 전위 순회 - 가운데 왼쪽 오른쪽 
    static void preOrder(Node node){
        if(node == null) return;

        sb.append(node.num).append(' ');
        preOrder(node.left);
        preOrder(node.right);
    }

}
