package hu.szjanos;

import hu.szjanos.felulet.Felulet;
import hu.szjanos.logika.DHaromszog;

public class Main {

    public static void main(String[] args) {
        Felulet f = new Felulet();
        try {
            DHaromszog teszt1 = new DHaromszog("1,11 1,48 1,85", 1);
            DHaromszog teszt2 = new DHaromszog("1,34 5,0 0,56", 2);
            DHaromszog teszt3 = new DHaromszog("1 2 3", 3);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
}
