import java.util.Scanner;
import java.util.Set;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.math.*;

public class Main {
	static ArrayList<Mail> all_mails = new ArrayList<Mail>();
	static ArrayList<String> input_mails = new ArrayList<String>();
	static ArrayList<Mail> all_manila_mails = new ArrayList<Mail>();
	static ArrayList<Mail> all_quezon_mails = new ArrayList<Mail>();
	static ArrayList<Mail> all_makati_mails = new ArrayList<Mail>();
	static ArrayList<Mail> all_pasay_mails = new ArrayList<Mail>();
	static ArrayList<String> mails_for_manila = new ArrayList<String>();
	static ArrayList<String> mails_for_quezon = new ArrayList<String>();
	static ArrayList<String> mails_for_makati = new ArrayList<String>();
	static ArrayList<String> mails_for_pasay = new ArrayList<String>();
	static int current;
	static HashMap<Integer, String> postoffices = new HashMap<Integer,String>();
	static HashMap<String, Integer> manila_hashmap = new HashMap<String, Integer>(),
			 quezon_hashmap = new HashMap<String, Integer>(),
			 makati_hashmap = new HashMap<String, Integer>(),
			 pasay_hashmap = new HashMap<String, Integer>();
	static Graph manila_graph = new Graph(manila_hashmap.size()),
			  quezon_graph = new Graph(quezon_hashmap.size()),
			  makati_graph = new Graph(makati_hashmap.size()),
			  pasay_graph = new Graph(pasay_hashmap.size());
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		postoffices.put(1, "Manila");
		postoffices.put(2, "Quezon");
		postoffices.put(3, "Makati");
		postoffices.put(4, "Pasay");
		
		int ctr;
		String filename;
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Please enter the filename of the map:");
		filename = scanner.nextLine();
		
		scanCSV(filename);
		distributeAll();
		initializeAllHashMaps();
		
		buildAllGraphs();
		current = displayMainMenu();
		displayMenu(current);
//		System.out.println("Please enter the number of inputs: ");
//		ctr = Integer.parseInt(scanner.nextLine());
//		for(int x = 0; x < ctr; x++)
//		{
//			String[] input = scanner.nextLine().split(",");
//			comparetoHashMap(manila_hashmap, input, manila_graph);
//		}
		
