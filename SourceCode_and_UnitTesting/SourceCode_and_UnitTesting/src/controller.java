	import java.io.BufferedReader;
	import java.io.InputStreamReader;
	import java.nio.file.Files;
	import java.nio.file.Path;
	import java.util.ArrayList;
	import java.util.List;
	import java.util.Random;
	import java.util.Scanner;
	import org.json.JSONArray;
	import org.json.JSONObject;
	import java.util.Random;



	public class controller {
		
		//game round counter
		private int round = 1;

		//create scanner, get user input
		Scanner scanner = new Scanner(System.in);
		private List<Player> playerList = new ArrayList<>();
		private int playerchoice = 0;
		private int winnerPlayer=0;
		private String winnerplayername ="";
		private int choice1 = 0;
		private int[] maxRoundWinner={0,0,0,0,0,0};
		private int noOfLoser=0;
		private Property property;
		private Go go;
		private Chance chance;
		private FreeParking freeparking;
		private GoToJail goToJail;
		private IncomeTax incomeTax;
		private int jailMove;
		private Board board = new Board();
		private List<Square> squareList = board.getSquareList();
		private int postion;
		private String askResult;
		private String playerName;
		private String firstpos;
		private int checkgameend = 0;
		private static final String CHAR_POOL = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    	private static final int NAME_LENGTH = 5;
		Dice dice = new Dice();

		
		//method to start game
		public void startGame(int numOfPlayer, JSONObject saveData, String filename){

			board.loadBoard(filename);
			firstpos = squareList.get(0).getType();
			if(saveData == null) {
				int nameofplayer = 0;
				for (int i = 0; i < numOfPlayer; i++) {
				System.out.println("Random Name(1) or Enter Name(0)");
				while(true){
					try {
						//get user input and close scanner 
						nameofplayer = scanner.nextInt();
						scanner.nextLine(); 
						//if input is not between 2 to 6, tell user and keep asking input
						if( nameofplayer != 1 && nameofplayer !=0) {
							throw new ArithmeticException("Input should be number 1 or 0, try again...\n");
						}
						break;
					}
					catch (Exception e) {
						System.out.println("Input should be number 1 or 0, try again...\n");
						scanner.nextLine();			 
					 }
					}
				
					if(nameofplayer == 0){
					System.out.println("Enter the name for Player " + (i + 1) + ":");
					playerName = scanner.nextLine(); 
					playerList.add(i, new Player(i+1 , playerName, firstpos));
					}else{
					playerList.add(i, new Player(i+1 , generateRandomName(), firstpos));
					}
				}
			}

			//load player data from save
			else{

				
				for (int i = 1; i < numOfPlayer+1; i++) {

					// Creating example List and adding the data to it
					int money = Integer.valueOf(saveData.getJSONObject(String.valueOf(i)).getString("money"));
					int pos = Integer.valueOf(saveData.getJSONObject(String.valueOf(i)).getString("pos"));
					String playerName = saveData.getJSONObject(String.valueOf(i)).getString("name");
					String posname = saveData.getJSONObject(String.valueOf(i)).getString("posname"); // Retrieve posname
					JSONArray tempArray = saveData.getJSONObject(String.valueOf(i)).getJSONArray("property");

					ArrayList<String> tempList = new ArrayList<String>();
					if (tempArray != null) {
						for (int x=0;x<tempArray.length();x++){

								tempList.add(tempArray.getString(x));
						}
					}



					Player player = new Player(money, pos, i, tempList, playerName);
					player.setCurrentPos(posname); // Set the saved position name
        			playerList.add(player);
					
				}
					

				//load the current round
				setRound(Integer.valueOf(saveData.getString("round")));
			}






			//Round start, max 100 rounds
			while (getRound() <= 100) {
				
				
				
	//-----------------------------------------------------------save game code start----------------------------------------------------------------------
				boolean choicesave = true;
				while(choicesave) {




					try {

						//cache the game data first
						JSONObject gameData = new JSONObject();
						gameData.put("round", String.valueOf(getRound()));
						gameData.put("totalPayer", String.valueOf(numOfPlayer));
						gameData.put("filename", String.valueOf(filename));
						for(int k = 0; k < numOfPlayer; k++){

							JSONArray propertyArray = new JSONArray();


							String[] tempPropertyList = playerList.get(k).getPropertyArrayList().toArray(new String[0]);



							for (String property : tempPropertyList) {
								propertyArray.put(property);
							}


							JSONObject tempData = new JSONObject();
							tempData.put("pos", String.valueOf(playerList.get(k).getCurrentPosNo()));
							tempData.put("money", String.valueOf(playerList.get(k).getMoney()));
							tempData.put("property", propertyArray);
							tempData.put("name", String.valueOf(playerList.get(k).getName()));
							tempData.put("posname", String.valueOf(playerList.get(k).getCurrentPos()));
							gameData.put(String.valueOf(k+1), tempData);


						}





						//at the beginning of every round, system wil ask player whether to save the game.
						System.out.println("Want to save the game? (0: No, 1: save to save1, 2: save to save2, 3: save to save3)\n");
						int input = 0;
						while (true){
							
							try {

								//get user input and close scanner
								input = scanner.nextInt();
	
	
								//if input is not between 2 to 6, tell user and keep asking input
								if( input < 0 || input > 3 ) {
	
	
									throw new ArithmeticException("Input should number 0 to 3, try again...\n");
	
	
								}
	
							}
	
							//if input is not integer, keep looping
							catch (Exception e) {
	
								System.out.println("Input should number 0 to 3, try again...\n");
								scanner.nextLine();
								continue;
	
							}
	
							//input is valid, break loop
							break;
	
						}
						//BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));



						//String input =  buf.readLine();
						
						switch (input){

							//player don't want to save the game
							case 0:

								choicesave = false;

								break;


							//save the game to save1.json
							case 1:


								Files.write(Path.of("save/save1.json"), gameData.toString().getBytes());

								choicesave = false;
								break;

							//save the game to save2.json
							case 2:

								Files.write(Path.of("save/save2.json"), gameData.toString().getBytes());

								choicesave = false;
								break;


							//save the game to save3.json
							case 3:

								Files.write(Path.of("save/save3.json"), gameData.toString().getBytes());

								choicesave = false;
								break;

							default :
								//throw new Exception("Input should be number 0 to 3, try again...\n");

						}

					}

					//if input is not integer, keep looping
					catch (Exception e) {

						System.out.println("Error: "+ e + "\n");
						scanner.nextLine();
						continue;

					}

					//input is valid, break loop
					break;
				}
				
				//-----------------------------------------------------------save game code end----------------------------------------------------------------------


				for (Player player: playerList){

					checkEndGame(numOfPlayer);
					if(winnerPlayer != 0){
						break;
					}
					//Printing the game board 
					board.printBoard();
					System.out.println("ROUND: "  + getRound());
					System.out.println("");

					//Printing player information every round
					for (Player players: playerList){
						System.out.println( players.getName() + "    Money: "+players.getMoney()+"    Position: "+players.getCurrentPosNo()+"    PosName/state "+players.getCurrentPos()+"    OwnProperty: "+players.getProperty().toString());
					}
					System.out.println("");

					//Check if this player is lost already
					if(player.getMoney()<=0 || player.getGameOverStatus()==true){
						System.out.println(player.getName()+player.getPlayerNum()+" has lost already ****");
						System.out.println("Round goes to Next player");
						continue;
					}else if(player.getJailStop()>0){ //Check if this player is in the Jail
						jailMove= goToJail.escapeJail(player,player.getJailStop());
						if(jailMove==0){
							continue;
						}else{
							System.out.println("Escape successfully!");
							postion = (player.getCurrentPosNo()+jailMove);
							player.setCurrentPosNo(postion);
							usualGameFlow(player);

						}
					}

					//If they are not lost and in the Jail, they can play the game as usual in this else{}
					else{
						while (true) {
						System.out.println(player.getName() + " :");
						System.out.println("Enter 1 to roll the dice.....");
						System.out.println("Or Enter 0 to back home page.....");
						while(true){
						try {
							//get user input and close scanner
							playerchoice = scanner.nextInt();
							//if input is not 1, tell user and keep asking input
							if(playerchoice != 1 && playerchoice != 0) {
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
						if(playerchoice == 0){
							System.out.println("Are you sure to back home page? 0:exit ; 1:continue play\n");
							while(true){
								try {
									//get user input and close scanner
									choice1 = scanner.nextInt();
									//if input is not 1, tell user and keep asking input
									if(choice1 != 0 &&  choice1 != 1) {
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
							if (choice1 == 0){
							App.showMainMenu();
								break;
							}else{
								continue;
							}   
						}else{
							break;
						}
						}
						postion = 	(player.getCurrentPosNo()+dice.getResult());
						
						//Check if they pass Goal
						if(postion == 20){
							postion = postion%20 + 1;
						}
						else if(postion/20.0 > 1.0){
							postion = postion%20;
						}else {
							postion = postion%20;
						}
						player.setCurrentPosNo(postion);

						//If the dice is rolled
						if(playerchoice == 1){

							usualGameFlow(player);

							if(postion==20){
								squareList.get(0).setPlayerHere(player.getPlayerNum());
							}else{
								squareList.get(postion-1).setPlayerHere(player.getPlayerNum());
							}
						}
					}
					System.out.println(".......");
					System.out.println(".......");
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
				

				checkEndGame(numOfPlayer);
				if(winnerPlayer != 0){
					checkgameend = 1;
					System.out.println("Winner is Player "+winnerplayername+" !!!!");
					break;
				}

				setRound(getRound()+1);
			}
			if (checkgameend == 0){
			int max = playerList.get(0).getMoney();
			for(Player player:playerList){
				if(player.getMoney() > max){
					max = player.getMoney();
				}
			}
			int[] maxRoundWinner = new int[playerList.size()];
			for(Player player:playerList){
				if(player.getMoney() == max){
					maxRoundWinner[player.getPlayerNum()-1]=1;
				}
			}
			System.out.print("Winner is: ");
			boolean first = true;
			for (int i = 0; i < playerList.size(); i++) {
				if (maxRoundWinner[i] == 1) {
					if (!first) {
						System.out.print(", "); 
					}
					System.out.print(playerList.get(i).getName()); 
					first = false; 
				}
			}
			System.out.println(); 
		}
		System.out.println("");
		System.exit(0);
		}
		
		public void usualGameFlow(Player player){
			if(postion == 0){
				System.out.println("Back to Go");
			}
			else if(!squareList.get(postion-1).getType().equals("Property")){
				System.out.print("Enter: ");
				System.out.print(squareList.get(postion-1).getType());
				System.out.println(" ("+postion+")");
				player.setCurrentPos(squareList.get(postion-1).getType());
				switch (squareList.get(postion-1).getType()){
					case "Go":
						go = (Go)squareList.get(postion-1);
						go.Trigger_Go(player);
						break;
					case "Chance":
						chance = (Chance)squareList.get(postion-1);
						chance.Trigger_Chance(player);
						break;
					case "FreeParking":
						freeparking = (FreeParking)squareList.get(postion-1);
						freeparking.Trigger_freeparking();
						break;
					case "GoToJail":
						goToJail = (GoToJail) squareList.get(postion-1);
						goToJail.Trigger_GoJail(player);
						break;
					case "Income Tax":
						incomeTax = (IncomeTax) squareList.get(postion-1);
						incomeTax.Trigger_incomeTax(player);
						break;


				}
			}else{
				property =(Property) squareList.get(postion-1);
				player.setCurrentPos(property.getName());
				askResult= property.defaultAsking(player,player.getPlayerNum());
				if(askResult == null){
					System.out.println("You don't want to buy "+property.getName());
				}

				else if(askResult.equals("pay")){
					System.out.println("You need to pay $"+property.getRent());

					if(player.getMoney() < property.getRent()){  //Check if player have enought money to pay, if not then GG, if yes then continue
						player.setMoney(0);
						player.setGameOverStatus(true);
						System.out.println("You don't have enough money to pay -----> Game Over!!");
					}else{
						player.setMoney(player.getMoney()-property.getRent());
						playerList.get(property.getOwner()-1).setMoney(playerList.get(property.getOwner()-1).getMoney()+property.getRent());
						System.out.println("Paid successfully!");
					}


				}
				else if(askResult.equals("money")){
					System.out.println("You dont have enough money to buy...... Skip");
				}

				else{
						player.setMoney(player.getMoney()-property.getPrice());
						System.out.println("Bought "+property.getName()+" successfully!");
						player.addProperty(property.getName());
				}
			}

		}

		public void checkEndGame(int numOfPlayer){
			for (Player count_player: playerList){
				if(count_player.getGameOverStatus()==true){
					noOfLoser++;
				}
			}
			if(noOfLoser == (numOfPlayer-1)){
				for(Player count_player1: playerList){
					if(count_player1.getGameOverStatus()==false){
						winnerplayername = count_player1.getName();
					}
				}

			}
			noOfLoser=0;
		}

		
		public int getRound() {
			return round;
		}


		public void setRound(int round) {
			this.round = round;
		}
		
		public static String generateRandomName() {
        Random random = new Random();
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < NAME_LENGTH; i++) {
            int index = random.nextInt(CHAR_POOL.length());
            name.append(CHAR_POOL.charAt(index));
        }
        return name.toString();
   	 	}
	}

