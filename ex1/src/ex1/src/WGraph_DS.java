package ex1.src;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
/**
 * This class create graph and simple function tools
 * inserted NodeInfo class it is node of graph
 * the graph complicated in nodes and edges
 * edges connect nodes
 * simple tools help to construct weighted graph
 * @author Ivan
 *
 */
public class WGraph_DS implements weighted_graph,Serializable {
	Collection<node_info> nodeCollection =new ArrayList<node_info>();
	HashMap<Integer, NodeInfo> nodeHashMap=new HashMap<Integer, NodeInfo>();
	Collection<Edge> edges= new LinkedList<Edge>();
	int modCount=0;
	/**
	 * This class is node contains unique key,Info text and tag for graph needs
	 * @author Ivan
	 *
	 */
	class NodeInfo implements node_info,Serializable{
		int key=new Integer(0);// JVM not love this inside JVM we have pools of integers and chars for string
		String info=new String();
		double tag=new Double(0);//this too
		/**
		 * constructor to initialize parameter key
		 * @param key
		 */
		public NodeInfo(int key) {
			this.key=key;
		}
		/**
		 * empty constructor
		 */
		public NodeInfo() {
		}
		/** 
		 * copy constructor
		 * @param ni
		 * assigns parameters key,tag and info from giveted node to this node
		 */
		public NodeInfo(NodeInfo ni) {
			this.key=ni.getKey();
			this.tag=ni.getTag();
			this.info=ni.getInfo();
		}
		/**
		 * The method return node key
		 * @return key  
		 */
		@Override
		public int getKey() {
			// TODO Auto-generated method stub
			return key;
		}
		/**
		 * The method return node info
		 * @return info  
		 */
		@Override
		public String getInfo() {
			// TODO Auto-generated method stub
			return info;
		}
		/**
		 * The method get parameter s and set it to parameter info
		 * @param info  
		 */
		@Override
		public void setInfo(String s) {
			this.info=s;
			
		}
		/**
		 * The method return node tag
		 * @return tag  
		 */
		@Override
		public double getTag() {
			// TODO Auto-generated method stub
			return tag;
		}
		/**
		 * The method get parameter t and set it to parameter tag
		 * @param tag  
		 */
		@Override
		public void setTag(double t) {
			this.tag=t;
			
		}
		/*
		 * @Override public String toString() { return new
		 * StringBuffer(" key ").append(this.key)
		 * .append(" info ").append(this.info).append(" tag ").append(this.tag).toString
		 * (); }
		 */
		
	}
	
	/**
	 * method to found node by key and return @param tamp is temporary object of node_info type
	 * @param nodeCollection <--- is collection of nodes 
	 * @return tamp
	 */
	public Collection<Edge> getAllEdge() {
		return edges;
	}
	/**
	 * Method get key and return node which this key
	 * @return node_info
	 */
	@Override
	public node_info getNode(int key) {
		return nodeHashMap.get(key);
	}
	/**
	 * Method to check connection between two nodes
	 * the method run on edge collection and check connection between to keys
	 * @return boolean
	 */
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

	/**
	 * Method return weight of edge between two nodes
	 * the method run on edge collection and check connection between to keys
	 * if connection exist return edge weight
	 * @return i.getVal() if exist else -1  parameter of double type
	 */
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

	/**
	 * method create new node in graph
	 * Mast get uncle parameter key
	 */
	@Override
	public void addNode(int key) {
		NodeInfo newNode=new NodeInfo(key);//<-----???need to cheak the metod
		//this.key++;
		nodeHashMap.put(key, newNode);	
		modCount++;
	}
	/**
	 * method create edge between two nodes
	 * the method create new edge object and fill them in keys of nodes and weight value
	 * or change older weight value if the connection exist
	 */
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
	/**
	 * Method return collection of all nodes
	 * @return nodeCollection
	 */
	@Override
	public Collection<node_info> getV() {
		nodeCollection.addAll(nodeHashMap.values());
		return nodeCollection;
	}
	/**
	 * Method return collection of neighbors nodes
	 * method run on edge collection an collect all node who have edge between needed node
	 * @return tampeCollection
	 */
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

	/**
	 * Method remove node and the all connection for hem to not have pointers on hem
	 * and return the removed node late time
	 * @return nodeTampe
	 */
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
	/**
	 * Method remove edge 'connection' between nodes
	 * method get nodes keys found edge who start and edn on tjis keys and remove them
	 */
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

	/**
	 * Method return number of nodes in graph
	 * @return nodeHashMap.size() object of integer type
	 */
	@Override
	public int nodeSize() {
		return nodeHashMap.size();
	}

	/**
	 * Method return number of edges in graph
	 * @return edges.size()/2 object of integer type
	 */
	@Override
	public int edgeSize() {
		// TODO Auto-generated method stub
		return edges.size()/2;
	}

	/**
	 * Method return number of changes in graph
	 * @return modCount object of integer type
	 */
	@Override
	public int getMC() {
		// TODO Auto-generated method stub
		return modCount;
	}
	/*
	 * @Override public String toString() { return new
	 * StringBuffer(" nodeCollection ").append(this.nodeCollection)
	 * .append(" nodeHashMap ").append(this.nodeHashMap).append(" edges ").append(
	 * this.edges).toString(); }
	 */

}
