package org.seanb.lotReset.mca;

import java.util.ArrayList;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

import org.jnbt.CompoundTag;
import org.jnbt.NBTInputStream;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class ReadMCA{
    RandomAccessFile file;

    /**
     * Read chunk data from the region file
     * @param fileName region file to read from
     * @param chunkX x axis value of the chunk
     * @param chunkZ z axis value of the chunk
     * @return <code>byte[] chunkData</code> if no errors are encountered
     */
    public byte[] read(String fileName, long chunkX, long chunkZ){
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
                    this.file.close();
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
                        this.file.close();
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
                            this.file.close();
                            return null;
                        }
                        else if (length <= 0){
                            x[(int) a] = 0;
                            file.seek((long)(a * 4));
                            file.writeInt(x[(int) a]);
                            for (int j = 0; j < numSectors; ++j){
                                open.set(sectorNumber + j, true);
                            }
                            this.file.close();
                            return null;
                        }
                        else{
                            byte version = file.readByte();
                            if (version == 1){
                                byte[] data = new byte[length - 1];
                                file.read(data);
                                this.file.close();
                                return data;
                            }
                            else if (version == 2){
                                byte[] data = new byte[length - 1];
                                file.read(data);
                                this.file.close();
                                return data;
                            }
                            else {
                                this.file.close();
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
     * Uncompresses the compressed data
     * @param compressedData compressed ChunkData
     * @return <code>byte[] output</code> if no errors are encountered
     */
    public byte[] uncompress(byte[] compressedData){
    	Inflater inflate = new Inflater();
    	inflate.setInput(compressedData, 0, compressedData.length);
    	byte[] output = new byte[1000000];
    	try {
			inflate.inflate(output);
			inflate.end();
		} catch (DataFormatException e) {
			e.printStackTrace();
			inflate.end();
			return null;
		}
    	return output;
    }
    
    public CompoundTag getTag(byte[] chunkData){
    	DataInputStream stream = new DataInputStream(new BufferedInputStream(new InflaterInputStream(new ByteArrayInputStream(chunkData))));
    	try {
			NBTInputStream in = new NBTInputStream(stream);
			CompoundTag out = (CompoundTag) in.readTag();
			in.close();
			return out;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
    }

    /**
     * Closes the file if the file was not null
     * @throws IOException
     */
    public void close() throws IOException{
        if (file != null){
            file.close();
        }
    }
}