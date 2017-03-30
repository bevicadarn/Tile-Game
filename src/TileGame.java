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

	

