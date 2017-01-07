import java.util.*;

public class Vertex {
	String nodeID,label,sex,language;
	int age;
	boolean visit;
	int index = 0;
	LinkedList<Vertex> neighbour =  new LinkedList<Vertex>();
	
	Vertex(String nodeID, String label, String sex, String language, int age)
	{
		this.nodeID = nodeID;
		this.label  = label;
		this.sex = sex;
		this.language = language;
		this.age = age;
		visit = false;
	}
	
	public boolean isNeighbour(Vertex v){
		if(neighbour.contains(v)){
			return true;
		}
		return false;
	}
	
	public Vertex getElement(){
		while(index < neighbour.size()){
			if(neighbour.get(index).visit){
				index++;
			}
			else{
				return neighbour.get(index);
			}
		}
		return null;
	}
	
}
