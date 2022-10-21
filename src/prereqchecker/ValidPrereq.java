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
 * ValidPreReqInputFile name is passed through the command line as args[1]
 * Read from ValidPreReqInputFile with the format:
 * 1. 1 line containing the proposed advanced course
 * 2. 1 line containing the proposed prereq to the advanced course
 * 
 * Step 3:
 * ValidPreReqOutputFile name is passed through the command line as args[2]
 * Output to ValidPreReqOutputFile with the format:
 * 1. 1 line, containing either the word "YES" or "NO"
 */
public class ValidPrereq {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.ValidPrereq <adjacency list INput file> <valid prereq INput file> <valid prereq OUTput file>");
            return;
        }
        else {
            newPrereq(args[0], args[1], args[2]);
        }
    }

    public static void newPrereq(String inputFile, String newPrereqFile, String outputFile) {
        HashMap<String, LinkedList<String>> map = AdjList.createAdjList(inputFile);
        ArrayList<String> allCoursePrereqs = new ArrayList<String>();
        StdIn.setFile(newPrereqFile);
        String course = StdIn.readString();
        String prereq = StdIn.readString();
        String output = "YES";
        HashMap<String, LinkedList<String>> expandedList = AdjList.findAllPrereqs(map);
        LinkedList<String> coursePrereqs = new LinkedList<String>();
        if (expandedList.get(prereq) != null) {
            coursePrereqs = expandedList.get(prereq);
        }
        for (int i = 0; i < coursePrereqs.size(); i++) {
            String courseID = coursePrereqs.get(i);
            if (expandedList.get(prereq) != null) {
                LinkedList<String> ll = expandedList.get(prereq);
                if (ll.contains(course)) {
                    output = "NO";
                }
            }
        }
        StdOut.setFile(outputFile);
        StdOut.print(output);

    }
}


