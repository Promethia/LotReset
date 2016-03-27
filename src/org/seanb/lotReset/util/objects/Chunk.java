package org.seanb.lotReset.util.objects;

public class Chunk {
	/**
	 * The x axis value of the chunk in chunk co-ordinates
	 */
	private long x;
	/**
	 * The y axis value of the chunk in chunk co-ordinates
	 */
	private long y;
	/**
	 * The z axis value of the chunk in chunk co-ordinates
	 */
	private long z;
	/**
	 * The name of the region file that contains the chunk
	 */
	private String region;
	
	/**
	 * Make a chunk object from chunk co-ordinates x and z,
	 * and set the region file location of the chunk to be r.x>>5.z>>5.mca
	 * @param x x axis value in chunk co-ordinates
	 * @param y y axis value in chunk co-ordinates
	 * @param z z axis value in chunk co-ordinates
	 */
	public Chunk(long x, long y, long z){
		this.x = x;
		this.y = y;
		this.z = z;
		this.region = "r." + (x >> 5) + "." + (z >> 5) + ".mca";
	}
	
	/**
	 * Make a chunk object from chunk co-ordinates x and z and file name
	 * @param x x axis value in chunk co-ordinates
	 * @param y y axis value in chunk co-ordinates
	 * @param z z axis value in chunk co-ordinates
	 * @param loc region file location of the chunk
	 */
	public Chunk(long x, long y, long z, String region){
		this.x = x;
		this.y = y;
		this.z = z;
		this.region = region;
	}
	
	/**
	 * Make a chunk object from block co-ordinates x, z, and then the max x and z
	 * and set the region file location of the chunk to be r.x>>5.z>>5.mca
	 * @param minX minimum x axis value in block co-ordinates
	 * @param minY minimum y axis value in block co-ordinates
	 * @param minZ minimum z axis value in block co-ordinates
	 * @param maxX maximum x axis value in block co-ordinates
	 * @param maxY maximum y axis value in block co-ordinates
	 * @param maxZ maximum z axis value in block co-ordinates
	 */
	public Chunk(long minX, long minZ, long maxX, long maxZ){
		this.x = (int)Math.floor(minX/16);
		this.z = (int)Math.floor(minZ/16);
		this.region = "r." + (this.x >> 5) + "." + (this.z >> 5) + ".mca";
	}
	
	
	/**
	 * Gets the chunk's x axis value
	 * @return <code>long x</code>: the chunk's x axis value
	 */
	public long getX(){
		return this.x;
	}
	
	/**
	 * Gets the chunk's y axis value
	 * @returnv<code>long y</code>: the chunk's y axis value
	 */
	public long getY(){
		return this.y;
	}
	/**
	 * Gets the chunk's z axis value
	 * @return <code>long z</code>: the chunk's z axis value
	 */
	public long getZ(){
		return this.z;
	}
	
	/**
	 * Gets the chunk's region file name
	 * @return <code>String region</code>: the chunk's containing region file name
	 */
	public String getRegion(){
		return this.region;
	}
	
	/**
	 * Sets the chunk's x axis value
	 * @param x the value to set the chunk's x axis value to
	 */
	public void setX(long x){
		this.x = x;
	}
	
	/**
	 * Sets the chunk's y axis value
	 * @param y the value to set the chunk's y axis value to
	 */
	public void setY(long y){
		this.y = y;
	}
	
	/**
	 * Sets the chunk's z axis value
	 * @param z the value to set the chunk's z axis value to
	 */
	public void setZ(long z){
		this.z = z;
	}
	
	/**
	 * Sets the chunk's containing region file name
	 * @param region the value to set the chunk's containing region file name to
	 */
	public void setRegion(String region){
		this.region = region;
	}
	
	/**
	 * Sets the chunk's containing region file name using chunk x and z axis values
	 * @param x the x axis value to set the file name with
	 * @param z the z axis value to set the file name with
	 */
	public void setRegion(long x, long z){
		this.region = "r." + (x >> 5) + "." + (z >> 5) + ".mca";
	}
}