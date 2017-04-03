import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TileGame3 {


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

	

	@SuppressWarnings("deprecation")

		Font tableFont = new Font("Courier", Font.PLAIN, 12);

		JPanel contentPane = new JPanel();

		contentPane.setLayout(new GridLayout(5, 1, 2, 2));


		final int FRAME_WIDTH = 500;
		final int FRAME_HEIGHT = 400;
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		panelOne = createPanel();
		// panelOne.setLayout (new GridLayout(1, 4));
		// panelOne.setBackground(Color.lightGray);

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
		


		JButton [] [] buttonPositions = new JButton [gridSize] [gridSize];
		int [] nullCoordinate = new int [2];

		for (int i = 0; i < 16; i++) {

			gameButtons[i] = new JButton();
			if (i < 15) {
				gameButtons[i].setLabel(Integer.toString(i + 1));
				currValues[i] = i + 1;
				winValues[i] = i + 1;
			} else{
				gameButtons[i].setLabel("");
				zeroPosition = 15;
				currValues[i] = 0;
				winValues[i] = 0;

			}
			gameButtons[i].addActionListener(this);
		
		//	panelOne.add(gameButtons[i]);
		}

		// buttonPositions = setButtonPos(gameButtons);

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
		// updateButtons();

		contentPane.add(panelOne);
		contentPane.add(panelTwo);
		contentPane.add(panelThree);
		contentPane.add(panelFour);
		contentPane.add(panelFive);
		setContentPane(contentPane);


		
	}









	
	
	
	
	
	private static boolean canMoveUp(int thisPosition) {
		boolean canMove = false;
		System.out.println("Up - thisPosition = " + thisPosition);
		System.out.println("gridSize = " + gridSize);
		System.out.println("zeroPosition = " + zeroPosition);
		if (thisPosition - gridSize == zeroPosition) {
			canMove = true;
		}
		return canMove;
	}
	
	
	
	private static boolean canMoveDown(int thisPosition) {
		boolean canMove = false;
		System.out.println("Down - thisPosition = " + thisPosition);
		System.out.println("gridSize = " + gridSize);
		System.out.println("zeroPosition = " + zeroPosition);
		if (thisPosition + gridSize == zeroPosition) {
			canMove = true;
		}
		return canMove;
	}
	
	private static boolean canMoveLeft(int thisPosition) {
		boolean canMove = false;
		System.out.println("Left - thisPosition = " + thisPosition);
		System.out.println("gridSize = " + gridSize);
		System.out.println("zeroPosition = " + zeroPosition);
		if (thisPosition - 1 == zeroPosition) {
			canMove = true;
		}
		return canMove;
	}
	
	private static boolean canMoveRight(int thisPosition) {
		boolean canMove = false;
		System.out.println("Right - thisPosition = " + thisPosition);
		System.out.println("gridSize = " + gridSize);
		System.out.println("zeroPosition = " + zeroPosition);
		if (thisPosition + 1 == zeroPosition) {
			canMove = true;
		}
		return canMove;
	}
	
	
	
	private static int [] shuffle(int [] toShuffle) {
		for (int i = 0; i < 1001; i++) {
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
		if(targetPos < maxPosition) {
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
			if (targetPos % gridSize > 0 ) {
				toShuffle[zeroPosition] = toShuffle[targetPos];
				toShuffle[targetPos] = 0;
				zeroPosition = targetPos;
			}
		}
		return toShuffle;
	}
	
	private static int [] shuffleRight(int [] toShuffle) {
		int targetPos = zeroPosition + 1;
	/*	System.out.println("targetPos = " + targetPos);
		System.out.println("zeroPosition = " + zeroPosition);
		System.out.println("maxPosition = " + maxPosition); */
		if(targetPos < maxPosition) {
			
			if (zeroPosition % gridSize > 0 ) {
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
	
	
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent thisEvent) {
	
		if (thisEvent.getSource() == shuffleButton) {
			for (int i = 0; i < 16; i++)
				System.out.println("Value " + currValues[i]);
			
			currValues = shuffle(currValues);
			for (int i = 0; i < 16; i++)
			System.out.println("Value " + currValues[i]);
			// gameButtons = shuffle();
			for (int i = 0; i < 16; i++) {
				if (currValues[i] > 0) {
					gameButtons[i].setLabel(Integer.toString(currValues[i]));
				} else {
					gameButtons[i].setLabel("");
				}
			}
			// buttonPositions = setButtonPos(gameButtons);
	
	
			// addNumberPanels(boxPanels, buttonPositions);
	
	
	
			getContentPane().revalidate();
			getContentPane().repaint();
			getContentPane().revalidate();
	
	
		} else {
			JButton thisSource = (JButton) thisEvent.getSource();
			int tempPos = zeroPosition;
			if (thisSource.getText() != "") {
				tempPos = Integer.parseInt(thisSource.getText()) - 1;
			}
			
			
			if(canMoveUp(tempPos) || canMoveDown(tempPos) || canMoveLeft(tempPos) || canMoveRight(tempPos)) {
				System.out.println("currValues = " + currValues);
				System.out.println("zeroPosition = " + zeroPosition);
				System.out.println("tempPos = " + tempPos);
				
				currValues[zeroPosition] = currValues[tempPos];
				gameButtons[zeroPosition].setLabel(Integer.toString(currValues[tempPos]));
				currValues[tempPos] = 0;
				gameButtons[tempPos].setLabel("");
				zeroPosition = tempPos;
				
				if (checkWin(winValues, currValues)) {
					textBox.setText("Congratulations, you have won!!!!!");
				}
				
				
				getContentPane().revalidate();
				getContentPane().repaint();
				getContentPane().revalidate();
				
			} else {
				String invalid = "invalid Move";
				textBox.setText(invalid + tempPos);
			}
			
	
			// System.exit(0);
		}
	}
	

	public JPanel createPanel() {
		JPanel thisPanel = new JPanel();
		thisPanel.setLayout (new GridLayout(1, 4));
		thisPanel.setBackground(Color.lightGray);
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
	private static int maxPosition;
	private static int minPosition = 0;

	private JButton [] [] buttonPositions;
	private JLabel startLabel, endLabel, incrementLabel;
    private JTextField startValue, endValue, incrementValue;
    private JButton [] gameButtons;
    private JButton shuffleButton, tempButton;
    private JTextArea textBox;
    private JPanel contentPane, panelOne, panelTwo, panelThree, panelFour, panelFive;
    private JPanel [] boxPanels;
    private int index, nullPosition;
    private int [] nullCoordinate, winValues, currValues;

}


	



