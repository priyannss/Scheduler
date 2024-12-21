package Projects.Schedular.visuals;

import Projects.Schedular.models.Process;

import java.util.List;

public class GanttChart {
    public static void displayGanttChart(List<Process> processes) {
        System.out.println("\nGantt Chart:");

        processes.sort((p1, p2)-> (Integer.compare(p1.completionTime, p2.completionTime)));

        // Print the top border
        for (Process p : processes) {
            System.out.print("+");
            for (int i = 0; i < String.valueOf(p.completionTime).length() + 5; i++) {
                System.out.print("-");
            }
        }
        System.out.println("+");

        // Print the process IDs
        for (Process p : processes) {
            System.out.print("| P" + p.pid);
            for (int i = 0; i < String.valueOf(p.completionTime).length() + 2; i++) {
                System.out.print(" ");
            }
        }
        System.out.println("|");

        // Print the bottom border
        for (Process p : processes) {
            System.out.print("+");
            for (int i = 0; i < String.valueOf(p.completionTime).length() + 5; i++) {
                System.out.print("-");
            }
        }
        System.out.println("+");

        // Print the times
        System.out.print("0"); // Initial start time
        for (Process p : processes) {
            System.out.print("      " + p.completionTime);
        }
        System.out.println("\n");
    }

    public static class ExecutionInterval {
        public int pid;
        public int startTime;
        public int endTime;

        public ExecutionInterval(int pid, int startTime, int endTime) {
            this.pid = pid;
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }

    public static void displayGanttChartPreemptive(List<ExecutionInterval> executionTimeline) {
        System.out.println("Detailed Gantt Chart:");
        for (ExecutionInterval interval : executionTimeline) {
            System.out.printf("P%d [%d - %d] \n", interval.pid, interval.startTime, interval.endTime);
        }
        System.out.println();
    }
}
