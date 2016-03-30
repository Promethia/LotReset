package org.seanb.lotReset.util.objects;

public class Section extends Chunk {
	private long y;
	
	public Section(long x, long y, long z){
		super(x, z);
		this.y = (long)Math.floor(y/16.);
	}
	
	public long getY(){
		return this.y;
	}
	
	public void setY(long y){
		this.y = y;
	}
}
