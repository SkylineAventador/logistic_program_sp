package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;

import static java.lang.String.format;

public class Graph {
    int vertices;
    int[][] adjacencyMatrix;
    int[][] pathMatrix;
    final static int INFINITY = 999;

    Graph(int vertices) {
        this.vertices = vertices;
        adjacencyMatrix = new int[vertices][vertices];
        pathMatrix = new int[vertices][vertices];

    }

    public void addEgde(int source, int destination, int weight) {
        Edge edge = new Edge(source, destination, weight);

        adjacencyMatrix[source][destination] = weight;
        adjacencyMatrix[destination][source] = weight;
    }

    public void print_matrix_to_file (String filename) throws IOException {
        FileWriter fileWriter = new FileWriter(filename);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                printWriter.print(adjacencyMatrix[i][j] + " ");
            }
            printWriter.print("\n");
        }
        printWriter.close();
    }

    public void print_matrix_to_file2 (String filename) throws IOException {
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

    public void floyd_warshall() throws IOException {

        pathMatrix = new int[vertices][vertices];

        for (int i = 0; i < vertices; i++)
            for (int j = 0; j < vertices; j++)
                pathMatrix[i][j] = adjacencyMatrix[i][j];

        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                if (pathMatrix[i][j] == INFINITY)
                    continue;
                for (int k = 0; k < vertices; k++) {
                    if (pathMatrix[i][k] + pathMatrix[k][j] < pathMatrix[i][j])
                        pathMatrix[i][j] = pathMatrix[i][k] + pathMatrix[k][j];
                }
            }
        }
    }

    public int get_shortest_distance_from_A_to_B (int A, int B) {
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                if (i == A && j == B) {
                    if (pathMatrix[i][j] != INFINITY) {
                        return pathMatrix[i][j];
                    }
                }
            }
        }
        return 0;
    }
}