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
package io.github.seanboyy.lotReset.json;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import io.github.seanboyy.lotReset.config.ReadConfig;
import io.github.seanboyy.lotReset.util.objects.Config;
import io.github.seanboyy.lotReset.util.objects.Lot;

/**
 * Utility for reading JSON files from a URL location
 * @author <a href=https://www.github.com/seanboyy>Seanboyy</a>
 * @since 1.0
 */
public class ReadJSON {
	/**Read JSON file by using configuration file's JSON value,
	 * which points to the file
	 * @param configLocation String which specifies the location of the config.properties file
	 * @return <code>ArrayList&ltArrayList&ltLot&gt&gt lots</code> if no errors are encountered
	 * @since 1.0 - Implemented config.properties in 2.0
	 */
    public static ArrayList<ArrayList<Lot>> read(String configLocation){
        JSONParser parser = new JSONParser();
        URL url;
        File f;
        //lots is multidimensional in this format: {{fromLot,toLot},{fromLot,toLot},{fromLot,toLot},etc...}
        ArrayList<ArrayList<Lot>> lots = new ArrayList<ArrayList<Lot>>();
        Config config = ReadConfig.read(configLocation);
        final String[] alphabet = config.getAlpha();
        final String[] types = config.getType();
        final String[] worlds = config.getWorld();
        try{
            url = new URL(config.getJSON());
            InputStream in = url.openStream();
            f = new File("temp.json");
            FileWriter file = new FileWriter(f);
            Scanner input = new Scanner(in);
            while(input.hasNextLine()){
            	file.write(input.nextLine());
            }
            input.close();
            file.close();
            Object obj = parser.parse(new FileReader(f));
            JSONObject jsonObj = (JSONObject) obj;
            JSONObject regions = (JSONObject) jsonObj.get("Regions");
            for (String a : worlds){
            	for (String b : types){
            		for (String c : alphabet){
                		for (int d = 1; d <= alphabet.length; ++d){
                			String lotId = a + "-" + b + "-" + c + d;
                			String lotIdA = a + "_" + b + "-" + c + d;
                			String lotIdB = a + "-" + b + "_" + c + d;
                			String lotIdC = a + "_" + b + "_" + c + d;
                			JSONObject lot = (JSONObject) regions.get(lotId);
                			JSONObject lotA = (JSONObject) regions.get(lotIdA);
                			JSONObject lotB = (JSONObject) regions.get(lotIdB);
                			JSONObject lotC = (JSONObject) regions.get(lotIdC);
                			ArrayList<Lot> lotInfo = new ArrayList<Lot>();
                			if (lot != null){
                				lotInfo.add(new Lot((long)lot.get("source_minX"), (long)lot.get("source_maxX"), (long)lot.get("source_minY"), (long)lot.get("source_maxY"), (long)lot.get("source_minZ"), (long)lot.get("source_maxZ"), (String)lot.get("source_file"),lotId));
                				lotInfo.add(new Lot((long)lot.get("dest_minX"), (long)lot.get("dest_maxX"), (long)lot.get("dest_minY"), (long)lot.get("dest_maxY"), (long)lot.get("dest_minZ"), (long)lot.get("dest_maxZ"), (String)lot.get("dest_file"),lotId));
                				lots.add(lotInfo);
                			}
                			if (lotA != null){
                				lotInfo.add(new Lot((long)lotA.get("source_minX"), (long)lotA.get("source_maxX"), (long)lotA.get("source_minY"), (long)lotA.get("source_maxY"), (long)lotA.get("source_minZ"), (long)lotA.get("source_maxZ"), (String)lotA.get("source_file"),lotIdA));
                				lotInfo.add(new Lot((long)lotA.get("dest_minX"), (long)lotA.get("dest_maxX"), (long)lotA.get("dest_minY"), (long)lotA.get("dest_maxY"), (long)lotA.get("dest_minZ"), (long)lotA.get("dest_maxZ"), (String)lotA.get("dest_file"),lotIdA));
                				lots.add(lotInfo);
                			}
                			if (lotB != null){
                			    lotInfo.add(new Lot((long)lotB.get("source_minX"), (long)lotB.get("source_maxX"), (long)lotB.get("source_minY"), (long)lotB.get("source_maxY"), (long)lotB.get("source_minZ"), (long)lotB.get("source_maxZ"), (String)lotB.get("source_file"),lotIdB));
                				lotInfo.add(new Lot((long)lotB.get("dest_minX"), (long)lotB.get("dest_maxX"), (long)lotB.get("dest_minY"), (long)lotB.get("dest_maxY"), (long)lotB.get("dest_minZ"), (long)lotB.get("dest_maxZ"), (String)lotB.get("dest_file"),lotIdB));
                				lots.add(lotInfo);
                			}
                			if (lotC != null){
                				lotInfo.add(new Lot((long)lotC.get("source_minX"), (long)lotC.get("source_maxX"), (long)lotC.get("source_minY"), (long)lotC.get("source_maxY"),  (long)lotC.get("source_minZ"), (long)lotC.get("source_maxZ"), (String)lotC.get("source_file"),lotIdC));
                				lotInfo.add(new Lot((long)lotC.get("dest_minX"), (long)lotC.get("dest_maxX"), (long)lotC.get("dest_minY"), (long)lotC.get("dest_maxY"), (long)lotC.get("dest_minZ"), (long)lotC.get("dest_maxZ"), (String)lotC.get("dest_file"),lotIdC));
                				lots.add(lotInfo);
                			}
                		}
            		}
            	}
            }
            f.delete();
            if(f.exists()){
            	System.out.println("Could not delete temp.json");
            }
        } catch(IOException e){
            System.out.println("FILE ERROR FROM READING JSON");
            e.printStackTrace();
            return null;
        } catch(ParseException e){
            System.out.println("PARSER ERROR FROM READING JSON");
            e.printStackTrace();
            return null;
        }
        return lots;
    }
}