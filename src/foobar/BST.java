package foobar;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

public class BST {

	public static String answer(int[] seq) { 
		
		if(seq == null || seq.length == 0) {
			return "0";
		}

		Tree tree = new Tree(seq);
		Result answer = calculateAnswer(tree.getRoot());
		
		return String.valueOf(answer.total);
	}
	
	public static class Tree {
		
		Node root;
		
		public Tree(int[] seq) {
			root = buildTree(seq);
		}
		
		public Node getRoot() {
			return root;
		}
		
		private Node buildTree(int[] seq) {
			
			Node root = new Node(seq[0]);
			
			for(int i = 1; i < seq.length; i++) {			
				insertInTree(root, seq[i]);
			}
			
			return root;
		}
		
		private void insertInTree(Node root, int value) {
			
			Node walk = root;
			
			while(true) {
				
				if(value < walk.getValue()) {
					if(walk.getLeftChild() == null) {
						walk.setLeftChild(new Node(value));
						break;
					}
					walk = walk.getLeftChild();
				} else {
					if(walk.getRightChild() == null) {
						walk.setRightChild(new Node(value));
						break;
					}				
					walk = walk.getRightChild();
				}
			}
		}
		
		public int[] convertToArr() {
			
			List<Integer> list = new LinkedList<>();
			
			convertToArrRec(root, list);
			
			int[] array = new int[list.size()];
			for(int i = 0; i < list.size(); i++) array[i] = list.get(i);			
			
			return array;
		}
		
		private void convertToArrRec(Node root, List<Integer> list) {
			
			if(root == null) {
				return;
			}
			
			convertToArrRec(root.getLeftChild(), list);
			
			list.add(root.getValue());
			
			convertToArrRec(root.getRightChild(), list);
		}		
		
		class Node {
			
			private Node leftChild;
			private Node rightChild;
			private int value;
			
			public Node(int value) {
				this.value = value;
			}
			
			public void setRightChild(Node node) {
				this.rightChild = node;
			}

			public void setLeftChild(Node node) {
				this.leftChild = node;
			}

			public int getValue() {
				return value;
			}
			
			public Node getLeftChild() {
				return leftChild;
			}
			
			public Node getRightChild() {
				return rightChild;
			}
			
			public boolean hasLeftChild() {
				return leftChild != null;
			}
			
			public boolean hasRightChild() {
				return rightChild != null;
			}
		}		
	}
	
	public static Result calculateAnswer(Tree.Node root) {
		
		if(root == null || !root.hasLeftChild() && !root.hasRightChild()) {
			return new Result();
		}
		
		if(!root.hasLeftChild()) {
			Result result = calculateAnswer(root.getRightChild());
			result.setCountChildren(result.getCountChildren() + 1);
			return result;
		}	
		
		if(!root.hasRightChild()) {
			Result result = calculateAnswer(root.getLeftChild());
			result.setCountChildren(result.getCountChildren() + 1);
			return result;
		}			
		
		Result resultLeft = calculateAnswer(root.getLeftChild());
		Result resultRight = calculateAnswer(root.getRightChild());

		return new Result(resultLeft, resultRight);
	}

	static class Result {
		
		private BigInteger total;
		private int countChildren = 1;

		public Result(Result resultLeft, Result resultRight) {
			
			countChildren = resultLeft.getCountChildren() + resultRight.getCountChildren() + 1;
			total = calculatePermutations(resultLeft, resultRight);
			total = total.multiply(resultLeft.getTotal());
			total = total.multiply(resultRight.getTotal());
		}
		
		public void setCountChildren(int countChildren) {
			this.countChildren = countChildren;
		}

		public Result() {
			total = BigInteger.ONE;
		}
		
		private BigInteger calculatePermutations(Result resultLeft, Result resultRight) {
			
			int lowestChildren = Math.min(resultLeft.getCountChildren(), resultRight.getCountChildren());
			int highestChildren = Math.max(resultLeft.getCountChildren(), resultRight.getCountChildren());
			
			if(lowestChildren == 0) {
				return resultLeft.getTotal().compareTo(resultRight.getTotal()) > 0 ? 
						resultLeft.getTotal() : 
						resultRight.getTotal();
			}
			
			int highestChildrenLength = highestChildren + 1;
			if(lowestChildren == 1) {
				return new BigInteger(String.valueOf(highestChildrenLength));
			}
						
			BigInteger[] dp = new BigInteger[highestChildrenLength];
			for(int i = 0; i < highestChildrenLength; i++) {
				dp[i] = new BigInteger(String.valueOf(i+1));
			}
			
			return calculatePermutationsRec(2, lowestChildren, highestChildrenLength, dp);
		}
		
		private BigInteger calculatePermutationsRec(int currentIteration, int lowest, int highest, BigInteger[] prevDp) {
			
			if(currentIteration == lowest) {								
				return sumArray(prevDp);
			}
			
			BigInteger[] dp = new BigInteger[highest];
			dp[0] = prevDp[0];
			
			for(int i = 1; i < prevDp.length; i++) {
				dp[i] = new BigInteger(dp[i-1].toString());
				dp[i] = dp[i].add(prevDp[i]);
			}
			
			return calculatePermutationsRec(currentIteration + 1, lowest, highest, dp);
		}
		
		private BigInteger sumArray(BigInteger[] arr) {
			
			BigInteger sum = BigInteger.ZERO;
			
			for(BigInteger elem: arr) {
				sum = sum.add(elem);
			}			
			
			return sum;
		}

		public int getCountChildren() {
			return countChildren;
		}
		
		public BigInteger getTotal() {
			return total;
		}
	}
	
	
	public static void main(String[] args){
	
		int[] digest = {5, 9, 8, 2, 1};
	
		String res = answer(digest);		
		System.out.println(res);	 
	}
}
