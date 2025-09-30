package Task1;

public final class GraphService {

    private final Graph graph;

    public GraphService(Graph graph) {
        this.graph = graph;
    }

    public void addVertex(String name, int value) {
        graph.getVertices().add(new Vertex(name, value));
    }

    public void addEdge(String fromName, String toName) {
        final Vertex from = findVertexByName(fromName);
        final Vertex to = findVertexByName(toName);

        if (from == null || to == null) {
            throw new IllegalArgumentException("Vertex not found");
        }

        final int fromIndex = graph.getVertices().indexOf(from);
        final int toIndex = graph.getVertices().indexOf(to);
        final boolean[][] matrix = graph.getAdjacencyMatrix();

        matrix[fromIndex][toIndex] = true;
        if (!graph.isDirected()) {
            matrix[toIndex][fromIndex] = true;
        }
    }

    private Vertex findVertexByName(String name) {
        for (final Vertex vertex : graph.getVertices()) {
            if (vertex.name().equals(name)) {
                return vertex;
            }
        }
        return null;
    }
}
