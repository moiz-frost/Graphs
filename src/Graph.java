import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.text.html.HTMLDocument.Iterator;


public class Graph {
	private Vertex[] adjList;
	private int index = 0;
	
	
	Graph(int s){
		adjList = new Vertex[s];
		
	}
	
	//Add vertex add edges
	
	public void addVertex(String nodeID, String label, String sex, String language, int age){
		if(index > adjList.length){
			System.out.println("Graph is full :P");
			return;
		}
		if(getVertex(label) != null){
			System.out.println("Cannot create duplicate vertex");
			return;
		}
		
		adjList[index] = new Vertex(nodeID, label, sex, language, age);
		index++;
	}
	
	public void associateVextex(String id1, String id2){
	    Vertex v1 = getVertex(id1);
	    Vertex v2 = getVertex(id2);
		if(v1 == null || v2 == null){
			System.out.println("Vertex does not exist");
			return;
		}
	    if(v1 == v2){
			System.out.println("You cant associate the same vertex with itself");
			return;
		}
		if(v1.isNeighbour(v2)){
			System.out.println("Already associated :)");
			return;
		}else{
			v1.neighbour.add(v2);
			v2.neighbour.add(v1);
		}
	}
	
	public Vertex getVertex(String id){
		int i = 0;
		while(adjList[i]!= null) {
			
			if(adjList[i].nodeID.equals(id)){
				return adjList[i];
			}
			i++;
		}
		return null;
	}
	
	/*
	 * Q2) Part 4
	 * Suggest a person to make new friends by providing list of person who may know
	 * each other (due to mutual friendship) or highly likely to be a friend. (using DFS
	 * or BFS)
	 * 
	 */
	public void getMutual(String id){
		Vertex center = getVertex(id);
		 Set<Vertex> mutual = new HashSet<Vertex>();
		int index=0;
		for (int i = 0; i < center.neighbour.size(); i++) {
			Vertex friend = center.neighbour.get(i);
			for (int j = 0; j < friend.neighbour.size(); j++) {
				if(!center.isNeighbour(friend.neighbour.get(j))){
					mutual.add(friend.neighbour.get(j));
				}
			}
		}
		
		for (Vertex vertex : mutual) {
			System.out.println("You may know :" + vertex.label);
		}
	}
	
	public int depthFirst(Vertex start, int sum){
		if(start == null || start.visit == true){
			return sum;
		}
		
		sum++;
		System.out.println(start.label);
		start.visit  = true;
		sum = depthFirst(start.getElement(), sum);
		
		return sum;
	}
	

	
	/*
	 * Prints the list of people a person is friends with
	 * Q2) Part (A)
	 * How many connected component in the network. (use DFS or BFS)
	 */
	public void getFriendsNetwork(String id){
		int sum = 0;
		sum = depthFirst(getNode(id), sum);
		System.out.println("Total people in cluster: " + sum);
	}
	
	/*
	 * Gets the node when the id is given
	 */
	public Vertex getNode(String id){
		int i = 0;
		while(i < adjList.length){
			if(adjList[i].nodeID.equals(id)){
				return adjList[i];
			}
			i++;
		}
		return null;
	}
	
	/*
	 * This method prints the total people added in the graph
	 */
	public void getTotalPeople() {
		System.out.println("Total people in graph:" + index);
	}
	/*
	 * Q2) part b and c
	 * How many male and female in the given friends network. 
	 * How many of your friends are in group of teenage (under 20), youngster (over
	 * 20, under 35), older (over 35).
	 */
	public void getFriendsStats(String id) {
		int male = 0;
		int female = 0;
		int teens = 0;
		int youngs = 0;
		int adults = 0;
		int total = 0;
		LinkedList<Vertex> l = getNode(id).neighbour;
		int i = 0;
		while(i < l.size()){
			total++;
			
			if(l.get(i).age < 20){
				teens++;
			}else if(l.get(i).age > 35){
				adults++;
			}else{
				youngs++;
			}
			
			if(l.get(i).sex.equals("male")){
				male++;
			}else{
				female++;
			}
			i++;
		}
		System.out.println("=============================");
		System.out.println("Total Friends: " + total);
		System.out.println("Teens: " + teens);
		System.out.println("Youngsters: " + youngs);
		System.out.println("Adults: " + adults);
		System.out.println("Total males: " + male);
		System.out.println("Total females: " + female);
		System.out.println("Male Percent: " + (float) male/total * 100);
		System.out.println("Female Percent: " + (float) female/total * 100);
		System.out.println("=============================");
	}
	
	public void printData() {
		int i = 0;
		while(i < adjList.length){
		depthFirst(adjList[i],0);
		i++;
		}
	}
	
	/*
	 * Q2 ) part 3 and 4
	 * Find node with maximum degree
	 * Find node with minimum degree
	 * Gets the person with min and max nodes 
	 */
	public void getMinMax() {
		int i = 0;
		int maxId = 0;
		int minId = 0;
		
		while(adjList[i] != null){
		if(adjList[maxId].neighbour.size() >= adjList[i].neighbour.size()){
			maxId = maxId; 
		}else{
			maxId = i;
		}
		i++;
		}
		i=0;
		while(adjList[i] != null){
			if(adjList[minId].neighbour.size() <= adjList[i].neighbour.size()){
				minId = minId; 
			}else{
				minId = i;
			}
			i++;
			}
		
		System.out.println("Person with max nodes: " + adjList[maxId].label + " Nodes: " + adjList[maxId].neighbour.size());
		System.out.println("Person with min nodes: " + adjList[minId].label + " Nodes: " + adjList[minId].neighbour.size());
		
		
	}
	
	
}
