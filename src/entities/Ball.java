package entities;

import static utilities.Constants.BALL_PATH;
import static utilities.Constants.Directions.DOWN;
import static utilities.Constants.Directions.LEFT;
import static utilities.Constants.Directions.RIGHT;
import static utilities.Constants.Directions.UP;
import static utilities.Helpers.isLimitBottom;
import static utilities.Helpers.isLimitLeft;
import static utilities.Helpers.isLimitRigth;
import static utilities.Helpers.isLimitTop;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import main.Game;
import managers.BrickManager;
import utilities.LoadSave;

public class Ball extends Entity {
  private BufferedImage image;
  private boolean flying = false;
  private BrickManager globeManager;
  private double movingY = -1;
  private double movingX = -1;
  private Player player;

  public Ball(float x, float y) {
    super(x, y, Game.TILES_SIZE / 2, Game.TILES_SIZE / 2);
    image = LoadSave.getImage(BALL_PATH);
    initHitBox(x, y, Game.TILES_SIZE / 2, Game.TILES_SIZE / 2);
  }

  public void render(Graphics g, int x, int y) {
    g.drawImage(image, (int) hitBox.x, (int) hitBox.y, (int) getHitBox().width, (int) getHitBox().height, null);

    if (Game.DEBUG) {
      showHitBox(g);
    }
  }

  public void update(int x, int y) {
    if (flying) {
      hitBox.y += movingY;
      hitBox.x += movingX;
      rebootShooting();
    } else {
      updatePosition((float) x, (float) y);
    }
  };

  private void rebootShooting() {
    if (globeManager.intersectBrick(hitBox, LEFT) || globeManager.intersectBrick(hitBox, RIGHT)) {
      setMovingX(-movingX);
    } else if (globeManager.intersectBrick(hitBox, DOWN) || globeManager.intersectBrick(hitBox, UP)) {
      setMovingY(-movingY);
    }

    if (isLimitBottom(hitBox.y)) {
      flying = false;
      setMovingY(-movingY);
      player.setFails(player.getFails() - 1);
    } else if (isLimitRigth(hitBox.x)) {
      setMovingX(-1);
    } else if (isLimitLeft(hitBox.x)) {
      setMovingX(1);
    } else if (isLimitTop(hitBox.y)) {
      setMovingY(1);
    }

    intersectsPlayer();
  }

  private void intersectsPlayer() {
    Rectangle2D rectangle = player.getHitBox();
    if (hitBox.intersects(rectangle)) {
      setMovingY(-1);
    }
  }

  private void updatePosition(float x, float y) {
    hitBox.x = x;
    hitBox.y = y;
  }

  public void setFlying(boolean b) {
    flying = true;
  }

  public void setGlobeManager(BrickManager globeManager) {
    this.globeManager = globeManager;
  }

  public void setMovingY(double directionY) {
    this.movingY = directionY;
  }

  public void setMovingX(double directionX) {
    this.movingX = directionX;
  }

  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }
}
