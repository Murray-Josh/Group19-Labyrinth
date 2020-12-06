package core;

import java.io.Serializable;

/**
 * A 2 dimension grid coordinate consisting of an X and Y value
 *
 * @author Joseph Omar
 * @version 1.2
 */
public class Coordinate implements Serializable {

   private int x;
   private int y;

   /**
    * Constructs a new Core.Coordinate object
    *
    * @param x x Core.Coordinate
    * @param y y Core.Coordinate
    */
   public Coordinate(int x, int y) {
      setX(x);
      setY(y);
   }

   /**
    * Gets the x coordinate
    *
    * @return x coordinate
    */
   public int getX() {
      return this.x;
   }

   /**
    * Set the x coordinate
    *
    * @param x x Core.Coordinate
    */
   public void setX(int x) {
      this.x = x;
   }

   /**
    * gets the y coordinate
    *
    * @return y coordinate
    */
   public int getY() {
      return this.y;
   }

   /**
    * Set the y coordinate
    *
    * @param y y coordinate
    */
   public void setY(int y) {
      this.y = y;
   }

   /**
    * Sets both the x and y coordinates to new values
    *
    * @param x x coordinate
    * @param y y coordinate
    */
   public void set(int x, int y) {
      this.x = x;
      this.y = y;
   }

   /**
    * Increase the X value by number
    *
    * @param number to increase by
    */
   public void increaseX(int number) {
      this.x += number;
   }

   /**
    * Increase the Y value by number
    *
    * @param number to increase by
    */
   public void increaseY(int number) {
      this.y += number;
   }

   /**
    * Decrease the X value by number
    *
    * @param number to decrease by
    */
   public void decreaseX(int number) {
      this.x -= number;
   }

   /**
    * Decrease the Y value by number
    *
    * @param number to decrease by
    */
   public void decreaseY(int number) {
      this.y -= number;
   }
}
