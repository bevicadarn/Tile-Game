import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;

public class TileGame {

	public static void main(String[] args) {
		GamePanels gameForm = new GamePanels();
		gameForm.setTitle("Tile Game");
		gameForm.setVisible(true);
		gameForm.setResizable(false);
		gameForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}	
}	

class GamePanels extends JFrame implements ActionListener {
	
	/**
	 * 
	 */
	public GamePanels() {

		Font tableFont = new Font("Courier", Font.PLAIN, 12);
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new GridLayout(5, 1, 2, 2));

		final int FRAME_WIDTH = 500;
		final int FRAME_HEIGHT = 400;
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		panelOne = createPanel();
		panelTwo = createPanel();
		panelThree = createPanel();
		panelFour = createPanel();

		boxPanels = new JPanel [4];
		boxPanels[0] = panelOne;
		boxPanels[1] = panelTwo;
		boxPanels[2] = panelThree;
		boxPanels[3] = panelFour;

		gameButtons = new JButton[16];
		
		currValues = new int [16];
		winValues = new int [16];
		
		for (int i = 0; i < 16; i++) {
			gameButtons[i] = new JButton();
			if (i < 15) {
				gameButtons[i].setText(Integer.toString(i + 1));
				currValues[i] = i + 1;
				winValues[i] = i + 1;
			} else{
				gameButtons[i].setText("");
				zeroPosition = 15;
				currValues[i] = 0;
				winValues[i] = 0;
			}
			gameButtons[i].addActionListener(this);
		}

		// Add output box
		panelFive = new JPanel();
		panelFive.setLayout(new GridLayout(1,2));
		textBox = new JTextArea();
		textBox.setFont(tableFont);
		textBox.setEditable(false);
		textBox.setBackground(Color.black);
		textBox.setForeground(Color.yellow);
		textBox.setLineWrap(true);
		textBox.setWrapStyleWord(true);
		panelFive.add(textBox);

		shuffleButton = new JButton("Shuffle");
		shuffleButton.addActionListener(this);
		shuffleButton.setBackground(Color.blue); 
		shuffleButton.setForeground(Color.white);
		panelFive.add(shuffleButton);
		

		addNumberPanels(boxPanels, gameButtons);

		
		// Add Panels to form

