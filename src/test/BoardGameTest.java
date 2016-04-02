package test;

import static org.junit.Assert.*;

import org.junit.Test;

import foobar.BoardGame;

public class BoardGameTest {

	@Test
	public void testInitial1() {
		
		int t = 1;
		int n = 2;		
		int expectedResult = 1;
		
		assertEquals(expectedResult, BoardGame.answer(t, n));
	}
	
	@Test
	public void testInitial2() {
		
		int t = 3;
		int n = 2;
		int expectedResult = 3;
		
		assertEquals(expectedResult, BoardGame.answer(t, n));
	}
	
	@Test
	public void testInitial3() {
		
		int t = 0;
		int n = 2;
		int expectedResult = 0;
		
		assertEquals(expectedResult, BoardGame.answer(t, n));
	}	

}
