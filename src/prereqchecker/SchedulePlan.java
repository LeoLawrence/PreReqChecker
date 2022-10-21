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
 * SchedulePlanInputFile name is passed through the command line as args[1]
 * Read from SchedulePlanInputFile with the format:
 * 1. One line containing a course ID
 * 2. c (int): number of courses
 * 3. c lines, each with one course ID
 * 
 * Step 3:
 * SchedulePlanOutputFile name is passed through the command line as args[2]
 * Output to SchedulePlanOutputFile with the format:
 * 1. One line containing an int c, the number of semesters required to take the course
 * 2. c lines, each with space separated course ID's
 */
public class SchedulePlan {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.SchedulePlan <adjacency list INput file> <schedule plan INput file> <schedule plan OUTput file>");
            return;
        }
        else {
            planSchedule(args[0], args[1], args[2]);
        }

    }

    public static void planSchedule(String inputFile, String schedulePlanFile, String outputFile) {
        HashMap<String, LinkedList<String>> adjList = AdjList.findAllPrereqs(AdjList.createAdjList(inputFile));
        ArrayList<String> temp = new ArrayList<String>();
        ArrayList<String> taken = new ArrayList<String>();
        StdIn.setFile(schedulePlanFile);
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
        LinkedList<String> prereqs = new LinkedList<String>();
        if (adjList.get(target) != null) {
            prereqs = adjList.get(target);
        }
        for (String e : taken) {
            if (prereqs.contains(e)) {
                prereqs.remove(e);
            }
        }
        if (prereqs.contains(target)) {
            prereqs.remove(target);
        }
        int semesters = 0;

        // at this present moment, prereqs is all the courses that need to be taken
        String output = "";
        Queue<String> q = new LinkedList<String>();
        while (prereqs.size() > 0) {
            for (String e : prereqs) {
                boolean possible = true;
                LinkedList<String> values = adjList.get(e);
                for (String f : values) {
                    if (!taken.contains(f) && !f.equals(e)) {
                        possible = false;
                    }
                }
                if (possible && !taken.contains(e) && !q.contains(e)) {
                    q.add(e);
                }
            }
            while (!q.isEmpty()) {
                String ptr = q.remove();
                prereqs.remove(ptr);
                taken.add(ptr);
                output += ptr + " ";
                if (q.isEmpty() && prereqs.size() > 0) {
                    output += "\n";
                }
            }
            semesters++;
        }

        StdOut.setFile(outputFile);
        StdOut.print(semesters + "\n" + output);
    }
}

/* 
Steps:
- Convert to expanded AdjList
- Find all taken courses
- Find all prerequsites for the target course
- Out of all of the prerequisites, make two lists:
    - Ones that can be taken immediately
    - Ones that cannot be taken immediately
- Add the ones that can be taken immediately to the taken courses AND StdOut
- Repeat until all the prerequisites for the target course are fulfilled

*/
