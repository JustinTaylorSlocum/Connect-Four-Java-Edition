/** Justin Slocum
 * 4/24/19
 */

import javafx.scene.shape.Circle;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class C4View extends JFrame {
        private JPanel turnPanel, buttonPanel, boardPanel;
        private JLabel currentPlayerTurn;
        private final int WINDOW_WIDTH = 616, WINDOW_HEIGHT = 686;

    public C4View() {
        setTitle("Ultimate Connect Four");

        setSize(616,686);

        buildTurnPanel();
        buildButtonPanel();
        setBoard();

        add(turnPanel, BorderLayout.SOUTH);
        add(buttonPanel, BorderLayout.NORTH);
        add(boardPanel, BorderLayout.CENTER);
        setVisible(true);
    }



    public void buildButtonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 7));
        for (int i = 0; i < 7; i++) {
            JButton tempButton = new JButton("V");
            tempButton.setSize(80,50);
            buttonPanel.add(tempButton);
        }

    }
    //JPanel[] allThePanels;
    /*
    for i=0 to 6
        for j = 0; to 6
        JPanel temp buildTinyPanel;
        grid[i][j]=temp;

        public JPanel buildTinyPanel() {}

     */
    //public void drop()


    public void setBoard() {
        boardPanel = new JPanel();
        boardPanel.setSize(WINDOW_WIDTH, 480);
        boardPanel.setLayout(new GridLayout(7, 6));
        for (int i = 0; i < 42; i++) {
            boardPanel.add(buildTinyPanel());
        }
    }

   public JPanel buildTinyPanel() {
        JPanel tempPanelToCreate = new JPanel();
        tempPanelToCreate.setBackground(Color.BLUE);
        tempPanelToCreate.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        tempPanelToCreate.setSize(80, 80);
        tempPanelToCreate.add(createCircleIconLabel(), BorderLayout.CENTER);
        return tempPanelToCreate;
    }


//Anything that is an interaction with a button should be sent to the controller.
//One more basic grid changes and one more wins (Observers) if column is full.

    public JLabel createCircleIconLabel() {
        ImageIcon tokenIcon = new ImageIcon();
        JLabel tempLab = new JLabel(tokenIcon);
        return tempLab;
    }

    public void buildTurnPanel() {
        turnPanel = new JPanel();
        currentPlayerTurn = new JLabel("Current Turn: ");
        turnPanel.setBackground(Color.CYAN);
        turnPanel.add(currentPlayerTurn, BorderLayout.WEST);
    }


    public static void main(String[] args) {
        C4Model z = new C4Model();
        z.showGrid();
        new C4View();
    }
}
