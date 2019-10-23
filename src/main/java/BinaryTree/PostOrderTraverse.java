package BinaryTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class PostOrderTraverse {
	static void traverse(TreeNode root){
		Deque<TreeNode> stack= new ArrayDeque<>();
		List<TreeNode> l = new ArrayList<>();
		while(true){
			if(root!=null){
				l.add(root);
				stack.push(root);
				root=root.right;
			}
			else{
				if(stack.isEmpty())break;
				else{
					root = stack.pop();
					root=root.left;
				}
			}
		}
		Collections.reverse(l);
		l.forEach((a)->System.out.println(a.val));
	}
	
	public static void main(String[] args) {
		TreeNode root = TreeNode.stringToTreeNode("[1,2,3,4,5,6,7,8,9,0]");
		System.out.println(root);
		traverse(root);
	}
}
