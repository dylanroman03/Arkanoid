package managers;

import static utilities.Constants.getNumberPath;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import utilities.LoadSave;

public class StatusManager {
  public static void renderGoals(Graphics g, int goals, int y) {
    int x =  Game.TILES_SIZE * 15;

    String number = String.valueOf(goals);
    char[] digits1 = number.toCharArray();

    for (char c : digits1) {
      String path = getNumberPath(Character.getNumericValue(c));
      BufferedImage image = LoadSave.getImage(path);

      g.drawImage(image, x, y, Game.TILES_SIZE / 2, Game.TILES_SIZE / 2, null);
      x += Game.TILES_SIZE;
    }
  }
  
}
