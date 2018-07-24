import java.util.*;
import java.io.*;

public class Main {
	static ArrayList<Mail> mails = new ArrayList<Mail>(); //Initialization of the ArrayList containing all of the data from the CSV
	static ArrayList<Mail> input_mails = new ArrayList<Mail>();
//	static ArrayList<Mail> manila_mails = new ArrayList<Mail>(); //ArrayList containing all of the mails for Manila
	static ArrayList<Mail> quezon_mails = new ArrayList<Mail>(); //ArrayList containing all of the mails for Quezon
	static ArrayList<Mail> makati_mails = new ArrayList<Mail>(); //ArrayList containing all of the mails for Makati
	static ArrayList<Mail> pasay_mails = new ArrayList<Mail>(); //ArrayList containing all of the mails for Pasay

	public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException{
		// TODO Auto-generated method stub
		String mailinput, file, temp, key = "";  //The variable used to store the line from the user input and the one used to store the filename respectively
		ArrayList<String> inputs = new ArrayList<String>(); //The String array that is used to store all the inputs made by the user
		int numinputs, ctr, input; //Variable used to store the number of mails the user wishes to enter
		Scanner scanner = new Scanner(System.in);
		
		
		System.out.println("Location of Map:");
		file = scanner.nextLine();

		
		scanCSV(mails, file); //Population of the ArrayList
		
		
		boolean done = false;

		
		while(!done)
		{
			for (int i = 0; i < 50; ++i) System.out.println();
			
			input = displayMainMenu();
			switch(input)
			{
			case 1:
				route(1);	
				while(!input_mails.isEmpty()) {
						String dest = input_mails.get(0).destination;
						
						if (dest.contains("Makati,"))
							route(3);
						else if(dest.contains("Quezon City"))
							route(2);
						else if (dest.contains("Pasay City"));
							route(4);
						
					
				}
				
				break;
				
			case 2:
				route(2);
				while(!input_mails.isEmpty()) {
						String dest = input_mails.get(0).destination;
						
						
						if (dest.contains("Makati,"))
							route(3);
						else if(dest.contains("Manila,"))
							route(1);
						else if (dest.contains("Pasay City"))
							route(4);
						else
							System.out.println("hehe");
						
				}
				
				break;
			
			case 3:
				route(3);
				while(!input_mails.isEmpty()) {
						String dest = input_mails.get(0).destination;
						
						
						if (dest.contains("Manila,"))
							route(1);
						else if(dest.contains("Quezon City"))
							route(2);
						else if (dest.contains("Pasay City"));
							route(4);
							
				}
				
				break;
				
			case 4:
				route(4);
				while(!input_mails.isEmpty()) {

						String dest = input_mails.get(0).destination;
						
						
						if (dest.contains("Manila,"))
							route(1);
						else if(dest.contains("Quezon City"))
							route(2);
						else if (dest.contains("Makati,"));
							route(3);
						
				}
				
				break;
				
			case 5:
				done = true;
				break;
			}
		}
		
		
		
		
		
		
		scanner.close();
		return;
		
		
	}
	
