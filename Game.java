package ttt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Game {
	Position position = new Position();
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame("Java TTT");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLayout(new GridLayout(3, 3));
				final Game game = new Game();
				final JButton[] buttons = new JButton[9];
				for (int i = 0; i < 9; i++) {
					final int idx = i;
					final JButton button = new JButton();
					buttons[i] = button;
					button.setPreferredSize(new Dimension(100,100));
					button.setBackground(Color.WHITE);
					button.setOpaque(true);
					button.setFont(new Font(null, Font.CENTER_BASELINE, 70));
					button.addMouseListener(new MouseListener() {
						
						public void mouseReleased(MouseEvent e) {}
						
						public void mousePressed(MouseEvent e) {}
						
						public void mouseExited(MouseEvent e) {}
						
						public void mouseEntered(MouseEvent e) {}
						
						@Override
						public void mouseClicked(MouseEvent e) {
							button.setText("" + game.position.turn);
							game.move(idx);
							
							if (!game.position.gameEnd()){	
								int best = game.position.bestMove();
								buttons[best].setText("" + game.position.turn);
								game.move(best);
							}
							if (game.position.gameEnd()) {
								String message = "";
							
								if (game.position.win('x')) {
									message = "Wygra³eœ!";
								}
								else if (game.position.win('o')) {
									message = "Przegra³eœ!";
								}
								else {
									message = "Remis";
								}
								
								JOptionPane.showMessageDialog(null, message);
						}
					}
					});
					frame.add(button);
				}
				frame.pack();
				frame.setVisible(true);
			}
		});
		
	}
	protected void move(int idx) {
		position = position.move(idx);
		
	}

}
