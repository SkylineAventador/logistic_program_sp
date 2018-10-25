package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class Main {
    /** KONSTANTY **/
    final static int MIN_POCET_SIDEL = 500;
    final static int MAX_POCET_SIDEL = 2000;

    final static int MIN_KM = 1;
    final static int MAX_KM = 50;

    final static int MIN_CEST_Z_SIDEL = 200;
    final static int MAX_CEST_Z_SIDEL = 500;

    final static int INFINITY = 999;

    static Random rand = new Random();

    public static void main(String[] args) throws IOException {


        // Randomny pocet sidel od 500-2000
        int sidla = rand.nextInt((MAX_POCET_SIDEL - MIN_POCET_SIDEL) + 1) + MIN_POCET_SIDEL;
        Graph graph = new Graph(sidla);

        generate_graph(graph, sidla);
        graph.shortestPath();
        graph.print_matrix_to_file("/home/janelle/IdeaProjects/logistic_program_sp/src/com/company/output.txt");

    }

    private static void generate_graph(Graph graph, int sidla) {
        for (int i = 0; i < sidla; i++ ) {
            int prvni_sidlo = i;
            for (int j = 0; j < sidla; j++ ) {
                int druhe_sidlo = j;
                if (prvni_sidlo == druhe_sidlo)
                {
                    graph.addEgde(prvni_sidlo, druhe_sidlo, INFINITY);
                    continue;
                }
                int km = rand.nextInt((MAX_KM - MIN_KM) + 1) + MIN_KM;
                graph.addEgde(prvni_sidlo, druhe_sidlo, km);
            }
        }
    }

}
