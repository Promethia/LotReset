package io.github.seanboyy.lotReset.mca;

import io.github.seanboyy.nbt.ByteTag;
import io.github.seanboyy.nbt.CompoundTag;
import io.github.seanboyy.nbt.ListTag;
import io.github.seanboyy.nbt.StreamTools;

import java.io.*;
import java.util.ArrayList;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;


/**
 * Utility for reading and writing MCA files
 * @author <a href=https://www.github.com/seanboyy>Seanboyy</a>
 * @since 4.0
 */
public class MCAIO {
    /**
     * Write chunk data to region file
     * @param chunkData data to be written to file
     * @param fileName file to write to
     * @param chunkX x axis value of chunk
     * @param chunkZ z axis value of chunk
     * @since 1.0
     */
    private static void write(byte[] chunkData, String fileName, long chunkX, long chunkZ){
        RandomAccessFile file;
        ArrayList<Boolean> open;
        if((chunkZ & 31) < 31){
            try{
                file = new RandomAccessFile(fileName, "rw");
                int cDLen = (int)file.length() / 4096;
                open = new ArrayList<>(cDLen);
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
                                if (open.get(i)){
                                    ++h;
                                }
                                else{
                                    h = 0;
                                }
                            }
                            else if (open.get(i)){
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
                        file.seek(a * 4);
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
                        file.seek(a * 4);
                        file.writeInt(x[(int) a]);
                        System.out.println("\nWRITE SUCCESSFUL TYPE C\nChunk @ " + chunkX + ", " + chunkZ + "\n");
                    }
                }
                file.close();
            } catch (IOException e){
                System.out.println("FILE ERROR FROM WRITING CHUNK");
                e.printStackTrace();
            }
        }
    }

    /**
     * Read chunk data from the region file
     * @param fileName region file to read from
     * @param chunkX x axis value of the chunk
     * @param chunkZ z axis value of the chunk
     * @return <code>DataInputStream data</code> if no errors are encountered
     * @since 1.0
     */
    public static DataInputStream read(String fileName, long chunkX, long chunkZ){
        RandomAccessFile file;
        if (((chunkX & 31) < 0 ) || ((chunkX & 31) >=32) || ((chunkZ & 31) < 0 || ((chunkZ & 31) >= 32))){
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
                        file.seek(a * 4);
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
                            file.seek(a * 4);
                            file.writeInt(x[(int) a]);
                            for (int j = 0; j < numSectors; ++j){
                                open.set(sectorNumber + j, true);
                            }
                            file.close();
                            return null;
                        }
                        else if (length <= 0){
                            x[(int) a] = 0;
                            file.seek(a * 4);
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
                                return new DataInputStream(new BufferedInputStream(new GZIPInputStream(new ByteArrayInputStream(data))));
                            }
                            else if (version == 2){
                                byte[] data = new byte[length - 1];
                                file.read(data);
                                file.close();
                                return new DataInputStream(new BufferedInputStream(new InflaterInputStream(new ByteArrayInputStream(data))));
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
     * @param is chunk data stream
     * @param Y section number (will be from 0 - 15)
     * @return <code>{@link CompoundTag} section</code>
     * @throws IOException
     * @since 3.0
     */
    public static CompoundTag getSection(DataInputStream is, int Y) throws IOException{
        CompoundTag tag = StreamTools.read(is);
        CompoundTag emptyTag = new CompoundTag();
        CompoundTag level = tag.getCompoundTag("Level");
        ListTag sections = level.getListTag("Sections", 10);
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

    /**
     * Writes Section data to chunk
     * @param tag1 from section data
     * @param tag2 to section data
     * @param X x value of chunk
     * @param Y y value of section
     * @param Z z value of chunk
     * @param region region name of chunk
     * @throws IOException Can throw IOException if outputting streams fail
     */
    public void setSection(CompoundTag tag1, CompoundTag tag2, int X, int Y, int Z, String region) throws IOException{
        CompoundTag emptyTag = new CompoundTag();
        try(DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new DeflaterOutputStream(new MCAIO.ChunkStream(X, Z, region))))) {
            if (!(tag1.equals(emptyTag)) && !(tag2.equals(emptyTag))) {
                byte y = ((ByteTag) tag1.getTag("Y")).getByte();
                byte y1 = ((ByteTag) tag2.getTag("Y")).getByte();
                if (y == y1) {
                    tag2.getListTag("Sections", 10).set(Y, tag1);
                } else {
                    out.close();
                }
                StreamTools.writeCompressed(tag2, out);
            } else if (tag1.equals(emptyTag) && !(tag2.equals(emptyTag))) {
                tag2.getListTag("Sections", 10).set(Y, tag1);
                StreamTools.writeCompressed(tag2, out);
            } else if (!(tag1.equals(emptyTag)) && tag2.equals(emptyTag)) {
                tag2.getListTag("Sections", 10).set(Y, tag1);
                StreamTools.writeCompressed(tag2, out);
            } else {
                out.close();
            }
        }
    }

    /**
     * Chunk data stream helper
     * @author <a href=http://www.github.com/seanboyy>Seanboyy</a>
     * @since 3.3.4
     */
    public class ChunkStream extends ByteArrayOutputStream{
        private int x;
        private int z;
        private String region;
        /**
         * Create a new chunk data stream
         * @param x chunk x value
         * @param z chunk z value
         * @param region chunk region name
         */
        ChunkStream(int x, int z, String region){
            super(8096);
            this.x = x;
            this.z = z;
            this.region = region;
        }

        /**
         * On closing the stream, write the stream data to the region file.
         */
        public void close() {
            MCAIO.write(this.buf, this.region, this.x, this.z);
        }
    }
}
