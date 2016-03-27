package org.seanb.lotReset.util.objects;

import java.util.ArrayList;

public class Lot {
	private long minX;
	private long maxX;
	private long minZ;
	private long maxZ;
	private String world;
	private String lotName;

	/**
	 * Creates a lot object
	 * @param minX the value of the lot's lowest x axis position
	 * @param maxX the value of the lot's largest x axis position
	 * @param minZ the value of the lot's lowest z axis position
	 * @param maxZ the value of the lot's largest z axis position
	 * @param world the world name of the lot
	 * @param lotName the lot title of the lot
	 */
	public Lot(long minX, long maxX, long minZ, long maxZ, String world, String lotName){
		this.minX = minX;
		this.maxX = maxX;
		this.minZ = minZ;
		this.maxZ = maxZ;
		this.world = world;
		this.lotName = lotName;
	}
	
	/**
	 * Gets the minimum x axis value from the lot object
	 * @return <code>long minX</code>: the minimum x axis value
	 */
	public long getMinX(){
		return this.minX;
	}
	
	/**
	 * Gets the maximum x axis value from the lot object
	 * @return <code>long maxX</code>: the maximum x axis value
	 */
	public long getMaxX(){
		return this.maxX;
	}
	
	/**
	 * Gets the minimum z axis value from the lot object
	 * @return <code>long minZ</code>: the minimum z axis value
	 */
	public long getMinZ(){
		return this.minZ;
	}
	
	/**
	 * Gets the maximum z axis value from the lot object
	 * @return <code>long maxZ</code>: the maximum z axis value
	 */
	public long getMaxZ(){
		return this.maxZ;
	}
	
	/**
	 * Gets the world title from the lot object
	 * @return <code>String world</code>: the world title
	 */
	public String getWorld(){
		return this.world;
	}
	
	/**
	 * Gets the lot id from the lot object
	 * @return <code>String lotName</code>: the lot id
	 */
	public String getLotName(){
		return this.lotName;
	}
	
	/**
	 * Sets the minimum x axis value
	 * @param mnX the <code>long</code> to set minX to
	 */
	public void setMinX(long mnX){
		this.minX = mnX;
	}
	
	/**
	 * Sets the maximum x axis value
	 * @param mxX the <code>long</code> to set maxX to
	 */
	public void setMaxX(long mxX){
		this.maxX = mxX;
	}
	
	/**
	 * Sets the minimum z axis value
	 * @param mnZ the <code>long</code> to set minZ to
	 */
	public void setMinZ(long mnZ){
		this.minZ = mnZ;
	}
	
	/**
	 * Sets the maximum z axis value
	 * @param mxZ the <code>long</code> to set maxZ to
	 */
	public void setMaxZ(long mxZ){
		this.maxZ = mxZ;
	}
	
	/**
	 * Sets the world name
	 * @param wld the <code>String</code> to set world to
	 */
	public void setWorld(String wld){
		this.world = wld;
	}
	
	/**
	 * Sets the lot id
	 * @param name the <code>String</code> to set lotName to
	 */
	public void setLotName(String name){
		this.lotName = name;
	}
	
	/**
	 * Gets the chunks within the lot
	 * @return <code>ArrayList&ltChunk&gt chunkList</code>: the list of chunks
	 */
	public ArrayList<Chunk> getChunks(){
		ArrayList<Chunk> chunkList = new ArrayList<Chunk>();
		for (long z = minZ; z < maxZ; z+=16){
			for (long x = minX; x < maxX; x+=16){
				Chunk chunk = new Chunk(x, z, x + 16, z + 16);
				chunkList.add(chunk);
			}
		}
		return chunkList;
	}
}
