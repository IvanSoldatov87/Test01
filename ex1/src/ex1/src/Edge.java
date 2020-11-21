package ex1.src;

import java.sql.Array;

public class Edge {
	private int start;
	private double val;
	private int end;
	public Edge() {}
	public Edge(int s,double v,int e) {
		this.start=s;
		this.val=v;
		this.end=e;
	}
	/**
	 * empty contractor
	 */
	
	public double getVal()
	{
		return this.val;
	}
	public int getStart()
	{
		return this.start;
	}
	public int getEnd()
	{
		return this.end;
	}
	public void setVal(double val)
	{
		this.val=val;
	}
	

}
