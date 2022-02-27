import java.util.ArrayList;

/**
 * 
 */

/**
 * @author Peter
 * @version 1.0
 * Course: ITEC 3860 Spring 2022
 * Written: February 8, 2022 12:50am.
 * 
 * This class is responsible for holding the room objects 
 *
 */
public class GameRoom {
	
	private int id;
	private String name;
	private String description;
	private ArrayList<Exit> exit;
	private boolean hasVisited;
	
	
	public GameRoom(int id,String name, String description, ArrayList exit) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.exit = exit;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}
	
	public void setHasVisited() {
		this.hasVisited = true;
		
	}
	
	public boolean getHasVisited() {
		return this.hasVisited;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList getExit() {
		return exit;
	}


	public void setExit(ArrayList exit) {
		this.exit = exit;
	}
	
	public Exit getAnExit(String direction) {
		Exit result = null;
		for(Exit ex: exit ) {
			if(ex.getDirection().equals(direction)) {
				result = ex;
			}
		}
		return result;
		
		
	}
	
	//method for getting to another room
	public void goToRoom(String room) {
		for(Exit ex: exit) {
			if(ex.getDirection().equals(room)) {
				hasVisited = true;
				System.out.print(hasVisited);
			}
		}
		
	}
	
	/*
	 * Should the classes be in a hash map with an id
	 * userInput: west
	 * 


 	*/
	@Override
	public String toString() {
		String visit="";
		if(this.getHasVisited()== true) {
			visit ="Has visited";
		}else {
			visit ="Has not visited";
		}
		System.out.println(this.getName()+": "+ visit);
		return this.description;
		//return "GameRoom [description=" + description + "You can go " + result + "]";
	}
	
	
	

}
