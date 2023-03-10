package com.company;

public class Cell {
    private CellState cellState;

    private int lifeTime;

    public Cell(CellState cellState, int lifeTime) {
        this.cellState = cellState;
        this.lifeTime = lifeTime;
    }

    public Cell(CellState cellState) {
        this(cellState, 0);
    }

    public void minimize() {
        lifeTime--;
        if (lifeTime == 0){
            this.cellState = CellState.FREE;
        }
    }

    public CellState getCellState() {
        return cellState;
    }

    public void setCellState(CellState cellState) {
        this.cellState = cellState;
    }

    public int getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(int lifeTime) {
        this.lifeTime = lifeTime;
    }
}
