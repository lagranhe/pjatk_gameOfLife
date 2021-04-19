package com.psm;

public class GameOfLife {

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public void nastepnaRunda (int[][] plansza) {
        if (plansza == null || plansza.length == 0) return;
        int dlugosc = plansza.length;
        int szerokosc = plansza[0].length;

        //odczytujemy aktualny stan komórek tej rundy
        for (int x = 0; x < dlugosc; x++) {
            for (int y = 0; y < szerokosc; y++) {

                //liczymy żywych sąsiadów
                int lives = policzZywychSasiadow(plansza, dlugosc, szerokosc, x, y);

                // jeśli komórka jest żywa i ilość sąsiadów jest 2 lub 3 to komórka zostaje żywa
                // Komórka jest żywa, czyli pierwszy bit = 1; ---> aktualny stan 01;
                if (plansza[x][y] == 1 && lives >= 2 && lives <= 3) {
                    // Komórka będzie żyła. Robimy drugi bit równy 1 : 01 ---> 11
                    plansza[x][y] = 3;
                }

                // jeśli komórka jest martwa i ilość sąsiadów jest 3 to komórka staje żywa
                // Komórka jest martwa, czyli pierwszy bit = 0; ---> aktualny stan 00
                if (plansza[x][y] == 0 && lives == 3) {
                    // Komórka będzie żyła. Robimy drugi bit równy 1 : 00 ---> 10
                    plansza[x][y] = 2;
                }
            }
        }

        //zmieniamy stan komórek przed następną rundą
        for (int x = 0; x < dlugosc; x++) {
            for (int y = 0; y < szerokosc; y++) {
                plansza[x][y] >>= 1;  // zmieniamy stan komórki z terazniejszego na przyszły
            }
        }
    }

    public int policzZywychSasiadow(int[][] plansza, int dlugosc, int szerokosc, int oX, int oY) {
        int zywychSasiadow = 0;
        for (int x = Math.max(oX - 1, 0); x <= Math.min(oX + 1, dlugosc - 1); x++) {
            for (int y = Math.max(oY - 1, 0); y <= Math.min(oY + 1, szerokosc - 1); y++) {
                zywychSasiadow += plansza[x][y] & 1;
            }
        }

        zywychSasiadow -= plansza[oX][oY] & 1;
        return zywychSasiadow;
    }

    public void rysujPlansze (int [][] plansza){
        int dlugosc = plansza.length;
        int szerokosc = plansza[0].length;

        for (int x = 0; x < dlugosc; x++) {
            for (int y = 0; y < szerokosc; y++) {
                if (plansza[x][y] == 1){
                    System.out.print(ANSI_RED + plansza[x][y] + " " + ANSI_RESET);
                } else {
                    System.out.print(ANSI_GREEN + plansza[x][y] + " " + ANSI_RESET);
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
