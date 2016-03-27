package org.seanb.lotReset;

import java.io.File;
import java.util.ArrayList;
import org.seanb.lotReset.mca.ReadMCA;
import org.seanb.lotReset.mca.WriteMCA;
import org.seanb.lotReset.util.objects.Lot;
import org.seanb.lotReset.util.objects.Chunk;
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
        for(int i = 0; i < lots.size(); ++i){
        	if(lots.size() != 0){
        		Lot fromLot = lots.get(i).get(0);
            	Lot toLot = lots.get(i).get(1);
            	ArrayList<Chunk> cList = fromLot.getChunks();
            	ArrayList<Chunk> cListA = toLot.getChunks();
            	//second loop swaps each chunk in the list of chunks
            	for (int a = 0; a < cList.size() && a < cListA.size(); a++){
            		write.write(read.read(fromLot.getWorld() + cList.get(a).getRegion(), cList.get(a).getX(), cList.get(a).getZ()), toLot.getWorld() + cListA.get(a).getRegion(), cListA.get(a).getX(), cListA.get(a).getZ());
            		write.write(read.read(fromLot.getWorld() + cList.get(a).getRegion(), cList.get(a).getX(), cList.get(a).getZ()), toLot.getWorld() + cListA.get(a).getRegion(), cListA.get(a).getX(), cListA.get(a).getZ());
            	}
            	System.out.println("Lot " + fromLot.getLotName() + " has been reset.");
        	}
        	else{
        		break;
        	}
        }
	}
}