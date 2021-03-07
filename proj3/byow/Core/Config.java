package byow.Core;

public class Config {
    public int worldWidth;
    public int worldHeight;
    public int frameWidth;
    public int frameHeight;

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
