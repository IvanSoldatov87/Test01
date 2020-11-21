package ex1.src;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class WGraph_DS implements weighted_graph {
	//private node_info nodeInfo;
	Collection<node_info> nodeCollection =new ArrayList<node_info>();
	HashMap<Integer, NodeInfo> nodeHashMap=new HashMap<Integer, NodeInfo>();
	private Collection<Edge> edges= new ArrayList<Edge>();
	public static int key=0;
	int modCount=0;
	class NodeInfo implements node_info{
		int key=new Integer(0);// JVM not love this inside JVM we have pools of integers and chars for string
		String info=new String();
		double tag=new Double(0);//this too
		public NodeInfo(int key) {
			this.key=key;
		}
		public NodeInfo() {
		}
		public NodeInfo(NodeInfo ni) {
			this.key=ni.getKey();
			this.tag=ni.getTag();
			this.info=ni.getInfo();
		}
		@Override
		public int getKey() {
			// TODO Auto-generated method stub
			return key;
		}

		@Override
		public String getInfo() {
			// TODO Auto-generated method stub
			return info;
		}

		@Override
		public void setInfo(String s) {
			this.info=s;
			
		}

		@Override
		public double getTag() {
			// TODO Auto-generated method stub
			return tag;
		}

		@Override
		public void setTag(double t) {
			this.tag=t;
			
		}
		
	}
	
	/**
	 * method to found node by key and return @param tamp is temporary object of node_info type
	 * @param nodeCollection <--- is collection of nodes 
	 * @return tamp
	 */
	public Collection<Edge> getAllEdge() {
		return edges;
	}
	@Override
	public node_info getNode(int key) {
		return nodeHashMap.get(key);
	}
	@Override
	public boolean hasEdge(int node1, int node2) {
		for(Edge i:this.edges) {
			if((i.getStart()==node1 && i.getEnd()==node2)||(i.getStart()==node2 && i.getEnd()==node1)) return true;
		}	
		return false;
	}

	@Override
	public double getEdge(int node1, int node2) {
		for(Edge i:this.edges) {
			if((i.getStart()==node1 && i.getEnd()==node2)||(i.getStart()==node2 && i.getEnd()==node1)) i.getVal();
		}
		return 0;
	}

	@Override
	public void addNode(int key) {
		NodeInfo newNode=new NodeInfo(key);//<-----???need to cheak the metod
		this.key++;
		nodeHashMap.put(key, newNode);	
		modCount++;
	}
	@Override
	public void connect(int node1, int node2, double w) {
		if(hasEdge(node1,node2)) {
			for(Edge i:this.edges) {
				if(i.getStart()==node1 && i.getEnd()==node2 || i.getStart()==node2 && i.getEnd()==node1) i.setVal(w);
				modCount++;
			}
		}else {
			Edge edge= new Edge(node1,w,node2);
			edges.add(edge);
			modCount++;
		}
	}
	@Override
	public Collection<node_info> getV() {
		nodeCollection.addAll(nodeHashMap.values());
		return nodeCollection;
	}

	@Override
	public Collection<node_info> getV(int node_id) {//return naiber collection
		Collection<node_info> tampeCollection = null;
		for(Edge i:edges) {
			if(i.getStart()==node_id)tampeCollection.add(getNode(i.getEnd()));
			
		}
		return tampeCollection;
	}

	@Override
	public node_info removeNode(int key) {
		if(getNode(key)!=null) {
			nodeHashMap.remove(key);
		}
		for(Edge i:edges) {
			if(i.getStart()==key)edges.remove(i);
			if(i.getEnd()==key)edges.remove(i);
		}
		modCount++;
		return null;
	}
	@Override
	public void removeEdge(int node1, int node2) {
		for(Edge i:edges) {
			if(i.getStart()==node1 && i.getEnd()==node2)edges.remove(i);
			if(i.getStart()==node2 && i.getEnd()==node1)edges.remove(i);
		}
		modCount++;
		
	}

	@Override
	public int nodeSize() {
		return nodeHashMap.size();
	}

	@Override
	public int edgeSize() {
		// TODO Auto-generated method stub
		return edges.size();
	}

	@Override
	public int getMC() {
		// TODO Auto-generated method stub
		return modCount;
	}

}
