package Projects.Schedular.utils;

import Projects.Schedular.models.Process;

import java.util.List;

public class SchedularUtils {
    public static void calculateAverageTimes(List<Process> processes) {
        double totalWaitTime = 0;
        double totalTurnAroundTime = 0;

        for (Process p : processes) {
            totalWaitTime += p.waitTime;
            totalTurnAroundTime += p.turnAroundTime;
        }

        System.out.println("\nAverage Waiting Time: " + totalWaitTime / processes.size());
        System.out.println("Average Turnaround Time: " + totalTurnAroundTime / processes.size());
    }

    public static void displayProcesses(List<Process> processes) {
        System.out.println("PID\t\tArrival\t\tBurst\t\tCompletion\t\tTurnaround\t\tWaiting");
        for (Process p : processes) {
            System.out.printf("%d \t\t%d     \t\t%d   \t\t%d        \t\t%d        \t\t%d\n", p.pid, p.arrivalTime,
                    p.burstTime, p.completionTime, p.turnAroundTime, p.waitTime);
        }
    }
}
