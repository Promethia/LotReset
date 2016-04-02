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
	
	/**
	 * Sets the section's Y axis value
	 * @param y the value to set the y axis value to
	 */
	public void setY(long y){
		this.y = y;
	}
}
