import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	static ArrayList<Mail> all_mails = new ArrayList<Mail>();
	static ArrayList<Mail> input_mails = new ArrayList<Mail>();
	static ArrayList<Mail> manila_mails = new ArrayList<Mail>();
	static ArrayList<Mail> quezon_mails = new ArrayList<Mail>();
	static ArrayList<Mail> makati_mails = new ArrayList<Mail>();
	static ArrayList<Mail> pasay_mails = new ArrayList<Mail>();
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		String filename;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter the filename of the map:");
		filename = scanner.nextLine();
		scanCSV(all_mails, filename);
		
		scanner.close();
	}
	
	static void scanCSV(ArrayList<Mail> all_mails, String filename) throws IOException
	{
		String path = "/Users/Jericho/Documents/Subjects/DASALGO/" + filename,
			   header,
			   temp;
		BufferedReader br = new BufferedReader(new FileReader(path));
		String[] line;
		int ctr;
		
		header = br.readLine();
		
		while((temp = br.readLine()) != null)
		{
			line = temp.split(",");
			all_mails.add(new Mail(line[0], line[1], line[2], Float.parseFloat(line[3])));
			System.out.println("Post Office: " + line[0] +"   " + "Source: " +line[1] + "   " + "Destination: " +line[2] + "   " + "Distance: " +line[3]);
			
		}
		br.close();
		return;
	}
	
//	static void distribute(ArrayList<Mail> all_mails, 

}
