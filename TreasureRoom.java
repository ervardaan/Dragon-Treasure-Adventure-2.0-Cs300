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

/**
 * create a class treasure room which extends room
 */
public class TreasureRoom extends Room {
  private static final String TREASURE_WARNING = "You sense that there is treasure nearby.\n";
  private static PImage treasureBackground; // the image ALWAYS used for treasure rooms
  // ask-if backgroundimage is not declared then we cannot make the object without calling
  // setbackgroundimage method
  // what should we do-either use null-will give npe or some default value-but if the method is not
  // called, then all
  // objects will get the same default image which is not the image we want

  /**
   * constructor to create a treasure room
   *
   * @param ID get id of room
   */
  public TreasureRoom(int ID) {
    super(ID, "In the back of this room, you spot a treasure chest. It is locked...",
            treasureBackground);
    // calling superclass's constructor
  }

  /**
   * accessor method to get treasure warning
   *
   * @return the warning
   */
  public static String getTreasureWarning() {
    // return the warning
    return TREASURE_WARNING;
  }

  /**
   * mmutator method to set background image
   *
   * @param treasureBackground gets the image to set
   */
  public static void setTreasureBackground(processing.core.PImage treasureBackground) {
    TreasureRoom.treasureBackground = treasureBackground;// setting the image
  }

  /**
   * checks if player can grab treasure
   *
   * @param p room of player
   * @return true if player can grab the treasure
   */
  public boolean playerCanGrabTreasure(Player p) {
    // checking the condition if p's room is instance of treasure room and player has a key
    if (p.getCurrentRoom() instanceof TreasureRoom && p.hasKey()) {
      return true;
    }
    return false;
  }
}
