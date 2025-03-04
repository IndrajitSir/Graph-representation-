package Generics;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GenericGraph<T> {
    Map<T, List<T>> graph;
    Map<T, List<Map<T, Integer>>> weightedGraph;

    public GenericGraph() {
        this.graph = new HashMap<>();
    }

    public GenericGraph(boolean weighted) {
        try {
            if (!weighted) {
                throw new Error("Weighted field must be true!");
            }
            this.weightedGraph = new HashMap<>();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Map<T, List<T>> getGraph() {
        return graph;
    }

    public Map<T, List<Map<T, Integer>>> getGraph(boolean weighted) {
        if (weighted) {
            return weightedGraph;
        }
        return null;
    }

    public String add(T vertex1, T vertex2) {
        List<T> list1;
        List<T> list2;
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

    public String add(T vertex1, T vertex2, boolean weighted){
        List<Map<T,Integer>> list1;
        List<Map<T,Integer>> list2;
        Map<T, Integer> temp;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the weight:");
        int weight = 0;
        try {
            weight = sc.nextInt();
        } catch (Exception e) {
            System.out.println(e.getMessage() + e.getStackTrace() + e.getLocalizedMessage());
            sc.close();
        }
        try {
            if (vertex1 == vertex2 && this.weightedGraph.containsKey(vertex1)) {
                list1 = this.weightedGraph.get(vertex1);
                for (Map<T, Integer> map : list1) {
                    if (map.containsKey(vertex2))
                        return new String("Already connected!");
                }
                temp = new HashMap<>();
                temp.put(vertex2, weight);
                list1.add(temp);
                return new String("Added!");
            } else if (vertex1 == vertex2 && !this.weightedGraph.containsKey(vertex1)) {
                temp = new HashMap<>();
                list1 = new LinkedList<>();
                temp.put(vertex2, weight);
                list1.add(temp);
                this.weightedGraph.put(vertex1, list1);
                return new String("Added!");
            } else if (this.weightedGraph.containsKey(vertex1) || this.weightedGraph.containsKey(vertex2)
                    && vertex1 != vertex2) {
                list1 = this.weightedGraph.get(vertex1) != null ? this.weightedGraph.get(vertex1) : null;
                list2 = this.weightedGraph.get(vertex2) != null ? this.weightedGraph.get(vertex2) : null;

                if (list1 != null) {
                    for (Map<T, Integer> map : list1) {
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
                    for (Map<T, Integer> map : list2) {
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
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new String("Done!");
    }

    public void displayWeighted() {
        System.out.println("Graph(Adjacency List Weighted from Generic Graph)");
        for (Map.Entry<T, List<Map<T, Integer>>> s : weightedGraph.entrySet()) {
            System.out.println(s.getKey() + " -> " + s.getValue());
            List<Map<T, Integer>> listOfMaps = s.getValue();
            for (Map<T, Integer> map : listOfMaps) {
                System.out.println(s.getKey() + " -> " + map);
            }
            System.out.println("END!");
        }
    }

    public void displayUnweighted() {
        System.out.println("Graph(Adjacency List Unweighted from Generic Graph):");
        for (Map.Entry<T, List<T>> s : graph.entrySet()) {
            System.out.println(s.getKey() + " -> " + s.getValue());
        }
    }
}
