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
package io.github.seanboyy.lotReset;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import io.github.seanboyy.lotReset.mca.ReadMCA;
import io.github.seanboyy.lotReset.mca.WriteMCA;
import io.github.seanboyy.lotReset.util.objects.Lot;
import io.github.seanboyy.lotReset.util.objects.Section;
import io.github.seanboyy.lotReset.config.WriteConfig;
import io.github.seanboyy.lotReset.json.ReadJSON;

/**
 * Main Class
 * @author <a href=https://www.github.com/seanboyy>Seanboyy</a>
 * @since 1.0
 * @version 3.3.5
 */
public class Main{	
	/**
	 * Main running method
	 * @param args usually empty, unless config.properties does not exist, then it will have 4 entries: Alphabet, LotTypes, Worlds, JSON file URL
	 */
	public static void main(String[] args){
		WriteMCA write = new WriteMCA();
        File config = new File("config.properties");
        if (!config.exists()){
        	WriteConfig.writeFile("config.properties", args[0], args[1], args[2], args[3]);
        }
        ArrayList<ArrayList<Lot>> lots = ReadJSON.read("config.properties");
        //first loop grabs the sections from each lot in the list of lots
        for(ArrayList<Lot> lot : lots){
        	Lot fromLot = lot.get(0);
            Lot toLot = lot.get(1);
            ArrayList<Section> sList = fromLot.getSections();
            ArrayList<Section> sListA = toLot.getSections();
            //second loop swaps each section in the list of sections
            for (int a = 0; a < sList.size() && a < sListA.size(); a++){
            	try{
            		write.setSection(ReadMCA.getSection(ReadMCA.read(fromLot.getWorld() + sList.get(a).getRegion(), sList.get(a).getX(), sList.get(a).getZ()), (int)sList.get(a).getY()), ReadMCA.getSection(ReadMCA.read(toLot.getWorld() + sListA.get(a).getRegion(), sListA.get(a).getX(), sListA.get(a).getZ()), (int)sListA.get(a).getY()), (int)sList.get(a).getX(), (int)sList.get(a).getY(), (int)sList.get(a).getZ(), sList.get(a).getRegion());
            		write.setSection(ReadMCA.getSection(ReadMCA.read(fromLot.getWorld() + sList.get(a).getRegion(), sList.get(a).getX(), sList.get(a).getZ()), (int)sList.get(a).getY()), ReadMCA.getSection(ReadMCA.read(toLot.getWorld() + sListA.get(a).getRegion(), sListA.get(a).getX(), sListA.get(a).getZ()), (int)sListA.get(a).getY()), (int)sList.get(a).getX(), (int)sList.get(a).getY(), (int)sList.get(a).getZ(), sList.get(a).getRegion());
            	}catch(IOException e){
            		e.printStackTrace();
            	}
            }
            System.out.println("Lot " + fromLot.getLotName() + " has been reset.");
        }
	}
}