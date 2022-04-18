import java.util.*;

public class Graph {
    private HashMap<Integer, Vertex> vertices;

    public Graph() {
        this.vertices = new HashMap<>();
    }

    public Vertex addVertex(int name) {
        Vertex vertex = new Vertex();
        vertices.put(name, vertex);
        return vertex;
    }

    public void addEdge(int vertex1, int vertex2) {
        Vertex v1 = this.vertices.get(vertex1);
        Vertex v2 = this.vertices.get(vertex2);

        v1.addAdjacent(v2);
        v2.addAdjacent(v1);
    }

    public HashMap<Integer, Vertex> getVertices() {
        return this.vertices;
    }

    public int get(Vertex current) {
        int result = 0;
        for (int i : vertices.keySet()) {
            if (vertices.get(i).equals(current)) {
                result = i;
            }
        }
        return result;
    }
}