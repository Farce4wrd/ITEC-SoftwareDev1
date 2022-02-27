import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * 
 */

/**
 * @author Peter
 * @version 1.0
 * Course: ITEC 3860 Spring 2022
 * Written: February 8, 2022 12:50am.
 * 
 * This main class is responsible for starting the game, loading the game files, and providing access for user input while validating it all.
 *
 */
public class MiniGame {
	
	public static Set<String> userOptions = new HashSet<String>() ; //used for holding dynamic user directions/choices when user chooses a room
	public static int currRoomId;  									//used as pointer for room direction
	public static boolean playerIsRight;							//used to verify if player actually chose an actual direction within userOptions


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ArrayList<GameRoom> gameFiles = createGameFiles();
		boolean isRunning = true;
		boolean firstLoad = true;
		Scanner userInput = new Scanner(System.in);
		
		//Game loading begins
		while(isRunning) {
			currRoomId  = 1;  //To always start at the beginning
			//Purpose: To load once only when the game is ran for the first time.
			if(firstLoad == true) {
				System.out.println("Welcome to my adventure game. Find the way out or just explore the rooms");
				System.out.println("You can navigate the room by typing out the direction (e.g North, West, etc.)");
				System.out.println("Type start to continue, else type stop: ");
				firstLoad= false;
			}
			String player = userInput.next();
			

			if(player.equals("stop")) {
				System.out.println("Are you sure you want to exit? Type 'yes' to continue");
				if(userInput.next().equals("yes")) {
					isRunning = false; //game ends.
				}else {
					System.out.println("Type start to continue: ");
				}
			}else if(player.equalsIgnoreCase("start")){
				
				//loop to look through game files for that id and print it
				while(currRoomId != 7) {
					for(GameRoom room: gameFiles) {
						if(room.getId() == currRoomId) {
							System.out.println(room);
							room.setHasVisited();
							ArrayList<Exit> exit= room.getExit();
							for(Exit ex: exit) {
								userOptions.add(ex.getDirection());
							}

							//HotFix solution to make sure game ends when user reaches perfect destination.
							if(currRoomId == 7) {
								System.out.println("You've reached the end! Congrats!");
								System.exit(0);
							}
							
							//Tells the user what direction they can go in
							System.out.println("You can go "+ userOptions.toString());
							System.out.println("Where do you want to go? :");
							String playerChoice = userInput.next();
							
							//logic processing their answer
							Set<String> oldUserOptions = new HashSet<>(); //used to remove old options
							for(String options: userOptions) {
								if(playerChoice.equalsIgnoreCase(options)) { //West == West
									playerIsRight= true;
									Exit temp = room.getAnExit(options); //stores the Exit object that has direction and id
									currRoomId = temp.getRoomId();
									oldUserOptions.addAll(userOptions);
//									
//											
//								
								}
							}
							if(playerIsRight == false) {
								System.out.println("Error. Please try again.");
							}
							userOptions.removeAll(oldUserOptions);  //here we update the user options (e.g North, West becoming South, East)
							oldUserOptions.clear(); 
							

						}

					}
				}
				


			}else {
				System.out.println("Wrong Input. Please try again.");
			}
	




		}



	}

	//Creating rooms out of the minigame files and returns it as an arrayList
	public static ArrayList<GameRoom> createGameFiles() {
		ArrayList<GameRoom> gameRooms = new ArrayList<GameRoom>();

		try {
			File gameFiles = new File("minigame.txt");
			Scanner fileReader = new Scanner(gameFiles);
			ArrayList<String> navigation = new ArrayList<String>();

			while(fileReader.hasNextLine()) {
				ArrayList<Exit> exitList = new ArrayList<Exit>();
				//String tempLine = fileReader.nextLine();
				String temp = fileReader.next();
				int id = Integer.parseInt(temp);
				fileReader.nextLine(); //to make it skip line
				//int id = Integer.parseInt(fileReader.next());
				String name = fileReader.nextLine();
				//This is to get the room ids separate from the room directions
				Scanner directionsWithId = new Scanner(fileReader.nextLine());
				while(directionsWithId.hasNext()) {
					String direction = directionsWithId.next();
					int exitId = directionsWithId.nextInt();
					Exit exit = new Exit(direction, exitId);
					exitList.add(exit);
				}
				String description = fileReader.nextLine();
				GameRoom room = new GameRoom(id, name, description, exitList);
				gameRooms.add(room);
			}

	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Cant find game files.");
		}


		return gameRooms;

	}



}
