import java.util.Random;

public class TileGame {
	private static int gridSize = 4;
	private static int zeroPosition;
	private static int maxPosition;
	private static int minPosition = 0;

	public static void main(String[] args) {
		maxPosition = (int) Math.pow(gridSize + 0.0, 2.0);
		zeroPosition = maxPosition - 1;
		
		int [] winValues = new int [maxPosition];
		int [] currValues = new int [maxPosition];
		for (int i = 0; i < maxPosition; i++) {
			if (i < maxPosition - 1) {
				winValues[i] = i + 1;
				currValues[i] = i + 1;
			} else {
				winValues[i] = 0;
				currValues[i] = 0;				
			}
		}
		
	
		System.out.println("length = " + winValues.length);
		for (int i = 0; i < winValues.length; i++) {
			System.out.println("Array Val = " + winValues[i]);
			
		}
		
		
		currValues = shuffle(currValues);
		for (int i = 0; i < currValues.length; i++) {
			System.out.println("Shuffled Val = " + currValues[i]);
		}
		
		
		
		
		int tempPos = zeroPosition + 4;
		if(canMoveUp(tempPos) || canMoveDown(tempPos) || canMoveLeft(tempPos) || canMoveRight(tempPos)) {
			currValues[zeroPosition] = currValues[tempPos];
			currValues[tempPos] = 0;
			zeroPosition = tempPos;
		} else {
			String invalid = "invalidMove";
		}
		
		
		
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
		if (thisPosition - 1 == zeroPosition && ) {
			canMove = true;
		}
		return canMove;
	}
	
	private static boolean canMoveRight(int thisPosition) {
		boolean canMove = false;
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
	
}



































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
    private int [] nullCoordinate;







	public static void main(String[] args) {
		static GamePanels gameForm = new GamePanels();
		gameForm.setTitle("Tile Game");
		gameForm.setVisible(true);
		gameForm.setResizable(false);
		gameForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		maxPosition = (int) Math.pow(gridSize + 0.0, 2.0);
		zeroPosition = maxPosition - 1;
		
		int [] winValues = new int [maxPosition];
		int [] currValues = new int [maxPosition];
		for (int i = 0; i < maxPosition; i++) {
			if (i < maxPosition - 1) {
				winValues[i] = i + 1;
				currValues[i] = i + 1;
			} else {
				winValues[i] = 0;
				currValues[i] = 0;				
			}
		}

		currValues = shuffle(currValues);

		
		

		
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
		if (thisPosition - 1 == zeroPosition) {
			canMove = true;
		}
		return canMove;
	}
	
	private static boolean canMoveRight(int thisPosition) {
		boolean canMove = false;
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
	

	public void actionPerformed(ActionEvent thisEvent) {
	
		if (thisEvent.getSource() == shuffleButton) {
			gameButtons = shuffleButtons(gameButtons); 
			buttonPositions = setButtonPos(gameButtons);
			addNumberPanels(boxPanels, buttonPositions);



			getContentPane().revalidate();
			getContentPane().repaint();
			getContentPane().revalidate();


		} else {
			JButton thisSource = (JButton) thisEvent.getSource();
			int tempPos = (int)thisSource.getText();
			if(canMoveUp(tempPos) || canMoveDown(tempPos) || canMoveLeft(tempPos) || canMoveRight(tempPos)) {
				currValues[zeroPosition] = currValues[tempPos];
				currValues[tempPos] = 0;
				zeroPosition = tempPos;
			} else {
				String invalid = "invalid Move";
				textBox.setText(invalid);
			}
			

			// System.exit(0);
		}
	}






	class GamePanels extends JFrame implements ActionListener {

		public GamePanels() {
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


			JButton [] [] buttonPositions = new JButton [gridSize] [gridSize];
			int [] nullCoordinate = new int [2];

			for (int i = 0; i < 16; i++) {

				gameButtons[i] = new JButton();
				if (i < 15) {
					gameButtons[i].setLabel(Integer.toString(i + 1));
				} else{
					gameButtons[i].setLabel("");
					index = 16;

						nullCoordinate[0] = 3;
						nullCoordinate[1] = 3;

				}
				gameButtons[i].addActionListener(this);
			
			//	panelOne.add(gameButtons[i]);
			}

			buttonPositions = setButtonPos(gameButtons);

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
			

			addNumberPanels(boxPanels, buttonPositions);

			
			// Add Panels to form
			// updateButtons();

			contentPane.add(panelOne);
			contentPane.add(panelTwo);
			contentPane.add(panelThree);
			contentPane.add(panelFour);
			contentPane.add(panelFive);
			setContentPane(contentPane);

		}


	}


	public JPanel createPanel() {
		JPanel thisPanel = new JPanel();
		thisPanel.setLayout (new GridLayout(1, 4));
		thisPanel.setBackground(Color.lightGray);
		return thisPanel;
	}




	public void addNumberPanels(JPanel [] thesePanels, JButton [] [] positions) {
		JButton thisButton = null;
		for (int i = 0; i < thesePanels.length; i++){
			for (int j = 0; j < 4; j++) {
				thisButton = positions[i] [j];
				thesePanels[i].add(thisButton);
			}
		}		
	}




	



}
	

