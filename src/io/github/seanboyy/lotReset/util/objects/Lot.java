/*
 * This is a part of LotReset, licensed under the MIT License (MIT).
 * 
 * Copyright (c) 2016 Seanboyy (Sean Bamford)
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTIBILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIBLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package io.github.seanboyy.lotReset.util.objects;

import java.util.ArrayList;

/**
 * Lot object
 * @author <a href=https://www.github.com/seanboyy>Seanboyy</a>
 * @since 1.0
 */
public class Lot {
	/**
	 * The minimum x axis value
	 */
	private long minX;
	/**
	 * The maximum x axis value
	 */
	private long maxX;
	/**
	 * The minimum y axis value
	 */
	private long minY;
	/**
	 * The maximum y axis value
	 */
	private long maxY;
	/**
	 * The minimum z axis value
	 */
	private long minZ;
	/**
	 * The maximum z axis value
	 */
	private long maxZ;
	/**
	 * The world title of the lot
	 */
	private String world;
	/**
	 * The lot id of the lot
	 */
	private String lotName;

	/**
	 * Creates a lot object
	 * @param minX the value of the lot's lowest x axis position
	 * @param maxX the value of the lot's largest x axis position
	 * @param minY the value of the lot's lowest y axis position
	 * @param maxY the value of the lot's largest y axis position
	 * @param minZ the value of the lot's lowest z axis position
	 * @param maxZ the value of the lot's largest z axis position
	 * @param world the world name of the lot
	 * @param lotName the lot title of the lot
	 */
	public Lot(long minX, long maxX, long minY, long maxY, long minZ, long maxZ, String world, String lotName){
		this.minX = minX;
		this.maxX = maxX;
		this.minY = minY;
		this.maxY = maxY;
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
	 * Gets the minimum y axis value from the lot object
	 * @return <code>long minY</code>: the minimum y axis value
	 */
	public long getMinY(){
		return this.minY;
	}
	
	/**
	 * Gets the maximum y axis value from the lot object
	 * @return <code>long maxY</code>: the maximum y axis value
	 */
	public long getMaxY(){
		return this.maxY;
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
	 * Sets the minimum y axis value
	 * @param mnY the <code>long</code> to set minY to
	 */
	public void setMinY(long mnY){
		this.minY = mnY;
	}
	
	/**
	 * Sets the maximum y axis value
	 * @param mxY the <code>long</code>to set maxY to
	 */
	public void setMaxY(long mxY){
		this.maxY = mxY;
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
	 * @return <code>ArrayList&ltSection&gt sectionList</code>: a list of sections
	 * @since 1.0 - changed from &ltChunk&gt to &ltSection&gt in 3.0
	 */
	public ArrayList<Section> getSections(){
		ArrayList<Section> sectionList = new ArrayList<Section>();
		for (long y = this.minY; y < this.maxY; y += 16){
			for (long z = this.minZ; z < this.maxZ; z += 16){
				for (long x = this.minX; x < this.maxX; x += 16){
					Section section = new Section(x, y, z);
					sectionList.add(section);
				}
			}
		}
		return sectionList;
	}
}