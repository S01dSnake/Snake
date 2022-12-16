package com.company;

import jdk.swing.interop.SwingInterOpUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Game {
    private final Player player;

    private final Cell[][] gameField;

    //private MovementVector direction;

    private boolean isGameEnd;

    private int headX;
    private int headY;

    private final Random rnd = new Random();

//    public static final int DEFAULT_SNAKE_LENGTH = 3;
//    public static final int DEFAULT_FIELD_SIZE = 10;
//
//    public static final MovementVector DEFAULT_DIRECTION = MovementVector.RIGHT;

//    public Game(Player player, Settings gameSettings) {
//        this.player = player;
//        this.gameField = initField(gameSettings.getFieldSize(), gameSettings.getSnakeLength());
//        this.isGameEnd = false;
//    }


    public Game(Player player) {
        this.player = player;
        this.player.setDirection(MovementVector.RIGHT);
        this.player.setPlayerState(PlayerState.INGAME);
        
        gameField = initField(Settings.DEFAULT_FIELD_SIZE, Settings.DEFAULT_SNAKE_LENGTH);
        addAppleToField();

        //direction = Settings.DEFAULT_DIRECTION;
    }

    private void addAppleToField() {
        List<Point> freePoints = new ArrayList<>();
        for (int row = 0; row < gameField.length; row++) {
            for (int col = 0; col < gameField[row].length; col++) {
                if (gameField[row][col].getCellState() == CellState.FREE) {
                    freePoints.add(new Point(col, row));
                }
            }
        }

        if (freePoints.size() == 0) {
            player.setPlayerState(PlayerState.WIN);
        }

        Point applePoint = freePoints.get(rnd.nextInt(freePoints.size()));
        gameField[applePoint.y][applePoint.x] = new Cell(CellState.APPLE);
    }

    private Cell[][] initField(int fieldSize, int length) {
        headX = fieldSize / 2;
        headY = fieldSize / 2;

        Cell[][] field = new Cell[fieldSize][fieldSize];
        for (int r = 0; r < field.length; r++) {
            for (int c = 0; c < field[r].length; c++) {
                if (r == 0 || r == fieldSize - 1 || c == 0 || c == fieldSize - 1) {
                    field[r][c] = new Cell(CellState.WALL);
                } else {
                    field[r][c] = new Cell(CellState.FREE);
                }
            }
        }

        initSnake(field, length);

        return field;
    }

    public void printField() {
        for (Cell[] cells : gameField) {
            for (Cell cell : cells) {
                System.out.print(cell.getCellState() + " ");
            }
            System.out.println();
        }
    }

    private void initSnake(Cell[][] field, int snakeLength) {
        int curColIndex = headY;
        for (int curLenght = snakeLength; curLenght >= 1; curLenght--) {
            field[headY][curColIndex] = new Cell(CellState.SNAKE, curLenght);
            curColIndex--;
        }
    }


    public void move() {
        if (player.getPlayerState() == PlayerState.INGAME) {

            int newHeadX = headX, newHeadY = headY;

            switch (player.direction) {
                case RIGHT:
                    newHeadX++;
                    break;
                case BOT:
                    newHeadY--;
                    break;
                case TOP:
                    newHeadY++;
                    break;
                case LEFT:
                    newHeadX--;
                    break;
            }

            Cell newHeadCell = gameField[newHeadY][newHeadX];
            if (newHeadCell.getCellState() == CellState.SNAKE || newHeadCell.getCellState() == CellState.WALL) {
                player.setPlayerState(PlayerState.LOSE);
            }

            if (newHeadCell.getCellState() == CellState.APPLE) {
                player.addLength();
                addAppleToField();
            }else {
                minimizeCell();
            }
            gameField[newHeadY][newHeadX] = new Cell(CellState.SNAKE, player.getLength());
            headX = newHeadX;
            headY = newHeadY;
        }
    }

    public void minimizeCell() {
        for (Cell[] cells : gameField) {
            for (Cell cell : cells) {
                if (cell.getCellState() == CellState.SNAKE) {
                    cell.minimize();
                }
            }
        }

    }
}