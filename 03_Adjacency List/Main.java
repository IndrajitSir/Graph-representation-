import java.util.Arrays;
import java.util.HashMap;
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
    Map<Integer, List<Map<Integer, Integer>>> weightedGraph;
    private int vertex, edges;

    Graph(int vertex, int edges) {
        this.graph = new HashMap<>();
        this.vertex = vertex;
        this.edges = edges;
    }

    Graph(int vertex, int edges, boolean weighted) {
        this.edges = edges;
        this.vertex = vertex;
        try {
            if (!weighted) {
                throw new Error("Weighted field must be true!");
            }
            this.weightedGraph = new HashMap<>();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected int getVertex() {
        return this.vertex;
    }

    protected int getEdges() {
        return this.edges;
    }

    void displayWeighted() {
        System.out.println("Graph(Adjacency List Weighted)");
        for (Map.Entry<Integer, List<Map<Integer, Integer>>> s : weightedGraph.entrySet()) {
            System.out.println(s.getKey() + " -> " + s.getValue());
            List<Map<Integer, Integer>> listOfMaps = s.getValue();
            // listOfMaps.stream().forEach((map)-> System.out.println(map));
            for (Map<Integer, Integer> map : listOfMaps) {
                System.out.println(s.getKey() + " -> " + map);
            }
            System.out.println("END!");
            // Iterator<Map<Integer, Integer>> it = listOfMaps.iterator();
            // if(it.hasNext()){
            // System.out.println(it.next());
            // }
        }
    }

    void displayUnweighted() {
        System.out.println("Graph(Adjacency List Unweighted):");
        // System.out.println(this.graph);
        for (Map.Entry<Integer, List<Integer>> s : graph.entrySet()) {
            System.out.println(s.getKey() + " -> " + s.getValue());
        }
    }

    // String removeDirected(int vertex1, int vertex2) {
    // if (this.graph.containsKey(vertex1)) {
    // List<Integer> list = this.graph.get(vertex1);
    // if (list.contains(vertex2)) {
    // list.remove(vertex2);
    // return new String("Done!");
    // }
    // return new String("Relationship is not yet established!");
    // }
    // return new String("Key Does not exists!");
    // }

    String remove(int vertex1, int vertex2, boolean directed, boolean weighted) {
        if (directed && weighted) {
            return new String("Removing directed and weighted!");
        } else if (directed && !weighted) {
            return new String("Removing directed and unweighted!");
        } else if (!directed && weighted) {
            if (weightedGraph != null) {
                if (this.weightedGraph.containsKey(vertex1) && this.weightedGraph.containsKey(vertex2)) {
                    if (isConnected(vertex1, vertex2)) {
                        List<Map<Integer, Integer>> list = this.weightedGraph.get(vertex1);
                        List<Map<Integer, Integer>> list2 = this.weightedGraph.get(vertex2);
                        // list of maps me se ek map delete krna hai based on map ki key
                        for (Map<Integer, Integer> map : list2) {
                            if (map.containsKey(vertex1)) {
                                map.remove(vertex1);
                                // // Object removeThisMap = map.getClass();
                                // list2.remove(map);
                            }
                        }
                        for (Map<Integer, Integer> map : list) {
                            if (map.containsKey(vertex2)) {
                                map.remove(vertex2);
                                // // Object removeThisMap = map.getClass();
                                // list.remove(map);
                            }
                        }
                        return new String("Done!");
                    }
                    return new String("Relationship is not yet established!");
                }
                return new String("Key (values) Does not exists! OR Relationship is not yet established!");
            }
            return new String("Removing undirected and weighted!");
        } else { // (!directed && !weighted)
            if (graph != null) {
                if (this.graph.containsKey(vertex1) && this.graph.containsKey(vertex2)) {
                    List<Integer> list = this.graph.get(vertex1);
                    List<Integer> list2 = this.graph.get(vertex2);
                    if (list.contains(vertex2) && list2.contains(vertex1)) {
                        list2.remove(vertex1);
                        list.remove(vertex2);
                        return new String("Done!");
                    }
                    return new String("Relationship is not yet established!");
                }
                return new String("Key (values) Does not exists! OR Relationship is not yet established!");
            }
        }
        return new String("DONE!");
    }

    private boolean isConnected(int vertex1, int vertex2) {
        List<Map<Integer, Integer>> list1 = new LinkedList<>();
        List<Map<Integer, Integer>> list2 = new LinkedList<>();
        boolean exists = false;
        // Arrays.fill(arr, false);
        // System.out.println(arr[0] + " and " + arr[1]);
        try {
            if (vertex1 == vertex2 && this.weightedGraph.containsKey(vertex1)) {
                list1 = this.weightedGraph.get(vertex1);
                for (Map<Integer, Integer> map : list1) {
                    if (map.containsKey(vertex2))
                        exists = true;
                }

                if (exists) {
                    return true;
                }
                return false;
            } else if (this.weightedGraph.containsKey(vertex1) || this.weightedGraph.containsKey(vertex2)
                    && vertex1 != vertex2) {
                list1 = this.weightedGraph.get(vertex1) != null ? this.weightedGraph.get(vertex1) : null;
                list2 = this.weightedGraph.get(vertex2) != null ? this.weightedGraph.get(vertex2) : null;
                if (list1 != null) {
                    for (Map<Integer, Integer> map : list1) {
                        if (map.containsKey(vertex2))
                            exists = true;
                    }
                }
                if (list2 != null) {
                    for (Map<Integer, Integer> map : list2) {
                        if (map.containsKey(vertex1))
                            exists = true;
                    }
                }
                if (exists) {
                    return true;
                }
                return false;
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}

class UndirectedUnweightedGraph extends Graph implements utilities {
    List<Integer> list1;
    List<Integer> list2;

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
            System.out.println(add(u, v));
            // if (u == v && this.graph.containsKey(u)) {
            // list1 = this.graph.get(u);
            // if (!list1.contains(v)) {
            // list1.add(v);
            // }
            // } else if (u == v && !this.graph.containsKey(u)) {
            // this.graph.put(u, new LinkedList<>(Arrays.asList(v)));
            // }else if (this.graph.containsKey(u) && this.graph.containsKey(v) && u != v) {
            // list1 = this.graph.get(u);
            // list2 = this.graph.get(v);
            // if (!list1.contains(v)) {
            // list1.add(v);
            // list2.add(u);
            // list1 = list2 = null;
            // }
            // System.out.println("Already Connected!");
            // } else {
            // this.graph.put(u, new LinkedList<>(Arrays.asList(v)));
            // this.graph.put(v, new LinkedList<>(Arrays.asList(u)));
            // }

            System.out.println("DONE!");
        }
        sc.close();
    }

    @Override
    public String add(int vertex1, int vertex2) {
        if (vertex1 == vertex2 && this.graph.containsKey(vertex1)) {
            try {
                list1 = this.graph.get(vertex1);
                if (!list1.contains(vertex2)) {
                    list1.add(vertex2);
                    return new String("added!");
                }
                return new String("Already Connected!");
            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            }
        } else if (vertex1 == vertex2 && !this.graph.containsKey(vertex1)) {
            this.graph.put(vertex1, new LinkedList<>(Arrays.asList(vertex2)));
        } else if (this.graph.containsKey(vertex1) || this.graph.containsKey(vertex2) && vertex1 != vertex2) {
            try {
                boolean added = false;
                list1 = this.graph.get(vertex1) != null ? this.graph.get(vertex1) : null;
                list2 = this.graph.get(vertex2) != null ? this.graph.get(vertex2) : null;
                if (list1 != null && list1.contains(vertex2) && list2 != null && list2.contains(vertex1)) {
                    return new String("Already Connected!");
                }

                if (list1 == null) {
                    this.graph.put(vertex1, new LinkedList<>(Arrays.asList(vertex2)));
                }

                if (list2 == null) {
                    this.graph.put(vertex2, new LinkedList<>(Arrays.asList(vertex1)));
                }

                if (list1 != null && !list1.contains(vertex2)) {
                    added = list1.add(vertex2);
                }

                if (list2 != null && !list2.contains(vertex1)) {
                    added = list2.add(vertex1);
                }

                if (added)
                    return new String("added!");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        this.graph.put(vertex1, new LinkedList<>(Arrays.asList(vertex2)));
        this.graph.put(vertex2, new LinkedList<>(Arrays.asList(vertex1)));

        return new String("added!");
    }
}

class UndirectedWeightedGraph extends Graph implements utilities {
    List<Map<Integer, Integer>> list1 = new LinkedList<>();
    List<Map<Integer, Integer>> list2 = new LinkedList<>();
    Map<Integer, Integer> temp;

    UndirectedWeightedGraph(int vertex, int edges, boolean weighted) {
        super(vertex, edges, weighted);
    }

    // @SuppressWarnings("unlikely-arg-type")
    @Override
    public void create() {
        System.out.println("Creating...! you are permitted to enter "+ this.getEdges() +" vertexes(nodes):");
        Scanner sc = new Scanner(System.in);
        System.out.println("Connect edges:");

        for (int i = 0; i < this.getEdges(); i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            System.out.println(add(u, v));
            sc.close();
        }
    }

    @Override
    public String add(int vertex1, int vertex2) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the weight:");
        int weight = sc.nextInt();
        // int weight = 50;
        sc.close();
        try {
            if (vertex1 == vertex2 && this.weightedGraph.containsKey(vertex1)) {
                list1 = this.weightedGraph.get(vertex1);
                for (Map<Integer, Integer> map : list1) {
                    if (map.containsKey(vertex2))
                        return new String("Already connected!");
                }
                temp = new HashMap<>();
                temp.put(vertex2, weight);
                list1.add(temp);
                return new String("Added!");
            } else if (vertex1 == vertex2 && !this.weightedGraph.containsKey(vertex1)) {
                temp = new HashMap<>();
                temp.put(vertex2, weight);
                list1.add(temp);
                this.weightedGraph.put(vertex1, list1);
                return new String("Added!");
            } else if (this.weightedGraph.containsKey(vertex1) || this.weightedGraph.containsKey(vertex2)
                    && vertex1 != vertex2) {
                list1 = this.weightedGraph.get(vertex1) != null ? this.weightedGraph.get(vertex1) : null;
                list2 = this.weightedGraph.get(vertex2) != null ? this.weightedGraph.get(vertex2) : null;

                if (list1 != null) {
                    for (Map<Integer, Integer> map : list1) {
                        if (map.containsKey(vertex2))
                            return new String("Already connected!");
                    }
                    temp = new HashMap<>();
                    temp.put(vertex2, weight);
                    list1.add(temp);
                }

                if (list1 == null) {
                    temp = new HashMap<>();
                    list1 = new LinkedList<>();
                    temp.put(vertex2, weight);
                    list1.add(temp);
                    this.weightedGraph.put(vertex1, list1);
                }

                if (list2 != null) {
                    for (Map<Integer, Integer> map : list2) {
                        if (map.containsKey(vertex1))
                            return new String("Already connected!");
                    }
                    temp = new HashMap<>();
                    temp.put(vertex1, weight);
                    list2.add(temp);
                    return new String("Added!");
                }

                if (list2 == null) {
                    temp = new HashMap<>();
                    list2 = new LinkedList<>();
                    temp.put(vertex1, weight);
                    list2.add(temp);
                    this.weightedGraph.put(vertex2, list2);
                }
            } else {
                temp = new HashMap<>();
                list1 = new LinkedList<>();
                temp.put(vertex2, weight);
                list1.add(temp);
                this.weightedGraph.put(vertex1, list1);

                temp = new HashMap<>();
                list2 = new LinkedList<>();
                temp.put(vertex1, weight);
                list2.add(temp);
                this.weightedGraph.put(vertex2, list2);
                return new String("Added!");
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        return new String("Done!");
    }
}

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of vertex and edges:");
        int vertex = sc.nextInt();
        int edges = sc.nextInt();
        // int vertex = 4;
        // int edges = 5;
        UndirectedUnweightedGraph gp = new UndirectedUnweightedGraph(vertex, edges);
        // gp.create();
        gp.add(2, 3);
        gp.add(1, 2);
        gp.add(2, 4);
        gp.add(1, 3);
        gp.add(2, 2);
        gp.add(1, 1);
        gp.displayUnweighted();

        UndirectedWeightedGraph gp2 = new UndirectedWeightedGraph(vertex, edges, true);
        // gp2.create();
        gp2.add(2, 3);
        gp2.add(1, 2);
        gp2.add(2, 4);
        gp2.add(1, 3);
        gp2.add(2, 2);
        gp2.add(1, 1);
        gp2.displayWeighted();
        gp2.remove(2, 3, false, true);
        gp2.displayWeighted();
        sc.close();
    }

}

// class SingleList {
// List<Integer> list=null;
//
// List<Integer> create() {
// if (list != null) {
// return list;
// }
// list = new LinkedList<>();
// return list;
// }
// }
