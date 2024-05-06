package project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.border.CompoundBorder;
import java.awt.FlowLayout;

public class WolfGoatCabbage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

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
		contentPane.setBorder(new TitledBorder(new CompoundBorder(), "Wolf, Goat and Cabbage Puzzle", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));

		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton Resgin = new JButton("Resign");
		Resgin.setBackground(new Color(255, 0, 0));
		Resgin.setVerticalAlignment(SwingConstants.BOTTOM);
		Resgin.setForeground(new Color(0, 0, 0));
		Resgin.setToolTipText("Click the button to see solution!");
		Resgin.setFont(new Font("Times New Roman", Font.ITALIC, 14));
		Resgin.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(Resgin);
		
		JButton btnNewButton = new JButton("Goat");
		btnNewButton.setFont(new Font("Times New Roman", Font.ITALIC, 14));
		btnNewButton.setToolTipText("Do not leave him alone with Cabbage");
		btnNewButton.setBackground(new Color(128, 128, 64));
		contentPane.add(btnNewButton);
		
		JButton Wolf = new JButton("Wolf");
		Wolf.setToolTipText("Do not leave him alone with the Goat\r\n");
		Wolf.setBackground(new Color(192, 192, 192));
		Wolf.setHorizontalAlignment(SwingConstants.LEADING);
		Wolf.setFont(new Font("Times New Roman", Font.ITALIC, 14));
		contentPane.add(Wolf);
		
		JButton Cabbage = new JButton("Cabbage");
		Cabbage.setBackground(new Color(128, 255, 0));
		Cabbage.setFont(new Font("Times New Roman", Font.ITALIC, 14));
		contentPane.add(Cabbage);
		
	}

}
