import java.util.Random; 
import java.util.Scanner;


public class Hangman {

	public static void main(String args  []) {
		
		playGame();
		

	}
	
	//Updates your answer	
	public static String updateString(String solved, String secret, int index){
        char[] solvedChars = solved.toCharArray();
        char replaceChar = secret.charAt(index);
        solvedChars[index] = replaceChar;
        solved = String.valueOf(solvedChars);
            
        return solved;
    }

    //Updates previousGuesses made
    public static String updatePreviousGuesses(String previousGuesses, String input){
        String updated = previousGuesses + input;
        return updated;
    }

	//Creates a random string
	public static String createString(int length, int seed) {
		String random = "";
		int charnum;
		char genChar;
		
		for (int i = 0; i < length; i++) {
		    charnum = 97 + seed + (int)(26 * Math.random() );
		    if (charnum >= 97 && charnum <= 122){
		        genChar = (char)charnum;
		        random = random + genChar;
		    }
		    else{
		        while(!(charnum >= 97 && charnum <= 122)){
		            charnum = 97 + seed + (int)(26 * Math.random() );
		        }
		        genChar = (char)charnum;
		        random = random + genChar;
		    }
		}
		return random;	
		
		
	}

	//Check if your guess was already made
	public static String checkGuess(String previousGuesses, String input) {
		String error1 = "You should not enter more than 1 character";
		String error2 =  " has been chosen before, try again";
		String error3 = "Your input should be between a - z";
		
		String response = ""; 
		
		//more than one character entered
		if (input.length() > 1) {
			response = error1;		
		}
		//invalid character entered
		else if (input.charAt(0) < 97 || input.charAt(0) > 122) {
			response = error3;
		}
		//check if already guessed
		else if (previousGuesses.contains(input)){
			response = input + error2;
		} else {
			//previousGuesses += input;
			response = input;
		}
	
		return response;
		
	}
	
	//Runs the game
	public static void playGame() {
	    		//-------PLAY GAME----//

		//Initializing previousGuesses		
		String previousGuesses = "";
	
		//Input length of Random String	
		System.out.println("Enter the String length");
		Scanner lengthInput = new Scanner(System.in);
		int length = lengthInput.nextInt();
		int seed = 5;	
		
		//Creating random string
		String secret = createString(length, seed);

		System.out.println(secret); //Print secret

		System.out.println();
		
		//Initializing answer
		String solved = "";
		//initializing the solved string
		for (int i = 0; i < secret.length(); i++){
		    solved += "_";
		}
		
		//Begin Guessing
		System.out.println("Start guessing the string: ");
		System.out.println();
			
		Scanner guessInput = new Scanner(System.in);
		String guess;
		String present;

		//Loop for Rounds
		for (int interval = 1; interval <= 8; interval++) {
			System.out.println ("Guessing(round " + interval + "): Choosing your character from a-z:");
			guess = guessInput.nextLine();
			
			present = checkGuess(previousGuesses, guess);
			
			//Checking if error exists
			if (present.length() > 1) {
			    System.out.println(present);
			}
			else{
				//Updating previousGuesses
			    previousGuesses = updatePreviousGuesses(previousGuesses, present);
			   	
			   	   //Loop for Checking if present is the right guess
			    for(int i = 0; i < secret.length(); i++) {
				    if (present.charAt(0) == secret.charAt(i)) {
					    solved = updateString(solved, secret, i);
				    }
			    } 
			    System.out.println(solved);
			}
		
			System.out.println("End (round " + interval + ")");
			
			//Check if game is completed
			if (solved.equals(secret)){
		        System.out.println("Congratulations! You took " + interval + " rounds to  complete the game.");
		        break;
		    }
			System.out.println();

		}
		
		//After all rounds end 
		if ( !(solved.equals(secret)) ){
		    System.out.println("You lose!");
        }
    
        //-------END of GAME------//
	}
	
}
