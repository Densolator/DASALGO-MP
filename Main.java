import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws FileNotFoundException{
		// TODO Auto-generated method stub
		
		
		ArrayList<Mail> mails = new ArrayList<Mail>(); //Initialization of the ArrayList containing all of the data from the CSV
		scanCSV(mails); //Population of the ArrayList
		ArrayList<Mail> manila_mails = new ArrayList<Mail>(); //ArrayList containing all of the mails for Manila
		ArrayList<Mail> quezon_mails = new ArrayList<Mail>(); //ArrayList containing all of the mails for Quezon
		ArrayList<Mail> makati_mails = new ArrayList<Mail>(); //ArrayList containing all of the mails for Makati
		ArrayList<Mail> pasay_mails = new ArrayList<Mail>(); //ArrayList containing all of the mails for Pasay
		distribute(mails, manila_mails, quezon_mails, makati_mails, pasay_mails);
//		System.out.println(mails.get(0).getDestination());
		sort(manila_mails);
		sort(quezon_mails);
		sort(makati_mails);
		sort(pasay_mails);
		
		System.out.println("1. Manila , 2. Makati , 3. Quezon, 4. Pasay, 5. Exit");
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		System.out.println("Enter Starting Location: ");
	
		int startInput = reader.nextInt(); // Scans the next token of the input as an int.
		//once finished
		reader.close();
		
		if (startInput == 1) {
			 {
				System.out.println("Going to Manila");
//				Scanner reader = new Scanner(System.in);
//				System.out.println("Enter number of mail to deliver: ");
				
				routeM(manila_mails);
				
			}
			
			
		}
//		else if (startInput == 2) {
//			System.out.println("Going to Makati");
//			Scanner checker = new Scanner(System.in);  // Reading from System.in
//			System.out.println("Enter number of mail to deliver: ");
//		
//			int startInput = reader.nextInt(); // Scans the next token of the input as an int.
//			//once finished
//			reader.close();
//			}
//			
//		else if (startInput == 3) {
//			System.out.println("Going to Quezon");
//			Scanner checker = new Scanner(System.in);  // Reading from System.in
//			System.out.println("Enter number of mail to deliver: ");
//		
//			int startInput = reader.nextInt(); // Scans the next token of the input as an int.
//			//once finished
//			reader.close();
//			}
//		
//		else if (startInput == 4) {
//			System.out.println("Going to Pasay");
//			Scanner checker = new Scanner(System.in);  // Reading from System.in
//			System.out.println("Enter number of mail to deliver: ");
//		
//			int startInput = reader.nextInt(); // Scans the next token of the input as an int.
//			//once finished
//			reader.close();
//			}
//		else {
//			return;
//		}
//				

		

		
//		route(startInput, mails, manila_mails, quezon_mails, makati_mails, pasay_mails);
		
		return;
		
		
	}
	
private static void routeM(ArrayList<Mail> manila_mails) {
		// TODO Auto-generated method stub
	
	// Based from the Sample Miss sent 
	
	// size should be equal to the number mails to be delivered?
	// User inputs the location to send letters to then sort based on distance
	
	int size = manila_mails.size();
		for(int i = 0 ; i < size ; i++) {
			System.out.println(manila_mails.get(i).destination);
			System.out.println(manila_mails.get(i).distance);
			System.out.println(">");
		}		
	}

//	private static void route(int startInput, ArrayList<Mail> mails, ArrayList<Mail> manila_mails,
//			ArrayList<Mail> quezon_mails, ArrayList<Mail> makati_mails, ArrayList<Mail> pasay_mails) {
//		// routing
//		
//	
//			
//			
//	}


		

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
//		System.out.println(Pasay_mails.get(0).postOffice + " " + Pasay_mails.get(0).location + " " + Pasay_mails.get(0).destination);
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
		Scanner CSVScanner = new Scanner(new File("/Users/fredd/Desktop/DASALGO/Map (bonus).csv"));
//		Scanner CSVScanner = new Scanner(new InputStreamReader(System.in));
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
				//Prints Desination
//				System.out.println(mail.get(mail.size() - 1).getDestination());
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