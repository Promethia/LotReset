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
 * Config object
 * @author <a href=https://www.github.com/seanboyy>Seanboyy</a>
 * @since 2.0
 */
public class Config {
	/**
	 * The list of the alphabet
	 */
	private String[] alpha;
	/**
	 * The list of lot types
	 */
	private String[] type;
	/**
	 * The list of worlds
	 */
	private String[] world;
	/**
	 * The JSON file location
	 */
	private String json;
	
	/**
	 * Gets the alphabet from the config object
	 * @return <code>String[] alpha</code>: the alphabet list from the config object
	 */
	public String[] getAlpha(){
		return this.alpha;
	}
	
	/**
	 * Gets the types list from the config object
	 * @return <code>String[] type</code>: the types list from config object
	 */
	public String[] getType(){
		return this.type;
	}
	
	/**
	 * Gets the worlds list from the config object
	 * @return <code>String[] world</code>: the worlds list from config object
	 */
	public String[] getWorld(){
		return this.world;
	}
	
	/**
	 * Gets the json location from the config object
	 * @return <code>String json</code>: the JSON location from config object
	 */
	public String getJSON(){
		return this.json;
	}
	
	/**
	 * Sets the alphabet list in the config object
	 * @param a the <code>String[]</code> to set alpha to
	 */
	public void setAlpha(String[] a){
		this.alpha = a;
	}
	
	/**
	 * Sets the types list in the config object
	 * @param t the <code>String[]</code> to set type to
	 */
	public void setType(String[] t){
		this.type = t;
	}
	
	/**
	 * Sets the worlds list in the config object
	 * @param w the <code>String[]</code> to set world to
	 */
	public void setWorld(String[] w){
		this.world = w;
	}
	
	/**
	 * Sets the JSON location in the config object
	 * @param j the <code>String</code> to set json to
	 */
	public void setJSON(String j){
		this.json = j;
	}
	
	/**
	 * Creates a config object
	 * @param a the <code>String</code> value which will become the alphabet list in config object
	 * @param t the <code>String</code> value which will become the types list in config object
	 * @param w the <code>String</code> value which will become the worlds list in config object
	 * @param j the <code>String</code> value which will become the JSON location in config object
	 */
	public Config(String a, String t, String w, String j){
		String[] alphabet = a.split(", ");
		String[] types = t.split(", ");
		String[] worlds = w.split(", ");
		this.alpha = alphabet;
		this.type = types;
		this.world = worlds;
		this.json = j;
	}
}