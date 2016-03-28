package org.seanb.lotReset;

import java.io.File;
import java.util.ArrayList;
import org.seanb.lotReset.mca.ReadMCA;
import org.seanb.lotReset.mca.WriteMCA;
import org.seanb.lotReset.util.objects.Lot;
import org.seanb.lotReset.util.objects.Section;
import org.seanb.lotReset.config.WriteConfig;
import org.seanb.lotReset.json.ReadJSON;

public class Main{
	public static void main(String[] args){
    	ReadMCA read = new ReadMCA();
        WriteMCA write = new WriteMCA();
        ReadJSON readJ = new ReadJSON();
        WriteConfig cfgWrtr = new WriteConfig();
        File config = new File("config.properties");
        if (!config.exists()){
        	cfgWrtr.writeFile("config.properties", args[0], args[1], args[2], args[3]);
        }
        ArrayList<ArrayList<Lot>> lots = readJ.read("config.properties");
        //first loop grabs the chunks from each lot in the list of lots
        for(int i = 0; i < lots.size() && (lots.size() != 0); ++i){
        	Lot fromLot = lots.get(i).get(0);
            Lot toLot = lots.get(i).get(1);
            ArrayList<Section> sList = fromLot.getSections();
            ArrayList<Section> sListA = toLot.getSections();
            //second loop swaps each chunk in the list of chunks
            for (int a = 0; a < sList.size() && a < sListA.size(); a++){
            	write.write(read.read(fromLot.getWorld() + sList.get(a).getRegion(), sList.get(a).getX(), sList.get(a).getZ()), toLot.getWorld() + sListA.get(a).getRegion(), sListA.get(a).getX(), sListA.get(a).getZ());
            	write.write(read.read(fromLot.getWorld() + sList.get(a).getRegion(), sList.get(a).getX(), sList.get(a).getZ()), toLot.getWorld() + sListA.get(a).getRegion(), sListA.get(a).getX(), sListA.get(a).getZ());
            }
            System.out.println("Lot " + fromLot.getLotName() + " has been reset.");
        }
	}
}