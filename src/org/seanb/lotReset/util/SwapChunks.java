package org.seanb.lotReset.util;

public class SwapChunks{
	/**
	 * Swaps the data from a source chunk to a new chunk
	 * @param oldChunk Data from source chunk
	 * @param newChunk Data from destination chunk
	 * @return <code>byte[] newChunk</code>
	 */
    public byte[] swap(byte[] oldChunk, byte[] newChunk){
    	byte[] temp = newChunk;
        newChunk = oldChunk;
        oldChunk = temp;
        return newChunk;
    }
}