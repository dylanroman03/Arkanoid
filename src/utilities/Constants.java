package utilities;

public class Constants {
	public static class Directions {
		public static final int LEFT = 0;
		public static final int UP = 1;
		public static final int RIGHT = 2;
		public static final int DOWN = 4;
	}

	public static class PlayerConstants {
		public static final int RUNNING_LEFT = 1;
		public static final int RUNNING_RIGHT = 2;
	}


	public static final int GREEN_BRICK = 1;
	public static final int RED_BRICK = 2;
	public static final int YELLOW_BRICK = 3;

	public static String GetGlobePath (int globe) {
		switch (globe) {
			case GREEN_BRICK:
				return 	"res/Bricks/green.jpg";
			case RED_BRICK:
				return 	"res/Bricks/red.jpg";
			default: // 3 
				return 	"res/Bricks/yellow.jpg";
		}
	}

	public static int GetGlobePoints (int globe) {
		switch (globe) {
			case GREEN_BRICK:
				return 	300;
			case RED_BRICK:
				return 	200;
			default: // 3 
				return 	100;
		}
	}

	public static String PLAYER_PATH = "res/player.jpg";
	public static String BALL_PATH = "res/ball.png";
	public static String BACKGROUND_PATH = "res/background.png";

	public static String getNumberPath (int time) {
		return "res/Time/" + time + ".png";
	}
}
