package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

public class Graph {
    int vertices;
    LinkedList<Edge> [] adjacencylist;
    int[][] adjacencyMatrix;
    int[][] pathMatrix;
    final static int INFINITY = 999;

    Graph(int vertices) {
        this.vertices = vertices;
        //adjacencylist = new LinkedList[vertices];
        adjacencyMatrix = new int[vertices][vertices];
        pathMatrix = new int[vertices][vertices];
        //initialize adjacency lists for all the vertices
//        for (int i = 0; i <vertices ; i++) {
//            adjacencylist[i] = new LinkedList<>();
//        }

    }

    public void addEgde(int source, int destination, int weight) {
        Edge edge = new Edge(source, destination, weight);

//        adjacencylist[source].addFirst(edge);
//        adjacencylist[destination].addFirst(edge);

        adjacencyMatrix[source][destination] = weight;
        adjacencyMatrix[destination][source] = weight;
    }

    public void print_adj_list_to_file (String filename) throws IOException {
        FileWriter fileWriter = new FileWriter(filename);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        for (int i = 0; i <vertices ; i++) {
            LinkedList<Edge> list = adjacencylist[i];
            for (int j = 0; j <list.size() ; j++) {
                printWriter.print("Town-" + list.get(j).source + " is connected to " +
                        list.get(j).destination + " with weight " +  list.get(j).weight);
                printWriter.print("\n");
            }
        }
        printWriter.close();
    }

    public void print_matrix_to_file (String filename) throws IOException {
        FileWriter fileWriter = new FileWriter(filename);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                printWriter.print(pathMatrix[i][j] + " ");
            }
            printWriter.print("\n");
        }
        printWriter.close();
    }

    public void shortestPath() {
        // Matrix initialization
        for (int i = 0; i < vertices; i++) {
            System.arraycopy(adjacencyMatrix[i], 0, pathMatrix[i], 0, vertices);
        }

        for (int k = 0; k < vertices; k++) {
            // Pick all vertices as source one by one
            for (int i = 0; i < vertices; i++) {
                // Pick all vertices as destination for the above picked source
                for (int j = 0; j < vertices; j++) {
                    // If vertex k is on the shortest path from i to j, then update the value of dist[i][j]
                    if (pathMatrix[i][k] + pathMatrix[k][j] < pathMatrix[i][j]) {
                        pathMatrix[i][j] = pathMatrix[i][k] + pathMatrix[k][j];
                    }
                }
            }
        }
    }
}