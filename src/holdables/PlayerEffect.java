package holdables;

/**
 * Defines a type for the two specified player effects, that can be used to identify what effect to
 * apply.
 *
 * @author Joseph Omar
 * @version 2.0
 */
public enum PlayerEffect implements Effect, Holdable {
   DOUBLE_MOVE,
   BACKTRACK,
   NONE;

   /**
    * To string method for player effect
    *
    * @return Player effect as string
    */
   @Override
   public String toString() {

      switch (this) {
         case DOUBLE_MOVE:
            return "Double Move";
         case BACKTRACK:
            return "Backtrack";
         case NONE:
            return "No Effect";
         default:
            return "Not a Player Effect";
      }
   }
}
