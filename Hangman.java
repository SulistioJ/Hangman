package pa5;
import java.util.Scanner;
import java.lang.Character;

public class Hangman 
{
	public static void main (String args []) 
	{
		
		//Get scanner.
		Scanner Josh = new Scanner(System.in);
		System.out.println("Welcome guys to Hangman!");
		System.out.println("Player1 please enter a word. Player2 please do not look");
		String secret = Josh.next();
		
		//Prints out 100 blank spaces AND the hang-man.
		printHangman(6);
		for (int i = 1; i <= 100; i++) 
		{
			System.out.println(" ");
		}
		
		//This is to make the inputed word to dashes.
		String blanks = (createPartialWord(secret));
		System.out.println(blanks);
		
		//We declare the number of lives.
		//Cats have 9 lives supposedly.
		int catLifeUpsideDown = 6;
		
		//Just like in the prime sample code assume 
		//everything is false until proven innocent.
		boolean tried = false;
		int tries = 0;
	    
		// This loop is specifically for people who is brave enough
		// to try a word if they are wrong they die instantly.
		while(!tried && tries <= catLifeUpsideDown) 
		{ 
			System.out.println("Would you like to guess the secret word or guess a character?\n" + 
					"Type \"word\" for word, type \"char\" for character");
			
			//Use toLowerCase so string is exactly the same
			String choice = Josh.next();
			choice = choice.toLowerCase();
			if(choice.equals("word"))
			{
				System.out.println("Type your guess for secret word");
				String guessedWord = Josh.next();
				guessedWord = guessedWord.toLowerCase();
				if(guessedWord.equals(secret))
				{
					tried = true;
					System.out.println("Correct! You win");
					System.out.println("Here is the hangman!");
					printHangman(catLifeUpsideDown - tries);
				}
				else
				{
					tried = true;
					System.out.println("Incorrect Guess, Player1 wins. Good Game");
					System.out.println("Here is your hangman");
					printHangman(catLifeUpsideDown - tries);
				}
		    } 
			
			//if player entered CHAR or char toLowerCase will lower it to lowercase
			else if (choice.equals("char"))  
			{
				//display number of lives until they die
				System.out.println("You have " + (catLifeUpsideDown - tries) +" lives remaining");
				System.out.println("Enter char");
				char charGuessed = Josh.next().charAt(0);
				charGuessed = Character.toLowerCase(charGuessed);
				boolean isCharRight = false;
							
				for(int i = 0; i < secret.length(); i++)
				{
					if(secret.charAt(i) == (charGuessed))
					{
						blanks = updatePartialWord(blanks, secret, charGuessed);
						isCharRight = true;
					}
				}
				
				// if player doesn't guess a character right, increase their tries by 1.
				if(!isCharRight) 
				{
					tries++;
					System.out.println("Tsk tsk, try again.");
					System.out.println("Here's the partial word:" + blanks);
					
				} 
				else if(secret.equals(blanks)) 
				{
					// Check to see if the user correctly guessed the word
					tried = true;
					System.out.println("Word guessed correctly - " + secret);
				}
				else
				{
					System.out.println("Character guessed correctly, current word guess is :" + blanks);
				}
				System.out.println("The hangman looks like:");
				printHangman(catLifeUpsideDown - tries);
			}	
		}
		
		if(tried) 
		{
			System.out.println("The secret word is " + secret);
		}
		else 
		{
			System.out.println("You ran out of tries. Oh No!");
			System.out.println("There's the Hangman!");
			printHangman(catLifeUpsideDown - tries);
		}
	}
			
	
	//Method to create dashes from the word guessed Player1
	public static String createPartialWord(String word1) 
		{
			String dashes = "";
			for (int j = 0; j < word1.length(); j++)
			{
				dashes += "-";
			}
		 return dashes;
		}

	//A method to use with the updatePartialWord.
	//To replace dashes to real actual chars.
	public static String replaceChar(String word, char c, int i)
		{
			String newWord = "";
			for(int j = 0; j < word.length(); j++)
			{
				if(j == i) 
				{
					newWord += c;
				}
				else 
				{
				    newWord += word.charAt(j);
				}
			}
			return newWord;
		}
	
	//This is a method to update the dashes to partially guessed words
	public static String updatePartialWord(String partial, String secret, char c)
		{
			for (int j = 0; j < secret.length(); j++)
			{
				if (secret.charAt(j) == (c)) 
				{
					partial = replaceChar(partial, c, j);
				}
			}
			return partial;
		}
	//To print a hang-man. (Given code)
	private static void printHangman(int guessLeft) 
	{
	     String HEAD = " ";
	     String BODY = " ";
	     String LEGS = " ";
	     String LEFTARM = " ";
	     String RIGHTARM = " ";
	     System.out.println("_____");
	     System.out.println("|    |");
	      if (guessLeft < 6) 
	      {
	        HEAD = "  (  )";
	      }
	      System.out.println("| " + HEAD);
	    	 if (guessLeft < 5) 
	      {
	    	    BODY = " ||";
	    	  }
	    	 if (guessLeft < 4)
	    	 {
	    	    LEFTARM = " /";
	    	 }
	    	 if (guessLeft < 3)
	    	 {
	    		 RIGHTARM = " \\";
	     }
	    	 System.out.println("| " + LEFTARM + BODY + RIGHTARM);
	    	 if (guessLeft < 2)
	    	 {
	    		 LEGS = "  /"; 
	    	 }
	    	 if (guessLeft < 1)
	    	 {
	         LEGS += "  \\";
	     }
	    	 System.out.println("| " + LEGS);
	     System.out.println("|_____\n\n\n\n");
	}
}
