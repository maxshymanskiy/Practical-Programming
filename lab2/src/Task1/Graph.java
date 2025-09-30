package Task1;

import java.util.ArrayList;
import java.util.List;

public final class Graph {

    private final List<Vertex> vertices;
    private final boolean isDirected;
    private final boolean[][] adjacencyMatrix;

    public Graph(boolean isDirected, int maxVertices) {
        this.vertices = new ArrayList<>();
        this.isDirected = isDirected;
        this.adjacencyMatrix = new boolean[maxVertices][maxVertices];
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public boolean[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public boolean isDirected() {
        return isDirected;
    }
}

