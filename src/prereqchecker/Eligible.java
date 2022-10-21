package prereqchecker;

import java.util.*;

/**
 * 
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
 * EligibleInputFile name is passed through the command line as args[1]
 * Read from EligibleInputFile with the format:
 * 1. c (int): Number of courses
 * 2. c lines, each with 1 course ID
 * 
 * Step 3:
 * EligibleOutputFile name is passed through the command line as args[2]
 * Output to EligibleOutputFile with the format:
 * 1. Some number of lines, each with one course ID
 */
public class Eligible {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.Eligible <adjacency list INput file> <eligible INput file> <eligible OUTput file>");
            return;
        }
        else {
            removeTakenCourses(args[0], args[1], args[2]);
        }

	
    }

    public static void removeTakenCourses(String inputFile, String takenCourses, String outputFile) {
        HashMap<String, LinkedList<String>> adjList = AdjList.findAllPrereqs(AdjList.createAdjList(inputFile));
        ArrayList<String> temp = new ArrayList<String>();
        ArrayList<String> taken = new ArrayList<String>();
        StdIn.setFile(takenCourses);
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

        ArrayList<String> canTake = new ArrayList<String>();
        for (String e : adjList.keySet()) {
            boolean possible = true;
            LinkedList<String> values = adjList.get(e);
            for (String f : values) {
                if (!taken.contains(f) && !f.equals(e)) {
                    possible = false;
                }
            }
            if (possible && !taken.contains(e) && !canTake.contains(e)) {
                canTake.add(e);
            }
        }

        StdOut.setFile(outputFile);
        int printCounter = 0;
        for (String e : canTake) {
            if (e.trim().length() > 0) {
                StdOut.print(e);
                printCounter++;
                if (printCounter < canTake.size()-1) {
                    StdOut.print("\n");
                }
            }
        }
    }

    
}

/* 
NOTE: Collections.sort() works for linked lists
Steps:
- Convert to expanded AdjList
- Store all the courses + prereqs in an arraylist called takenCourses
- make arraylist called canTake
- for every key in adjList
    - boolean possible = true;
    - ll = linked list value
    - for every value of the linked list
        - if ll.get(i) is NOT in takenCourses
            - possible = false;
    - if possible:
        - add to canTake
- print canTake


*/