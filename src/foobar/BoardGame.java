package foobar;

import java.math.BigInteger;

public class BoardGame {
	
    public static int answer(int t, int n) { 

    	BigInteger[][] cache = new BigInteger[n+1][t+1];
    	
        return countPossiblities(1, t, n, cache).mod(new BigInteger("123454321")).intValue();
    } 	

    public static BigInteger countPossiblities(final int currentSquare, final int remainingRolls, final int lastSquare,
    									final BigInteger[][] cache) {
    	
    	if(currentSquare > lastSquare || currentSquare < 1) {
    		return BigInteger.ZERO;
    	}
    	
    	if(currentSquare == lastSquare) {    		
    		return BigInteger.ONE;
    	}    	
    	
    	if(remainingRolls == 0) {
    		return BigInteger.ZERO;
    	}
    	
    	if(currentSquare + remainingRolls < lastSquare) {
    		return BigInteger.ZERO;
    	}
    	
    	BigInteger possibleValue = cache[currentSquare][remainingRolls];
    	if(possibleValue != null) {
    		return possibleValue;
    	}
    	
    	if(currentSquare == 1) {
    		
    		BigInteger answer = countPossiblities(currentSquare, remainingRolls-1, lastSquare, cache) 
    						.add(countPossiblities(currentSquare+1, remainingRolls-1, lastSquare, cache));
    		
    		cache[currentSquare][remainingRolls] = answer;
    		
    		return answer;
    	}
    	
    	BigInteger answer = countPossiblities(currentSquare - 1, remainingRolls - 1, lastSquare, cache)
    						.add(countPossiblities(currentSquare, remainingRolls - 1, lastSquare, cache)) 
    						.add(countPossiblities(currentSquare + 1, remainingRolls - 1, lastSquare, cache));
		
		cache[currentSquare][remainingRolls] = answer;
		
		return answer;    	
    }
}