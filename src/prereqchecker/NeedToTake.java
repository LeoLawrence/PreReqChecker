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
 * NeedToTakeInputFile name is passed through the command line as args[1]
 * Read from NeedToTakeInputFile with the format:
 * 1. One line, containing a course ID
 * 2. c (int): Number of courses
 * 3. c lines, each with one course ID
 * 
 * Step 3:
 * NeedToTakeOutputFile name is passed through the command line as args[2]
 * Output to NeedToTakeOutputFile with the format:
 * 1. Some number of lines, each with one course ID
 */
public class NeedToTake {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java NeedToTake <adjacency list INput file> <need to take INput file> <need to take OUTput file>");
            return;
        }
        else {
            removeClasses(args[0], args[1], args[2]);
        }

    }

    public static void removeClasses(String fileName, String NeedToTakeInputFile, String outputFile) {
        HashMap<String, LinkedList<String>> adjList = AdjList.findAllPrereqs(AdjList.createAdjList(fileName));
        StdIn.setFile(NeedToTakeInputFile);
        //ArrayList<String> needToTake = new ArrayList<String>();
        Queue<String> needToTake = new LinkedList<String>();
        ArrayList<String> temp = new ArrayList<String>();
        ArrayList<String> taken = new ArrayList<String>();
        String target = StdIn.readString();
        int numCourses = StdIn.readInt();
        while (!StdIn.isEmpty()) {
            temp.add(StdIn.readString());
        }
        for (String e : temp) {
            if (adjList.get(e) != null) {
                LinkedList<String> ptr = adjList.get(e);
                for (String f : ptr) {
                    if (!taken.contains(f)) {
                        taken.add(f);
                    }
                }
            }
        }
        
        for (String e: temp) {
            if (!taken.contains(e)) {
                taken.add(e);
            }
        }
        if (adjList.get(target) != null) {
            LinkedList<String> ptr2 = adjList.get(target);
            for (String e : ptr2) {
                needToTake.add(e);
            }
        }

        for (String e : taken) {
            if (needToTake.contains(e)) {
                needToTake.remove(e);
            }
        }

        if (needToTake.contains(target)) {
            needToTake.remove(target);
        }

        StdOut.setFile(outputFile);
        String outputVal = "";
        while(!needToTake.isEmpty()) {
            outputVal += needToTake.remove();
            if (!needToTake.isEmpty()) {
                outputVal += "\n";
            }
        }
        StdOut.print(outputVal);
        

    }

}


/*

Steps:
- Convert to expanded AdjList
- Find all prereqs of target course and store in ArrayList called needToTake
- Find all prereqs taken given the courses taken and add them to ArrayList called alrTaken
- Add the prereqs to the alrTaken arrayList
- For every value in alrTaken, check if it is contained within needToTake
    - If so, remove the value in needToTake
- Print all of the needToTake ArrayList values

*/