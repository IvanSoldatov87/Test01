package ex1.src;

import java.io.Serializable;

public class Edge implements Serializable{
	private int start=0;
	private double val=0;
	private int end=0;
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
	/*
	 * @Override public String toString() { return new
	 * StringBuffer(" Start ").append(this.start)
	 * .append(" end : ").append(this.end).append(" val ").append(this.val).toString
	 * (); }
	 */

}
