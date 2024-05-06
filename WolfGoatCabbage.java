package project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.border.TitledBorder;
import javax.swing.border.CompoundBorder;
import java.awt.FlowLayout;
import java.awt.Component;


public class WolfGoatCabbage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel leftside;
	private JPanel rightside;
	private JButton resgin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WolfGoatCabbage frame = new WolfGoatCabbage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public WolfGoatCabbage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		//Give a title to our puzzle project
		contentPane.setBorder(new TitledBorder(new CompoundBorder(), "Wolf, Goat and Cabbage Puzzle", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));

		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		
		//Resign or quit button
		resgin = new JButton("Resign");
		resgin.setAlignmentX(Component.CENTER_ALIGNMENT);
		resgin.setBorderPainted(false);
		resgin.setBackground(new Color(255, 0, 0));
		resgin.setVerticalAlignment(SwingConstants.BOTTOM);
		resgin.setForeground(new Color(0, 0, 0));
		resgin.setToolTipText("Click the button to see solution!");
		resgin.setFont(new Font("Times New Roman", Font.ITALIC, 14));
		resgin.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(resgin, BorderLayout.SOUTH);
		//used Unicode Party website to get emojis for characters 
		//Goat button
		JButton Goat = new JButton("🐐 Goat");
		Goat.setFont(new Font("Segoe UI Emoji", Font.ITALIC, 14));
		Goat.setToolTipText("Do not leave him alone with Cabbage");
		Goat.setBackground(new Color(128, 128, 64));
		contentPane.add(Goat);
		
		//wolf button
		JButton Wolf = new JButton(" 🐺 Wolf");
		Wolf.setToolTipText("Do not leave him alone with the Goat\r\n");
		Wolf.setBackground(new Color(192, 192, 192));
		Wolf.setHorizontalAlignment(SwingConstants.LEADING);
		Wolf.setFont(new Font("Segoe UI Emoji", Font.ITALIC, 14));
		contentPane.add(Wolf);
		
		//Cabbage button
		JButton Cabbage = new JButton("🥬 Cabbage");
		Cabbage.setBackground(new Color(128, 255, 0));
		Cabbage.setFont(new Font("Segoe UI Emoji", Font.ITALIC, 14));
		contentPane.add(Cabbage, BorderLayout.WEST);
		
		//Farmer button
		JButton Farmer = new JButton("🧑‍🌾🏻 Farmer");
		Farmer.setFont(new Font("Segoe UI Emoji", Font.ITALIC, 14));
		contentPane.add(Farmer);
		
		//river space 
		JPanel riverPanel = new JPanel();
		riverPanel.setBackground(Color.BLUE);
		contentPane.add(riverPanel);
		riverPanel.setPreferredSize(new Dimension(100,220));
		
		//chat gpt uses rightpanel for rightside and leftpanel for leftside 
		//objects can move from the left side to the right side 
		leftside = new JPanel();
		rightside = new JPanel();
		contentPane.add(leftside);
		contentPane.add(rightside);
	
		
	}

}
