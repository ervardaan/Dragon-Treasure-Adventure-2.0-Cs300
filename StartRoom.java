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
 * this class creates a class which extends a room
 */
public class StartRoom extends Room {
    /**
     * constructor of start room
     *
     * @param ID    get id of start room
     * @param image get image for the room
     */
    public StartRoom(int ID, PImage image) {
        // calling super class's constructor
        super(ID, "You find yourself in the entrance to a cave holding treasure.", image);

    }

}
