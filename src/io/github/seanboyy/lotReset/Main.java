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
 * @author <a href=https://github.com/seanboyy>Seanboyy</a>
 * @since 1.0
 * @version 3.3.2
 */
public class Main{	
	/**
	 * Main running method
	 * @param args usually empty, unless config.properties does not exist, then it will have 4 entries: Alphabet, LotTypes, Worlds, JSON file URL
	 */
	public static void main(String[] args){
        File config = new File("config.properties");
        if (!config.exists()){
        	WriteConfig.writeFile("config.properties", args[0], args[1], args[2], args[3]);
        }
        ArrayList<ArrayList<Lot>> lots = ReadJSON.read("config.properties");
        //first loop grabs the sections from each lot in the list of lots
        for(int i = 0; i < lots.size() && (lots.size() != 0); ++i){
        	Lot fromLot = lots.get(i).get(0);
            Lot toLot = lots.get(i).get(1);
            ArrayList<Section> sList = fromLot.getSections();
            ArrayList<Section> sListA = toLot.getSections();
            //second loop swaps each section in the list of sections
            for (int a = 0; a < sList.size() && a < sListA.size(); a++){
            	try{
            	WriteMCA.setSection(ReadMCA.getSection(ReadMCA.read(fromLot.getWorld() + sList.get(a).getRegion(), sList.get(a).getX(), sList.get(a).getZ()), (int)sList.get(a).getY()), ReadMCA.read(toLot.getWorld() + sListA.get(a).getRegion(), sListA.get(a).getX(), sListA.get(a).getZ()), (int)sList.get(a).getY());
            	WriteMCA.setSection(ReadMCA.getSection(ReadMCA.read(fromLot.getWorld() + sList.get(a).getRegion(), sList.get(a).getX(), sList.get(a).getZ()), (int)sList.get(a).getY()), ReadMCA.read(toLot.getWorld() + sListA.get(a).getRegion(), sListA.get(a).getX(), sListA.get(a).getZ()), (int)sList.get(a).getY());
            	}catch(IOException e){
            		e.printStackTrace();
            	}
            }
            System.out.println("Lot " + fromLot.getLotName() + " has been reset.");
        }
	}
}