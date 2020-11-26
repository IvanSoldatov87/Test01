package ex1.src;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


import ex1.src.WGraph_DS.NodeInfo;
/**
 * This class exist complicated function on graph
 * Deep copy to copy all object to anther memory
 * Check if graph has connection between all nodes
 * found short path between nodes and show the path
 * Save the graph in file and load them
 * @author Ivan
 *
 */

public class WGraph_Algo implements weighted_graph_algorithms {
	WGraph_DS g;
	//WGraph_DS g2;
	NodeInfo nodInfo;
	final int maxInt=Integer.MAX_VALUE;
	Queue<node_info> queue =new LinkedList<node_info>();
	@Override
	
	/**
	 * Method initial graph 
	 * @param g is pointer to object of WGraph_DS type
	 */
	public void init(weighted_graph g) {
		this.g= (WGraph_DS) g;	
	
	}
	/**
	 * Method return our graph
	 * @return g is pointer to object of WGraph_DS type
	 */
	@Override
	public weighted_graph getGraph() {
		if(g==null) return null;// be careful by null
		return g;
	}
	/**
	 * Method make deep cope of graph
	 * to new object @param newG <---new copy Object WGraph_DS type and return the deep copy
	 * @return newG   <----is copy of our graph 
	 */
	@Override
	public weighted_graph copy() {
		WGraph_DS newG=new WGraph_DS();
		Iterator<node_info> it=g.getV().iterator();
		while(it.hasNext()) {
			nodInfo= (NodeInfo) it.next();
			//newG.nodeHashMap.put(nodInfo.getKey(), new NodeInfo(nodInfo)); <---- problem in inject inside class can't implement new object, need to check the problem// the copy can be implement in one row/one command
			newG.addNode(nodInfo.getKey());
			newG.getNode(nodInfo.getKey()).setInfo(nodInfo.getInfo());
			newG.getNode(nodInfo.getKey()).setTag(nodInfo.getTag());	
		}
		Collection<Edge> edges=g.getAllEdge();
		for(Edge i:edges) {
			newG.connect(i.getStart(),i.getEnd(), i.getVal());
		}
		return newG;
	}

	/**
	 * Method check if all nodes have edges and we can travel in all graph 
	 * the method return boolean object and use bfs method to run on all graph
	 * then the method check if we visited all nodes
	 * so if all nodes is visited return true alse false
	 * @return boolean
	 */
	@Override
	public boolean isConnected() {
		Iterator<node_info> itt=g.getV().iterator();
		if(g.nodeHashMap.size()<2)return true;
		bfs(itt.next());
		Iterator<node_info> ittt=g.getV().iterator();
		while(ittt.hasNext()) {
			if(ittt.next().getTag()!=1)return false;
		}
        return true;     
	}

