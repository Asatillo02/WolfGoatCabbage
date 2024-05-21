package updated;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;


public class FarmerPuzzle extends JFrame {
    private JPanel leftBank;
    private JPanel rightBank;
    private JPanel riverPanel;
    
    private JButton instructButton;
    private JButton moveButton;
    private JButton resignButton;
    private JButton recordsButton;
  
    
    private int secondsPassed = 0;
    private JLabel timerLabel;
    private Timer timer;
    
    private DefaultListModel<String> leftListModel;
    private DefaultListModel<String> rightListModel;
    private DefaultListModel<String> boatListModel;
    
    public FarmerPuzzle() {
        setTitle("Farmer, Wolf, Goat, and Cabbage Puzzle");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
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
        
        // Instructions, Opens Instruction GUI
        instructButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	stopTimer();
            	JOptionPane.showMessageDialog(null, "Your goal is to move all 4 to the other side of the river on the boat.\n"
            			+ "However, the boat only allows the Farmer to take either the Goat and Cabbage, the Goat and Wolf, or Wolf and Cabbage at the same time. The boat will not move without the farmer!\n"
            			+ "If the Goat and Cabbage are left alone, the goat will eat the cabbage. If the Goat and Wolf are left alone, the wolf will eat the goat.\n"
				+ "To select multiple elements, hold CTRL (if on Windows/Linus) or Command (if on Mac) while selecting elements.\n"
            			+ "\nAnd don't worry, your timers been paused! 😉 It'll resume when you press 'OK'. Good luck! Try for that record!"
						, "Instructions", JOptionPane.OK_OPTION);
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
            
            }
        });
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(instructButton);
        buttonPanel.add(moveButton);
        buttonPanel.add(resignButton);
        buttonPanel.add(recordsButton);
        
        // Code for timer
        timerLabel = new JLabel("", SwingConstants.CENTER);
		timerLabel.setText("Time Passed: " + secondsPassed + " seconds");
        timer = new Timer(1000, e -> {
            secondsPassed++;
            timerLabel.setText("Time Passed: " + secondsPassed + " seconds");
        });
        
        // Add panels to frame
        add(leftBank, BorderLayout.WEST);
        add(rightBank, BorderLayout.EAST);
        add(riverPanel, BorderLayout.CENTER);
        add(timerLabel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Starts timer once GUI opens
        startTimer(); 
    }

	// Method to start timer
    public void startTimer() {
		timer.start();
	}
	
    // Method to start timer
	public void stopTimer() {
		timer.stop();
	}
	
	// NEEDS TO BE DONE
    private void moveAction(JList<String> leftList, JList<String> rightList, JList<String> boatList) {
       
    }
    
    private void Resignation() {
    	int confirmDialog = JOptionPane.showConfirmDialog(null, "Are you sure you want to give up?"
    											, "Resign?", JOptionPane.YES_NO_OPTION);
    	if(confirmDialog == JOptionPane.YES_OPTION) {
    		showSolution();
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
    
    // NEEDS TO BE DONE
    private void hallOfFame() {
    	
    }
    
    // NEEDS TO BE DONE
    private void checkGameState() {
    	
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
                new FarmerPuzzle().setVisible(true);
            }
        });
    }
}
