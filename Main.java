import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws FileNotFoundException{
		// TODO Auto-generated method stub
		int startInput;  //yung gagamitin para sa pag-read nung starting
//		Mail[] mails = new Mail[](); //object with Starting location, destination and distance
		
		ArrayList<Float> distances = new ArrayList<>();
		ArrayList<Mail> mails = new ArrayList<Mail>();
		scanCSV(mails);
		System.out.println(mails.get(0).getDestination());
		sort(mails);
//		System.out.println(mails.get(3).distance);
		
		return;
		
		
	}
	//We use selection sort
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
//				System.out.println(lineArray[lineArray.length-1]);
				temp = Float.parseFloat(lineArray[lineArray.length - 1]);
				mail.add(new Mail(lineArray[0], lineArray[1], "", Float.parseFloat(lineArray[lineArray.length - 1])));
				for(ctr = 2; ctr < lineArray.length - 1; ctr++)
				{
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
				break;
			}
		}
		CSVScanner.close();
		return;
	}

}