		scanner.close();
	}
	
	static void scanCSV(String filename) throws IOException
	{
		String path ="/Users/Jericho/Documents/Subjects/DASALGO/" + filename,
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
		}
		br.close();
		return;
	}
	
	static void distributeAll()
	{
		int ctr;
		for(ctr = 0; ctr < all_mails.size(); ctr++)
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
		
		return;
	}

	static void distribute()
	{
		return;
	}
	
	static void initializeAllHashMaps()
	{
		createHashMap(all_manila_mails, manila_hashmap);
//		System.out.println(manila_hashmap.toString());
		createHashMap(all_quezon_mails, quezon_hashmap);
//		System.out.println(quezon_hashmap.toString());
		createHashMap(all_makati_mails, makati_hashmap);
//		System.out.println(makati_hashmap.toString());
		createHashMap(all_pasay_mails, pasay_hashmap);
//		System.out.println(pasay_hashmap.toString());
		
		return;
	}
	
	static void createHashMap(ArrayList<Mail> parameter_array, HashMap<String, Integer> hashmap)
	{
		int ctr, value = 0;
		for(ctr = 0; ctr < parameter_array.size() - 1; ctr++)
		{
			if(!hashmap.containsKey(parameter_array.get(ctr).source))
			{
				hashmap.put(parameter_array.get(ctr).source, value);
				value++;
			}
			else if(!hashmap.containsKey(parameter_array.get(ctr).destination))
			{
				hashmap.put(parameter_array.get(ctr).destination, value);
				value++;
			}
		}
		
		for(ctr = 0; ctr < parameter_array.size() - 1; ctr++)
		{
			if(!hashmap.containsKey(parameter_array.get(ctr).destination))
			{
				hashmap.put(parameter_array.get(ctr).destination, value);
				value++;
			}
			else if(!hashmap.containsKey(parameter_array.get(ctr).source))
			{
				hashmap.put(parameter_array.get(ctr).source, value);
				value++;
			}
		}
		
	}
	
	static void initializeGraph(Graph parameter_graph, ArrayList<Mail> mail, HashMap<String, Integer> hashmap)
	{
		parameter_graph.resize(hashmap.size());
		parameter_graph.initialize();
		
		for(int ctr = 0; ctr < mail.size(); ctr++)
		{
			parameter_graph.addEdge(hashmap.get(mail.get(ctr).source), hashmap.get(mail.get(ctr).destination), mail.get(ctr).distance);
		}
		
		return;
	}
	
	static void buildAllGraphs()
	{
		initializeGraph(manila_graph, all_manila_mails, manila_hashmap);
		applyFloydWarshall(manila_graph);
		initializeGraph(quezon_graph, all_quezon_mails, quezon_hashmap);
		applyFloydWarshall(quezon_graph);
		initializeGraph(makati_graph, all_makati_mails, makati_hashmap);
		applyFloydWarshall(makati_graph);
		initializeGraph(pasay_graph, all_pasay_mails, pasay_hashmap);
		applyFloydWarshall(pasay_graph);
		
		return;
	}
	
	static void applyFloydWarshall(Graph parameter_graph)
	{
		for (int k = 0; k < parameter_graph.size; k++)
            for (int i = 0; i < parameter_graph.size; i++)
                for (int j = 0; j < parameter_graph.size; j++)
                    if (parameter_graph.adjMatrix[i][k] + parameter_graph.adjMatrix[k][j] < parameter_graph.adjMatrix[i][j])
                        parameter_graph.adjMatrix[i][j] = parameter_graph.adjMatrix[i][k] + parameter_graph.adjMatrix[k][j];
	}
	
	static void comparetoHashMap(String input, int number)
	{
		switch(number) 
		{
			case 1:
				if(manila_hashmap.containsKey(input))
				{
					mails_for_manila.add(input);
//					input_mails.remove(input);
				}
				break;
			case 2:
				if(quezon_hashmap.containsKey(input))
				{
					mails_for_quezon.add(input);
//					input_mails.remove(input);
				}
				break;
			case 3:
				if(makati_hashmap.containsKey(input))
				{
					mails_for_makati.add(input);
//					input_mails.remove(input);
				}
				break;
			case 4:
				if(pasay_hashmap.containsKey(input))
				{
					mails_for_pasay.add(input);
//					input_mails.remove(input);
				}
				break;
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
	
	public static void map(int number)
	{
		HashMap<String, Integer> local_hashmap = new HashMap<String, Integer>();
		Graph local_graph = new Graph(number);
		
		switch(number)
		{
			case 1:
				local_hashmap = manila_hashmap;
				local_graph = manila_graph;
				break;
			case 2:
				local_hashmap = quezon_hashmap;
				local_graph = quezon_graph;
				break;
			case 3:
				local_hashmap = makati_hashmap;
				local_graph = makati_graph;
				break;
			case 4:
				local_hashmap = pasay_hashmap;
				local_graph = pasay_graph;
				break;
		}
		
		ArrayList<Integer> vertices = new ArrayList<Integer>();
		
		for(int x = input_mails.size() - 1; x >= 0; x--)
		{
			if(local_hashmap.containsKey(input_mails.get(x)))
			{
				vertices.add(local_hashmap.get(input_mails.get(x)));
				input_mails.remove(input_mails.get(x));
			}
			
		}
		
		route(local_hashmap, local_graph, vertices);
		
		System.out.println(input_mails.toString());
		
		if(!input_mails.isEmpty())
			displayMenu(displayMainMenu());
		
		return;
	}
	
	public static void route(HashMap<String, Integer> hashmap, Graph graph, ArrayList<Integer> vertices)
	{
		int origin = 0, minVertex = vertices.get(0);
		String location = "";
		Set listoflocations = hashmap.entrySet();
		for(int ctr = vertices.size()-1; ctr >= 0; ctr--)
		{
			for(int x: vertices)
			{
				if(graph.adjMatrix[origin][x] < graph.adjMatrix[origin][minVertex])
					minVertex = x;
			}
			System.out.println(graph.adjMatrix[origin][minVertex]);
			for(String y: hashmap.keySet())
			{
				if(hashmap.get(y) == minVertex)
					location = y;
			}
			System.out.println("We will now go to " + location);
			
			vertices.remove(vertices.indexOf(minVertex));
			origin = minVertex;
			if(vertices.isEmpty())
				break;
			minVertex = vertices.get(0);
		}
		
		
		
	}
	
	public static void displayMenu(int number)
	{
		System.out.println("We are going to " + postoffices.get(number) + " Post Office to get the mail to be delivered.");
		System.out.println("How many mails are there?");
		
		Scanner scanner = new Scanner(System.in);
		int testcases = Integer.parseInt(scanner.nextLine());
		
		for(int ctr = 1; ctr <= testcases; ctr++)
		{
			System.out.println("Destination of Mail " + ctr + " :");
			String input = scanner.nextLine();
			input_mails.add(input);
			comparetoHashMap(input, number);
		}
		map(number);
	}
	
}

