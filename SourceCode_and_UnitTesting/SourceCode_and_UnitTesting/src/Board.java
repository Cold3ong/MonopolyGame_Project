
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;

public class Board {
	Scanner scanner = new Scanner(System.in);
	
	private int sizeOfBoard = 20;
	List<Square> squareList = new ArrayList<>();

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLUE = "\033[0;34m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_lightBlue = "\033[0;36m";

	public void printBoard() {
		// Iterate through the squareList and print each square's details
		System.out.println("Here is the game board!");
		for (int i = 0; i < squareList.size(); i++) {
			Square square = squareList.get(i);
			StringBuilder output = new StringBuilder();
	
			// Print the index (1-based)
			output.append(i + 1).append(". ");
	
			// Check the type of the square and format the output accordingly
			if (square instanceof Property) {
				Property property = (Property) square;
				output.append(property.getName())
					  .append(" Cost: ").append(property.getPrice())
					  .append(" Rent: ").append(property.getRent());
			} else if (square instanceof Go) {
				output.append("Go");
			} else if (square instanceof Chance) {
				output.append("Chance");
			} else if (square instanceof IncomeTax) {
				output.append("Income Tax");
			} else if (square instanceof FreeParking) {
				output.append("Free Parking");
			} else if (square instanceof GoToJail) {
				output.append("Go To Jail");
			} else if (square instanceof InJailOrJustVisiting) {
				output.append("In Jail or Just Visiting");
			}
	
			// Print the formatted output for the square
			System.out.println(output.toString());
		}
	}
	//method to create Board
	public void createBoard() {
		
		//create squares



		
		//create Go at position 1			
		squareList.add(0, new Go("Go", 1));
		//create Property Central at position 2
		squareList.add(1, new Property("Central", 2, 800, 90,"light blue"));
		squareList.add(2, new Property("Wan Chai", 3, 700, 65,"light blue"));
		squareList.add(3, new IncomeTax("Income Tax", 4));
		squareList.add(4, new Property("Stanley", 5, 600, 60,"light blue"));
		squareList.add(5, new InJailOrJustVisiting("InJailOrJustVisiting", 6));
		squareList.add(6, new Property("Shek O", 7, 400, 60,"red"));
		squareList.add(7, new Property("Mong Kok", 8, 500, 40,"red"));
		squareList.add(8, new Chance("Chance", 9));
		squareList.add(9, new Property("Tsing Yi", 10, 400, 15,"red"));
		squareList.add(10, new FreeParking("FreeParking", 11));
		squareList.add(11, new Property("Shatin", 12, 700, 75,"blue"));
		squareList.add(12, new Chance("Chance", 13));
		squareList.add(13, new Property("Tuen Mun", 14, 400, 20,"blue"));
		squareList.add(14, new Property("Tai Po", 15, 500, 25,"blue"));
		squareList.add(15, new GoToJail("GoToJail", 16));
		squareList.add(16, new Property("Sai Kung", 17, 400, 10,"yellow"));
		squareList.add(17, new Property("Yuen Long", 18, 400, 25,"yellow"));
		squareList.add(18, new Chance("Chance", 9));
		squareList.add(19, new Property("Tai O", 20, 600, 25,"yellow"));
		//saveBoard("save/board.json");
		
		//test

		

	
	}

	public void customizeBoard() {
		squareList.clear(); // Clear any existing squares
	
		System.out.println("Enter the number of squares for the board (20):");
		int numSquares = 20; // Fixed to 20 as per your requirement
	
		// Loop for each square
		for (int i = 1; i <= numSquares; i++) {
			System.out.println("Enter details for Square " + i + ":");
			System.out.print("Square Type (Property, Go, Chance, Income Tax, InJailOrJustVisiting, FreeParking, GoToJail): ");
			String squareType = scanner.nextLine().trim().toLowerCase(); // Normalize input
	
			switch (squareType) {
				case "property": {
					System.out.print("Property Name: ");
					String propertyName = scanner.nextLine();
	
					int cost = 0;
					while (true) {
						System.out.print("Cost: ");
						try {
							cost = scanner.nextInt();
							scanner.nextLine(); // Consume the newline
							break;
						} catch (Exception e) {
							System.out.println("Invalid input. Cost must be an integer.");
							scanner.nextLine(); // Clear invalid input
						}
					}
	
					int rent = 0;
					while (true) {
						System.out.print("Rent: ");
						try {
							rent = scanner.nextInt();
							scanner.nextLine(); // Consume the newline
							break;
						} catch (Exception e) {
							System.out.println("Invalid input. Rent must be an integer.");
							scanner.nextLine(); // Clear invalid input
						}
					}
	
					System.out.print("Color: ");
					String color = scanner.nextLine();
	
					// Add the property to the square list
					squareList.add(new Property(propertyName, i, cost, rent, color));
					break;
				}
				case "go":
					squareList.add(new Go("Go", i));
					break;
				case "chance":
					squareList.add(new Chance("Chance", i));
					break;
				case "income tax": // Change to lowercase
					squareList.add(new IncomeTax("Income Tax", i));
					break;
				case "injailorjustvisiting": // Change to lowercase
					squareList.add(new InJailOrJustVisiting("InJailorJustVisiting", i));
					break;
				case "freeparking": // Change to lowercase
					squareList.add(new FreeParking("FreeParking", i));
					break;
				case "gotojail": // Change to lowercase
					squareList.add(new GoToJail("GoToJail", i));
					break;
				default:
					System.out.println("Invalid square type. Skipping...");
					i--; // Repeat this square creation
			}
		}
		int boardfile = 0;
		int boardchoice = 0;
		System.out.println("Do you want to save the board? 1:save, 0:no");
		while(true){
			try {
			boardchoice = scanner.nextInt();
			if(boardchoice != 1 && boardchoice != 0) {
				throw new Exception("Input should be 1 or 0,try again...\n");
			}
			break;
			}
			catch (Exception e) {
				System.out.println("Input should be 1 or 0,try again...\n");
				scanner.nextLine();
			}
		}
		if (boardchoice == 1) {
			System.out.println("Want to save to? (4, 5, 6)");
		while(true){
			try {
			boardfile = scanner.nextInt();
			if(boardfile != 4 && boardfile != 5 && boardfile !=6) {
				throw new Exception("Input should be 4 or 5 or 6,try again...\n");
			}
			break;
		}
			catch (Exception e) {
				System.out.println("Input should be 4 or 5 or 6,try again...\n");
				scanner.nextLine();
			}
		}
			String filename = "save/save" + boardfile + ".json";
			saveBoard(filename);
			System.out.println("Board customized and saved to " + filename);
		}
	}
	
	public List<Square> getSquareList() {
		return squareList;
	}

	// Save the board to a file
	public void saveBoard(String filename) {
		JSONObject boardData = new JSONObject();
		JSONArray squaresArray = new JSONArray();

		for (Square square : squareList) {
			JSONObject squareData = new JSONObject();
			squareData.put("type", square.getType());
			squareData.put("position", square.getPosition());

			if (square instanceof Property) {
				Property property = (Property) square;
				squareData.put("name", property.getName());
				squareData.put("price", property.getPrice());
				squareData.put("rent", property.getRent());
				squareData.put("color", property.getColor());
				squareData.put("owner", property.getOwner());
			} else if (square instanceof Go) {
				squareData.put("name", "Go");
			} else if (square instanceof Chance) {
				squareData.put("name", "Chance");
			} else if (square instanceof IncomeTax) {
				squareData.put("name", "Income Tax");
			} else if (square instanceof FreeParking) {
				squareData.put("name", "FreeParking");
			} else if (square instanceof GoToJail) {
				squareData.put("name", "GotoJail");
			} else if (square instanceof InJailOrJustVisiting) {
				squareData.put("name", "InJailOrJustVisiting");
			}

			squaresArray.put(squareData);
		}

		boardData.put("squares", squaresArray);

		// Write the JSON object to a file
		try (FileWriter file = new FileWriter(filename)) {
			file.write(boardData.toString(4)); // Pretty print with an indent of 4 spaces
			System.out.println("Board saved successfully to " + filename);
		} catch (IOException e) {
			System.out.println("Error saving board: " + e.getMessage());
		}
	}
	public void loadBoard(String filename) {
		try {
			String jsonString = turnFileToString(filename); // Use your method to read the file
			JSONObject boardData = new JSONObject(jsonString);
			JSONArray squaresArray = boardData.getJSONArray("squares");
	
			squareList.clear(); // Clear the current squares
			for (int i = 0; i < squaresArray.length(); i++) {
				JSONObject squareData = squaresArray.getJSONObject(i);
				String type = squareData.getString("type");
				int position = squareData.getInt("position");
	
				switch (type) {
					case "Property":
						String name = squareData.getString("name");
						int price = squareData.getInt("price");
						int rent = squareData.getInt("rent");
						String color = squareData.getString("color");
						squareList.add(new Property(name, position, price, rent, color));
						break;
					case "Go":
						squareList.add(new Go("Go", position));
						break;
					case "Chance":
						squareList.add(new Chance("Chance", position));
						break;
					case "Income Tax":
						squareList.add(new IncomeTax("Income Tax", position));
						break;
					case "FreeParking":
						squareList.add(new FreeParking("FreeParking", position));
						break;
					case "GoToJail":
						squareList.add(new GoToJail("GoToJail", position));
						break;
					case "InJailOrJustVisiting":
						squareList.add(new InJailOrJustVisiting("InJailOrJustVisiting", position));
						break;
					default:
						System.out.println("Unknown square type: " + type);
				}
			}
			System.out.println("Board loaded successfully from " + filename);
		} catch (Exception e) {
			System.out.println("Error loading board: " + e.getMessage());
		}
	}
	private String turnFileToString(String fileName) {
		StringBuilder sb = new StringBuilder();
		try {
			InputStream is = new FileInputStream(fileName);
			BufferedReader buf = new BufferedReader(new InputStreamReader(is));
			String line;
			while ((line = buf.readLine()) != null) {
				sb.append(line).append("\n");
			}
		} catch (Exception e) {
			System.out.println("Error reading file: " + e.getMessage());
		}
		return sb.toString();
	}
	public void editPosition(int position) {
		String changeType = "";
		System.out.println("Editing position " + position + ":");
		Square currentSquare = squareList.get(position - 1);
		// System.out.println("Current details: " + currentSquare);
	
		while (true) {
			try {
				System.out.print("Do you want to change the type of this square? Please enter 'yes' or 'no': ");
				changeType = scanner.nextLine().trim().toLowerCase(); // Convert input to lowercase for easy comparison

				// Check if the input is valid
				if (changeType.equals("yes") || changeType.equals("no")) {
					System.out.println("You entered: " + changeType); // Show the valid input
					break; // Exit the loop if input is valid
				} else {
					throw new IllegalArgumentException("Invalid input! Please enter 'yes' or 'no'.");
				}
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage()); // Print the error message if input is invalid
			}
		}


		if (changeType.equals("yes")) {
			System.out.print("Enter new square type (Property, Go, Chance, IncomeTax, FreeParking, GoToJail, InJailOrJustVisiting): ");
			String newType = scanner.nextLine().trim().toLowerCase();
	
			Square newSquare = null;
	
			switch (newType) {
				case "property":
					System.out.print("Enter Property Name: ");
					String name = scanner.nextLine();
	
					int cost = 0;
					while (true) {
						System.out.print("Enter Cost: ");
						try {
							cost = scanner.nextInt();
							break;
						} catch (Exception e) {
							System.out.println("Invalid input. Cost must be an integer.");
							scanner.nextLine();
						}
					}
	
					int rent = 0;
					while (true) {
						System.out.print("Enter Rent: ");
						try {
							rent = scanner.nextInt();
							break;
						} catch (Exception e) {
							System.out.println("Invalid input. Rent must be an integer.");
							scanner.nextLine();
						}
					}
	
					scanner.nextLine(); // Consume newline
					System.out.print("Enter Color: ");
					String color = scanner.nextLine();
	
					newSquare = new Property(name, position, cost, rent, color);
					break;
	
				case "go":
					newSquare = new Go("Go", position);
					break;
	
				case "chance":
					newSquare = new Chance("Chance", position);
					break;
	
				case "incometax":
					newSquare = new IncomeTax("Income Tax", position);
					break;
	
				case "freeparking":
					newSquare = new FreeParking("Free Parking", position);
					break;
	
				case "gotojail":
					newSquare = new GoToJail("Go To Jail", position);
					break;
	
				case "injailorjustvisiting":
					newSquare = new InJailOrJustVisiting("In Jail or Just Visiting", position);
					break;
	
				default:
					System.out.println("Invalid type. No changes made.");
					return;
			}
	
			// Replace the old square with the new one
			squareList.set(position - 1, newSquare);
			System.out.println("Square updated successfully!");
	
		} else {
			System.out.println("No changes made to the square type.");
		}
	}
	
}