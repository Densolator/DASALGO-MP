import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

public class Main {
	static ArrayList<Mail> all_mails = new ArrayList<Mail>();
	static ArrayList<Mail> input_mails = new ArrayList<Mail>();
	static ArrayList<Mail> all_manila_mails = new ArrayList<Mail>();
	static ArrayList<Mail> all_quezon_mails = new ArrayList<Mail>();
	static ArrayList<Mail> all_makati_mails = new ArrayList<Mail>();
	static ArrayList<Mail> all_pasay_mails = new ArrayList<Mail>();
	static ArrayList<Mail> mails_for_manila = new ArrayList<Mail>();
	static ArrayList<Mail> mails_for_quezon = new ArrayList<Mail>();
	static ArrayList<Mail> mails_for_makati = new ArrayList<Mail>();
	static ArrayList<Mail> mails_for_pasay = new ArrayList<Mail>();
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		String filename;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter the filename of the map:");
		filename = scanner.nextLine();
		scanCSV(filename);
		distributeAll();
		
		scanner.close();
	}
	
	static void scanCSV(String filename) throws IOException
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
	
	static void distributeAll()
	{
		int ctr;
		for(ctr = 0; ctr < all_mails.size() - 1; ctr++)
		{
			switch(all_mails.get(ctr).postoffice)
			{
			case "Manila City":
				all_manila_mails.add(all_mails.get(ctr));
				break;
			
			case "Quezon City":
				all_quezon_mails.add(all_mails.get(ctr));
				break;
			
			case "Makati City":
				all_makati_mails.add(all_mails.get(ctr));
				break;
			
			case "Pasay City":
				all_pasay_mails.add(all_mails.get(ctr));
				break;
			}
		}
		System.out.println(all_manila_mails.get(0).source);
		System.out.println(all_quezon_mails.get(0).source);
		System.out.println(all_makati_mails.get(0).source);
		System.out.println(all_pasay_mails.get(0).source);
		return;
	}

	static void distribute()
	{
		return;
	}
}
