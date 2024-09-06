package DesignPatterns;

import GameObject.Plate;
import GameObject.Shape;
import java.util.ArrayList;
import java.util.Iterator;

public class ShapeIterator implements Iterator<Shape> {

    int i = 0;
    ArrayList<Shape> array = new ArrayList<>();

    public ShapeIterator(ArrayList<Shape> array) {
        this.array = array;

    }

    @Override
    public boolean hasNext() {
        return i < array.size() - 1;
    }

    @Override
    public Shape next() {
        if (this.hasNext()) {
            return array.get(i++);
        }
        return null;
    }

    // removes last object 
    @Override
    public void remove() {
        array.remove(array.size() - 1);
    }

    public Shape previousShape(int index) {
        return array.get(index);
    }

    public int previousIndex() {

        return array.size() - 2;
    }

    public boolean hasPrevious() {
        return i > 0;
    }

    public boolean isSameColor(Plate plate1, Plate plate2) {
        if (plate1.getColor().equals(plate2.getColor())) {
            return true;
        }
        return false;
    }

    public Shape beforePreviousShape(int index) {
        return array.get(index - 1);
    }

    public Shape before2PreviousShape(int index) {
        return array.get(index - 2);
    }

}
