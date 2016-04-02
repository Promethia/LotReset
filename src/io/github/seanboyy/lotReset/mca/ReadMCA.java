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
package io.github.seanboyy.lotReset.mca;

import java.util.ArrayList;

import io.github.seanboyy.nbt.ByteTag;
import io.github.seanboyy.nbt.CompoundTag;
import io.github.seanboyy.nbt.ListTag;
import io.github.seanboyy.nbt.StreamTools;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Utility for reading .mca files
 * @author <a href=https://www.github.com/seanboyy>Seanboyy</a>
 * @since 1.0
 */
public class ReadMCA{

    /**
     * Read chunk data from the region file
     * @param fileName region file to read from
     * @param chunkX x axis value of the chunk
     * @param chunkZ z axis value of the chunk
     * @return <code>byte[] chunkData</code> if no errors are encountered
     * @since 1.0
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
    
    /**
     * Reads Section data from chunk
     * @param data chunk data
     * @param Y section number (will be from 0 - 15)
     * @return <code>{@link CompoundTag} section</code>
     * @throws IOException
     * @since 3.0
     */
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