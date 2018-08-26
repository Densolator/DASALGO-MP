
public class Graph {
	     float adjMatrix[][];
	     private int numVertices;
	     int size;
	     
	     public Graph(int numVertices) {
	         this.numVertices = numVertices;
	         adjMatrix = new float[numVertices][numVertices];
	         size = numVertices;
	   }
	     
	      public void addEdge(int i, int j, float weight) {
	                adjMatrix[i][j] = weight;
	    }
	 
	      public void removeEdge(int i, int j) {
	                adjMatrix[i][j] = Float.POSITIVE_INFINITY;
	    }
	 
	      public float isEdge(int i, int j) {
	                  return adjMatrix[i][j];
	    }
	      
	      public void initialize()
	      {
	    	  for(int i = 0; i < numVertices; i++)
	    	  {
	    		  for(int j = 0; j < numVertices; j++)
	    		  {
	    			  if(i == j)
	    				  adjMatrix[i][j] = 0;
	    			  else
	    				  adjMatrix[i][j] = Float.POSITIVE_INFINITY;
	    		  }
	    	  }
	      }

	    public String toString() {
	        StringBuilder s = new StringBuilder();
	        for (int i = 0; i < numVertices; i++) {
	            s.append(i + ": ");
	            for (float j : adjMatrix[i]) {
	                s.append((j) + " ");
	            }
	            s.append("\n");
	        }
	        return s.toString();
	    }


}


