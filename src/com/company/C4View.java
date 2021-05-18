/** Justin Slocum
 * 4/24/19
 */

package com.company;

import javax.swing.*;
import java.awt.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class C4View implements Observers {
    private final int WINDOW_WIDTH = 560, WINDOW_HEIGHT = 640;
    JFrame gameBoard = new JFrame();
    JPanel[][] gridPanel;
    int gameHeight = 6;
    int gameWidth = 7;
    JPanel tokenPanel,
            dataPanel,
            buttonPanel,
            turnPanel,
            resultPanel,
            gameGrid;

    JLabel turn, result;

    boolean hasWon = false;

    String title = "Ultimate Connect Four";
    JLabel playerTurn = new JLabel("Current Turn: ");
    JLabel gameResult = new JLabel("Game Result: ");

    JButton newGame = new JButton("New Game");
    JButton exitGame = new JButton("Exit");

    JButton button1 = new JButton("Drop 1");
    JButton button2 = new JButton("Drop 2");
    JButton button3 = new JButton("Drop 3");
    JButton button4 = new JButton("Drop 4");
    JButton button5 = new JButton("Drop 5");
    JButton button6 = new JButton("Drop 6");
    JButton button7 = new JButton("Drop 7");

    C4Model model;
    C4Controller controller;

    public C4View() {

        gameBoard.setTitle(title);
        gameBoard.pack();
        gameBoard.setResizable(false);

        gameBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gameBoard.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        newGame.addActionListener(new ButtonListener());
        exitGame.addActionListener(new ButtonListener());
        button1.addActionListener(new InsertListener());
        button2.addActionListener(new InsertListener());
        button3.addActionListener(new InsertListener());
        button4.addActionListener(new InsertListener());
        button5.addActionListener(new InsertListener());
        button6.addActionListener(new InsertListener());
        button7.addActionListener(new InsertListener());

        model = new C4Model();
        controller = new C4Controller();

        model.registerObserver((Observers) this);

        buildButtonPanel();
        createGameBoard();
        createDataRow();

        gameBoard.setVisible(true);
    }

    public void buildButtonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 7));

    }

    public void createGameBoard() {
        gameGrid = new JPanel();

        gameGrid.setLayout(new GridLayout(7, 6));
        gameGrid.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        gridPanel = new JPanel[gameHeight][gameWidth];

        gameGrid.add(button1);
        gameGrid.add(button2);
        gameGrid.add(button3);
        gameGrid.add(button4);
        gameGrid.add(button5);
        gameGrid.add(button6);
        gameGrid.add(button7);

        JPanel token;

        for (int i = 0; i < gameHeight; i++) {
            for (int j = 0; j < gameWidth; j++) {
                gridPanel[i][j] = buildTinyPanel();
            }
        }

        for (int i = 0; i < gameHeight; i++) {
            for (int j = 0; j < gameWidth; j++) {
                token = gridPanel[i][j];
                gameGrid.add(token);
            }
        }

        gameBoard.add(gameGrid, BorderLayout.NORTH);
    }

    public JPanel buildTinyPanel() {
        JPanel tempPanelToCreate = new JPanel();
        tempPanelToCreate.setBackground(Color.BLUE);
        tempPanelToCreate.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        tempPanelToCreate.setPreferredSize(new Dimension(80, 80));
        tempPanelToCreate.add(createCircleIconLabel(), BorderLayout.CENTER);
        return tempPanelToCreate;
    }
    public JLabel createCircleIconLabel() {
        ImageIcon tokenIcon = new ImageIcon();
        JLabel tempLab = new JLabel(tokenIcon);
        return tempLab;
    }

    public void createDataRow() {
        dataPanel = new JPanel();
        turnPanel = new JPanel();
        resultPanel = new JPanel();

        turn = new JLabel("Player 1");

        result = new JLabel("Pending...");

        turnPanel.add(playerTurn);
        turnPanel.add(turn);

        resultPanel.add(gameResult);
        resultPanel.add(result);

        dataPanel.add(turnPanel);
        dataPanel.add(resultPanel);

        buttonPanel = new JPanel();
        buttonPanel.add(newGame);
        buttonPanel.add(exitGame);

        gameBoard.add(dataPanel, BorderLayout.WEST);
        gameBoard.add(buttonPanel, BorderLayout.EAST);

    }

    private class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == newGame) {
                System.out.println("NEW GAME");
                model.resetGame();
            }
            if (e.getSource() == exitGame) {
                model.exit();
            }

            try {
                controller.controls(e.getActionCommand());
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

    }

    private class InsertListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                controller.dropToken(e.getActionCommand());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void updateBoard(int row, int column, int player) {
        for (int r = 0; r < gameHeight; r++) {
            for (int col = 0; col < gameWidth; col++) {
                if (player == 1 && r == row && col == column) {
                    gridPanel[row][column].setBackground(Color.YELLOW);
                }
                else if (player == 2 && r == row && col == column) {
                    gridPanel[row][column].setBackground(Color.RED);
                }
            }
        }

    }

    @Override
    public void displayTurn(int player) {
        if (player == 1) {
            turn.setText("Player " + (player + 1));
        }
        turn.setText("Player " + player);
    }

    @Override
    public void displayWinner() {
        int winner = controller.getWinnerFromModel();
        if (controller.checkModelForWin()) {
            result.setText("Player " + winner);
            JOptionPane.showMessageDialog(null,winner + " won the game!");
        }
    }

    @Override
    public void resetBoard() {
        for (int row = 0; row <  gameHeight; row++) {
            for (int col=0; col < gameWidth; col++) {
                gridPanel[row][col].setBackground(Color.BLUE);
            }
        }
        resetButtons();
    }

    public void resetButtons() {
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        button4.setEnabled(true);
        button5.setEnabled(true);
        button6.setEnabled(true);
        button7.setEnabled(true);
    }


    public void disableButton(int col) {
        col = col + 1;

        switch( col ) {
            case 1:
                button1.setEnabled(false);
                break;
            case 2:
                button2.setEnabled(false);
                break;
            case 3:
                button3.setEnabled(false);
                break;
            case 4:
                button4.setEnabled(false);
                break;
            case 5:
                button5.setEnabled(false);
                break;
            case 6:
                button6.setEnabled(false);
                break;
            case 7:
                button7.setEnabled(false);
                break;
            default:
                break;
        }
    }

    public static void main(String[] args) {
        new C4View();
    }
}