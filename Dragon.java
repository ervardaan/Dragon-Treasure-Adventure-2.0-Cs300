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
import processing.core.PApplet;
import processing.core.PImage;
import java.util.Random;

/**
 * this class makes a dragon
 */
public class Dragon extends Character implements Moveable {
  private Random randGen; // random num generator used for moving
  private static final String DRAGON_WARNING = "You hear a fire breathing nearby!\n";
  private static final String DRAGON_ENCOUNTER = "Oh no! You ran into the fire breathing dragon!\n";

  /**
   * constructor to make a dragon object
   *
   * @param currentRoom gets current room of dragon
   */
  public Dragon(Room currentRoom) {
    super(currentRoom, "DRAGON");// calling parameterized constructor of super class
    if (!(currentRoom instanceof TreasureRoom)) {
      throw new IllegalArgumentException("not a treasure room");
    }
    randGen = new Random();
  }

  /**
   * changes the room of dragon
   *
   * @param destination takes in the new room
   * @return true if successfully changed
   */
  public boolean changeRoom(Room destination) {
    // checking if changing is possible or not
    if (canMoveTo(destination)) {
      setCurrentRoom(destination);
      return true;
    }
    return false;
  }

  /**
   * checks if dragon can move to a particular room
   *
   * @param destination room we want it to transport
   * @return true if possible
   */
  public boolean canMoveTo(Room destination) {
    // checking the condition if possible for transportation
    if (!(destination instanceof PortalRoom)
            && getCurrentRoom().getAdjacentRooms().contains(destination)) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * we pick a random room for dragon to transport
   *
   * @return the random room
   */
  public Room pickRoom() {
    int i = randGen.nextInt(0, this.getAdjacentRooms().size());// random generator
    return this.getAdjacentRooms().get(i);
  }

  /**
   * accessor method to get warning of dragon nearby
   *
   * @return the warning
   */
  public static String getDragonWarning() {
    return DRAGON_WARNING;// return warning
  }

  /**
   * give encounter warning
   *
   * @return the warning
   */
  public static String getDragonEncounter() {
    return DRAGON_ENCOUNTER;// access encounter warning
  }
}
