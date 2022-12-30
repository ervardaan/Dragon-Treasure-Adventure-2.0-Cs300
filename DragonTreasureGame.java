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
import processing.core.PApplet;
import java.io.File;
import java.util.ArrayList;

import processing.core.PImage;
import java.io.IOException;
import java.util.Scanner;

/**
 * class created to run the program and it extends papplet for gui
 */
public class DragonTreasureGame extends PApplet {
  private ArrayList<Character> characters; // Stores the list of characters
  private ArrayList<Room> roomList; // Stores the all the rooms
  private File roomInfo; // File object storing room's descriptions
  private File mapInfo; // File object storing all the adjacent rooms
  private boolean isDragonTurn; // Boolean storing whether it's dragons turn or not
  private int gameState; // Stores the game state of the current game

  /**
   * The main method for the program
   *
   * @param args Unused Arguments
   */
  public static void main(String[] args) {
    // Papplet loads the current class and starts the game
    PApplet.main("DragonTreasureGame");

  }


  /**
   * Method which sets the Windows width and height
   */
  @Override
  public void settings() {
    // Sets the size of PApplet to be 800 by 600
    size(800, 600);
  }

  /**
   * This method initializes all our characters : KeyHolder, Player ,and Dragon
   */
  private void loadCharacters() {
    System.out.println("Adding characters...");
    // loading all characters in arraylist
    characters.add(new Character(getRoomByID(5), "KEYHOLDER"));
    characters.add(new Player(getRoomByID(1)));
    characters.add(new Dragon(getRoomByID(9)));
  }

  /**
   * This method sets up everything before the game is started
   */
  @Override
  public void setup()

  {
    // Methods to set up the PApplet's default conditions
    this.getSurface().setTitle("Dragon Treasure Adventure"); // sets the title of the window
    this.imageMode(PApplet.CORNER); // Images are drawn using the x,y-coordinate
    // as the top-left corner
    this.rectMode(PApplet.CORNERS); // When drawing rectangles interprets args
    // as top-left corner and bottom-right corner respectively
    this.focused = true; // window will be active upon running program
    this.textAlign(CENTER); // sets the text alignment to center
    this.textSize(20); // sets the font size for the text

    // Sets The room Objects Processing to be this PApplet
    Room.setProcessing(this);

    // Sets up the background of treasure room, and Portal room's image
    TreasureRoom.setTreasureBackground(loadImage("images/treasure.jpg"));
    PortalRoom.setPortalImage(loadImage("images/portal.png"));

    // Initialization of Game Variables
    gameState = 0; // indicates that game is still on
    isDragonTurn = false; // indicates that it is players turn first

    // Initializes File Objects
    roomInfo = new File("roominfo.txt");
    mapInfo = new File("map.txt");

    // Initialization of Rooms
    roomList = new ArrayList<Room>(); // creates an empty room list


    // Method to load the rooms and connect them
    loadRoomInfo();
    loadMap();

    // Initializes and loads the character Array
    characters = new ArrayList<Character>();
    loadCharacters();
  }

  /**
   * Loads in room info using the file stored in roomInfo
   *
   * @author Michelle
   */
  private void loadRoomInfo() {
    System.out.println("Loading rooms...");
    Scanner fileReader = null;
    try {

      // scanner to read from file
      fileReader = new Scanner(roomInfo);

      // read line by line until none left
      while (fileReader.hasNext()) {
        String nextLine = fileReader.nextLine();

        // parse info and create new room
        String[] parts = nextLine.split(" \\| ");
        int ID = Integer.parseInt(parts[1].trim()); // get the room id
        String imageName = null;
        String description = null;
        PImage image = null;
        Room newRoom = null;

        if (parts.length >= 3) {
          imageName = parts[2].trim();
          image = this.loadImage("images" + File.separator + imageName);

        }

        if (parts.length == 4) {
          description = parts[3].trim(); // get the room description
        }
        // System.out.println(parts[0]);
        switch (parts[0].trim()) {
          case "S":
            newRoom = new StartRoom(ID, image);
            break;
          case "R":
            newRoom = new Room(ID, description, image);
            break;
          case "P":
            newRoom = new PortalRoom(ID, description, image);
            break;
          case "T":
            newRoom = new TreasureRoom(ID);
            break;
          default:
            break;
        }

        if (newRoom != null) {
          roomList.add(newRoom);
        }
      }
    } catch (IOException e) { // handle checked exception
      e.printStackTrace();
    } finally {
      if (fileReader != null)
        fileReader.close(); // close scanner regardless of what happened for security reasons :)
    }
    // System.out.println(roomList.size());

  }

