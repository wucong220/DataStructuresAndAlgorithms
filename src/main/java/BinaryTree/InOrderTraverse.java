package BinaryTree;

import java.util.ArrayDeque;
import java.util.Deque;

public class InOrderTraverse {
	static void traverse(TreeNode root){
		Deque<TreeNode> stack= new ArrayDeque<>();
		while(true){
			if(root!=null){
				stack.push(root);
				root=root.left;
			}
			else{
				if(stack.isEmpty())break;
				else{
					root = stack.pop();
					System.out.println(root.val);
					root=root.right;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		TreeNode root = TreeNode.stringToTreeNode("[1,2,3,4,5,6,7,8,9,0]");
		System.out.println(root);
		traverse(root);
	}
}
