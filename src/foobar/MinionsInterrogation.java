package foobar;

import java.util.Arrays;
import java.util.Comparator;

public class MinionsInterrogation {
	
    private static final double EPSILON = 0.0000000000000000000000005;
    
	public static int[] answer(int[][] minions) { 

		final Minion[] minionsArray = createMinionArray(minions);
		
        Arrays.sort(minionsArray,
        			new Comparator<Minion>() {
        	@Override
        	public int compare(Minion m1, Minion m2) {
        	    
        	    if(Math.abs(m1.getRisk() - m2.getRisk()) < EPSILON) {
        	        return 0;
        	    }
        	    
        		return (int) (m1.getRisk() - m2.getRisk());
        	}
        });
        
        return extractIndexes(minionsArray);
	} 
	
	private static int[] extractIndexes(final Minion[] minionsArray) {
		
		int[] indexes = new int[minionsArray.length];
		
		for(int i = 0; i < minionsArray.length; i++) {
			
			indexes[i] = minionsArray[i].getIndex();
		}
		
		return indexes;
	}	
	
	private static Minion[] createMinionArray(final int[][] minions) {

		Minion[] minionArray = new Minion[minions.length];
		
		for(int i = 0; i < minions.length; i++) {
			
			int[] minion = minions[i];
			minionArray[i] = new Minion(i, minion[0], minion[1], minion[2]);
		}
		
		return minionArray;
	} 	

	static class Minion {
		
		private final int index;
		private final double givingAnswerProbability;
		private final double notGivingAnswerProbability;
		private final int time;
		private final double risk;

		public Minion(final int index,
				final int time,
				final int probabilityNumerator,
				final int probabilityDenominator) {

			this.givingAnswerProbability = (double) probabilityNumerator / (double) probabilityDenominator;
			this.notGivingAnswerProbability = 1 - givingAnswerProbability;
			this.time = time;
			this.index = index;
			this.risk = time / givingAnswerProbability;
		}

		public int getIndex() {
			return index;
		}		
		
		public double getGivingAnswerProbability() {
			return givingAnswerProbability;
		}
		
		public double getNotGivingAnswerProbability() {
			return notGivingAnswerProbability;
		}		

		public int getTime() {
			return time;
		}
		
		public double getRisk() {
			return risk;
		}
		
		public String toString() {
			return String.format("Index: %s, time: %d, probability answer: %f\n", 
									getIndex(),
									getTime(),
									getGivingAnswerProbability());
		}
	}    

	public static void main(String[] args){
		
		 int[] m1 = {100, 1, 10};
		 int[] m2 = {101, 2, 0};
		 int[] m3 = {100, 2, 10};
		 
		 int[][] mArr = new int[3][3];
		 mArr[0] = m1;
		 mArr[1] = m2;
		 mArr[2] = m3;
		 
		 int[] res = answer(mArr);
		 for(int a : res){
			 System.out.println(a);	 
		 } 
	 }	
}
