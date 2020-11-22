package ex1.src;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class WGraph_DS implements weighted_graph {
	//private node_info nodeInfo;
	Collection<node_info> nodeCollection =new ArrayList<node_info>();
	HashMap<Integer, NodeInfo> nodeHashMap=new HashMap<Integer, NodeInfo>();
	Collection<Edge> edges= new LinkedList<Edge>();
	//public static int key=0;
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
		Iterator<Edge> it=edges.iterator();
		Edge i;
		while(it.hasNext()) {
			i=it.next();
			if((i.getStart()==node1 && i.getEnd()==node2)||(i.getStart()==node2 && i.getEnd()==node1)) return true;
		}	
		return false;
	}

	@Override
	public double getEdge(int node1, int node2) {
		Iterator<Edge> it=edges.iterator();
		Edge i;
		while(it.hasNext()) {
			i=it.next();
			if((i.getStart()==node1 && i.getEnd()==node2)||(i.getStart()==node2 && i.getEnd()==node1)) return i.getVal();
		}
		return -1;
	}

	@Override
	public void addNode(int key) {
		NodeInfo newNode=new NodeInfo(key);//<-----???need to cheak the metod
		//this.key++;
		nodeHashMap.put(key, newNode);	
		modCount++;
	}
	@Override
	public void connect(int node1, int node2, double w) {
		if(node1!=node2) {
		if(hasEdge(node1,node2)) {
			for(Edge i:this.edges) {
				if(i.getStart()==node1 && i.getEnd()==node2 || i.getStart()==node2 && i.getEnd()==node1) i.setVal(w);
				modCount++;
			}
		}else {
			Edge edge= new Edge(node1,w,node2);
			Edge edge2= new Edge(node2,w,node1);
			edges.add(edge);
			edges.add(edge2);
			modCount++;
		}
		}
	}
	@Override
	public Collection<node_info> getV() {
		nodeCollection.addAll(nodeHashMap.values());
		return nodeCollection;
	}

	@Override
	public Collection<node_info> getV(int node_id) {//return naiber collection
		Collection<node_info> tampeCollection = new LinkedList<node_info>();
		Iterator<Edge> it=edges.iterator();
		Edge i;
		while(it.hasNext()) {
			i=it.next();
			if(i.getStart()==node_id)tampeCollection.add(getNode(i.getEnd()));
		}
		return tampeCollection;
	}

	@Override
	public node_info removeNode(int key) {
		Collection<Edge> tampeColl = new LinkedList<Edge>();
		node_info nodeTampe=null;
		if(getNode(key)!=null) {
			nodeTampe=getNode(key);
			nodeHashMap.remove(key);
			for(Edge i:edges) {
				if(i.getStart()==key)//edges.remove(i);
				tampeColl.add(i);
				if(i.getEnd()==key)//edges.remove(i);
				tampeColl.add(i);
			}
		}
		
		if(tampeColl!=null)
		edges.removeAll(tampeColl);
		modCount++;
		return nodeTampe;
	}
	@Override
	public void removeEdge(int node1, int node2) {
		Edge edge1=null;
		Edge edge2=null;
		Iterator<Edge> it = edges.iterator();
		while(it.hasNext()) {
			Edge i = it.next();
			if(i.getStart()==node1 && i.getEnd()==node2  )edge1=i;
			if(i.getStart()==node2 && i.getEnd()==node1)edge2=i;
		}
		if(edge1!=null)
		edges.remove(edge1);
		if(edge2!=null)
		edges.remove(edge2);
		modCount++;
		
	}

	@Override
	public int nodeSize() {
		return nodeHashMap.size();
	}

	@Override
	public int edgeSize() {
		// TODO Auto-generated method stub
		return edges.size()/2;
	}

	@Override
	public int getMC() {
		// TODO Auto-generated method stub
		return modCount;
	}

}
