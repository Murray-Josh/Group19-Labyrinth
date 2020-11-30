package core;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Creates a {@link Matrix} that can be used to represent a grid
 * @param <T> Type of Object to be stored
 * @author Joseph Omar
 * @version 1.1
 */
final public class Matrix<T> implements Iterable<T>, Serializable {
    private final int width;
    private final int height;
    private T[][] data;

    /**
     * Constructs a new {@link Matrix}
     * @param width Width of the matrix
     * @param height Height of the matrix
     * @param type Type of data to be stored in the matrix
     */
    public Matrix(int width, int height, Class<T[][]> type) {
        this.width = width;
        this.height = height;
        this.data = type.cast(Array.newInstance(type, width, height));
    }

    /**
     * Constructs a Matrix using a 2D array of <></>
     * @param data Data to be placed into the matrix
     */
    public Matrix(T[][] data) {
        this.width = data.length;
        this.height = data[0].length;
        this.data = data;
    }

    /**
     * Set the value at a coordinate in the matrix to a piece of data
     * @param x X Coordinate of the element
     * @param y Y Coordinate of the element
     * @param data Data to be stored at the coordinates
     * @throws IndexOutOfBoundsException If the coordinate is either too big or too small
     */
    public void set(int x, int y, T data) throws IndexOutOfBoundsException {
        this.data[x][y] = data;
    }

    /**
     * Gets the data stored at a coordinate in the {@link Matrix}
     * @param x X Coordinate of the element
     * @param y Y Coordinate of the element
     * @return Data stored at coordinate
     * @throws IndexOutOfBoundsException If the coordinate is either too big or too small
     * @throws NullPointerException If there is no data at the coordinate
     */
    public T get(int x, int y) throws IndexOutOfBoundsException, NullPointerException {
        return this.data[x][y];
    }

    /**
     * Sets the data at a particular coordinate in the {@link Matrix}
     * @param coordinate {@link Coordinate} of the data
     * @param data Data to be stored at the coordinates
     * @throws IndexOutOfBoundsException If the coordinate is either too big or too small
     */
    public void set(Coordinate coordinate, T data) throws IndexOutOfBoundsException {
        this.data[coordinate.getX()][coordinate.getY()] = data;
    }

    /**
     * Gets the data stored at a coordinate in the {@link Matrix}
     * @param coordinate {@link Coordinate} of the data
     * @return Data stored at coordinate
     * @throws IndexOutOfBoundsException If the coordinate is either too big or too small
     * @throws NullPointerException If there is no data at the coordinate
     */
    public T get(Coordinate coordinate) throws IndexOutOfBoundsException, NullPointerException{
        return this.data[coordinate.getX()][coordinate.getY()];
    }

    /**
     * Removes the data at a specific coordinate in the matrix
     * @param x X Coordinate of the element
     * @param y Y Coordinate of the element
     * @throws IndexOutOfBoundsException If the coordinate is either too big or too small
     */
    public void remove(int x, int y) throws IndexOutOfBoundsException{
        this.data[x][y] = null;
    }

    /**
     * Removes the data at a specific coordinate in the matrix
     * @param coordinate {@link Coordinate} of the data
     * @throws IndexOutOfBoundsException If the coordinate is either too big or too small
     */
    public void remove(Coordinate coordinate) throws IndexOutOfBoundsException {
        this.data[coordinate.getX()][coordinate.getY()] = null;
    }

    /**
     * Gets if a piece of data is stored in the {@link Matrix}
     * @param elem The element to check for
     * @return If the element exists in the {@link Matrix}
     */
    public boolean exists(T elem) {
        for (T[] i: data) {
            for (T j: i) {
                if (j.equals(elem)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Gets the width of the {@link Matrix}
     * @return Width
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Gets the height of the {@link Matrix}
     * @return Height
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Creates a {@link MatrixIterator} for use in for each loops
     * @return Iterator of type <></>
     */
    @Override
    public Iterator<T> iterator() {
        return new MatrixIterator();
    }

    private class MatrixIterator implements Iterator {
        private int posX = 0;
        private int posY = 0;
        private T current = null;

        /**
         *
         * @return If there is another element after the current one
         */
        @Override
        public boolean hasNext() { return posX < getWidth() && posY < getHeight();
        }

        /**
         * Gets the data of the next element in the Matrix
         * @return Data in the next element
         */
        @Override
        public T next() {
            if (posX < getWidth()) {
                return data[posX++][posY];
            } else if (posY < getHeight()) {
                posX = 0;
                return data[posX][posY++];
            } else {
                return null;
            }
        }
    }
}
