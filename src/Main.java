import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Graph dayemnoob = new Graph(50);
		
		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Coder\\Desktop\\Friends.txt"));
        String line = br.readLine();
        while(true){
        	 line = br.readLine();
        	 if(line == null) break;
             String[] b = line.split(",");
             dayemnoob.addVertex(b[0], b[1], b[2], b[3],  Integer.parseInt(b[4]));
        }
        br.close();
		
        BufferedReader links = new BufferedReader(new FileReader("C:\\Users\\Coder\\Desktop\\association.txt"));
        String link = links.readLine();
        while(true){
       	 line = links.readLine();
       	 if(line == null) break;
            String[] b = line.split(",");
            dayemnoob.associateVextex(b[0], b[1]);
            }
       links.close();
       
         //dayemnoob.getFriendsStats("100002258251610");
         //dayemnoob.getMinMax();
       dayemnoob.getMutual("100002258251610");
	}
	
	

}