  /**
   * Loads in room connections using the file stored in mapInfo
   *
   * @author Michelle
   */
  private void loadMap() {
    System.out.println("Loading map...");
    Scanner fileReader = null;
    try {
      // scanner to read from file
      fileReader = new Scanner(mapInfo);

      // read line by line until none left
      while (fileReader.hasNext()) {

        // parse info
        String nextLine = fileReader.nextLine();

        String parts[] = nextLine.split(" ");
        int id = Integer.parseInt(parts[0]);
        // System.out.println(id);
        Room toEdit = getRoomByID(id); // get the room we need to update info for adjacent rooms

        // add all the rooms to the adj room list of toEdit
        for (int i = 1; i < parts.length; i++) {
          Room toAdjAdd = getRoomByID(Integer.parseInt(parts[i]));
          toEdit.addToAdjacentRooms(toAdjAdd);
        }
      }
    } catch (IOException e) { // handle checked exception
      e.printStackTrace();
    } finally { // close scanner regardless of what happened for security reasons :)
      if (fileReader != null)
        fileReader.close();
    }
  }

  // note:indexof uses equals method
  /**
   * Get the room objected associated with the given ID.
   *
   * @param id the ID of the room to retrieve
   * @return the Room that corresponds to that id
   * @author Michelle
   */

  private Room getRoomByID(int id) {


    int indexToEdit = roomList.indexOf(new Room(id, "dummy", null));

    Room toEdit = roomList.get(indexToEdit);
    return toEdit;
  }

  /**
   * it does some operations when a key is pressed
   */
  @Override
  public void keyPressed() {
    // checks condition if game is still on
    if (gameState == 0) {

      // getting player object from characters
      Player player=null;
      for(Character c:characters)
      {
        if(c instanceof Player)
        {
          player=(Player)c;
        }
      }
      //Player player = (Player) characters.get(1);

      // getting current room of player
      int currentRoomID = player.getCurrentRoom().getID();

      // convert char to string
      String keyInString = String.valueOf(key); // Stores the value of key pressed in a string

      // Try catch to catch NumberFormatException
      try {

        // parsing string to int
        int pressedRoomID = Integer.parseInt(keyInString);

        // Gets the room associated with that ID
        Room roomForPressedID = getRoomByID(pressedRoomID);

        if (player.canMoveTo(roomForPressedID)) {
          player.changeRoom(roomForPressedID);
          isDragonTurn = true;
        } else {
          System.out.println("please enter a valid room");
        }

      } catch (Exception e) {
        System.out.println("please enter a valid room");
      }

    }
  }

  /**
   * this method draws all essential parts to get game started
   */
  public void draw() {

    // Gets all the variables
    Player player = null; //(Player) characters.get(1); // get player
    Dragon dragon = null; //(Dragon) characters.get(2); // get dragon
    Character keyHolder = null; //characters.get(0); // gets the keyHolder
for(Character c:characters)
{
  if(c instanceof Player)
  {
    player=(Player)c;
  }
  if(c instanceof Dragon)
  {
    dragon=(Dragon)c;
  }
  if(c.getLabel().equals("KEYHOLDER"));
  {
    keyHolder=c;
  }
}
    // Draws the players current room
    player.getCurrentRoom().draw();

    // If the game should continue, checks the conditions:
    if (gameState == 0) {

      // Checks For Warnings

      // If the player is near a portal
      if (player.isPortalNearby()) {
        System.out.println(PortalRoom.getPortalWarning());
      }
      // If the player is near a dragon
      if (player.isDragonNearby(dragon)) {
        System.out.println(Dragon.getDragonWarning());
      }
      // If the player is near a treasure room
      if (player.isTreasureNearby()) {
        System.out.println(TreasureRoom.getTreasureWarning());
      }

      // Checks if the player is in the same room as keyHolder
      // If the keyHolders current room and players current room are equal and the player doesn't
      // have a key
      if (keyHolder.getCurrentRoom().equals(player.getCurrentRoom()) && !player.hasKey()) {
        // If the above condition is satisfied, player obtains the key
        player.obtainKey();
      }

      // Checks if the player teleported successfully
      // Stores the previous room information
      Room previousRoom = player.getCurrentRoom();
      // If the player can teleport
      if (player.teleport()) {
        // If the previous room doesn't equal the current room, then player successfully teleported
        if (!previousRoom.equals(player.getCurrentRoom())) {
          System.out.println(PortalRoom.getTeleportMessage());
        }
      }

      // If it is dragons turn, from dragon's picked room if it can change the room then
      if (isDragonTurn && dragon.changeRoom(dragon.pickRoom())) {
        // Set the dragon's turn to be false
        isDragonTurn = false;
        // System.out.println("Dragon is in "+dragon.getCurrentRoom());
      }

      // If the players current room is the same as dragons
      if (player.getCurrentRoom().equals(dragon.getCurrentRoom())) {
        // Set the game state to 2, indicating player lost
        gameState = 2;
        // Prints the dragon encounter
        System.out.println(Dragon.getDragonEncounter());
        // Prints player lost message
        System.out.println("YOU LOST!");
      }

      // If the current room of player is Treasure Room, and Player has a key and the gameState is 0
      // (To avoid the
      // fact that if player with key and dragon are in the same treasure room, and player should
      // lose)
      if (player.getCurrentRoom() instanceof TreasureRoom && player.hasKey() && gameState == 0) {
        gameState = 1; // indicating the player Won
        System.out.println("PLAYER WON!"); // Prints player win message
      }

    }
  }

}
