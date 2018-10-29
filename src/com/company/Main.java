package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class Main {
    /** KONSTANTY **/
    final static int MIN_POCET_SIDEL = 500;
    final static int MAX_POCET_SIDEL = 2000;

    final static int MIN_KM = 15;
    final static int MAX_KM = 100;

    final static int MIN_CEST_Z_SIDEL = 200;
    final static int MAX_CEST_Z_SIDEL = 500;

    final static int INFINITY = 999;

    static Random rand = new Random();

    public static void main(String[] args) throws IOException {


        // Randomny pocet sidel od 500-2000
        int sidla = rand.nextInt((MAX_POCET_SIDEL - MIN_POCET_SIDEL) + 1) + MIN_POCET_SIDEL;
        Graph graph = new Graph(sidla);

        generate_graph(graph, sidla);
        graph.floyd_warshall();
        graph.print_matrix_to_file("output.txt");   // vypis grafu (matice)
        graph.print_matrix_to_file2("output2.txt"); // nejkratsi cesty (matice)
        System.out.println("Shortest distance from sidlo 1 to 50: " + graph.get_shortest_distance_from_A_to_B(1,50));

    }

    private static void generate_graph(Graph graph, int sidla) {
        for (int i = 0; i < sidla; i++ ) {
            int prvni_sidlo = i;
            int cesty =  rand.nextInt((MAX_CEST_Z_SIDEL - MIN_CEST_Z_SIDEL) + 1) + MIN_CEST_Z_SIDEL;

            // Заполнение матрицы бесконечностью
            for (int j = 0; j < sidla; j++ ) {
                int druhe_sidlo = j;
                int km = INFINITY;
                graph.addEgde(prvni_sidlo, druhe_sidlo, km);
            }

            // Создание от 200-500 дорог
            for (int j = 0; j < cesty; j++) {
                int druhe_sidlo = rand.nextInt(sidla);
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
