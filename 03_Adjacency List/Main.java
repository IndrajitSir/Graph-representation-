// import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
// import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

interface utilities {
    void create();
    String add(int vertex, int edges);
}

class Graph {
    Map<Integer, List<Integer>> graph;
    Map<Integer, Map<Integer, Integer>> weightedGraph;
    private int vertex, edges;

    Graph(int vertex, int edges) {
        this.graph = new HashMap<>();
        this.vertex = vertex;
        this.edges = edges;
    }
    // Graph(int vertex, int edges, boolean weighted){
    //     this.edges = edges;
    //     this.vertex = vertex;
    //     if(!weighted){
    //         this.graph = new HashMap<>();
    //     }
    //     this.weightedGraph = new HashMap<>();
    }

    protected int getVertex() {
        return this.vertex;
    }

    protected int getEdges() {
        return this.edges;
    }

    void displayWeighted() {
        System.out.println("Graph(graph):");
        for(Map.Entry<Integer, List<Integer>> s: graph.entrySet()){
            System.out.println(s.getKey() + " -> " + s.getValue());
        }
    }

    void displayUnweighted() {
        System.out.println("Graph(Adjacency List):");
        System.out.println(this.graph);
        for(Map.Entry<Integer, List<Integer>> s: graph.entrySet()){
            System.out.println(s.getKey() + " -> " + s.getValue());
        }
    }

    String removeDirected(int vertex1, int vertex2) {
        if (this.graph.containsKey(vertex1)) {
            List<Integer> list = this.graph.get(vertex1);
            if(list.contains(vertex2))
            {
                list.remove(vertex2);
                return new String("Done!");
            }
            return new String("Relationship is not yet established!");
        } 
        return new String("Key Does not exists!"); 
    }

    String removeUndirected(int vertex1, int vertex2) {
        if (this.graph.containsKey(vertex1) && this.graph.containsKey(vertex2)) {
            List<Integer> list = this.graph.get(vertex1);
            List<Integer> list2 = this.graph.get(vertex2);
            if(list.contains(vertex2) && list2.contains(vertex1))
            {
                list2.remove(vertex1);
                list.remove(vertex2);
                return new String("Done!");
            }
            return new String("Relationship is not yet established!");
        } 
        return new String("Key Does not exists!");   
    }
}

class UndirectedUnweightedGraph extends Graph implements utilities {
    UndirectedUnweightedGraph(int vertex, int edges) {
        super(vertex, edges);
    }

    @Override
    public void create() {
        System.out.println("Creating...");
        Scanner sc = new Scanner(System.in);
        System.out.println("Connect edges:");

        for (int i = 0; i < this.getEdges(); i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            if (this.graph.containsKey(u)) {
                List<Integer> list = this.graph.get(u);
                if(list.contains(v))
                {
                    System.out.println("Already Connected!");
                }
                list.add(v);
            }
            this.graph.put(u, new LinkedList<>(Arrays.asList(v)));
            

            if (this.graph.containsKey(v)) {
                List<Integer> list = this.graph.get(v);
                if(list.contains(u))
                {
                    System.out.println("Already Connected!");
                }
                list.add(u);
            }
            this.graph.put(v, new LinkedList<>(Arrays.asList(u)));            
            System.out.println("Entered");
        }
        sc.close();
    }

    @Override
    public String add(int vertex1, int vertex2) {
        if (this.graph.containsKey(vertex1)) {
            List<Integer> list = this.graph.get(vertex1);
            if(list.contains(vertex2))
            {
                return new String("Already Connected!");
            }
            list.add(vertex2);
        } else {
            // SingleList list = new SingleList();
            graph.put(vertex1, new LinkedList<>(Arrays.asList(vertex2)));
        }
        return new String("added!");
    }
}

class UndirectedWeightedGraph extends Graph implements utilities {
    UndirectedWeightedGraph(int vertex, int edges, boolean weighted) {
        super(vertex, edges, weighted);
    }

    @Override
    public void create() {
        System.out.println("Creating...");
        Scanner sc = new Scanner(System.in);
        System.out.println("Connect edges:");

        for (int i = 0; i < this.getEdges(); i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            if (this.graph.containsKey(u)) {
                List<Integer> list = this.graph.get(u);
                if(list.contains(v))
                {
                    System.out.println("Already Connected!");
                }
                list.add(v);
            }
            this.graph.put(u, new LinkedList<>(Arrays.asList(v)));
            

            if (this.graph.containsKey(v)) {
                List<Integer> list = this.graph.get(v);
                if(list.contains(u))
                {
                    System.out.println("Already Connected!");
                }
                list.add(u);
            }
            this.graph.put(v, new LinkedList<>(Arrays.asList(u)));            
            System.out.println("Entered");
        }
        sc.close();
    }

    @Override
    public String add(int vertex1, int vertex2) {
        if (this.graph.containsKey(vertex1)) {
            List<Integer> list = this.graph.get(vertex1);
            if(list.contains(vertex2))
            {
                return new String("Already Connected!");
            }
            list.add(vertex2);
        } else {
            // SingleList list = new SingleList();
            graph.put(vertex1, new LinkedList<>(Arrays.asList(vertex2)));
        }
        return new String("added!");
    }
}

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of vertex and edges:");
        int vertex = sc.nextInt();
        int edges = sc.nextInt();
        UndirectedUnweightedGraph gp = new UndirectedUnweightedGraph(vertex, edges);
        gp.create();
        gp.add(2, 3);
        gp.displayUnweighted();
        sc.close();
    }

}

// class SingleList {
//     List<Integer> list=null;

//     List<Integer> create() {
//         if (list != null) {
//             return list;
//         }
//         list = new LinkedList<>();
//         return list;
//     }
// }
