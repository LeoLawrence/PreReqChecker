package prereqchecker;
import java.util.*;

/**
 * Steps to implement this class main method:
 * 
 * Step 1:
 * AdjListInputFile name is passed through the command line as args[0]
 * Read from AdjListInputFile with the format:
 * 1. a (int): number of courses in the graph
 * 2. a lines, each with 1 course ID
 * 3. b (int): number of edges in the graph
 * 4. b lines, each with a source ID
 * 
 * Step 2:
 * AdjListOutputFile name is passed through the command line as args[1]
 * Output to AdjListOutputFile with the format:
 * 1. c lines, each starting with a different course ID, then 
 *    listing all of that course's prerequisites (space separated)
 */
public class AdjList {
    public static void main(String[] args) {
        if ( args.length < 2 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.AdjList <adjacency list INput file> <adjacency list OUTput file>");
            return;
        }
        else {
            createOutput(args[1], createAdjList(args[0]));
        }
    }

    public static HashMap<String, LinkedList<String>> createAdjList(String inputFile) {
        StdIn.setFile(inputFile);
        String course = "";
        int counter = 0;
        int numCourses = StdIn.readInt();
        HashMap<String, LinkedList<String>> adjListMap = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            course = StdIn.readString();
            LinkedList<String> valuePtr = new LinkedList<String>();
            valuePtr.add(course);
            if (!valuePtr.equals(" ") || !valuePtr.equals("")) {
                adjListMap.put(course, valuePtr);  
            }              
        }
        int coursePreqs = StdIn.readInt();
        while (!StdIn.isEmpty()) {
            String line = StdIn.readLine();
            String[] preReqs = line.split(" ", 0);
            String hashIndex = preReqs[0];
            if (adjListMap.get(hashIndex) == null) {
                LinkedList<String> ptr = new LinkedList<String>();
                for (int j = 1; j < preReqs.length; j++) {
                    ptr.add(preReqs[j]);
                }
                if (!ptr.equals(" ") || !ptr.equals("")) {
                    adjListMap.put(hashIndex, ptr);
                }
            }
            else {
                LinkedList<String> ptr = adjListMap.get(hashIndex);
                for (int j = 1; j < preReqs.length; j++) {
                    ptr.add(preReqs[j]);
                }
                if (!ptr.equals(" ") || !ptr.equals("")) {
                    adjListMap.put(hashIndex, ptr);
                }
            }
            
        }
        return adjListMap;
    }
    
    public static void createOutput(String outputFile, HashMap<String, LinkedList<String>> mapName) {
        StdOut.setFile(outputFile);
        String outputString = "";
        for (String ptr: mapName.keySet()) {
            LinkedList<String> head = mapName.get(ptr);
            while (head.size() != 0) {
                outputString += (head.remove() + " ");
                if (head.size() == 0) {
                    outputString += "\n";
                }
            }
        }
        StdOut.print(outputString);
    }

    public static HashMap<String, LinkedList<String>> findAllPrereqs(HashMap<String, LinkedList<String>> adjList) {
        for (String e : adjList.keySet()) {
            LinkedList<String> ll = adjList.get(e);
            for (int i = 0; i < ll.size(); i++) {
                String course = ll.get(i);
                if (adjList.get(course) != null) {
                    LinkedList<String> llPtr = adjList.get(course);
                    for (int j = 0; j < llPtr.size(); j++) {
                        if (!ll.contains(llPtr.get(j))) {
                            ll.add(llPtr.get(j));
                        }
                    }
                }
            }
        }
        return adjList;
    }

}
