package io.github.seanboyy.lotReset.util.objects;

/**
 * Section object
 * @author <a href=https://github.com/seanboyy>Seanboyy</a>
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
	
	/**
	 * Sets the section's Y axis value
	 * @param y the value to set the y axis value to
	 */
	public void setY(long y){
		this.y = y;
	}
}
