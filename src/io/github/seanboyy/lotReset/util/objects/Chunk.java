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

/**
 * Chunk object
 * @author <a href=https://www.github.com/seanboyy>Seanboyy</a>
 * @since 1.0
 */
public class Chunk {
	/**
	 * The x axis value of the chunk in chunk co-ordinates
	 */
	private long x;
	/**
	 * The z axis value of the chunk in chunk co-ordinates
	 */
	private long z;
	/**
	 * The name of the region file that contains the chunk
	 */
	private String region;
	
	/**
	 * Make a chunk object from block co-ordinates x, z 
	 * and set the region file location of the chunk to be r.x>>5.z>>5.mca
	 * @param x x axis value in block co-ordinates
	 * @param z z axis value in block co-ordinates
	 */
	public Chunk(long x, long z){
		this.x = (int)Math.floor(x/16.);
		this.z = (int)Math.floor(z/16.);
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