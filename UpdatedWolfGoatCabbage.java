package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import java.util.Scanner;

public class WolfGoatCabbage extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel leftBank;
    private JPanel rightBank;
    private JPanel riverPanel;
    
    private JButton instructButton;
    private JButton moveButton;
    private JButton resignButton;
    private JButton recordsButton;
    private JButton resetButton;
    
    private int secondsPassed = 0;
    private JLabel timerLabel;
    private Timer timer;
    
    private DefaultListModel<String> leftListModel;
    private DefaultListModel<String> rightListModel;
    private DefaultListModel<String> boatListModel;
    
    public WolfGoatCabbage() {
        setTitle("Farmer, Wolf, Goat, and Cabbage Puzzle");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        
        // Create panels
        leftBank = new JPanel();
        leftBank.setBorder(BorderFactory.createTitledBorder("Left Bank"));
        leftBank.setLayout(new BoxLayout(leftBank, BoxLayout.Y_AXIS));
        
        rightBank = new JPanel();
        rightBank.setBorder(BorderFactory.createTitledBorder("Right Bank"));
        rightBank.setLayout(new BoxLayout(rightBank, BoxLayout.Y_AXIS));
        
        riverPanel = new JPanel();
        riverPanel.setBorder(BorderFactory.createTitledBorder("River"));
        riverPanel.setLayout(new BoxLayout(riverPanel, BoxLayout.Y_AXIS));
        
        // Initialize lists
        leftListModel = new DefaultListModel<>();
        rightListModel = new DefaultListModel<>();
        boatListModel = new DefaultListModel<>();
        
        // Add initial items to the left bank
        leftListModel.addElement("🧑‍🌾 Farmer");
        leftListModel.addElement("🐺 Wolf");
        leftListModel.addElement("🐐 Goat");
        leftListModel.addElement("🥬 Cabbage");
        
        JList<String> leftList = new JList<>(leftListModel);
        JList<String> rightList = new JList<>(rightListModel);
        JList<String> boatList = new JList<>(boatListModel);
        
        // Makes river space blue
        boatList.setBackground(Color.BLUE);
        
        leftBank.add(new JScrollPane(leftList));
        rightBank.add(new JScrollPane(rightList));
        riverPanel.add(new JScrollPane(boatList));
        
        // Create buttons
        instructButton = new JButton("Instructions");
        moveButton = new JButton("Move");
        resignButton = new JButton("Resign");
        recordsButton = new JButton("Hall of Fame");
        resetButton = new JButton("Reset");
        
        // Instructions, Opens Instruction GUI
        instructButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopTimer();
                JOptionPane.showMessageDialog(null, "Your goal is to move all 4 to the other side of the river on the boat.\n"
                        + "However, the boat only allows the Farmer to take either the Goat and Cabbage, the Goat and Wolf, or Wolf and Cabbage at the same time. The boat will not move without the farmer!\n"
                        + "If the Goat and Cabbage are left alone, the goat will eat the cabbage. If the Goat and Wolf are left alone, the wolf will eat the goat.\n"
                        + "\nAnd don't worry, your timer has been paused! 😉 It'll resume when you press 'OK'. Good luck! Try for that record!",
                        "Instructions", JOptionPane.OK_OPTION);
                startTimer();
            }
        });

        // Move Button; Allows boat to 'move'
        moveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveAction(leftList, rightList, boatList);
            }
        });
        
        // Opens Resignation GUI
        resignButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopTimer();
                Resignation();
                
                // Starts timer again if player decides to continue
                startTimer();
            }
        });
        
        recordsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showHallOfFame();
            }
        });
        
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(instructButton);
        buttonPanel.add(moveButton);
        buttonPanel.add(resignButton);
        buttonPanel.add(recordsButton);
        buttonPanel.add(resetButton);
        
        // Code for timer
        timerLabel = new JLabel("", SwingConstants.CENTER);
        timerLabel.setText("Time Passed: " + secondsPassed + " seconds");
        timer = new Timer(1000, e -> {
            secondsPassed++;
            timerLabel.setText("Time Passed: " + secondsPassed + " seconds");
        });
        
        // Add panels to frame
        getContentPane().add(leftBank, BorderLayout.WEST);
        getContentPane().add(rightBank, BorderLayout.EAST);
        getContentPane().add(riverPanel, BorderLayout.CENTER);
        getContentPane().add(timerLabel, BorderLayout.NORTH);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        
        // Starts timer once GUI opens
        startTimer(); 
    }
    
    // Method to start timer
    public void startTimer() {
        timer.start();
    }

    // Method to stop timer
    public void stopTimer() {
        timer.stop();
    }

    // Move action method
    private void moveAction(JList<String> leftList, JList<String> rightList, JList<String> boatList) {
        List<String> leftselect = leftList.getSelectedValuesList();
        List<String> boatselect = boatList.getSelectedValuesList();
        List<String> rightselect = rightList.getSelectedValuesList();
        
        if (leftselect.isEmpty() && rightselect.isEmpty() && boatselect.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No item selected!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check invalid moves and conditions
        if (boatListModel.contains("🧑‍🌾 Farmer") && (boatListModel.contains("🐺 Wolf") && boatListModel.contains("🐐 Goat"))) {
            JOptionPane.showMessageDialog(this, "The wolf ate the goat", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else if (boatListModel.contains("🧑‍🌾 Farmer") && (boatListModel.contains("🐐 Goat") && boatListModel.contains("🥬 Cabbage"))) {
            JOptionPane.showMessageDialog(this, "The goat ate the cabbage", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (leftselect.size() > 2 || boatListModel.size() > 2) {
            JOptionPane.showMessageDialog(this, "You can only move up to 2 items at a time!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!boatListModel.contains("🧑‍🌾 Farmer") && (boatListModel.size() > 1 || !boatselect.isEmpty())) {
            JOptionPane.showMessageDialog(this, "You forgot to take the farmer!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Moving items in left bank
        for (String item : leftselect) {
            boatListModel.addElement(item);
            leftListModel.removeElement(item);
        }

        // Moving items from right bank
        for (String item : rightselect) {
            boatListModel.addElement(item);
            rightListModel.removeElement(item);
        }

        // Moving items from boat list to right bank
        for (String item : boatselect) {
            rightListModel.addElement(item);
            boatListModel.removeElement(item);
        }
        
        checkGameOver();
    }

    private void Resignation() {
        int confirmDialog = JOptionPane.showConfirmDialog(null, "Are you sure you want to give up?", "Resign?", JOptionPane.YES_NO_OPTION);
        if (confirmDialog == JOptionPane.YES_OPTION) {
            stopTimer();
            showSolution();
            resetGame();
        }
    }

    private void showSolution() {
        JOptionPane.showMessageDialog(this, "Solution: \n1. Farmer and Goat go to the right\n" +
                "2. Farmer returns alone\n" +
                "3. Farmer takes Wolf to the right\n" +
                "4. Farmer returns with Goat\n" +
                "5. Farmer takes Cabbage to the right\n" +
                "6. Farmer returns alone\n" +
                "7. Farmer takes Goat to the right\n"
                + "\n THE GAME WILL NOW RESET!");
        resetGame();
    }

    private void checkGameOver() {
        if (leftListModel.isEmpty() && boatListModel.isEmpty()) {
            stopTimer();
            JOptionPane.showMessageDialog(this, "Congratulations! You have successfully moved all items to the right bank.", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            showHallOfFame(); // Show hall of fame after winning
            resetGame();
        }
    }

    private void showHallOfFame() {
        String name = JOptionPane.showInputDialog(this, "Please enter your name:");
        if (leftListModel.isEmpty() && boatListModel.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hall of Fame:\n\nName: " + name + "\nTime taken: " + secondsPassed + " seconds", "Hall of Fame", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void resetGame() {
        leftListModel.clear();
        rightListModel.clear();
        boatListModel.clear();
        
        leftListModel.addElement("🧑‍🌾 Farmer");
        leftListModel.addElement("🐺 Wolf");
        leftListModel.addElement("🐐 Goat");
        leftListModel.addElement("🥬 Cabbage");
        
        secondsPassed = 0;
        startTimer();
        
        validate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WolfGoatCabbage().setVisible(true);
            }
        });
    }
}
