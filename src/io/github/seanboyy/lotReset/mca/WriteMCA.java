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

import java.io.RandomAccessFile;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import io.github.seanboyy.nbt.ByteTag;
import io.github.seanboyy.nbt.CompoundTag;
import io.github.seanboyy.nbt.ListTag;
import io.github.seanboyy.nbt.StreamTools;

/**
 * Utility for writing .mca files
 * @author <a href=https://www.github.com/seanboyy>Seanboyy</a>
 * @since 1.0
 */
public class WriteMCA{

    /**
     * Write chunk data to region file
     * @param chunkData data to be written to file
     * @param fileName file to write to
     * @param chunkX x axis value of chunk
     * @param chunkZ z axis value of chunk
     * @since 1.0
     */
    public static void write(byte[] chunkData, String fileName, long chunkX, long chunkZ){
        RandomAccessFile file;
        ArrayList<Boolean> open;
        if (((chunkX & 31) < 0 ) || ((chunkX & 31) >=32) || ((chunkZ & 31) < 0 || ((chunkZ & 31) >= 31))){
            return;
        }
        else{
            try{
                file = new RandomAccessFile(fileName, "rw");
                int cDLen = (int)file.length() / 4096;
                open = new ArrayList<Boolean>(cDLen);
                for (int l = 0; l < cDLen; ++l){
                    open.add(true);
                }
                byte[] empty = new byte[4096];
                open.set(0, false);
                open.set(1, false);
                file.seek(0L);
                int[] x = new int[1024];
                for (int y = 0; y < 1024; ++y){
                    int z = file.readInt();
                    x[y] = z;
                    if (z != 0 && (z >> 8) + (z & 255) <= open.size()){
                        for (int w = 0; w < (z & 255); ++w){
                            open.set((z >> 8) + w, false);
                        }
                    }
                }
                long a = (chunkX & 31) + ((chunkZ & 31) * 32);
                int offset = x[(int) a];
                int sectorNumber = offset >> 8;
                int numSectors = offset & 255;
                int e = (chunkData.length + 5) / 4096 + 1;
                if (e >= 256){
                    file.close();
                    return;
                }
                if (sectorNumber != 0 && numSectors == 1){
                    file.seek((long)(sectorNumber * 4096));
                    file.writeInt(chunkData.length + 1);
                    file.writeByte(2);
                    file.write(chunkData, 0, chunkData.length);
                    System.out.println("\nWRITE SUCCESSFUL TYPE A\nChunk @ " + chunkX + ", " + chunkZ + "\n");
                }
                else{
                    for (int f = 0; f < numSectors; ++f){
                        open.set(sectorNumber + f, true);
                    }
                    int firstEmpty = open.indexOf(true);
                    int h = 0;
                    if (firstEmpty != -1){
                        for (int i = firstEmpty; i < open.size(); ++i){
                            if (h != 0){
                                if (open.get(i).booleanValue()){
                                    ++h;
                                }
                                else{
                                    h = 0;
                                }
                            }
                            else if (open.get(i).booleanValue()){
                                firstEmpty = i;
                                h = 1;
                            }
                            if (h >= e){
                                break;
                            }
                        }
                    }
                    if (h >= e){
                        sectorNumber = firstEmpty;
                        x[(int) a] = (firstEmpty << 8 | e);
                        file.seek((long)(a * 4));
                        file.writeInt(x[(int) a]);
                        for (int j = 0; j < e; ++j){
                            open.set(sectorNumber + j, false);
                        }
                        file.seek(0L);
                        file.seek((long)(sectorNumber * 4096));
                        file.writeInt(chunkData.length + 1);
                        file.writeByte(2);
                        file.write(chunkData, 0, chunkData.length);
                        System.out.println("\nWRITE SUCCESSFUL TYPE B\nChunk @ " + chunkX + ", " + chunkZ + "\n");
                    }
                    else{
                        file.seek(file.length());
                        sectorNumber = open.size();
                        System.out.println(sectorNumber);
                        for (int k = 0; k < e; ++k){
                            file.write(empty);
                            open.add(false);
                        }
                        file.seek((long)(sectorNumber * 4096));
                        file.writeInt(chunkData.length + 1);
                        file.writeByte(2);
                        file.write(chunkData, 0, chunkData.length);
                        x[(int) a] = (firstEmpty << 8 | e);
                        file.seek(0L);
                        file.seek((long)(a * 4));
                        file.writeInt(x[(int) a]);
                        System.out.println("\nWRITE SUCCESSFUL TYPE C\nChunk @ " + chunkX + ", " + chunkZ + "\n");
                    }
                }
                file.close();
            } catch (IOException e){
                System.out.println("FILE ERROR FROM WRITING CHUNK");
                e.printStackTrace();
                return;
            }
        }
    }
    
    /**
     * Writes section data to chunk
     * @param tag {@link CompoundTag} section to write to chunk
     * @param data chunk data to write to
     * @param Y section number in chunk (will be from 0 - 15)
     * @throws IOException
     * @since 3.0
     */
    public static void setSection(CompoundTag tag, byte[] data, int Y) throws IOException{
    	CompoundTag emptyTag = new CompoundTag();
    	CompoundTag tag1 = StreamTools.readCompressed(new ByteArrayInputStream(data));
    	ListTag sections = tag.getListTag("Sections", 10);
    	ListTag sections1 = tag1.getListTag("Sections", 10);
    	CompoundTag section = sections.getCompoundTagAt(Y);
    	CompoundTag section1 = sections1.getCompoundTagAt(Y);
    	if(!(section.equals(emptyTag)) && !(section1.equals(emptyTag))){
        	byte y = ((ByteTag)section.getTag("Y")).getByte();
        	byte y1 = ((ByteTag)section1.getTag("Y")).getByte();
        	if(y == y1){
        		tag1.getListTag("Sections", 10).set(Y, section);
        	}
        	StreamTools.writeCompressed(tag1, new ByteArrayOutputStream());	
    	}
    	else if(section.equals(emptyTag) && !(section1.equals(emptyTag))){
    		tag1.getListTag("Sections", 10).set(Y, section);
    		StreamTools.writeCompressed(tag1, new ByteArrayOutputStream());
		}
    	else if(!(section.equals(emptyTag)) && section1.equals(emptyTag)){
    		tag1.getListTag("Sections", 10).set(Y, section);
    		StreamTools.writeCompressed(tag1, new ByteArrayOutputStream());
    	}
    	else{
    		return;
    	}
    }
}