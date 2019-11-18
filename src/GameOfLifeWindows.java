
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameOfLifeWindows extends JFrame {
    private JButton createButton;
    private JTextField gridHeight;
    private JPanel mainPanel;
    private JPanel cellPlayGrid;
    private JButton stopButton;
    private JTextField gridWidth;
    private JTextField percent;
    private Timer timer;
    private int[][] origin;

    public GameOfLifeWindows() throws InterruptedException {
        ButtonClickListener buttonClickListener = new ButtonClickListener();

        SetJTextFieldOnlyAlownNumber();
        createButton.setActionCommand("Create");
        stopButton.setActionCommand("Stop");

        createButton.addActionListener(buttonClickListener);
        stopButton.addActionListener(buttonClickListener);

        add(mainPanel);
        setTitle("Aaron GameOfLifeWindows");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        timer = new Timer(20, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    cellPlayGrid.removeAll();
                    origin = GameOfLife.NextGeneration(origin);
                    /*for better performance comment it for now*/
//                    if (!GameOfLife.CheckAllCellAlive(origin)) {
//                        timer.stop();
//                    }
                    ShowoncellPlayGrid(origin);
                    cellPlayGrid.updateUI();
                } catch (Exception ex) {

                }
            }
        });
    }

    private void SetJTextFieldOnlyAlownNumber() {
        gridWidth.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                if ((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') || ke.getKeyChar() == (char) 8) {
                    gridWidth.setEditable(true);
                } else {
                    gridWidth.setEditable(false);
                }
            }
        });

        gridHeight.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                if ((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') || ke.getKeyChar() == (char) 8) {
                    gridHeight.setEditable(true);
                } else {
                    gridHeight.setEditable(false);
                }
            }
        });

        percent.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                if ((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') || ke.getKeyChar() == (char) 8) {
                    percent.setEditable(true);
                } else {
                    percent.setEditable(false);
                }
            }
        });
    }

    private void ShowoncellPlayGrid(int[][] origin) {
//        ArrayList<JPanel> testl = new ArrayList<JPanel>();
        for (int i = 0; i < origin.length; i++) {
            for (int j = 0; j < origin[i].length; j++) {
                JPanel Jp = new JPanel();
                if (origin[i][j] == 0) {
                    Jp.setBackground(Color.white);
                } else {
                    Jp.setBackground(Color.black);
                }
                cellPlayGrid.add(Jp);
            }
        }

//        for (JPanel t : testl) {
//            cellPlayGrid.add(t);
//        }
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            switch (command) {
                case "Create": {
                    int gridx = Integer.valueOf(gridWidth.getText());
                    int gridy = Integer.valueOf(gridHeight.getText());
                    int percentInt = Integer.valueOf(percent.getText());
                    cellPlayGrid.setLayout(new GridLayout(gridy, gridx));
                    origin = GameOfLife.CreateRandomCellGrid(gridy, gridx, percentInt);
                    timer.start();
                    break;
                }
                case "Stop": {
                    timer.stop();
                    break;
                }
            }
        }
    }
}