	public static void route(int input)
	{
		int numinputs, ctr;
		String mailinput;
		ArrayList<String> inputs = new ArrayList<String>();
		Scanner scanner = new Scanner(System.in);
		switch(input)
		{
		case 1:
			ArrayList<Mail> manila_mails = new ArrayList<Mail>();
			for (int i = 0; i < 50; ++i) System.out.println();
			System.out.println("We are going to Manila City Post Office to get the mails to be delivered.");
			System.out.println("How many mails are there?");
			
			numinputs = scanner.nextInt();
			scanner.nextLine();//flushes the next line di ko na rin alam kung bakit kailangan neto basta gumagana
			ctr = 0;
			while(ctr < numinputs)
			{
				System.out.println("Destination of Mail " + (ctr+1) + ":");
				mailinput = scanner.nextLine();
				inputs.add(mailinput);
				ctr++;
			}
		
			stringCompare(inputs, input_mails, mails);
			distribute2(input_mails, manila_mails, "Manila,");
			sort(manila_mails);
			
			for (int i = 0; i < 50; ++i) System.out.println();
			
			for(ctr = 0; ctr < manila_mails.size(); ctr++)
			{
				if(ctr == 0)
					System.out.println("First, we will go to " + manila_mails.get(ctr).getDestination());
				else if(ctr == (input_mails.size()-1))
					System.out.println("Lastly, we will go to " + manila_mails.get(ctr).getDestination());
				else
					System.out.println("Then we will go to " + manila_mails.get(ctr).getDestination());
				
			}
			manila_mails.clear();
			System.out.println("All mails for Manila City are delivered!");
			
			break;
		case 2:
			for (int i = 0; i < 50; ++i) System.out.println();
			System.out.println("We are going to Quezon City Post Office to get the mails to be delivered.");
			System.out.println("How many mails are there?");
			
			numinputs = scanner.nextInt();
			scanner.nextLine();//flushes the next line di ko na rin alam kung bakit kailangan neto basta gumagana
			ctr = 0;
			while(ctr < numinputs)
			{
				System.out.println("Destination of Mail " + (ctr+1) + ":");
				mailinput = scanner.nextLine();
				inputs.add(mailinput);
				ctr++;
			}
			
			stringCompare(inputs, input_mails, mails);
//			distribute(input_mails, manila_mails, quezon_mails, makati_mails, pasay_mails);
			distribute2(input_mails, quezon_mails, "Quezon City");
			sort(quezon_mails);
			for (int i = 0; i < 50; ++i) System.out.println();
			
			for(ctr = 0; ctr < quezon_mails.size(); ctr++)
			{
				if(ctr == 0)
					System.out.println("First, we will go to " + quezon_mails.get(ctr).getDestination());
				else if(ctr == (quezon_mails.size()-1))
					System.out.println("Lastly, we will go to " + quezon_mails.get(ctr).getDestination());
				else
					System.out.println("Then we will go to " + quezon_mails.get(ctr).getDestination());
			}
			quezon_mails.clear();
			
			System.out.println("All mails for Quezon City are delivered!");
			
			break;
		case 3:
			for (int i = 0; i < 50; ++i) System.out.println();
			System.out.println("We are going to Makati City Post Office to get the mails to be delivered.");
			System.out.println("How many mails are there?");
			
			numinputs = scanner.nextInt();
			scanner.nextLine();//flushes the next line di ko na rin alam kung bakit kailangan neto basta gumagana
			ctr = 0;
			while(ctr < numinputs)
			{
				System.out.println("Destination of Mail " + (ctr+1) + ":");
				mailinput = scanner.nextLine();
				inputs.add(mailinput);
				ctr++;
			}
			
			stringCompare(inputs, input_mails, mails);
//			distribute(input_mails, manila_mails, quezon_mails, makati_mails, pasay_mails);
			distribute2(input_mails, makati_mails, "Makati,");
			sort(makati_mails);
			for (int i = 0; i < 50; ++i) System.out.println();
			
			for(ctr = 0; ctr < makati_mails.size(); ctr++)
			{
				if(ctr == 0)
					System.out.println("First, we will go to " + makati_mails.get(ctr).getDestination());
				else if(ctr == (makati_mails.size()-1))
					System.out.println("Lastly, we will go to " + makati_mails.get(ctr).getDestination());
				else
					System.out.println("Then we will go to " + makati_mails.get(ctr).getDestination());
			}
			makati_mails.clear();
			
			System.out.println("All mails for Makati City are delivered!");
			
			break;
		case 4:
			for (int i = 0; i < 50; ++i) System.out.println();
			System.out.println("We are going to Pasay City Post Office to get the mails to be delivered.");
			System.out.println("How many mails are there?");
			
			numinputs = scanner.nextInt();
			scanner.nextLine();//flushes the next line di ko na rin alam kung bakit kailangan neto basta gumagana
			ctr = 0;
			while(ctr < numinputs)
			{
				System.out.println("Destination of Mail " + (ctr+1) + ":");
				mailinput = scanner.nextLine();
				inputs.add(mailinput);
				ctr++;
			}
			
			stringCompare(inputs, input_mails, mails);
			distribute2(input_mails, pasay_mails, "Pasay City");
			sort(pasay_mails);
			for (int i = 0; i < 50; ++i) System.out.println();
			
			for(ctr = 0; ctr < pasay_mails.size(); ctr++)
			{
				if(ctr == 0)
					System.out.println("First, we will go to " + pasay_mails.get(ctr).getDestination());
				else if(ctr == (pasay_mails.size()-1))
					System.out.println("Lastly, we will go to " + pasay_mails.get(ctr).getDestination());
				else
					System.out.println("Then we will go to " + pasay_mails.get(ctr).getDestination());
			}
			pasay_mails.clear();
			
			System.out.println("All mails for Pasay City are delivered!");
			
			break;
		}
		return;
	}
	
	public static void distribute2(ArrayList<Mail> input_mails, ArrayList<Mail> destination, String key)
	{
		int ctr = 0, size = input_mails.size(), temp = 0;
		while(ctr < size)
		{

			if(input_mails.get(temp).destination.contains(key))
			{
				
				destination.add(input_mails.get(temp));
				input_mails.remove(temp);
			}
			else
				temp++;
			ctr++;
				
		}
	}
	
	public static void stringCompare(ArrayList<String> inputs, ArrayList<Mail> mails, ArrayList<Mail> mailslist)
	{
		int temp, ctr1 = 0, ctr2, numinputs;
		numinputs = inputs.size();
		while(ctr1 < numinputs)
		{
			for(ctr2 = 0; ctr2 < mailslist.size(); ctr2++)
			{
				if(mailslist.get(ctr2).destination.equals(inputs.get(ctr1)))
					mails.add(mailslist.get(ctr2));
			}
			ctr1++;
		}
		return;
	}
	
	public static int displayMainMenu()
	{
		Scanner scanner = new Scanner(System.in);
		int input;
		
		System.out.println("1 - Manila City");
		System.out.println("2 - Quezon City");
		System.out.println("3 - Makati City");
		System.out.println("4 - Pasay City");
		System.out.println("5 - Exit");
		input = scanner.nextInt();
		
		return input;
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
	
	
	public static void scanCSV(ArrayList<Mail> mail, String file) throws IOException, FileNotFoundException
	{
		String filename = "/Users/Jericho/Documents/Subjects/DASALGO/" + file;
		String line = "", header;
		Float temp;
		int ctr;
		
		BufferedReader br  = new BufferedReader(new FileReader(filename));
		header = br.readLine();
//		System.out.println(header);
		
		while((line = br.readLine()) != null)
		{
			String[] linearray = line.split(",");
			
			temp = Float.parseFloat(linearray[linearray.length-1]);
			
			mail.add(new Mail(linearray[0], linearray[1], "", temp));
			
			ctr = 2;
			for(ctr = 2; ctr < linearray.length - 1; ctr++)
			{
				if(ctr == 2)
					mail.get(mail.size() - 1).setDestination(mail.get(mail.size() - 1).destination + linearray[ctr]);
				else
					mail.get(mail.size() - 1).setDestination(mail.get(mail.size() - 1).destination + "," + linearray[ctr]);
			}
			
//			System.out.println(mail.get(mail.size()-1).destination);
		}
		
		br.close();
		return;
	}

}
