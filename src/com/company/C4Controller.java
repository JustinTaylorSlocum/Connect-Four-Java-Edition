package com.company;

public class C4Controller {

    C4Model model;

    public C4Controller() {
        model = new C4Model();
    }

    public void dropToken(String string) throws Exception {
        model.dropToken(string);
    }

    public int getWinnerFromModel() {
        return model.gameResult();
    }

    public void controls(String string) throws Exception {
        if (string.equals("Exit Game"))
            model.exit();

        if (string.equals("New Game"))
            model.resetGame();

    }

    public boolean checkModelForWin() {
        if (model.checkWin()) return true;

        return false;
    }
}