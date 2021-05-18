package com.company;

public interface C4ModelInterface {
    void resetGame();  // reset the game
    void exit(); // exit the game
    boolean checkWin();
    int checkForWinVertical();
    int checkForWinHorizontal();
    int checkForWinDiagonal();
    void dropToken(String string) throws Exception;
    void findOpenToken(int row, int column);
    void notifyObserver(int row, int column);
    void changeTurn();
    int gameResult();
}
