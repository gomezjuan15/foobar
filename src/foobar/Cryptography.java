package foobar;

public class Cryptography {
	
    public static int[] answer(int[] digest) { 

        int prevMessage = 0;
        int[] messages = new int[digest.length];
        
        for(int i = 0; i < digest.length; i++) {
        	
            for(int j = 0; j < 255; j++) {
            	
            	int candidateDigest = calculateDigest(j, prevMessage);
            	if(candidateDigest == digest[i]) {
            		prevMessage = j;
            		messages[i] = prevMessage;
            		break;
            	}
            }        
        }
        
        return messages;
    }

	private static int calculateDigest(int i, int prevMessage) {
		
		return ((129 * i) ^ prevMessage) % 256;
	} 
	
	public static void main(String[] args){
		
		 int[] digest = {0, 129, 3, 129, 7, 129, 3, 129, 15, 129, 3, 129, 7, 129, 3, 129};
		 
		 int[] res = answer(digest);
		 for(int a : res){
			 System.out.println(a);	 
		 } 
	 }	
}
