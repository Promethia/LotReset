package org.seanb.lotReset.mca;

import java.util.ArrayList;

import org.seanb.nbt.ByteTag;
import org.seanb.nbt.CompoundTag;
import org.seanb.nbt.ListTag;
import org.seanb.nbt.StreamTools;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class ReadMCA{

    /**
     * Read chunk data from the region file
     * @param fileName region file to read from
     * @param chunkX x axis value of the chunk
     * @param chunkZ z axis value of the chunk
     * @return <code>byte[] chunkData</code> if no errors are encountered
     */
    public static byte[] read(String fileName, long chunkX, long chunkZ){
        RandomAccessFile file;
        if (((chunkX & 31) < 0 ) || ((chunkX & 31) >=32) || ((chunkZ & 31) < 0 || ((chunkZ & 31) >= 31))){
            return null;
        }
        else{
            try{
                file = new RandomAccessFile(fileName, "rw");
                int cDLen = (int)file.length() / 4096;
                ArrayList<Boolean> open = new ArrayList<Boolean>(cDLen);
                for (int l = 0; l < cDLen; ++l){
                    open.add(true);
                }
                open.set(0, false);
                open.set(1, false);
                file.seek(0L);
                int[] x = new int[1024];
                for (int y = 0; y < 1024; ++y){
                    int z = file.readInt();
                    x[y] = z;
                    if (z != 0 && (z >> 8) + (z & 255) <= open.size()){
                        for (int l = 0; l < (z & 255); ++l){
                            open.set((z >> 8) + l, false);
                        }
                    }
                }
                long a = (chunkX & 31) + (chunkZ & 31) * 32;
                int offset = x[(int) a];
                if (offset == 0){
                    file.close();
                    return null;
                }
                else{
                    int sectorNumber = offset >> 8;
                    int numSectors = offset & 255;
                    if (sectorNumber + numSectors > open.size()){
                        x[(int) a] = 0;
                        file.seek((long)(a * 4));
                        file.writeInt(x[(int) a]);
                        for (int j = 0; j < numSectors; ++j){
                            open.set(sectorNumber + j, true);
                        }
                        file.close();
                        return null;
                    }
                    else{
                        file.seek(0L);
                        file.seek((long)(sectorNumber * 4096));
                        int length = file.readInt();
                        if (length > 4096 * numSectors){
                            x[(int) a] = 0;
                            file.seek((long)(a * 4));
                            file.writeInt(x[(int) a]);
                            for (int j = 0; j < numSectors; ++j){
                                open.set(sectorNumber + j, true);
                            }
                            file.close();
                            return null;
                        }
                        else if (length <= 0){
                            x[(int) a] = 0;
                            file.seek((long)(a * 4));
                            file.writeInt(x[(int) a]);
                            for (int j = 0; j < numSectors; ++j){
                                open.set(sectorNumber + j, true);
                            }
                            file.close();
                            return null;
                        }
                        else{
                            byte version = file.readByte();
                            if (version == 1){
                                byte[] data = new byte[length - 1];
                                file.read(data);
                                file.close();
                                return data;
                            }
                            else if (version == 2){
                                byte[] data = new byte[length - 1];
                                file.read(data);
                                file.close();
                                return data;
                            }
                            else {
                                file.close();
                                return null;
                            }
                        }
                    }
                }
            } catch(IOException e){
                System.out.println("FILE ERROR FROM READING CHUNK");
                e.printStackTrace();
                return null;
            }
        }
    }
    
    public static CompoundTag getSection(byte[] data, int Y) throws IOException{
    	CompoundTag tag = StreamTools.readCompressed(new ByteArrayInputStream(data));
    	CompoundTag emptyTag = new CompoundTag();
    	ListTag sections = tag.getListTag("Sections", 10);
    	CompoundTag section = sections.getCompoundTagAt(Y);
    	if(!(section.equals(emptyTag))){
        	byte y = ((ByteTag)section.getTag("Y")).getByte();
        	if (y == (byte)Y){
        		return section;
        	}
        	else{
        		return null;
        	}
    		
    	}
    	else{
    		return emptyTag;
    	}
    }
}