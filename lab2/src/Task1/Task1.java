package Task1;

/*
Завдання № 1. Бібліотека графів.
Розробити бібліотеку графів. Функції що повинні підтримуватися:
    - додавання вершини
    - створення дуг
    - виведення графу в консоль
    - вершина повинна містити назву та значення (int, наприклад)
    - реалізувати створення як орієнтованих, так і неорієнтованих графів
 */

public class Task1 {

    public static void main(String[] args) {

        final Graph directedGraph = new Graph(true, 10);
        final GraphService directedService = new GraphService(directedGraph);
        final GraphView view = new GraphView();

        directedService.addVertex("A", 10);
        directedService.addVertex("B", 20);
        directedService.addVertex("C", 30);

        directedService.addEdge("A", "B");
        directedService.addEdge("A", "C");
        directedService.addEdge("B", "C");

        view.displayGraph(directedGraph);

        System.out.println();

        final Graph undirectedGraph = new Graph(false, 10);
        final GraphService undirectedService = new GraphService(undirectedGraph);

        undirectedService.addVertex("A", 40);
        undirectedService.addVertex("B", 20);
        undirectedService.addVertex("C", 30);

        undirectedService.addEdge("A", "B");
        undirectedService.addEdge("A", "C");
        undirectedService.addEdge("B", "C");

        view.displayGraph(undirectedGraph);
    }
}