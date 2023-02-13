package utilities;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class LoadSave {
  public static BufferedImage getImage(String path) {
    BufferedImage img = null;

    try {
      InputStream is = new FileInputStream(path);
      img = ImageIO.read(is);
      is.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return img;
  }
}
