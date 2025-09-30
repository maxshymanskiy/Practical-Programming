package Task1;

import java.util.List;

public final class GraphView {

    public void displayGraph(final Graph graph) {
        printGraphHeader(graph);
        displayEdges(graph);
    }

    private void printGraphHeader(final Graph graph) {
        if (graph.isDirected()) {
            System.out.println("Directed Edges:");
        } else {
            System.out.println("Undirected Edges:");
        }
    }

    private void displayEdges(final Graph graph) {
        boolean hasEdges = printAllEdges(graph);

        if (!hasEdges) {
            System.out.println("No edges in the graph");
        }
    }

    private boolean printAllEdges(final Graph graph) {
        final List<Vertex> vertices = graph.getVertices();
        final boolean[][] adjacencyMatrix = graph.getAdjacencyMatrix();
        boolean hasEdges = false;

        for (int i = 0; i < vertices.size(); i++) {
            for (int j = 0; j < vertices.size(); j++) {
                if (adjacencyMatrix[i][j] && shouldPrintEdge(graph, i, j)) {
                    printSingleEdge(graph, vertices.get(i), vertices.get(j));
                    hasEdges = true;
                }
            }
        }

        return hasEdges;
    }

    private boolean shouldPrintEdge(final Graph graph, final int i, final int j) {
        return graph.isDirected() || i <= j;
    }

    private void printSingleEdge(final Graph graph, final Vertex from, final Vertex to) {
        String edgeSymbol = graph.isDirected() ? " -> " : " -- ";
        System.out.println(from + edgeSymbol + to);
    }
}