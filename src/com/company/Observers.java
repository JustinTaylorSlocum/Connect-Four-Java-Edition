package com.company;

public interface Observers {
    void updateBoard(int row, int column, int player);
    void displayTurn(int player);
    void displayWinner();
    void resetBoard();
    void disableButton(int col);
}