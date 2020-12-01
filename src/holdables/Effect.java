package holdables;

import javafx.scene.image.Image;
import styles.Style;

/**
 * Defines the Effect types
 *
 * @author Joseph Omar
 * @version 3.0
 */
public interface Effect extends Holdable {
    String toString();
    Image getImage(Style style);
}
