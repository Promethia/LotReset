package io.github.seanboyy.lotReset;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import io.github.seanboyy.lotReset.util.objects.Lot;
import io.github.seanboyy.lotReset.util.objects.Section;
import io.github.seanboyy.lotReset.json.JSONIO;
import io.github.seanboyy.lotReset.config.ConfigIO;
import io.github.seanboyy.lotReset.mca.MCAIO;

/**
 * Main Class
 * @author <a href=https://www.github.com/seanboyy>Seanboyy</a>
 * @since 1.0
 * @version 3.4.1
 */
public class Main{
    /**
     * Main running method
     * @param args usually empty, unless config.properties does not exist, then it will have 4 entries: Alphabet, LotTypes, Worlds, JSON file URL
     */
    public static void main(String[] args){
        MCAIO write = new MCAIO();
        File config = new File("config.properties");
        if (!config.exists()){
            if(args.length < 4){
                System.err.println("ERROR: invalid arguments\nUSAGE: <alphabet> <lot-types> <worlds> <url>");
                return;
            }
            else{
                ConfigIO.writeFile("config.properties", args[0], args[1], args[2], args[3]);
            }
        }
        ArrayList<ArrayList<Lot>> lots = JSONIO.read("config.properties");
        //first loop grabs the sections from each lot in the list of lots
        for(ArrayList<Lot> lot : lots){
            Lot fromLot = lot.get(0);
            Lot toLot = lot.get(1);
            ArrayList<Section> sList = fromLot.getSections();
            ArrayList<Section> sListA = toLot.getSections();
            //second loop swaps each section in the list of sections
            for (int a = 0; a < sList.size() && a < sListA.size(); a++){
                try{
                    write.setSection(MCAIO.getSection(MCAIO.read(fromLot.getWorld() + sList.get(a).getRegion(), sList.get(a).getX(), sList.get(a).getZ()), (int)sList.get(a).getY()), MCAIO.getSection(MCAIO.read(toLot.getWorld() + sListA.get(a).getRegion(), sListA.get(a).getX(), sListA.get(a).getZ()), (int)sListA.get(a).getY()), (int)sList.get(a).getX(), (int)sList.get(a).getY(), (int)sList.get(a).getZ(), fromLot.getWorld() + sList.get(a).getRegion());
                    write.setSection(MCAIO.getSection(MCAIO.read(fromLot.getWorld() + sList.get(a).getRegion(), sList.get(a).getX(), sList.get(a).getZ()), (int)sList.get(a).getY()), MCAIO.getSection(MCAIO.read(toLot.getWorld() + sListA.get(a).getRegion(), sListA.get(a).getX(), sListA.get(a).getZ()), (int)sListA.get(a).getY()), (int)sList.get(a).getX(), (int)sList.get(a).getY(), (int)sList.get(a).getZ(), fromLot.getWorld() + sList.get(a).getRegion());
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            System.out.println("Lot " + fromLot.getLotName() + " has been reset.");
        }
    }
}