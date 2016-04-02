package foobar;

import java.util.Arrays;

public class TriangleRabbit {
	
    public static int answer(int[][] vertices) { 
        
    	int sum = 0;
    	Triangle triangle = new Triangle(vertices);
    	
    	for(int x = triangle.getMinX()+1; x < triangle.getMaxX(); x++) {
    		
    		sum += triangle.countPoints(x);
    	}
    	
    	return sum;
    }
    
    public static class Triangle {
    	
    	private static final double BOUNDARY_ELIMINATOR = 0.1;
		private Line line1;
    	private Line line2;
    	private Line line3;
    	
    	private int minX;
    	private int midX;
    	private int maxX;
    	
    	private BoundariesCalculator calculator;
    	
    	public Triangle(int[][] vertices) {
    		
    		this(vertices[0][0], vertices[0][1],
    				vertices[1][0], vertices[1][1],
    				vertices[2][0], vertices[2][1]);
    	}
    	
    	public Triangle(int x1, int y1,
    					int x2, int y2,
    					int x3, int y3) {
    		
    		int[] xPoints = {x1, x2, x3};
    		Arrays.sort(xPoints);
    		minX = xPoints[0];
    		midX = xPoints[1];
    		maxX = xPoints[2];
    		
    		minX = Math.min(x1, Math.min(x2, x3));
    		maxX = Math.max(x1, Math.max(x2, x3));
    		
    		LineFactory lineFactory = new LineFactory();    		
    		line1 = lineFactory.createLine(x1, y1, x2, y2);
    		line2 = lineFactory.createLine(x2, y2, x3, y3);
    		line3 = lineFactory.createLine(x3, y3, x1, y1);
    		
    		calculator = new BoundariesCalculatorFactory().createCalculator(line1, line2, line3);
    	}
    	
    	public int countPoints(int x) {
    		
    		return calculator.calculate(x);    		    		
    	}
    	
    	public int getMinX() {
    		return minX;
    	}
    	
    	public int getMidX() {
    		return midX;
    	}    	
    	
    	public int getMaxX() {
    		return maxX;
    	}
    	
    	@Override
    	public String toString() {
    		return line1.toString() + "\n" +
    				line2.toString() + "\n" +
    				line3.toString();
    	}
    	
    	public static class BoundariesCalculatorFactory {
    		
    		public BoundariesCalculator createCalculator(Line line1, Line line2, Line line3) {
    			
        		if(line1 instanceof VerticalLine) {
        			return new VerticalBoundariesCalculator(line2, line3);
        		}
        		if(line2 instanceof VerticalLine) {
        			return new VerticalBoundariesCalculator(line1, line3);
        		}
        		if(line3 instanceof VerticalLine) {
        			return new VerticalBoundariesCalculator(line1, line2);
        		}
        		
        		return new NormalBoundariesCalculator(line1, line2, line3);
    		}
    	}

    	
    	public interface BoundariesCalculator {
    		public int calculate(int x);
    	}

    	public static class NormalBoundariesCalculator implements BoundariesCalculator {
    		
    		Line line1;
    		Line line2;
    		Line line3;
    		
    		public NormalBoundariesCalculator(Line line1, Line line2, Line line3) {
    			this.line1 = line1;
    			this.line2 = line2;
    			this.line3 = line3;
    		}
    		
    		@Override
    		public int calculate(int x) {   
    			
    			double y1 = line1.calculateY(x);
    			double y2 = line2.calculateY(x);
    			double y3 = line3.calculateY(x);
    			
    			double max = Math.max(y1, Math.max(y2, y3));
    			
    			if(y1 == max) {
    				return countPoints(line2, line3, x);
    			}
    			
    			if(y2 == max) {
    				return countPoints(line1, line3, x);
    			}
    			
    			return countPoints(line1, line2, x); 			
    		}
    	}    	
    	
    	public static class VerticalBoundariesCalculator implements BoundariesCalculator {
    		
    		Line line1;
    		Line line2;
    		
    		public VerticalBoundariesCalculator(Line line1, Line line2) {
    			this.line1 = line1;
    			this.line2 = line2;
    		}
    		
    		@Override
    		public int calculate(int x) {    			
    			return countPoints(line1, line2, x);
    		}
    	}
    	
    	public static int countPoints(Line line1, Line line2, int x) {
    		
    		double a = line1.calculateY(x);
    		double b = line2.calculateY(x);
    		
    		double min = Math.min(a, b);
    		double max = Math.max(a, b);
    		
    		if(isInteger(max)) {
    			max -= BOUNDARY_ELIMINATOR;
    		}
    		
    		if(isInteger(min)) {
    			min += BOUNDARY_ELIMINATOR;
    		}    		
    		
    		return (int) Math.floor(max - min);
    	}
    	
    	public static boolean isInteger(double x) {
    		return x == Math.floor(x);
    	}
    	
    	public static class LineFactory {
    		
    		public Line createLine(int x1, int y1, int x2, int y2) {
    			
    			if(y1 == y2) {
    				return new HorizontalLine(x1, y1, x2, y2);
    			}
    			
    			if(x1 == x2) {
    				return new VerticalLine();
    			}
    			
    			return new Line(x1, y1, x2, y2);
    		}
    	}

		public static class Line {
    		
    		private int x1;
    		protected int y1;
    		private int x2;
    		private int y2;    		
    		private double slope;
    		private double yIntercept;
    		
    		private double z;
    		
    		public Line() {
    			//Vertical line
    		}
    		
    		public Line(int x1, int y1, int x2, int y2) {
    			
    			this.x1 = x1;
    			this.y1 = y1;    			
    			this.x2 = x2;
    			this.y2 = y2;
    			
    			this.slope = (double) (y2 - y1) / (double) (x2 - x1);
    			this.yIntercept = y1 - this.slope*x1;
    			
    			this.z = -(this.slope * x1) + y1;
    		}
    		
    		public double calculateY(int x) {
    			return this.slope * x + this.z;
    		}
    		
    		@Override
    		public String toString() {
    			return this.slope + "x" + "+" + yIntercept;
    		}
    	}
    	
    	public static class HorizontalLine extends Line { 
    		
    		public HorizontalLine(int x1, int y1, int x2, int y2) {    			
    			super(x1, y1, x2, y2);
    		}
    		
    		@Override
    		public double calculateY(int x) {
    			return this.y1;
    		}
    	}
    	
    	public static class VerticalLine extends Line {
    		
    		public VerticalLine() {    			
    			//super();
    		}
    		
    		@Override
    		public double calculateY(int x) {
    			return -1;
    		}
    	}    	
    }
}
