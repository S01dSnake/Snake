package com.company;

import java.util.Timer;

public class Main {
    public static void main(String[] args) {
        Game game = new Game(new Player("Вася"));
        game.move();
        game.move();
        game.printField();
    }
}
