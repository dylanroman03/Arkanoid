package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import inputs.KeyboardInputs;

public class GamePanel extends JPanel {
	private Game game;

	public GamePanel(Game game) {
		this.game = game;

		setPanelSize();
		addKeyListener(new KeyboardInputs(this));
	}

	private void setPanelSize() {
		Dimension size = new Dimension(Game.GAME_WIDTH, Game.GAME_HEIGHT);
		setPreferredSize(size);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.white);
		for (int i = 0; i < 64; i++)
			for (int j = 0; j < 40; j++)
				g.fillRect(i * 20, j * 20, 20, 20);

		game.render(g);

	}

	public Game getGame() {
		return game;
	}

}
