package com.company;

public class Player {
    private String name;

    private int length;
    private int points;

    protected MovementVector direction;

    //public final static int DEFAULT_LENGTH = 3;

    private PlayerState playerState;


    public Player(String name) {
        this.name = name;
    }

    public Player(String name, int length) {
        this.name = name;
        this.length = length;
    }

    public void addLength() {
        length++;
    }

    public void addPoints() {
        points++;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public MovementVector getDirection() {
        return direction;
    }

    public void setDirection(MovementVector direction) {
        this.direction = direction;
    }

    public PlayerState getPlayerState() {
        return playerState;
    }

    public void setPlayerState(PlayerState playerState) {
        this.playerState = playerState;
    }
}
