package core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Creates a {@link Matrix} that can be used to represent a grid
 *
 * @param <T> Type of Object to be stored
 * @author Joseph Omar
 * @version 1.1
 */
final public class Matrix<T> implements Iterable<T>, Serializable {
    private final int width;
    private final int height;
    private final Object[][] data;

    /**
     * Constructs a new {@link Matrix}
     *
     * @param width  Width of the matrix
     * @param height Height of the matrix
     */
    public Matrix(int width, int height) {
        this.width = width;
        this.height = height;
        data = new Object[width][height];
    }

    /**
     * Constructs a Matrix using a 2D array of <></>
     *
     * @param data Data to be placed into the matrix
     */
    public Matrix(Object[][] data) {
        this.width = data.length;
        this.height = data[0].length;
        this.data = data;
    }

    /**
     * Set the value at a coordinate in the matrix to a piece of data
     *
     * @param x    X Coordinate of the element
     * @param y    Y Coordinate of the element
     * @param data Data to be stored at the coordinates
     * @throws IndexOutOfBoundsException If the coordinate is either too big or too small
     */
    public void set(int x, int y, T data) throws IndexOutOfBoundsException {
        this.data[x][y] = data;
    }

    /**
     * Gets the data stored at a coordinate in the {@link Matrix}
     *
     * @param x X Coordinate of the element
     * @param y Y Coordinate of the element
     * @return Data stored at coordinate
     * @throws IndexOutOfBoundsException If the coordinate is either too big or too small
     * @throws NullPointerException      If there is no data at the coordinate
     */
    @SuppressWarnings("unchecked")
    public T get(int x, int y) throws IndexOutOfBoundsException, NullPointerException {
        return (T) this.data[x][y];
    }

    /**
     * Sets the data at a particular coordinate in the {@link Matrix}
     *
     * @param coordinate {@link Coordinate} of the data
     * @param data       Data to be stored at the coordinates
     * @throws IndexOutOfBoundsException If the coordinate is either too big or too small
     */
    public void set(Coordinate coordinate, T data) throws IndexOutOfBoundsException {
        this.data[coordinate.getX()][coordinate.getY()] = data;
    }

    /**
     * Gets the data stored at a coordinate in the {@link Matrix}
     *
     * @param coordinate {@link Coordinate} of the data
     * @return Data stored at coordinate
     * @throws IndexOutOfBoundsException If the coordinate is either too big or too small
     * @throws NullPointerException      If there is no data at the coordinate
     */
    @SuppressWarnings("unchecked")
    public T get(Coordinate coordinate) throws IndexOutOfBoundsException, NullPointerException {
        return (T) this.data[coordinate.getX()][coordinate.getY()];
    }

    /**
     * Removes the data at a specific coordinate in the matrix
     *
     * @param x X Coordinate of the element
     * @param y Y Coordinate of the element
     * @throws IndexOutOfBoundsException If the coordinate is either too big or too small
     */
    public void remove(int x, int y) throws IndexOutOfBoundsException {
        this.data[x][y] = null;
    }

    /**
     * Removes the data at a specific coordinate in the matrix
     *
     * @param coordinate {@link Coordinate} of the data
     * @throws IndexOutOfBoundsException If the coordinate is either too big or too small
     */
    public void remove(Coordinate coordinate) throws IndexOutOfBoundsException {
        this.data[coordinate.getX()][coordinate.getY()] = null;
    }

    /**
     * Gets if a piece of data is stored in the {@link Matrix}
     *
     * @param elem The element to check for
     * @return If the element exists in the {@link Matrix}
     */
    public boolean exists(T elem) {
        for (Object[] i : data) {
            for (Object j : i) {
                if (j.equals(elem)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Gets the data in a column
     *
     * @param x Index of the column
     * @return Data in column x
     */
    @SuppressWarnings("unchecked")
    public T[] getColumn(int x) {
        ArrayList<T> toReturn = new ArrayList<>();
        for (Object elem : data[x]) {
            toReturn.add((T) elem);
        }
        return (T[]) toReturn.toArray();
    }

    /**
     * Gets the data in a row
     *
     * @param y Index of the row
     * @return Data in row y
     */
    @SuppressWarnings("unchecked")
    public T[] getRow(int y) {
        ArrayList<T> toReturn = new ArrayList<>();
        for (Object[] column : data) toReturn.add((T) column[y]);
        return (T[]) toReturn.toArray();
    }

    /**
     * Gets the width of the {@link Matrix}
     *
     * @return Width
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Gets the height of the {@link Matrix}
     *
     * @return Height
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Creates a {@link MatrixIterator} for use in for each loops
     *
     * @return Iterator of type <></>
     */
    @Override
    @SuppressWarnings("unchecked")
    public Iterator<T> iterator() {
        return new MatrixIterator();
    }

    private class MatrixIterator implements Iterator {
        private int posX = 0;
        private int posY = 0;

        /**
         * @return If there is another element after the current one
         */
        @Override
        public boolean hasNext() {
            return posX < getWidth()+1 && posY < getHeight();
        }

        /**
         * Gets the data of the next element in the Matrix
         *
         * @return Data in the next element
         */
        @Override
        @SuppressWarnings("unchecked")
        public T next() {
            if (posX < getWidth()) {
                return (T) data[posX++][posY];
            } else if (posY < getHeight()) {
                posX = 0;
                return (T) data[posX][posY++];
            } else {
                return null;
            }
        }
    }
}
