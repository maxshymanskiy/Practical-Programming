package Task1;

public class Task1 {

    public static void main(String[] args) {
        // Створення орієнтованого графа
        final Graph directedGraph = new Graph(true, 10);
        final GraphService directedService = new GraphService(directedGraph);
        final GraphView view = new GraphView();

        directedService.addVertex("A", 1);
        directedService.addVertex("B", 2);
        directedService.addVertex("C", 3);
        directedService.addEdge("A", "B");
        directedService.addEdge("B", "C");

        System.out.println("Directed Graph:");
        view.displayGraph(directedGraph);

        // Створення неорієнтованого графа
        final Graph undirectedGraph = new Graph(false, 10);
        final GraphService undirectedService = new GraphService(undirectedGraph);

        undirectedService.addVertex("X", 10);
        undirectedService.addVertex("Y", 20);
        undirectedService.addEdge("X", "Y");

        System.out.println("\nUndirected Graph:");
        view.displayGraph(undirectedGraph);
    }
}
