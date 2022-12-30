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
 * create a room class for all 9 rooms
 */
public class Room {

  private String description; // verbal description of the room

  private ArrayList<Room> adjRooms; // list of all rooms directly connect

  private final int ID; // a "unique" identifier for each room

  protected static PApplet processing; // PApplet object which the rooms will use to

  // draw stuff to the GUI

  private PImage image; // stores the image that corresponds to the background of a room

  /**
   * constructor to create a room object
   *
   * @param ID          gets id of a particular room
   * @param description gets description of room
   * @param image       get image of a particular room
   */
  public Room(int ID, String description, processing.core.PImage image)

  {

    this.description = description;

    this.ID = ID;

    adjRooms = new ArrayList<Room>();// create a room arraylist

    this.image = image;

  }

  /**
   * accessor method to get id
   *
   * @return id
   */
  public int getID()

  {

    return ID;// get id

  }

  /**
   * accessor method to get description
   *
   * @return description
   */
  public String getDescription()

  {

    return description;// get description

  }

  /**
   * accessor method to get adjacent room's arraylist
   *
   * @return get arraylist
   */
  public ArrayList<Room> getAdjacentRooms()

  {

    return adjRooms;// return arraylist

  }

  /**
   * mutator method to set processing for room
   *
   * @param processing it is the papplet variable as argument
   */
  public static void setProcessing(processing.core.PApplet processing)

  {
    // set new processing
    Room.processing = processing;
  }

  /**
   * mutator method to set another room to list
   *
   * @param toAdd get room to be added
   */
  public void addToAdjacentRooms(Room toAdd)

  {
    // add room to arraylist
    adjRooms.add(toAdd);

  }

  /**
   * checks if room given is adjcent
   *
   * @param r argument room
   * @return true if condition is true
   */
  public boolean isAdjacent(Room r)

  {

    // if (adjRooms.contains(r))
    //
    // {
    //
    // return true;
    //
    // }
    //
    // return false;
    for (int i = 0; i < adjRooms.size(); i++) {
      // checks the condition for equality of rooms
      if (adjRooms.get(i).equals(r)) {
        return true;
      }
    }
    return false;

  }

  /**
   * overridden method form object class
   *
   * @param other object class object
   * @return true if 2 rooms are equal
   */
  @Override

  public boolean equals(Object other)

  {
    // checks if object is instance of room and id are same
    if (other instanceof Room && this.ID == ((Room) other).ID) {

      return true;

    }

    return false;

  }


  /**
   * overridden method from object clsss
   *
   * @return string representation of a room
   */
  @Override

  public String toString()

  {

    String s = this.ID + ":" + this.description + "\n";

    s += "Adjacent Rooms: ";
    // running loop to print all adjacent rooms' id
    for (int i = 0; i < adjRooms.size(); i++) {



      s += adjRooms.get(i).ID + " ";

    }

    return s.trim();

  }

  /**
   * draws the room
   */
  public void draw()

  {
    // PImage i1=processing.loadImage("C:/Users/varda/eclipse-workspace/P05 DragonTreasureGame
    // 2.0/images/1.jpg");
    // PImage i2=processing.loadImage("1.jpg");
    // PImage i3=processing.loadImage("1.jpg");
    // PImage i4=processing.loadImage("1.jpg");
    // PImage i5=processing.loadImage("1.jpg");
    // PImage i6=processing.loadImage("1.jpg");
    processing.image(image, 0, 0);

    // both loadImage and image are methods of PApplet

    processing.fill(-7028);// flavescent

    processing.rect(0, 500, 800, 600);// 600-500=100 as height of rectangle

    processing.fill(0);// black

    processing.text(toString(), 300, 525);



  }

}
