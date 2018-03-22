package io.github.seanboyy.lotReset.util.objects;

/**
 * Section object
 * @author <a href=https://www.github.com/seanboyy>Seanboyy</a>
 * @since 3.0
 */
public class Section extends Chunk {
    /**
     * Y axis value
     */
    private long y;

    /**
     * Creates a new section object
     * @param x x axis value in block co-ordinates
     * @param y y axis value in block co-ordinates
     * @param z z axis value in block co-ordinates
     */
    public Section(long x, long y, long z){
        super(x, z);
        this.y = (long)Math.floor(y/16.);
    }

    /**
     * Gets the section's Y axis value
     * @return the y axis value
     */
    public long getY(){
        return this.y;
    }
}
