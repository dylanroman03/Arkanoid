package utilities;

import static utilities.Constants.PlayerConstants.RUNNING_LEFT;
import static utilities.Constants.PlayerConstants.RUNNING_RIGHT;

import entities.Player;
import main.Game;

public class Helpers {

  public static boolean canMove(Player player, float x, float y) {
    float height = player.getHitBox().height;
    float width = player.getHitBox().width;

    switch (player.getPlayerAction()) {
      case RUNNING_LEFT:
        if (!isLimit(x, y))
          if (!isLimit(x, y + height))
            return true;
        break;
      case RUNNING_RIGHT:
        if (!isLimit(x + width, y))
          if (!isLimit(x + width, y + height))
            return true;
        break;
    }

    return false;
  }


  public static boolean isLimitBottom(float y) {
		if (y >= Game.GAME_HEIGHT)
			return true;

    return false;
  }

  public static boolean isLimitTop(float y) {
		if (y <= 0)
			return true;

    return false;
  }

  public static boolean isLimitRigth(float x) {
		if (x >= Game.GAME_WIDTH)
			return true;

    return false;
  }

  public static boolean isLimitLeft(float x) {
		if (x <= 0)
			return true;

    return false;
  }


  public static boolean isLimit(float x, float y) {
    if (x < 0 || x >= Game.GAME_WIDTH)
			return true;
		if (y < 0 || isLimitBottom(y))
			return true;

    return false;
  }
}
