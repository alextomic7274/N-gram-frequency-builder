package ie.atu.sw;

import java.util.Scanner;

public class Menu {
	private NgramGenerator ng = new NgramGenerator();
	private boolean keepRunning = true;
	private Scanner s;
	private String directory = null;
	private int ngSize = 0;
	private String outputFile = null;
		
	// Constructor that creates scanner s when menu object is created.
	public Menu() {
		s = new Scanner(System.in);
	}
	
	// Prints menu and displays user options available
	public static void showMenu() {
		
		// Display Menu Options
		System.out.println(ConsoleColour.WHITE);
		System.out.println("************************************************************");
		System.out.println("*      ATU - Dept. Computer Science & Applied Physics     *");
		System.out.println("*                                                          *");
		System.out.println("*                  N-Gram Frequency Builder                *");
		System.out.println("*                                                          *");
		System.out.println("************************************************************");
		System.out.println("(1) Specify Text File Directory");
		System.out.println("(2) Specify n-Gram Size");
		System.out.println("(3) Specify Output File");
		System.out.println("(4) Build n-Grams ");
		System.out.println("(5) Quit");
		// Ask user to select option
		System.out.print(ConsoleColour.BLACK_BOLD_BRIGHT);
		System.out.print("Select Option [1-5]>");
		System.out.println();
	}
	
	// Starts menu functionality to take user input and process it 
	public void start() throws Exception{
		
		while (keepRunning) {
			
			showMenu();
			int userChoice = Integer.parseInt(s.next()); // Blocks program
		
			if (userChoice == 1) {	// Gets/Sets directory files are in
				specifyDirectory();
			}	else if (userChoice == 2) {	// Specify N-gram size
				setSize();
			}	else if (userChoice == 3) {	// Specify Output File
				setOutputFile();
			}	else if (userChoice == 4) { // Build N-grams
				build();
				startProgress();
			}	else if (userChoice == 5) { // Quit
				System.out.println("INFO: Quitting...");
				break;
			}	else {
				System.out.println("ERROR: Invalid Input, select from listed options");
			}
		}
		
	}
	
	private void specifyDirectory() {
		System.out.println("Enter directory (e.g Home/Files) -->");
		this.directory = s.next();
	}
	
	private void setSize() {
		System.out.println("Enter N-gram size 1-5 -->");
		this.ngSize = Integer.parseInt(s.next());
		Parser p = new Parser(ngSize);


	}
	
	private void setOutputFile() {
		System.out.println("Enter output file name -->");
		this.outputFile = s.next();
	}
	
	private void build() throws Exception{
		//startProgress();
		Parser p = new Parser(ngSize);
		p.parseDirectory(directory);
		//ng.save(p.getTable(), outputFile);
		
	}
	
	private static void startProgress() throws Exception {
		System.out.print(ConsoleColour.YELLOW);	//Change the colour of the console text
		int size = 100;							//The size of the meter. 100 equates to 100%
		for (int i =0 ; i < size ; i++) {		//The loop equates to a sequence of processing steps
			printProgress(i + 1, size); 		//After each (some) steps, update the progress meter
			Thread.sleep(10);					//Slows things down so the animation is visible 
		}
	}
	
	public static void printProgress(int index, int total) {
		if (index > total) return;	//Out of range
        int size = 50; 				//Must be less than console width
	    char done = '█';			//Change to whatever you like.
	    char todo = '░';			//Change to whatever you like.
	    
	    //Compute basic metrics for the meter
        int complete = (100 * index) / total;
        int completeLen = size * complete / 100;
        
        /*
         * A StringBuilder should be used for string concatenation inside a 
         * loop. However, as the number of loop iterations is small, using
         * the "+" operator may be more efficient as the instructions can
         * be optimized by the compiler. Either way, the performance overhead
         * will be marginal.  
         */
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
        	sb.append((i < completeLen) ? done : todo);
        }
        
        /*
         * The line feed escape character "\r" returns the cursor to the 
         * start of the current line. Calling print(...) overwrites the
         * existing line and creates the illusion of an animation.
         */
        System.out.print("\r" + sb + "] " + complete + "%");
        
        //Once the meter reaches its max, move to a new line.
        if (done == total) System.out.println("\n");
    }
	
	
}