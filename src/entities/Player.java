package entities;

import static utilities.Constants.PLAYER_PATH;
import static utilities.Constants.PlayerConstants.RUNNING_LEFT;
import static utilities.Constants.PlayerConstants.RUNNING_RIGHT;
import static utilities.Helpers.canMove;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import utilities.LoadSave;

public class Player extends Entity {
	private BufferedImage image;
	private int playerAction;
	private boolean moving = false;
	private boolean left, right;
	private float playerSpeed = 1f;
	private int goals = 0;
	private int fails = 3;

	public int getFails() {
		return fails;
	}

	public void setFails(int fails) {
		this.fails = fails;
	}

	public Player(float x, float y, int width, int height) {
		super(x, y, width, height);
		loadImages();
		initHitBox(x, y, width, height);
	}

	public void update(Ball ball) {
		updatePosition();
		setMoving();
	}

	public void render(Graphics g) {
		g.drawImage(image, (int) hitBox.x, (int) hitBox.y, width, height, null);

		if (Game.DEBUG) {
			showHitBox(g);
		}
	}

	private void setMoving() {
		if (moving) {
			if (left)
				playerAction = RUNNING_LEFT;
			else
				playerAction = RUNNING_RIGHT;
		}
	}

	private void updatePosition() {
		moving = false;

		if (!left && !right)
			return;

		float xSpeed = 0, ySpeed = 0;

		if (left && !right) {
			xSpeed = -playerSpeed;
			moving = true;
		} else if (right && !left) {
			xSpeed = playerSpeed;
			moving = true;
		}

		boolean canMove = canMove(this, hitBox.x + xSpeed, hitBox.y + ySpeed);
		if (canMove) {
			hitBox.x += xSpeed;
			hitBox.y += ySpeed;
		}
	}

	private void loadImages() {
		image = LoadSave.getImage(PLAYER_PATH);
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
		if (left) {
			this.right = false;
		}
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;

		if (right) {
			this.left = false;
		}
	}

	public void resetDirection() {
		left = false;
		right = false;
		moving = false;
	}

	public int getPlayerAction() {
		return playerAction;
	}

	public void setGoals(int goals) {
		this.goals += goals;
	}

	public int getGoals() {
		return goals;
	}
}
