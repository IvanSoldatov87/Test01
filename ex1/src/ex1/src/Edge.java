package ex1.src;


public class Edge {
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
	

}
