import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;

public class ColorImageInTerminal {
    public static void main(String[] args) throws Exception {
        BufferedImage img = ImageIO.read(new File("your-image.jpg"));

        int width = 80;
        int height = img.getHeight() * width / img.getWidth();

        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        resized.getGraphics().drawImage(img, 0, 0, width, height, null);

        for (int y = 0; y < resized.getHeight(); y++) {
            for (int x = 0; x < resized.getWidth(); x++) {
                Color color = new Color(resized.getRGB(x, y));
                System.out.print("\u001b[48;2;" + color.getRed() + ";" + color.getGreen() + ";" + color.getBlue() + "m ");
            }
            System.out.print("\u001b[0m\n"); // Reset after each line
        }
    }
}
