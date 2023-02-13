package managers;

import static utilities.Constants.GetGlobePoints;
import static utilities.Constants.Directions.DOWN;
import static utilities.Constants.Directions.LEFT;
import static utilities.Constants.Directions.RIGHT;
import static utilities.Constants.Directions.UP;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import entities.Brick;
import entities.Player;
import main.Game;

public class BrickManager {
  private Brick[][] globes = new Brick[4][20];
  private Player player;

  public BrickManager() {
    int yInt = Game.TILES_SIZE * 2;
    for (int i = 0; i < globes.length; i++) {
      for (int j = 0; j < globes[0].length; j++) {
        Random r = new Random();
        int type = r.nextInt(4 - 1) + 1;
        globes[i][j] = new Brick((Game.TILES_SIZE * j), yInt + (Game.TILES_SIZE * i), type);
      }
    }
  }

  public void render(Graphics g) {
    for (Brick[] globes : globes) {
      for (Brick globe : globes) {
        if (globe.visible) {
          globe.render(g);
        }
      }
    }
  }

  public boolean intersectBrick(Rectangle2D missile, int direction) {
    for (Brick[] globes2 : globes) {
      for (Brick globe : globes2) {

        if (globe.visible) {
          Rectangle2D hitBox = globe.getHitBox();
          boolean intersected = false;

          if (direction == UP) {
            intersected = missile.intersects(hitBox.getX(), hitBox.getY(), hitBox.getWidth(), 1);
          } else if (direction == DOWN) {
            intersected = missile.intersects(hitBox.getX(), hitBox.getY() + hitBox.getHeight(),
                hitBox.getWidth(), 1);
          } else if (direction == RIGHT) {
            intersected = missile.intersects(hitBox.getX() + hitBox.getWidth(), hitBox.getY(), 1, hitBox.getHeight());
          } else if (direction == LEFT) {
            intersected = missile.intersects(hitBox.getX(), hitBox.getY(), 1, hitBox.getHeight());
          }

          if (intersected) {
            globe.visible = false;
            player.setGoals(GetGlobePoints(globe.type));
            return true;
          }
        }
      }
    }
    return false;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

  public boolean checkWin() {
    for (Brick[] globes2 : globes) {
      for (Brick globe : globes2) {
        if (globe.visible) {
          return false;
        }
      }
    }

    return true;
  }

}
