package test;

import static org.junit.Assert.*;

import org.junit.Test;

import foobar.TriangleRabbit;

public class TriangleRabbitTest {
	
	private static final double EPSILON = 0.00000005;

	@Test
	public void testLineFactory() {
		
		TriangleRabbit.Triangle.Line line = new TriangleRabbit.Triangle.LineFactory().createLine(2, 1, 5, 1);
		assertTrue(line instanceof TriangleRabbit.Triangle.HorizontalLine);
		
		line = new TriangleRabbit.Triangle.LineFactory().createLine(1, 2, 1, 5);
		assertTrue(line instanceof TriangleRabbit.Triangle.VerticalLine);
		
		line = new TriangleRabbit.Triangle.LineFactory().createLine(1, 2, 3, 5);
		assertFalse(line instanceof TriangleRabbit.Triangle.HorizontalLine);
		assertFalse(line instanceof TriangleRabbit.Triangle.VerticalLine);
	}
	
	@Test
	public void testLineY() {

		TriangleRabbit.Triangle.Line line = new TriangleRabbit.Triangle.LineFactory().createLine(2, 1, 5, 1);
		assertEquals(1, line.calculateY(3), EPSILON);
		assertEquals(1, line.calculateY(10), EPSILON);

		
		line = new TriangleRabbit.Triangle.LineFactory().createLine(-1, -1, -3, -3);
		assertEquals(0, line.calculateY(0), EPSILON);
		assertEquals(1, line.calculateY(1), EPSILON);
	}	
	
	@Test
	public void testMinAndMax() {
		
		int[][] vertices = {
			{0, 0},
			{1, 1},
			{2, 0}
		};
		
		TriangleRabbit.Triangle triangle = new TriangleRabbit.Triangle(vertices);
		assertEquals(0, triangle.getMinX());
		assertEquals(2, triangle.getMaxX());
	}
	
	@Test
	public void testBoundariesFactoryNormal() {
		
		int[][] vertices = {
			{0, 0},
			{1, 1},
			{2, 0}
		};
		
		int x1 = vertices[0][0];
		int y1 = vertices[0][1];
		int x2 = vertices[1][0];
		int y2 = vertices[1][1];
		int x3 = vertices[2][0];
		int y3 = vertices[2][1];
		
		TriangleRabbit.Triangle.Line line1 = new TriangleRabbit.Triangle.LineFactory().createLine(x1, y1, x2, y2);
		TriangleRabbit.Triangle.Line line2 = new TriangleRabbit.Triangle.LineFactory().createLine(x2, y2, x3, y3);
		TriangleRabbit.Triangle.Line line3 = new TriangleRabbit.Triangle.LineFactory().createLine(x3, y3, x1, y1);
		
		TriangleRabbit.Triangle.BoundariesCalculator boundariesCalculator = new TriangleRabbit.Triangle.BoundariesCalculatorFactory().createCalculator(line1, line2, line3);
		assertTrue(boundariesCalculator instanceof TriangleRabbit.Triangle.NormalBoundariesCalculator);
	}
	
	@Test
	public void testBoundariesFactoryVertical() {
		
		int[][] vertices = {
			{0, 0},
			{0, 1},
			{2, 0}
		};
		
		int x1 = vertices[0][0];
		int y1 = vertices[0][1];
		int x2 = vertices[1][0];
		int y2 = vertices[1][1];
		int x3 = vertices[2][0];
		int y3 = vertices[2][1];
		
		TriangleRabbit.Triangle.Line line1 = new TriangleRabbit.Triangle.LineFactory().createLine(x1, y1, x2, y2);
		TriangleRabbit.Triangle.Line line2 = new TriangleRabbit.Triangle.LineFactory().createLine(x2, y2, x3, y3);
		TriangleRabbit.Triangle.Line line3 = new TriangleRabbit.Triangle.LineFactory().createLine(x3, y3, x1, y1);
		
		TriangleRabbit.Triangle.BoundariesCalculator boundariesCalculator = new TriangleRabbit.Triangle.BoundariesCalculatorFactory().createCalculator(line1, line2, line3);
		assertTrue(boundariesCalculator instanceof TriangleRabbit.Triangle.VerticalBoundariesCalculator);
	}
	
	@Test
	public void testBoundariesFactoryVertical2() {
		
		int[][] vertices = {
			{0, 0},
			{1, 1},
			{1, 2}
		};
		
		int x1 = vertices[0][0];
		int y1 = vertices[0][1];
		int x2 = vertices[1][0];
		int y2 = vertices[1][1];
		int x3 = vertices[2][0];
		int y3 = vertices[2][1];
		
		TriangleRabbit.Triangle.Line line1 = new TriangleRabbit.Triangle.LineFactory().createLine(x1, y1, x2, y2);
		TriangleRabbit.Triangle.Line line2 = new TriangleRabbit.Triangle.LineFactory().createLine(x2, y2, x3, y3);
		TriangleRabbit.Triangle.Line line3 = new TriangleRabbit.Triangle.LineFactory().createLine(x3, y3, x1, y1);
		
		TriangleRabbit.Triangle.BoundariesCalculator boundariesCalculator = new TriangleRabbit.Triangle.BoundariesCalculatorFactory().createCalculator(line1, line2, line3);
		assertTrue(boundariesCalculator instanceof TriangleRabbit.Triangle.VerticalBoundariesCalculator);
	}	
	
	@Test
	public void testCountPoints1() {
		
		int[][] vertices = {
			{0, 0},
			{2, 2},
			{2, 0}
		};
		
		TriangleRabbit.Triangle triangle = new TriangleRabbit.Triangle(vertices);
		assertEquals(0, triangle.countPoints(1));
	}
	
	@Test
	public void testCountPoints2() {
		
		int[][] vertices = {
			{0, 0},
			{3, 3},
			{3, 0}
		};
		
		TriangleRabbit.Triangle triangle = new TriangleRabbit.Triangle(vertices);
		assertEquals(1, triangle.countPoints(2));
	}
	
	@Test
	public void testCountPoints3() {
		
		int[][] vertices = {
			{-1, -1},
			{1, 0},
			{0, 1}
		};
		
		TriangleRabbit.Triangle triangle = new TriangleRabbit.Triangle(vertices);
		assertEquals(1, triangle.countPoints(0));
	}
	
	@Test
	public void testAnswer1() {
		
		int[][] vertices = {
			{-1, -1},
			{1, 0},
			{0, 1}
		};

		TriangleRabbit.Triangle triangle = new TriangleRabbit.Triangle(vertices);
		//System.out.println(triangle);
		
		assertEquals(1, TriangleRabbit.answer(vertices));
	}
	
	@Test
	public void testAnswer2() {
		
		int[][] vertices = {
			{2, 3},
			{6, 9},
			{10, 160}
		};
		
		TriangleRabbit.Triangle triangle = new TriangleRabbit.Triangle(vertices);
		assertEquals(2, triangle.getMinX());
		assertEquals(10, triangle.getMaxX());
		
		//assertEquals(18, triangle.countPoints(3));
		
		System.out.println(triangle);

		//assertEquals(289, TriangleRabbit.answer(vertices));
	}	

}
