import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
		int ctr;
		String filename;
		HashMap<String, Integer> manila_hashmap = new HashMap<String, Integer>(),
							 quezon_hashmap = new HashMap<String, Integer>(),
							 makati_hashmap = new HashMap<String, Integer>(),
							 pasay_hashmap = new HashMap<String, Integer>();
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Please enter the filename of the map:");
		filename = scanner.nextLine();
		
		scanCSV(filename);
		distributeAll();
		initializeAllHashMaps(manila_hashmap, quezon_hashmap, makati_hashmap, pasay_hashmap);
				
		Graph manila_graph = new Graph(manila_hashmap.size()),
			  quezon_graph = new Graph(quezon_hashmap.size()),
			  makati_graph = new Graph(makati_hashmap.size()),
			  pasay_graph = new Graph(pasay_hashmap.size());
		
		
		buildAllGraphs(manila_graph, quezon_graph, makati_graph, pasay_graph, manila_hashmap, quezon_hashmap, makati_hashmap, pasay_hashmap);
//		System.out.println(displayMainMenu());
		System.out.println("Please enter the number of inputs: ");
		ctr = Integer.parseInt(scanner.nextLine());
		for(int x = 0; x < ctr; x++)
		{
			String[] input = scanner.nextLine().split(",");
			comparetoHashMap(manila_hashmap, input, manila_graph);
		}
		
		scanner.close();
	}
	
	static void scanCSV(String filename) throws IOException
	{
		String path = filename,
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
	
	static void initializeAllHashMaps(HashMap<String, Integer> manila_hashmap, HashMap<String, Integer> quezon_hashmap, HashMap<String, Integer> makati_hashmap, HashMap<String, Integer> pasay_hashmap)
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
		parameter_graph.initialize();
		
		for(int ctr = 0; ctr < mail.size(); ctr++)
		{
			parameter_graph.addEdge(hashmap.get(mail.get(ctr).source), hashmap.get(mail.get(ctr).destination), mail.get(ctr).distance);
		}
		return;
	}
	
	static void buildAllGraphs(Graph manila_graph, Graph quezon_graph, Graph makati_graph, Graph pasay_graph, HashMap<String, Integer> manila_hashmap, HashMap<String, Integer> quezon_hashmap, HashMap<String, Integer> makati_hashmap, HashMap<String, Integer> pasay_hashmap)
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
	
	static void comparetoHashMap(HashMap<String, Integer> hashmap, String[] input, Graph graph)
	{
		if(hashmap.containsKey(input[1]) && hashmap.containsKey(input[2]))
			System.out.println(graph.adjMatrix[hashmap.get(input[1])][hashmap.get(input[2])]);
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
	
}

