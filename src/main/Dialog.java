package main;

import javax.swing.JOptionPane;

public class Dialog extends JOptionPane {
  Game game;

  public Dialog(GamePanel panel, Game game, String message) {
    this.game = game;
    int result = showConfirmDialog(
        panel,
        message,
        "Quieres jugar de nuevo?",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE);

    if(result == JOptionPane.YES_OPTION) {
      game.playAgain();
    } else {
      System.exit(0);
    }
  }

}