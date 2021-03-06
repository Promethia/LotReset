package io.github.seanboyy.lotReset.util.objects;

import java.util.ArrayList;

/**
 * Lot object
 * @author <a href=https://www.github.com/seanboyy>Seanboyy</a>
 * @since 1.0
 */
public class Lot {
    /**
     * The minimum x axis value
     */
    private long minX;
    /**
     * The maximum x axis value
     */
    private long maxX;
    /**
     * The minimum y axis value
     */
    private long minY;
    /**
     * The maximum y axis value
     */
    private long maxY;
    /**
     * The minimum z axis value
     */
    private long minZ;
    /**
     * The maximum z axis value
     */
    private long maxZ;
    /**
     * The world title of the lot
     */
    private String world;
    /**
     * The lot id of the lot
     */
    private String lotName;

    /**
     * Creates a lot object
     * @param minX the value of the lot's lowest x axis position
     * @param maxX the value of the lot's largest x axis position
     * @param minY the value of the lot's lowest y axis position
     * @param maxY the value of the lot's largest y axis position
     * @param minZ the value of the lot's lowest z axis position
     * @param maxZ the value of the lot's largest z axis position
     * @param world the world name of the lot
     * @param lotName the lot title of the lot
     */
    public Lot(long minX, long maxX, long minY, long maxY, long minZ, long maxZ, String world, String lotName){
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
        this.minZ = minZ;
        this.maxZ = maxZ;
        this.world = world;
        this.lotName = lotName;
    }

    /**
     * Gets the world title from the lot object
     * @return <code>String world</code>: the world title
     */
    public String getWorld(){
        return this.world;
    }

    /**
     * Gets the lot id from the lot object
     * @return <code>String lotName</code>: the lot id
     */
    public String getLotName(){
        return this.lotName;
    }

    /**
     * Gets the chunks within the lot
     * @return <code>ArrayList&ltSection&gt sectionList</code>: a list of sections
     * @since 1.0 - changed from &ltChunk&gt to &ltSection&gt in 3.0
     */
    public ArrayList<Section> getSections(){
        ArrayList<Section> sectionList = new ArrayList<>();
        for (long y = this.minY; y < this.maxY; y += 16){
            for (long z = this.minZ; z < this.maxZ; z += 16){
                for (long x = this.minX; x < this.maxX; x += 16){
                    Section section = new Section(x, y, z);
                    sectionList.add(section);
                }
            }
        }
        return sectionList;
    }
}