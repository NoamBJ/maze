import java.util.*;
import javax.swing.*;

public class Vertex {
    private LinkedList<Vertex> adjacents;

    public Vertex() {
        this.adjacents = new LinkedList<>();
    }

    public void addAdjacent(Vertex adjacentButton) {
        adjacents.add(adjacentButton);
    }

    public LinkedList<Vertex> getAdjacents() {
        return this.adjacents;
    }
}