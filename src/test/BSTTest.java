package test;

import static org.junit.Assert.*;

import org.junit.Test;

import foobar.BST;

public class BSTTest {

	@Test
	public void testAnswer0() {
		int[] seq = {};
		String exp = "0";
		BST answer = new BST();
		
		assertEquals(exp, answer.answer(seq));
	}	
	
	@Test
	public void testAnswer1() {
		int[] seq = {1};
		String exp = "1";
		BST answer = new BST();
		
		assertEquals(exp, answer.answer(seq));
	}
	
	@Test
	public void testAnswer2() {
		int[] seq = {1, 2};
		String exp = "1";
		BST answer = new BST();
		
		assertEquals(exp, answer.answer(seq));
	}
	
	@Test
	public void testAnswer3() {
		int[] seq = {1, 2, 3};
		String exp = "1";
		BST answer = new BST();
		
		assertEquals(exp, answer.answer(seq));
	}	
	
	@Test
	public void testAnswer4() {
		int[] seq = {2, 1, 3};
		String exp = "2";
		BST answer = new BST();
		
		assertEquals(exp, answer.answer(seq));
	}	
	
	@Test
	public void testAnswer5() {
		int[] seq = {8, 7, 9};
		String exp = "2";
		BST answer = new BST();
		
		assertEquals(exp, answer.answer(seq));
	}	
	
	@Test
	public void testAnswer6() {
		int[] seq = {8, 9, 7};
		String exp = "2";
		BST answer = new BST();
		
		assertEquals(exp, answer.answer(seq));
	}
	
	@Test
	public void testAnswer7() {
		int[] seq = {5, 8, 9, 7};
		String exp = "2";
		BST answer = new BST();
		
		assertEquals(exp, answer.answer(seq));
	}
	
	@Test
	public void testAnswer8() {
		int[] seq = {5, 2, 8, 9, 7};
		String exp = "8";
		BST answer = new BST();
		
		assertEquals(exp, answer.answer(seq));
	}
	
	@Test
	public void testAnswer9() {
		int[] seq = {5, 8, 2, 9, 7};
		String exp = "8";
		BST answer = new BST();
		
		assertEquals(exp, answer.answer(seq));
	}
	
	@Test
	public void testAnswer10() {
		int[] seq = {5, 8, 9, 7, 2};
		String exp = "8";
		BST answer = new BST();
		
		assertEquals(exp, answer.answer(seq));
	}	
	
	@Test
	public void testAnswer11() {
		int[] seq = {5, 8, 9, 7, 2, 1};
		String exp = "20";
		BST answer = new BST();
		
		assertEquals(exp, answer.answer(seq));
	}
	
	@Test
	public void testAnswer12() {
		int[] seq = {5, 8, 2, 9, 7, 1};
		String exp = "20";
		BST answer = new BST();
		
		assertEquals(exp, answer.answer(seq));
	}	
	
	@Test
	public void testAnswer13() {
		int[] seq = {5, 8, 2, 9, 1, 7};
		String exp = "20";
		BST answer = new BST();
		
		assertEquals(exp, answer.answer(seq));
	}
	
	@Test
	public void testAnswer14() {
		int[] seq = {5, 8, 2, 9, 1, 3};
		String exp = "20";
		BST answer = new BST();
		
		assertEquals(exp, answer.answer(seq));
	}	
	
	@Test
	public void testAnswer15() {
		int[] seq = {5, 8, 1, 9, 7, 2, 3};
		String exp = "40";
		BST answer = new BST();
		
		assertEquals(exp, answer.answer(seq));
	}	
	
	@Test
	public void testAnswer16() {
		int[] seq = {5, 8, 2, 9, 7, 1, 3};
		String exp = "80";
		BST answer = new BST();
		
		assertEquals(exp, answer.answer(seq));
	}
	
	@Test
	public void testAnswer17() {
		int[] seq = {5, 1, 2, 3, 6, 7, 8, 9};
		String exp = "35";
		BST answer = new BST();
		
		assertEquals(exp, answer.answer(seq));
	}	

	@Test
	public void testTreeBuilding() {
		
		testTreeBuilding1();
		testTreeBuilding2();
		testTreeBuilding3();
		testTreeBuilding4();
		testTreeBuilding5();
		testTreeBuilding6();
	}
	
	private void testTreeBuilding1() {
		int[] seq = {5, 2, 1, 8, 7, 9};
		int[] inOrder = {1, 2, 5, 7, 8, 9};
		BST.Tree tree = new BST.Tree(seq);
		
		assertArrayEquals(inOrder, tree.convertToArr());		
	}
	
	private void testTreeBuilding2() {
		int[] seq = {1};
		int[] inOrder = {1};
		BST.Tree tree = new BST.Tree(seq);
		
		assertArrayEquals(inOrder, tree.convertToArr());		
	}
	
	private void testTreeBuilding3() {
		int[] seq = {1, 2};
		int[] inOrder = {1, 2};
		BST.Tree tree = new BST.Tree(seq);
		
		assertArrayEquals(inOrder, tree.convertToArr());		
	}
	
	private void testTreeBuilding4() {
		int[] seq = {2, 1};
		int[] inOrder = {1, 2};
		BST.Tree tree = new BST.Tree(seq);
		
		assertArrayEquals(inOrder, tree.convertToArr());		
	}
	
	private void testTreeBuilding5() {
		int[] seq = {5, 7, 1, 8, 9, 2};
		int[] inOrder = {1, 2, 5, 7, 8, 9};
		BST.Tree tree = new BST.Tree(seq);
		
		assertArrayEquals(inOrder, tree.convertToArr());		
	}
	
	private void testTreeBuilding6() {
		int[] seq = {2, 1, 3};
		int[] inOrder = {1, 2, 3};
		BST.Tree tree = new BST.Tree(seq);
		
		assertArrayEquals(inOrder, tree.convertToArr());		
	}	
}
