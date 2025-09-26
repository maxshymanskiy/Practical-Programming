package Task1;

public final class GraphView {

    public void displayGraph(final Graph graph) {
        final var vertices = graph.getVertices();
        final var matrix = graph.getAdjacencyMatrix();

        System.out.println("Vertices:");
        for (final var vertex : vertices) {
            System.out.println(vertex);
        }

        System.out.println("\nEdges:");
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = 0; j < vertices.size(); j++) {
                if (matrix[i][j]) {
                    System.out.printf("%s -> %s\n",
                            vertices.get(i).getName(),
                            vertices.get(j).getName()
                    );
                }
            }
        }
    }
}
