import java.util.Random;

public class TileGame {
	private static int gridSize = 4;
	private static int zeroPosition;
	private static int maxPosition;
	private static int minPosition = 0;
	

	
	
	public static void main(String[] args) {
	
	
	
		int [] winValues = new int [] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0};
		int [] currValues = new int [] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0};
		zeroPosition = winValues.length - 1;
		maxPosition = zeroPosition;
		System.out.println("length = " + winValues.length);
		for (int i = 0; i < winValues.length; i++) {
			System.out.println("Array Val = " + winValues[i]);
			
		}
		
		
		currValues = shuffle(currValues);
		for (int i = 0; i < currValues.length; i++) {
			System.out.println("Shuffled Val = " + currValues[i]);
		}
		
		
	
	}
	
	
	private static int [] shuffle(int [] toShuffle) {
		for (int i = 0; i < 10001; i++) {
			String direction = whichDirection();
			System.out.println(direction);
			switch (direction) {
				case "Up": 
					toShuffle = moveUp(toShuffle);
					break;
				case "Down": ;
					toShuffle = moveDown(toShuffle);
					break;
				case "Left": ;
					toShuffle = moveLeft(toShuffle);
					break;
				case "Right": ;
					toShuffle = moveRight(toShuffle);
					break;
			}
			
			
		}
		return toShuffle;
	}
	
	private static int [] moveUp(int [] toShuffle) {
		int targetPos = zeroPosition - gridSize;
		if(targetPos >= 0) {
			toShuffle[zeroPosition] = toShuffle[targetPos];
			toShuffle[targetPos] = 0;
			zeroPosition = targetPos;
		}
		
		return toShuffle;
	}
	
	private static int [] moveDown(int [] toShuffle) {
		int targetPos = zeroPosition + gridSize;
		if(targetPos < maxPosition) {
			toShuffle[zeroPosition] = toShuffle[targetPos];
			toShuffle[targetPos] = 0;
			zeroPosition = targetPos;
		}
		return toShuffle;
	}
	
	private static int [] moveLeft(int [] toShuffle) {
		int targetPos = zeroPosition - 1;
	
		if(zeroPosition >= 0) {
			// If there is no remainder then target pos is end of previous line and not valid
			if (targetPos % gridSize > 0 ) {
				toShuffle[zeroPosition] = toShuffle[targetPos];
				toShuffle[targetPos] = 0;
				zeroPosition = targetPos;
			}
		}
		return toShuffle;
	}
	
	private static int [] moveRight(int [] toShuffle) {
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

	

