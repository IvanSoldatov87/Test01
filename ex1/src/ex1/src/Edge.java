package ex1.src;

import java.io.Serializable;
/**
 * this class create edge between to nodes save nodes keys and  weight of edge
 * @author Ivan
 *
 */

public class Edge implements Serializable{
	private int start=0;
	private double val=0;
	private int end=0;
	/**
	 * empty contractor
	 */
	public Edge() {}
	/**
	 * copy contractor
	 * @param s key of one node
	 * @param v edge weight
	 * @param e key of second node
	 */
	public Edge(int s,double v,int e) {
		this.start=s;
		this.val=v;
		this.end=e;
	}
	
	/**
	 * The method return weight of edge
	 * @return val     edge value
	 */
	public double getVal()
	{
		return this.val;
	}
	/**
	 * The method return key of node
	 * @return start     node key
	 */
	public int getStart()
	{
		return this.start;
	}
	/**
	 * The method return key of node
	 * @return end     node key
	 */
	public int getEnd()
	{
		return this.end;
	}
	/**
	 * The method set weight value
	 * @param val     weight value
	 */
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
