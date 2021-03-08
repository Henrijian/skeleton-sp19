package byow.Core;

public class Config {
    public final String FILE_NAME = ".save_data";
    public final int worldWidth;
    public final int worldHeight;
    public final int frameWidth;
    public final int frameHeight;

    public Config(int worldWidth, int worldHeight, int frameWidth, int frameHeight) {
        if (worldWidth <= 0) {
            throw new IllegalArgumentException("Width of world cannot less than or equals to zero.");
        }
        if (worldHeight <= 0) {
            throw new IllegalArgumentException("Height of world cannot less than or equals to zero.");
        }
        if (frameWidth <= 0) {
            throw new IllegalArgumentException("Width of frame cannot less than or equals to zero.");
        }
        if (frameHeight <= 0) {
            throw new IllegalArgumentException("Height of frame cannot less than or equals to zero.");
        }
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
    }
}
