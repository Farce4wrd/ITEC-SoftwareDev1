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
 * This class is responsible for encapsulating the room id of other room objects and directions which would be used for user options.
 */
public class Exit {
	
	private String direction;
	private int roomId;
	
	public Exit(String direction, int roomId) {
		this.direction = direction;
		this.roomId = roomId;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

}
