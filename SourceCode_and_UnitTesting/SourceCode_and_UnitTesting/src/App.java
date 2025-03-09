import java.io.*;
import java.util.Scanner;
import org.json.JSONObject;


public class App {
	
    private static Board board;
    public static String playerinput;



    public static void main(String[] args){
        board = new Board(); 
        showMainMenu();
    }

    public static void showMainMenu() {
        JSONObject saveData = null;
    	
        // Initialize and utilize the system

        boolean started = true;
        String savename = "";
        
        //create game controller
    	controller gc = new controller();

        // board.loadBoard("save/board.json");
        // board.editPosition(3);
        // board.printBoard();


        //keep looping if user input invalid input
        while(started) {
        
            System.out.println("");
            System.out.println("Select the commands below (Type the number): ");
            System.out.println("1.New game");
            System.out.println("2.Load game");
            System.out.println("3.customizeBoard");
            System.out.println("0.Exit Monopoly");
            System.out.println("");

           
            //create scanner, get user input 
            Scanner scanner = new Scanner(System.in);
            
            playerinput = scanner.nextLine();
            
      
            
            //functions
            switch (playerinput){
                
            	//"0.Exit Monopoly"
                case "0":
 
                	System.exit(0);
                    
                    break;

                    
                //"1.New game"
                case "1":
                   
                	     
                	
                	int numOfPlayer = 0;
              
                	
                	boolean Errorofcase1 = true;
                	//keep looping if user input invalid number
                    while(Errorofcase1) {
                    	     	
                    	
                    	
                    	System.out.println("Enter the number of players (2 to 6): \n");
                    	
                    	
                    	
                    	try {
                    		//get user input and close scanner 
                    		numOfPlayer = scanner.nextInt();
                    		//if input is not between 2 to 6, tell user and keep asking input
                    		if( numOfPlayer < 2 || numOfPlayer > 6) {
                    			throw new ArithmeticException("Input should be number 2 to 6, try again...\n");
                    		}
                    	}
                    	//if input is not integer, keep looping
                        catch (Exception e) {
                       	System.out.println("Input should be number 2 to 6, try again...\n");
                       	scanner.nextLine();
                       	continue;
                            
                        }
                    	
                    System.out.println("Would you like to use default or load an existing one? (0: default, 1: Load)");
                    int choice = 0;
                    while(true){
						try {
							//get user input and close scanner
                            choice = scanner.nextInt();
							//if input is not 1, tell user and keep asking input
							if(choice != 1 && choice != 0) {
								throw new Exception("Input should be 1 or 0,try again...\n");
							}
							break;
						}
						//if input is not integer, keep looping
						catch (Exception e) {
							System.out.println("Input should be 1 or 0,try again...\n");
							scanner.nextLine();
						}
					}
			        int boardsave = 0;
   		 	        if (choice == 0) {  
                        //board.createBoard();
                        savename = "save/board.json";
    		        } else if (choice == 1) {
       		 	        System.out.print("Enter the Number to load the board:(4,5,6) ");
                    while (true) {
				    try{
        		        boardsave = scanner.nextInt();
				        if( boardsave < 4 || boardsave > 6) {
					    throw new ArithmeticException("Input should be number 4 to 6, try again...\n");
				        }
                        else{
                            break;
                        }
				    }
				    catch (Exception e) {
					    System.out.println("Input should be number 4 to 6, try again...\n");
					    scanner.nextLine();
				    }
                    }
                    savename = "save/save" + boardsave + ".json";
    		        } else {
        		        System.out.println("Invalid choice. Exiting.");
        	        return;
    		        }
                    	//input is valid, break loop
                    break;
                    }  
                    gc.startGame(numOfPlayer, saveData, savename);
                    started = false;
                    break;

                    
                    
                //"2.Load game"
                case "2":


                    int saveNum = 0;
                    boolean ErrorofCase2 = true;
                    //keep looping if user input invalid number
                    //only have 3 save space, ask user which save they want to load
                    System.out.println("Enter the save number(1, 2, 3): \n");
                    while(ErrorofCase2) {

                        try {

                            //get user input and close scanner
                            saveNum = scanner.nextInt();


                            //if input is not between 2 to 6, tell user and keep asking input
                            if( saveNum < 1 || saveNum > 3 ) {


                                throw new ArithmeticException("Input should number 1 to 3, try again...\n");


                            }

                        }

                        //if input is not integer, keep looping
                        catch (Exception e) {

                            System.out.println("Input should number 1 to 3, try again...\n");
                            scanner.nextLine();
                            continue;

                        }

                        //input is valid, break loop
                        break;

                    }

                    //read the json string from txt and turn into json object

                    try {

                        //load save file
                        saveData = new JSONObject( turnFileToString("save/save" + saveNum + ".json") );


                    } catch (Exception e) {
                        System.out.println("Error:" + e);
                        e.printStackTrace();
                    }
                    
                    //pass the json to load game
                    gc.startGame(Integer.parseInt(saveData.getString("totalPayer")), saveData, saveData.getString("filename"));
                  
                    break;

                case "3": 
                while (true) {
                    System.out.println("Choose an option:");
                    System.out.println("1. Edit Existing Board");
                    System.out.println("2. Create New Board");
                    System.out.println("3. Exit");
                    System.out.print("Enter your choice (1/2/3): ");
        
                    int choice;
                    while (true) {
                        try{
                            choice = scanner.nextInt();
                            if( choice < 1 || choice > 3) {
                            throw new ArithmeticException("Input should be number 1 to 3, try again...\n");
                            }
                            else{
                                break;
                            }
                        }
                        catch (Exception e) {
                            System.out.println("Input should be number 1 to 3, try again...\n");
                            scanner.nextLine();
                        }
                    }
        
                    switch (choice) {
                        case 1:
                        int boardsave = 0;
                        int boardposition = 0;
                        System.out.print("Enter the Number You want to save:(4,5,6) ");
                        while (true) {
                        try{
                            boardsave = scanner.nextInt();
                            if( boardsave < 4 || boardsave > 6) {
                            throw new ArithmeticException("Input should be number 4 to 6, try again...\n");
                            }
                            else{
                                break;
                            }
                        }
                        catch (Exception e) {
                            System.out.println("Input should be number 4 to 6, try again...\n");
                            scanner.nextLine();
                        }
                        }
                        savename = "save/save" + boardsave + ".json";
                        board.loadBoard("save/board.json");

                        board.printBoard();
                        Boolean abc = true;
                        while(abc){
                            int change1 = 0;
                            System.out.println("Change board?: change(1) , no(0)");
                            while (true) {
                                try{
                                    change1 = scanner.nextInt();
                                    if( change1 != 0 && change1 != 1) {
                                    throw new ArithmeticException("Input should be number 1 or 0, try again...\n");
                                    }
                                    else{
                                        break;
                                    }
                                }
                                catch (Exception e) {
                                    System.out.println("Input should be number 1 to 0, try again...\n");
                                    scanner.nextLine();
                                }
                            }
                            if (change1==1){
                                System.err.println("Enter the Number of position of the board:(1 - 20)");
                                while (true) {
                                    try{
                                        boardposition = scanner.nextInt();
                                        if( boardposition < 1 || boardposition > 20) {
                                    throw new ArithmeticException("Input should be number 1 to 20, try again...\n");
                                    }
                                else{
                                    break;
                                }
                                }
                            catch (Exception e) {
                                System.out.println("Input should be number 1 to 20, try again...\n");
                                scanner.nextLine();
                                }
                            }
                                board.editPosition(boardposition);
                                }
                                else{
                                    abc = false;
                            }
                        
                        }
                        board.saveBoard(savename);
                        System.out.println("The Board has been saved...\n");
                        board.printBoard();


                        break;
                            //input is valid, break loop
                        case 2:
                            board.customizeBoard();
                            break;
                        case 3:
                            System.out.println("Exiting...");
                            App.showMainMenu();
                            return;
                        default:
                            System.out.println("Invalid choice. Please select 1, 2, or 3.");
                    }
                    
                    break;
                }

                break;
                //validation
                default:
                	
                	System.out.println("Wrong input, try again...\n");	
                	

            }
            
        }





    }

    //change txt to string object
    private static String turnFileToString(String fileName){
        String str = "";
        try {
            InputStream is = new FileInputStream(fileName);
            BufferedReader buf = new BufferedReader(new InputStreamReader(is));

            String line = buf.readLine();
            StringBuilder sb = new StringBuilder();

            while (line != null) {
                sb.append(line).append("\n");
                line = buf.readLine();
            }

            String fileAsString = sb.toString();

            return fileAsString;

        } catch(Exception e){
            System.out.println("Error: " + e);
        }

        return str;
    }

}

