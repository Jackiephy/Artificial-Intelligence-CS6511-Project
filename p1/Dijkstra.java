package p1;

import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Dijkstra {
	//Undirected Graph Sign
    public static final boolean UNDIRECTED_GRAPH = false;
    //Directed Graph Sign
    public static final boolean DIRECTED_GRAPH = true;
    //Adjacency Matrix Sign
    public static final boolean ADJACENCY_MATRIX = true;
    //Adjacency List Sign
    public static final boolean ADJACENCY_LIST = false;
    
    public static final int MAX_VALUE = Integer.MAX_VALUE;
    private boolean graphType;
    private boolean method;
    private int vertexSize;
    private int matrixMaxVertex;
    //a one-dimensional array that stores all vertex information
    private Object[] vertexesArray;
    //A two-dimensional array that stores the relationships between vertices in a graph
    //and the relationship between edges
    private int[][] edgesMatrix;
 
    //Record if the i-th node has been visited
    private boolean[] visited;
    
    public Dijkstra(boolean graphType, boolean method, int size) {
        this.graphType = graphType;
        this.method = method;
        this.vertexSize = 0;
        this.matrixMaxVertex = size;
 
        if (this.method) {
            visited = new boolean[matrixMaxVertex];
            vertexesArray = new Object[matrixMaxVertex];
            edgesMatrix = new int[matrixMaxVertex][matrixMaxVertex];
            //Initialize the array
            //the value of no edge association between the vertices is the maximum value of the Integer type.
            for (int row = 0; row < edgesMatrix.length; row++) {
                for (int column = 0; column < edgesMatrix.length; column++) {
                    edgesMatrix[row][column] = MAX_VALUE;
                }
            }
 
        }
    }
 
    /********************Dijkstra Algorithm——Shortest Path****************************/
    //Calculate the shortest distance from one vertex to the other
    public void Dijkstra1(Object obj) throws Exception {
        Dijkstra1(getVertexIndex(obj));
    }
    public void Dijkstra1(int v0, int v1) {
        int[] dist = new int[matrixMaxVertex];
        int[] prev = new int[matrixMaxVertex];
        //Initialize visited, dist an d path
        for (int i = 0; i < vertexSize; i++) {
            //Firstly assume that the shortest path is the shortest
            dist[i] = edgesMatrix[v0][i];
            visited[i] = false;
            //Finally through the direct point in case is the starting point
            if (i != v0 && dist[i] < MAX_VALUE)
                prev[i] = v0;
            //no directed path
            else
                prev[i] = -1; 
        }
        //Initially the source point v0∈visited set
        //indicating that the shortest path from v0 to v0 has been found
        visited[v0] = true;
        // Let's assume that we will transfer to the rest of the points
        // via a point transfer which will be closer
        // Assume again that it will be closer by two points 
        // Again and again until all the possible point transfer is listed
        int minDist;
        int v = 0;
        for (int i = 1; i < vertexSize; i++) {
            //Pick a distance recently via the point
        	//subscript load v
            minDist = MAX_VALUE;
 
            for (int j = 0; j < vertexSize; j++) {
                if ((!visited[j]) && dist[j] < minDist) {
                    v = j; 
                    minDist = dist[j];
                }
            }
            visited[v] = true;
            //The vertex v is merged into S
            //and the shortest path from v0 to the v vertex is min. 
            //It is assumed that from v0 to v
            //and then v goes directly to the remaining points
            //updating the current last pass point and distance
            for (int j = 0; j < vertexSize; j++) {
                if ((!visited[j]) && edgesMatrix[v][j] < MAX_VALUE) {
 
                    if (minDist + edgesMatrix[v][j] <= dist[j]) {
                        //If the shortest path to the j point via a v point is shorter
                    	//It will be updated.
                        dist[j] = minDist + edgesMatrix[v][j];
                        prev[j] = v;
                    }
 
                }
            }
 
        }
        int i = v1;
        System.out.println("The shortest path from " + vertexesArray[v0] + " to " + vertexesArray[i] + " is: " + dist[i]);
    }
    //Get the corresponding index of the vertex value in the array
    private int getVertexIndex(Object obj) throws Exception {
        int index = -1;
        for (int i = 0; i < vertexSize; i++) {
            if (vertexesArray[i].equals(obj)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new Exception("Error!");
        }
 
        return index;
    }
    public void Dijkstra2(int v0) {
        // LinkedList implements Queue interface FIFO
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < vertexSize; i++) {
            visited[i] = false;
        }
 
        //This loop is to ensure that each vertex is traversed to
        for (int i = 0; i < vertexSize; i++) {
            if (!visited[i]) {
                queue.add(i);
                visited[i] = true;
 
                while (!queue.isEmpty()) {
                    int row = queue.remove();
                    System.out.print(vertexesArray[row] + "-->");
 
                    for (int k = getMin(row); k >= 0; k = getMin(row)) {
                        if (!visited[k]) {
                            queue.add(k);
                            visited[k] = true;
                        }
                    }
 
                }
            }
        }
    }
 
    private int getMin(int row) {
        int minDist = MAX_VALUE;
        int index = 0;
        for (int j = 0; j < vertexSize; j++) {
            if ((!visited[j]) && edgesMatrix[row][j] < minDist) {
                minDist = edgesMatrix[row][j];
                index = j;
            }
        }
        if (index == 0) {
            return -1;
        }
        return index;
    }
 
    public boolean addVertex(Object val) {
        assert (val != null);
        vertexesArray[vertexSize] = val;
        vertexSize++;
        return true;
    }
 
    public boolean addEdge(int vnum1, int vnum2, int weight) {
        assert (vnum1 >= 0 && vnum2 >= 0 && vnum1 != vnum2 && weight >= 0);
 
        //Directed graph
        if (graphType) {
            edgesMatrix[vnum1][vnum2] = weight;
 
        } else {
            edgesMatrix[vnum1][vnum2] = weight;
            edgesMatrix[vnum2][vnum1] = weight;
        }
 
        return true;
    }
    
    public static void main(String[] args) throws Exception {
        Dijkstra graph = new Dijkstra(Dijkstra.UNDIRECTED_GRAPH, Dijkstra.ADJACENCY_MATRIX, 200);
        int[] vnum = new int[3];
        
        for(int i = 0; i < 200; i++) {
        	String s = String.valueOf(i);
        	graph.addVertex(s);
        }
        
        File file = new File("D://document/Projects/JAVA/workplace/Arora_AI/src/p1/test.txt");
		FileReader fileReader = new FileReader(file);
		LineNumberReader reader = new LineNumberReader(fileReader);
		int number = 201;
		String txt = "";
		int lines = 0;
		while ((txt != null)&&(number != 2189)) {
			lines++;
			if (lines == number) {
				number++;
				txt = reader.readLine();
				String arr[] = txt.split(",");
				for(int i = 0; i < 3; i++) {
					vnum[i] = Integer.parseInt(arr[i]);
				}
				graph.addEdge(vnum[0], vnum[1], vnum[2]);
			}
		}
		reader.close();
		fileReader.close();
		
		Scanner in = new Scanner(System.in);
		System.out.println("Input Start Point: ");
		int v0 = in.nextInt();
		System.out.println("Input End Point: ");
		int v1 = in.nextInt();
        graph.Dijkstra1(v0, v1);
        graph.Dijkstra2(0);
        System.out.println();
    }
 
}