	/**
	 * method run on the graph and signs visited nodes
	 * method collect non visited nodes then check them neighborhoods of visited node and add him to collection if the node is not visited yet
	 * method sort nodes between connected to graph nodes and unconnected
	 */
	public void bfs(node_info node )
    {
		Iterator itt=g.getV().iterator();
        while(itt.hasNext()){
        	node_info nd= (node_info) itt.next();
        	nd.setTag(0);
        }
        node_info nodeInfo;
        node.setTag(1);
        queue.add(node);
        while (!queue.isEmpty())
        {
        	nodeInfo = queue.remove();
        	nodeInfo.setTag(1);
        	if(!(g.getV(nodeInfo.getKey()).isEmpty())) {
            Iterator<node_info> it=g.getV(nodeInfo.getKey()).iterator();
            while (it.hasNext())
            {
            	node_info nTampe=it.next();
                if (nTampe.getTag()==0)
                {
                    queue.add(nTampe);
                    //nTampe.setTag(1);
                }
            }
            }
        }
    }
	
	
	/**
	 * method look for short path between two nodes
	 * the method get to keys and run on the graph and look for short path
	 * the sorted path will be the minimum sums of edge weighted in the path
	 * @return sums of edge weighted in the path
	 */
	@Override
	public double shortestPathDist(int src, int dest) {
		if(g.getNode(src)==null || g.getNode(dest)==null) return 0;   
		return bfs2(g.getNode(src),g.getNode(dest));
	}
	/**
	 * method run on the graph and signs visited nodes 
	 * method sort nodes between connected to graph nodes and unconnected
	 * if needed nodes are visited so the are have path
	 */
	public double bfs2(node_info src,node_info dest )
    {
		String visit="V";
        for(node_info i :this.g.getV()){
            i.setTag(maxInt);
        }
        node_info nodeInfo;
        src.setTag(0);
        src.setInfo(visit);
        queue.add(src);
        while (!queue.isEmpty())
        {
        	nodeInfo = queue.remove();
        	nodeInfo.setInfo(visit);
        	if(!g.getV(nodeInfo.getKey()).isEmpty()) {
            Iterator<node_info> it=g.getV(nodeInfo.getKey()).iterator();
            while (it.hasNext())
            {
            	node_info nTampe=it.next();
                if (nTampe.getInfo().isEmpty())
                {
                    queue.add(nTampe);
                    if(nTampe.getTag()>g.getEdge(nodeInfo.getKey(), nTampe.getKey())+nodeInfo.getTag()) {
                    nTampe.setTag(g.getEdge(nodeInfo.getKey(), nTampe.getKey())+nodeInfo.getTag());
                    }
                }
                if(nTampe.getTag()>g.getEdge(nodeInfo.getKey(), nTampe.getKey())+nodeInfo.getTag()) {
                	nTampe.setTag(nodeInfo.getTag()+g.getEdge(nodeInfo.getKey(), nTampe.getKey()));
                }
            }
            }
        }
        if(dest.getTag()==maxInt)return 0;
        return dest.getTag();
    }
	
	/**
	 * method look for short path and print them
	 * first method run in all nodes and look for short path
	 * then in reveres run method equal tag between nodes an edge weighted to found shore path
	 * @return result Object of Collection type
	 */
	@Override
	public List<node_info> shortestPath(int src, int dest) {
		bfs(g.getNode(src));
		  List<node_info> result = new ArrayList<node_info>() ; 
		  	node_info node=g.getNode(dest);
	        node_info nodeInfo;
	        queue.add(node);
	        while (!queue.isEmpty())
	        {
	        	nodeInfo = queue.remove();
	        	//nodeInfo.setTag(1);
	        	if(!(g.getV(nodeInfo.getKey()).isEmpty())) {
	            Iterator<node_info> it=g.getV(nodeInfo.getKey()).iterator();
	            while (it.hasNext())
	            {
	            	node_info nTampe=it.next();
	                if (nTampe.getTag()==(nodeInfo.getTag()-g.getEdge(nTampe.getKey(), nodeInfo.getKey())))
	                {
	                	result.add(nTampe);
	                    queue.add(nTampe);
	                }
	            }
	        }
	     }
	    Collections.reverse(result);
        return  result;
    }
	

	/**
	 * method make file and save in collection of nodes and edges
	 * this method make reserve copy on HD 
	 * @return boolean if copy is successful
	 */
	@Override
	public boolean save(String file) {
		String fileName= file;
	    FileOutputStream fos;
	   // WGraph_DS g2=(WGraph_DS) copy();
		try {
			fos = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
		    oos.writeObject(g);
		    oos.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	    
		
		return true;
	}

	/**
	 * method load graph from file in HD
	 * and initialize them in class
	 * @return boolean if load is successful
	 */
	@Override
	public boolean load(String file) {
		String fileName= file;
		   FileInputStream fin;
		try {
			fin = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(fin);
			init((WGraph_DS) ois.readObject());
			ois.close();
		} catch ( IOException | ClassNotFoundException e) {
			
			e.printStackTrace();
			return false;
		}
		   
		  
		return true;
	}

}
