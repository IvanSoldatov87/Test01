Weighted graphs

Weighted graph = a graph whose edges have weights
The weight of an edge can represent:
Cost or distance = the amount of effort needed to travel from one place to another
Representing weighted graphs using an adjacency list

Installation
Use the FileInputStrea,FileNotFoundException,FileOutputStream,IOException,
,ObjectInputStream,ObjectOutputStream,ArrayList,Collection,Collections,
HashMap,Iterator,LinkedList,List,Queue librerys

Usage
bfs​(node_info node)	
method run on the graph and signs visited nodes method collect non visited nodes then check them neighborhoods of visited node and add him to collection if the node is not visited yet method sort nodes between connected to graph nodes and unconnected

double	bfs2​(node_info src, node_info dest)	
method run on the graph and signs visited nodes method sort nodes between connected to graph nodes and unconnected if needed nodes are visited so the are have path

weighted_graph	copy()	
Method make deep cope of graph to new object @param newG <---new copy Object WGraph_DS type and return the deep copy

weighted_graph	getGraph()	
Method return our graph

void	init​(weighted_graph g)	
Init the graph on which this set of algorithms operates on.

boolean	isConnected()	
Method check if all nodes have edges and we can travel in all graph the method return boolean object and use bfs method to run on all graph then the method check if we visited all nodes so if all nodes is visited return true alse false

boolean	load​(java.lang.String file)	
method load graph from file in HD and initialize them in class

boolean	save​(java.lang.String file)	
method make file and save in collection of nodes and edges this method make reserve copy on HD

java.util.List<node_info>	shortestPath​(int src, int dest)	
method look for short path and print them first method run in all nodes and look for short path then in reveres run method equal tag between nodes an edge weighted to found shore path

double	shortestPathDist​(int src, int dest)	
method look for short path between two nodes the method get to keys and run on the graph and look for short path the sorted path will be the minimum sums of edge weighted in the path

License
Ivan
