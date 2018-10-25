package com.company;

import java.util.Random;

public class Main {
    /** KONSTANTY **/
    final static int MIN_POCET_SIDEL = 500;
    final static int MAX_POCET_SIDEL = 2000;

    final static int MIN_KM = 1;
    final static int MAX_KM = 50;

    final static int MIN_CEST_Z_SIDEL = 200;
    final static int MAX_CEST_Z_SIDEL = 500;

    public static void main(String[] args) {
        Random rand = new Random();

        // Randomny pocet sidel od 500-2000
        int sidla = rand.nextInt((MAX_POCET_SIDEL - MIN_POCET_SIDEL) + 1) + MIN_POCET_SIDEL;

        Graph graph = new Graph(sidla*500);

        for (int i = 0; i < sidla; i++) {
            // Nazev prvniho sidla
            int prvni_sidlo = rand.nextInt(sidla) + 1;
            // Pocet cest z prvniho sidla
            int pocet_cest_z_sidla =  rand.nextInt((MAX_CEST_Z_SIDEL - MIN_CEST_Z_SIDEL) + 1) + MIN_CEST_Z_SIDEL;

            for (int j = 0; j < pocet_cest_z_sidla; j++) {
                // Nazev druheho sidla
                int druhe_sidlo = rand.nextInt(sidla) + 1;
                // Vzdalenost mezi prvnim a druhym sidlem
                int km = rand.nextInt((MAX_KM - MIN_KM) + 1) + MIN_KM;
                if (prvni_sidlo == druhe_sidlo)
                    continue;

                // Vytvorit hrany (cestu) mezi sidlami
                graph.addEgde(prvni_sidlo, druhe_sidlo, km);
                graph.addEgde(druhe_sidlo, prvni_sidlo, km);
            }
        }
        graph.printGraph();
    }
}
