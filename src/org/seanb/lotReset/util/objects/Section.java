package org.seanb.lotReset.util.objects;

public class Section extends Chunk {
	private long x;
	private long y;
	private long z;
	private String region;
	
	public Section(long x, long y, long z){
		super(x, z);
		this.y = (long)Math.floor(y/16.);
	}
	
	public long getX(){
		return this.x;
	}
	
	public long getY(){
		return this.y;
	}
	
	public long getZ(){
		return this.z;
	}

	public String getRegion(){
		return this.region;
	}

	public void setX(long x){
		this.x = x;
	}
	
	public void setY(long y){
		this.y = y;
	}

	public void setZ(long z){
		this.z = z;
	}

	public void setRegion(String region){
		this.region = region;
	}
	
	public void setRegion(long x, long z){
		this.region = "r." + (x >> 5) + "." + (z >> 5) + ".mca";
	}
}
