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


import processing.core.PImage;
import java.util.Random;
import java.util.ArrayList;
import processing.core.PApplet;

/**
 * this class makes a portal room which teleports player
 */
public class PortalRoom extends Room {
  private Random randGen; // random number generator for location picking
  private static final String PORTAL_WARNING = "You feel a distortion in space nearby.\n";
  private static final String TELEPORT_MESSAGE =
          "The space distortion teleported you to another room!\n";
  private static PImage portalImage;
  // image of a portal to be shown in all portal rooms

  /**
   * constructor to make a portal room object
   *
   * @param ID          gets id number of room
   * @param description gets description
   * @param image       gets image of particular room
   */
  public PortalRoom(int ID, String description, processing.core.PImage image) {
    super(ID, description, image);// call super class constructor-parameterized
    randGen = new Random();
  }

  /**
   * accessor method to get teleport message
   *
   * @return teleport message
   */
  public static String getTeleportMessage() {
    return TELEPORT_MESSAGE;// returning message
  }

  /**
   * sending message of portal
   *
   * @return portal warning
   */
  public static String getPortalWarning() {
    return PORTAL_WARNING;// return message
  }

  /**
   * get location to transport
   *
   * @return any random adjacent room to teleport
   */
  public Room getTeleportLocation() {

    int r = randGen.nextInt(0, this.getAdjacentRooms().size());// random generator
    return this.getAdjacentRooms().get(r);
  }

  /**
   * draws the portal room
   */
  public void draw() {
    super.draw();// calling superclass draw method
    Room.processing.image(portalImage, 325, 225);
  }

  /**
   * mutator method to set image
   *
   * @param portalImage takes in the new image
   */
  public static void setPortalImage(processing.core.PImage portalImage) {
    PortalRoom.portalImage = portalImage;// mutates the original image
  }
}
