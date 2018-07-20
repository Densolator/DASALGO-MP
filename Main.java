import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws FileNotFoundException{
		// TODO Auto-generated method stub
		int startInput;  //yung gagamitin para sa pag-read nung starting
		
		ArrayList<Mail> mails = new ArrayList<Mail>(); //Initialization of the ArrayList containing all of the data from the CSV
		scanCSV(mails); //Population of the ArrayList
		ArrayList<Mail> manila_mails = new ArrayList<Mail>(); //ArrayList containing all of the mails for Manila
		ArrayList<Mail> quezon_mails = new ArrayList<Mail>(); //ArrayList containing all of the mails for Quezon
		ArrayList<Mail> makati_mails = new ArrayList<Mail>(); //ArrayList containing all of the mails for Makati
		ArrayList<Mail> pasay_mails = new ArrayList<Mail>(); //ArrayList containing all of the mails for Pasay
		distribute(mails, manila_mails, quezon_mails, makati_mails, pasay_mails);
//		System.out.println(mails.get(0).getDestination());
		sort(mails);
//		System.out.println(mails.get(3).distance);
		
		return;
		
		
	}
	
	public static void distribute(ArrayList<Mail> All_mails, ArrayList<Mail> Manila_mails, ArrayList<Mail> Quezon_mails, ArrayList<Mail> Makati_mails, ArrayList<Mail> Pasay_mails)
	{
		int size = All_mails.size(), ctr;
		for(ctr = 0; ctr < size; ctr++)
		{
			if(All_mails.get(ctr).destination.contains("Manila,"))
				Manila_mails.add(All_mails.get(ctr));
			else if(All_mails.get(ctr).destination.contains("Quezon,"))
				Quezon_mails.add(All_mails.get(ctr));
			else if(All_mails.get(ctr).destination.contains("Makati,"))
				Makati_mails.add(All_mails.get(ctr));
			else if(All_mails.get(ctr).destination.contains("Pasay,"))
				Pasay_mails.add(All_mails.get(ctr));

		}
		System.out.println(Pasay_mails.get(0).postOffice + " " + Pasay_mails.get(0).location + " " + Pasay_mails.get(0).destination);
		return;
	}
	
	//This function sorts all the data according to the distance (ascending). We use selection sort for this function.
	public static void sort(ArrayList<Mail> mails)
	{
		int length = mails.size();
		int itr1, itr2;
		// 2 for loops
		for(itr1 = 0; itr1 < length; itr1++)
		{
			int min = itr1;
			for(itr2 = itr1 +1; itr2 < length; itr2++)
				if(mails.get(itr2).distance < mails.get(min).distance)
					min = itr2;
					
			Mail temp = mails.get(min);
			mails.set(min, mails.get(itr1));
			mails.set(itr1, temp);
			
		}
		return;
	}
	
	//This function gets all the data from the user input/CSV file and stores them into objects which are then stored in an object array
	public static void scanCSV(ArrayList<Mail> mail) throws FileNotFoundException
	{
//		Scanner CSVScanner = new Scanner(new File("/Users/Jericho/Documents/Subjects/DASALGO/Map (bonus).csv"));
		Scanner CSVScanner = new Scanner(new InputStreamReader(System.in));
		String line = "";
		float temp;
		int ctr;
		String header = CSVScanner.nextLine();
		System.out.println(header);
		while(CSVScanner.hasNextLine())
		{
			try
			{
				ctr = 2;
				line = CSVScanner.nextLine();
				String[] lineArray = line.split(",");
				temp = Float.parseFloat(lineArray[lineArray.length - 1]);
				mail.add(new Mail(lineArray[0], lineArray[1], "", Float.parseFloat(lineArray[lineArray.length - 1])));
				for(ctr = 2; ctr < lineArray.length - 1; ctr++)
				{
					if(ctr == 2)
						mail.get(mail.size() - 1).setDestination(mail.get(mail.size() - 1).destination + lineArray[ctr]);
					else
						mail.get(mail.size() - 1).setDestination(mail.get(mail.size() - 1).destination + "," + lineArray[ctr]);
				}
				System.out.println(mail.get(mail.size() - 1).getDestination());
			}
			catch (NumberFormatException ex)
			{
//				CSVScanner.next();
//				line = CSVScanner.nextLine();
//				String[]lineArray = line.split(",");
//				System.out.println(lineArray[lineArray.length-1]);
//				temp = Float.parseFloat(lineArray[lineArray.length-1]);
//				dists.add(temp);
//				System.out.println(line);
//				System.out.println(CSVScanner.nextLine());
				break;
			}
		}
		CSVScanner.close();
//		System.out.println("pinish na");
		return;
	}

}
