//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P05 DRAGON TREASURE GAME VERSION 2
// Course: CS 300 Fall 2022
//
// Author: VARDAAN KAPOOR
// Email: VKAPOOR5@WISC.EDU
// Lecturer: (Mouna Kacem, Hobbes LeGault, or Jeff Nyhoff)-PROESSOR HOBBES
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources: NOTHING
//
//////////////////////////////////////////////////////////////////////////


import java.util.ArrayList;

/**
 * this class makes a character class which works as a base class for player,dragon classes
 */
public class Character {
  private Room currentRoom;// stores current room
  private String label;// label which describes the meaning of the class

  /**
   * parameterized constructor which constructs a character object
   *
   * @param currentRoom the room which character is in presently
   * @param label       the meaning of the object
   * @throws IllegalArgumentException throws when room doesn't exist
   */
  public Character(Room currentRoom, String label) throws IllegalArgumentException {
    if (currentRoom == null)// checking if room given as input is not null-otherwise throw exception
    {
      throw new IllegalArgumentException("room doesn't exist");
    } else {
      this.currentRoom = currentRoom;
      this.label = label;
    }
  }

  /**
   * this method gets us the currentroom
   *
   * @return currentRoom
   */
  public Room getCurrentRoom() {
    return currentRoom;// returns currentRoom
  }

  /**
   * accessor method to get the current label
   *
   * @return label
   */
  public String getLabel() {
    return label;// returns label
  }

  /**
   * accessor method to get the adjacent rooms arrayList
   *
   * @return arrayList
   */
  public ArrayList<Room> getAdjacentRooms() {
    return currentRoom.getAdjacentRooms();// returns arrayList

  }

  /**
   * mutator method to se the current room to some new room
   *
   * @param newRoom gets us the new room to update over previous room
   */

  public void setCurrentRoom(Room newRoom) {
    currentRoom = newRoom;// room changed
  }
}
