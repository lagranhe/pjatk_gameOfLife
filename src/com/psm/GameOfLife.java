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
        int x;
        int y;

        //1 komórka
        x = (oX - 1) < 0 ? plansza.length - 1 : oX - 1;
        y = (oY - 1) < 0 ? plansza[x].length - 1 : oY - 1;
        if ((plansza[x][y] & 1) == 1) zywychSasiadow++;

        //2 komórka
        x = oX;
        y = (oY - 1) < 0 ? plansza[x].length - 1 : oY - 1;
        if ((plansza[x][y] & 1) == 1) zywychSasiadow++;

        //3 komórka
        x = (oX + 1) > plansza.length-1 ? 0 : oX + 1;
        y = (oY - 1) < 0 ? plansza[x].length - 1 : oY - 1;
        if ((plansza[x][y] & 1) == 1) zywychSasiadow++;

        //4 komórka
        x = (oX - 1) < 0 ? plansza.length - 1 : oX - 1;
        y = oY;
        if ((plansza[x][y] & 1) == 1) zywychSasiadow++;

        //6 komórka
        x = (oX + 1) > plansza.length-1 ? 0 : oX + 1;
        y = oY;
        if ((plansza[x][y] & 1) == 1) zywychSasiadow++;

        //7 komórka
        x = (oX - 1) < 0 ? plansza.length - 1 : oX - 1;
        y = (oY + 1) > plansza[x].length - 1 ? 0 : oY + 1;
        if ((plansza[x][y] & 1) == 1) zywychSasiadow++;

        //8 komórka
        x = oX;
        y = (oY + 1) > plansza[x].length - 1 ? 0 : oY + 1;
        if ((plansza[x][y] & 1) == 1) zywychSasiadow++;

        //9 komórka
        x = (oX + 1) > plansza.length-1 ? 0 : oX + 1;
        y = (oY + 1) > plansza[x].length - 1 ? 0 : oY + 1;
        if ((plansza[x][y] & 1) == 1) zywychSasiadow++;

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