		contentPane.add(panelOne);
		contentPane.add(panelTwo);
		contentPane.add(panelThree);
		contentPane.add(panelFour);
		contentPane.add(panelFive);
		setContentPane(contentPane);

	}

	private static boolean canMoveUp(int thisPosition) {
		boolean canMove = false;

		if (thisPosition - gridSize == zeroPosition) {
			canMove = true;
		}
		return canMove;
	}
	
	private static boolean canMoveDown(int thisPosition) {
		boolean canMove = false;
		if (thisPosition + gridSize == zeroPosition) {
			canMove = true;
		}
		return canMove;
	}
	
	private static boolean canMoveLeft(int thisPosition) {
		boolean canMove = false;
		System.out.println((thisPosition) % gridSize);
		if (thisPosition - 1 == zeroPosition && (thisPosition) % gridSize > 0 ) {
			canMove = true;
		}
		return canMove;
	}
	
	private static boolean canMoveRight(int thisPosition) {
		boolean canMove = false;
		System.out.println((thisPosition) % gridSize);
		if (thisPosition + 1 == zeroPosition  && (thisPosition) % gridSize != 3 ) {
			canMove = true;
		}
		return canMove;
	}
	
	private static int [] shuffle(int [] toShuffle) {
		for (int i = 0; i < 550; i++) {
			String direction = whichDirection();
		
			switch (direction) {
				case "Up": 
					toShuffle = shuffleUp(toShuffle);
					break;
				case "Down": ;
					toShuffle = shuffleDown(toShuffle);
					break;
				case "Left": ;
					toShuffle = shuffleLeft(toShuffle);
					break;
				case "Right": ;
					toShuffle = shuffleRight(toShuffle);
					break;
			}
		}
		return toShuffle;
	}
	
	private static int [] shuffleUp(int [] toShuffle) {
		int targetPos = zeroPosition - gridSize;
		if(targetPos >= minPosition) {
			toShuffle[zeroPosition] = toShuffle[targetPos];
			toShuffle[targetPos] = 0;
			zeroPosition = targetPos;
		}
		return toShuffle;
	}
	
	private static int [] shuffleDown(int [] toShuffle) {
		int targetPos = zeroPosition + gridSize;
		if(targetPos < maxPosition + 1) {
			toShuffle[zeroPosition] = toShuffle[targetPos];
			toShuffle[targetPos] = 0;
			zeroPosition = targetPos;
		}
		return toShuffle;
	}
	
	private static int [] shuffleLeft(int [] toShuffle) {
		int targetPos = zeroPosition - 1;
		if(zeroPosition >= minPosition) {
			// If there is no remainder then target pos is end of previous line and not valid
			if (zeroPosition % gridSize > 0) {
				toShuffle[zeroPosition] = toShuffle[targetPos];
				toShuffle[targetPos] = 0;
				zeroPosition = targetPos;
			}
		}
		return toShuffle;
	}
	
	private static int [] shuffleRight(int [] toShuffle) {
		int targetPos = zeroPosition + 1;
		if(targetPos < maxPosition + 1) {
			// if no remainder then zero position is on the rightmost position so can't move
			if (targetPos % gridSize > 0 ) {
				toShuffle[zeroPosition] = toShuffle[targetPos];
				toShuffle[targetPos] = 0;
				zeroPosition = targetPos;
			}
		}
		return toShuffle;
	}
	
	private static  String whichDirection() {
		String direction = "";
		Random rn = new Random();
		int whichWay = rn.nextInt(4);
		
		 switch (whichWay) {
		 	case 0: direction = "Up";
		 		break;
		 	case 1: direction = "Down";
				break;
		 	case 2: direction = "Left";
		 		break;
		 	case 3: direction = "Right";
				break;
		 }
		 return direction;
	}
	
	public boolean checkWin (int [] current, int [] winning) { 
		boolean hasWon = true;
		for (int i = 0; i < current.length; i++) {
			if (current[i] != winning[i]) {
				hasWon = false;
			}
		}		
		return hasWon;
	}
	
	

	public void actionPerformed(ActionEvent thisEvent) {
	
		if (thisEvent.getSource() == shuffleButton) {
			
			currValues = shuffle(currValues);
			for (int i = 0; i < 16; i++) {
				if (currValues[i] > 0) {
					gameButtons[i].setText(Integer.toString(currValues[i]));
				} else {
					gameButtons[i].setText("");
				}
			}
			textBox.setText("");
	
			getContentPane().revalidate();
			getContentPane().repaint();
			getContentPane().revalidate();
	
	
		} else {
			JButton thisSource = (JButton) thisEvent.getSource();
			int tempPos = zeroPosition;
			int actualPos = tempPos;
	
			if (thisSource.getText() != "") {
				tempPos = Integer.parseInt(thisSource.getText()) - 1;
				for (int i = 0; i < gameButtons.length; i++){
					if (gameButtons[i].getText() != "") {
						if (tempPos == Integer.parseInt(gameButtons[i].getText()) - 1) {
							actualPos = i;
						}
					}
				}
			}
			
			
			if(canMoveUp(actualPos) || canMoveDown(actualPos) || canMoveLeft(actualPos) || canMoveRight(actualPos)) {
				
				currValues[zeroPosition] = currValues[actualPos];
				gameButtons[zeroPosition].setText(Integer.toString(currValues[actualPos]));
				currValues[actualPos] = 0;
				gameButtons[actualPos].setText("");
				zeroPosition = actualPos;
				textBox.setText("");
				
				if (checkWin(winValues, currValues)) {
					textBox.setText("Congratulations, you have won!!!!!");
				}
				
				getContentPane().revalidate();
				getContentPane().repaint();
				getContentPane().revalidate();
				
			} else {
				String invalid = "Invalid Move";
				textBox.setText(invalid);
			}
			
	
			// System.exit(0);
		}
	}
	
	public JPanel createPanel() {
		JPanel thisPanel = new JPanel();
		thisPanel.setLayout (new GridLayout(1, 4));
		thisPanel.setBackground(Color.yellow);
		return thisPanel;
	}
	
	public void addNumberPanels(JPanel [] thesePanels, JButton [] gameButtons) {
		JButton thisButton = null;
		int counter = 0;
		for (int i = 0; i < thesePanels.length; i++){
			for (int j = 0; j < 4; j++) {
				thisButton = gameButtons[counter];
				thesePanels[i].add(thisButton);
				counter++;
			}
		}		
	}

	private static int gridSize = 4;
	private static int zeroPosition;
	private static int maxPosition = 15;
	private static int minPosition = 0;
    private JButton [] gameButtons;
    private JButton shuffleButton;
    private JTextArea textBox;
    private JPanel contentPane, panelOne, panelTwo, panelThree, panelFour, panelFive;
    private JPanel [] boxPanels;
    private int [] winValues, currValues;

}
