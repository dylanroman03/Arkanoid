package main;

import static managers.StatusManager.renderGoals;
import static utilities.Constants.BACKGROUND_PATH;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import entities.Ball;
import entities.Player;
import managers.BrickManager;
import utilities.LoadSave;

public class Game {
	private GamePanel gamePanel;
	private final int FPS_SET = 120;
	private final int UPS_SET = 200;
	private boolean isGaming = true;
	private boolean isPlaying = true;
	private BufferedImage background;

	private Player player;
	private Ball ball;
	private BrickManager brickManager;

	public final static int TILES_DEFAULT_SIZE = 13;
	public final static float SCALE = 3f;
	public final static int TILES_WIDTH = 20;
	public final static int TILES_HEIGTH = 15;
	public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
	public final static int GAME_WIDTH = TILES_SIZE * TILES_WIDTH;
	public final static int GAME_HEIGHT = TILES_SIZE * TILES_HEIGTH;
	public final static boolean DEBUG = false;

	public Game() {
		background = LoadSave.getImage(BACKGROUND_PATH);
		initClasses();

		gamePanel = new GamePanel(this);
		new GameWindow(gamePanel);
		gamePanel.requestFocus();

		startGameLoop();
	}

	private void initClasses() {
		int xInit = 0;
		int yInit = GAME_HEIGHT - TILES_SIZE;

		player = new Player(xInit, yInit, TILES_SIZE * 2, TILES_SIZE  / 2);


		brickManager = new BrickManager();
		brickManager.setPlayer(player);

		ball = new Ball(player.getHitBox().x, player.getHitBox().y - 25);
		ball.setGlobeManager(brickManager);
		ball.setPlayer(player);
	}

	private void startGameLoop() {
		while (isGaming) {
			run();
		}
	}

	public void update() {
		player.update(ball);
		ball.update((int) player.getHitBox().x, (int) (player.getHitBox().y - 25));

		if (player.getFails() == 3) {
			isPlaying = false;	
			callDialog(false);
		}

		if (brickManager.checkWin()) {
			isPlaying = false;
			callDialog(true);
		}
	}

	public void render(Graphics g) {
    g.drawImage(background, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
		brickManager.render(g);
		player.render(g);
		ball.render(g, (int) player.getHitBox().x, (int) (player.getHitBox().y - 25));
		renderGoals(g, player.getGoals(), 0);
		renderGoals(g, player.getFails(), TILES_SIZE);
	}

	public void run() {

		double timePerFrame = 1000000000.0 / FPS_SET;
		double timePerUpdate = 1000000000.0 / UPS_SET;

		long previousTime = System.nanoTime();

		long lastCheck = System.currentTimeMillis();

		double deltaU = 0;
		double deltaF = 0;

		while (isGaming) {
			long currentTime = System.nanoTime();

			deltaU += (currentTime - previousTime) / timePerUpdate;
			deltaF += (currentTime - previousTime) / timePerFrame;
			previousTime = currentTime;

			if (deltaU >= 1) {
				update();
				deltaU--;
			}

			if (deltaF >= 1) {
				if (isPlaying) {
					gamePanel.repaint();
				}
				deltaF--;
			}

			if (System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
			}
		}

	}

	protected void callDialog(boolean winning) {
		String message = winning ? "Felicidades has ganado" : "Lo Sentimos has Perdido";
		message += " tu puntaje es: "  + player.getGoals() + "\n ¿Quieres jugar de nuevo?";
		new Dialog(gamePanel, this, message);
	}

	public void playAgain() {
		initClasses();
		isPlaying = true;
	}

	public Player getPlayer() {
		return player;
	}

	public Ball getBall() {
		return ball;
	}
}
