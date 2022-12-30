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
///////////////////////////////////////////////////////////////////////////////
import java.util.ArrayList;

// thing i learnt-keep interface in src folder where our implementing class exists

/**
 * this class makes a player which plays the game
 */
public class Player extends Character implements Moveable {
  private boolean hasKey;// checks if player has a key

  /**
   * constructor to make a player object
   *
   * @param currentRoom gives the player its room
   * @throws IllegalArgumentException if room given is not a start room
   */

  public Player(Room currentRoom) throws IllegalArgumentException {
    super(currentRoom, "PLAYER");// calls parameterized constructor of super class in 1st line
    if (!(currentRoom instanceof StartRoom)) {
      throw new IllegalArgumentException("not a start room");// room given is not instance of start
      // room class
    }
    hasKey = false;

  }

  /**
   * checks if player contains the key
   *
   * @return the boolean variable hasKey
   */
  public boolean hasKey() {
    return hasKey;// returns boolean value of hasKey
  }

  /**
   * gives the player the key and prints a message
   */
  public void obtainKey() {
    hasKey = true;
    System.out.println("KEY OBTAINED");// prints statement after giving key
  }

  /**
   * mutator method which changes the current room
   *
   * @param destination the new room
   * @return true if room is changed
   */
  public boolean changeRoom(Room destination) {
    // checks if move s valid
    if (canMoveTo(destination)) {
      setCurrentRoom(destination);
      return true;
    }
    return false;// otherwise returns false
  }

  /**
   * checks if room or destination is valid and player can move or not
   *
   * @param destination new room
   * @return true if move is valid -false otherwise
   */
  public boolean canMoveTo(Room destination) {
    // checks if move is valid
    if (getCurrentRoom().getAdjacentRooms().contains(destination)) {
      return true;
    } else {
      return false;// else give false
    }
  }

  /**
   * teleports the player to new room
   *
   * @return true if teleportation is successfully done
   */
  // important-used instanceof method
  // ask this method
  public boolean teleport() {
    // if room given is an object of portalroom then it is a portal room-if it is an object of Room
    // class
    // then condition will result in false
    // change room if possible
    if (getCurrentRoom() instanceof PortalRoom) {
      changeRoom(((PortalRoom) getCurrentRoom()).getTeleportLocation());
      return true;
    }
    return false;
  }

  /**
   * checks if one of the adjacent rooms is a portal room or not
   *
   * @return true if it is
   */
  public boolean isPortalNearby() {
    ArrayList<Room> check1 = getAdjacentRooms();// get arraylist
    // check if any element in arraylist is instance of portal room
    for (int i = 0; i < check1.size(); i++) {
      if (!(check1.get(i) instanceof PortalRoom)) {

      } else {
        return true;
      }

    }
    return false;
  }

  /**
   * checks if one of the adjacent rooms has a dragon
   *
   * @param d gets the dragon object
   * @return true if dragon is nearby
   */
  public boolean isDragonNearby(Dragon d) {
    // condition checks if any adjacent room contains the room of the dragon
    if (getCurrentRoom().getAdjacentRooms().contains(d.getCurrentRoom())) {
      return true;
    }
    return false;
  }

  /**
   * checks if treasure is in nearby rooms
   *
   * @return true if it is there
   */
  public boolean isTreasureNearby() {
    // checks if any adjacent room has a treasure in it
    ArrayList<Room> check1 = getAdjacentRooms();
    for (int i = 0; i < check1.size(); i++) {
      if (!(check1.get(i) instanceof TreasureRoom)) {

      } else {
        return true;
      }

    }
    return false;
  }
}
