# PreReqChecker
Naive approach to making a PreReqChecker. Very little, if any, example code was used aside from the input files (\*.in)

To run the files:
- Download and unzip the folder (or git clone).
- Navigate to the folder through the terminal.
- Compile with javac -d bin src/prereqchecker/\*.java
- Test each method below individually (no Driver file).

**AdjList:** 
- Run using java -cp bin prereqchecker.AdjList adjlist.in adjlist.out
- Creates an adjacency list of prerequisite courses.

**Eligible:**
- Run using java -cp bin prereqchecker.Eligible adjlist.in eligible.in eligible.out
- Using the courses listed in the eligible.in file, creates a list of possible courses a student can take. Assumes the student has already taken all prerequisites needed for the courses listed in eligible.in

**NeedToTake:**
- Run using java -cp bin prereqchecker.NeedToTake adjlist.in needtotake.in needtotake.out
- The needtotake.in file lists a course that a student wants to take, followed by the courses they have already taken. The method will write to the needtotake.out file with the appropriate courses the student must take in order to take their chosen course.

**ValidPrereq:**
- Run using java -cp bin prereqchecker.ValidPrereq adjlist.in validprereq.in validprereq.out
- The validpreqreq.in file lists a course that a student wants to take, followed by a course. If the course listed is a prerequisite for the course the student wants to take, the output file will say "YES." If not, the output file will say "NO."

**SchedulePlan:**
- Run using java -cp bin prereqchecker.SchedulePlan adjlist.in scheduleplan.in scheduleplan.out
- The scheduleplan.in file lists a course a student wants to take, followed by the courses the student has already taken. Assumes the student has already taken all prerequisite courses for the course they have already taken. The method populates the scheduleplan.out file with the number of courses the student can take per semester to minimize the amount of time before they are able to take their chosen course.
