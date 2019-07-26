/**
 * This class implements the user interface and it contains the main method
 * @author Leland Conn, , Student List #: 45, Student #: 250946924
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class UserInterface {
	
	public static void main(String[] args) {
		
		File file = new File(args[0]);
		OrderedDictionary dict = new OrderedDictionary();
		
	    try {
	    		
	    		//Read the file storing the data
	        Scanner scan = new Scanner(file);
	        
	        String key = scan.nextLine();
    			String data = scan.nextLine();
    			String type;
	        
    			//Identify the data as image files, audio files, and text
    			//then insert them into the UserDictionary object
	        while (scan.hasNextLine()) {
	        		
	        		if (data.endsWith(".jpg") || data.endsWith(".gif")) {
	        			type = "image";
	        		}
	        		
	        		else if (data.endsWith(".wav") || data.endsWith(".mid")) {
	        			type = "audio";
	        		}
	        		
	        		else {
	        			type = "text";
	        		}
	        		
	        		Pair pair = new Pair(key, type);
	        		Record rec = new Record(pair, data);
	        		
	        		try {
	        			dict.put(rec);
	        		}
	        		
	        		catch (DictionaryException d) {
	    	    			continue;
	        		}
	        		key = scan.nextLine();
	        		data = scan.nextLine();
	        }
	        scan.close();
	    } 
	    catch (FileNotFoundException e) {
	        System.out.println("Error: File not found.");
	    }
	    
	    //Read user input
	    StringReader keyboard = new StringReader();
	    String line = keyboard.read("Enter a command: ");
	    line = line.toLowerCase();
	    //Analyze input one element of the sentence at a time
	    StringTokenizer s = new StringTokenizer(line);
	    //Initialize to play sounds of audio files
	    SoundPlayer sound = new SoundPlayer();
	    //Initialize to show image files
	    PictureViewer pic = new PictureViewer();
	    
	    boolean running = true;
	    while(running) {
	    		String input = s.nextToken();
	    		//If the ordered dictionary has records containing the given word, then each one of these records
	    		//(word,type,data) will be processed in the following manner:
	    		//	– if type = “text”, then print data on the screen,
	    		//	– if type = “audio”, then play the audio file whose name is stored in data,
	    		//	– if type = “image”, then display the image stored in the file whose name is stored in data.
			if (input.equals("get")) {
				String input2 = s.nextToken();
				String type = "not found";
				//Create all possible types of Pair objects with word as input2
				Pair p1 = new Pair(input2, "text");
				Pair p2 = new Pair(input2, "audio");
				Pair p3 = new Pair(input2, "image");
				Record result1 = dict.get(p1);
				Record result2 = dict.get(p2);
				Record result3 = dict.get(p3);
				Record nonNullRecord = new Record(p1, input2);
				
				//Check if word is in the UserDictionary and what type its data is
				if (result1 != null && result1.getKey().compareTo(p1) == 0 
					&& result1.getKey().getType().equals("text")) {
					type = "text";
					System.out.println(result1.getData());
					
				}
				
				if(result2 != null && result2.getKey().compareTo(p2) == 0 
					&& result2.getKey().getType().equals("audio")) {
					type = "audio";
					try {
						sound.play(result2.getData());
					}
					catch(MultimediaException m) {
						System.out.println("Error: Multimedia exception");
					}
				}
				
				if(result3 != null && result3.getKey().compareTo(p3) == 0 
					&& result3.getKey().getType().equals("image")){
					type = "image";
					try {
						pic.show(result3.getData());
					}
					catch(MultimediaException m) {
						System.out.println("Error: Multimedia exception");
					}
				}
				
				//If the word is not in the UserDicitonary
				if (type.equals("not found")) {
					System.out.println("The word " + input2 + " is not found in the ordered dictionary.");
					//Find the successor and predecessor of the word if it were in the UserDictionary
					dict.put(nonNullRecord);
					Record succ = dict.successor(nonNullRecord.getKey());
					Record pred = dict.predecessor(nonNullRecord.getKey());
					String predOut = " ";
					String succOut = " ";
					if (pred != null) {
						predOut = pred.getKey().getWord();
					}
					if (succ != null) {
						succOut = succ.getKey().getWord();
					}
					System.out.println("Preceding word:  " + predOut);
					System.out.println("Following word:  " + succOut);
					dict.remove(nonNullRecord.getKey());
				}
			}

			//Removes from the ordered dictionary the record with key (word,type), or if no such record
			//exists, it prints "No record in the ordered dictionary has key (word,type)."
			else if (input.equals("delete")) {
				String word = s.nextToken();
				String type = s.nextToken();
	    			Pair p = new Pair(word, type);
	    			try {
	    				//Tries to remove Pair with inputted word and type
	    				dict.remove(p);
	    			}
	    			//Catches exception if element does not exist 
	    			catch(DictionaryException d) {
	    				System.out.println(d);
	    			}
			}
			
			//Inserts the record (word,type,data) into the ordered dictionary if there is
			//no record with key (word,type) already there; otherwise it prints
			//A record with the given key (word,type) is already in the ordered dictionary.
			else if (input.equals("put")) {
				String word = s.nextToken();
				String type = s.nextToken();
				String data = s.nextToken();
				try {
					while (s.hasMoreTokens()) {
						data += " " + s.nextToken();
					}
					Pair key = new Pair(word, type);
					Record record = new Record(key, data);
					dict.put(record);
				}
				catch(DictionaryException d) {
					System.out.println(d);
				}
			}
			
			//Here prefix is a string with one or more letters. The program prints all the
			//words (if any) in the ordered dictionary that start with prefix (if prefix is a word
		    //in the ordered dictionary, it must be printed also). If several records in the
			//dictionary store the same word w and w starts with prefix, then the word w will be printed
			//as many times as records contain it.
			else if (input.equals("list")) {
				
				String output = "";
				String prefix = s.nextToken();
				
				//Create Pair objects of all types with prefix as the word
				Pair pair1 = new Pair(prefix, "text");
				Pair pair2 = new Pair(prefix, "audio");
				Pair pair3 = new Pair(prefix, "image");
				
				Record rec1 = dict.get(pair1);
				Record rec2 = dict.get(pair2);
				Record rec3 = dict.get(pair3);
				Record rec = null;
				Record nonNullRec = new Record(pair1, "General data");
				
				//Check if any of the records of each type are in the UserDictionary
				if (rec1 != null && rec1.getData().startsWith(prefix)) {
					rec = rec1; 
				}
				else if (rec2 != null && rec2.getKey().getWord().equals(prefix)) {
					rec = rec2;
				}
				else if (rec3 != null && rec3.getKey().getWord().equals(prefix)) {
					rec = rec3;
				}
				boolean notInDict = false;
				//If the word is not contained in a node in the UserDicitonary
				if(rec == null) {
					//Insert a record using the word into the UserDictionary to
					//later find the successors that start with the prefix
					dict.put(nonNullRec);
					notInDict = true;
					rec = dict.get(pair1);
				}
				else {
					output += prefix;
				}
				rec = dict.successor(rec.getKey());
				//Adds all words that start with the prefix to the output string
				while (rec != null && rec.getKey().getWord().startsWith(prefix)) {
					output += rec.getKey().getWord() + ", ";
					rec = dict.successor(rec.getKey());
				}
				if (notInDict) {
					dict.remove(pair1);
				}
				if (output.length() > 1) {
					output = output.substring(0, output.length() - 2);
					System.out.println(output);
				}
				else {
					System.out.println("No words in the ordered dictionary start with prefix " + prefix);
				}
			}
			
			//Prints the record with smallest key in the ordered dictionary.
			else if (input.equals("smallest")) {
				Record smallest = dict.smallest();
				System.out.println(smallest.getKey().getWord() + ", " + smallest.getKey().getType() 
				+ ", " + smallest.getData() + ".");
			}
			
			//Prints the record with largest key in the ordered dictionary.
			else if (input.equals("largest")) {
				Record largest = dict.largest();
				System.out.println(largest.getKey().getWord() + ", " + largest.getKey().getType() 
				+ ", " + largest.getData() + ".");
			}
			
			//This command terminates the program.
			else if (input.equals("finish")) {
				running = false;
				System.exit(1);
			}
			//Asks the user for another command
			line = keyboard.read("Enter a command: ");
			line = line.toLowerCase();
		    s = new StringTokenizer(line);
	    }
	}
}
