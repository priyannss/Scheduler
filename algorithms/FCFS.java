package Projects.Schedular.algorithms;

import Projects.Schedular.models.Process;

import java.util.List;

// First-Come First Served

public class FCFS {
    public void schedule(List<Process> processes) {
        processes.sort((p1, p2) -> (Integer.compare(p1.arrivalTime, p2.arrivalTime)));
        int currentTime = 0;

        for (Process p : processes) {
            if (currentTime < p.arrivalTime) {
                currentTime = p.arrivalTime;
            }
            p.completionTime = currentTime + p.burstTime;
            p.turnAroundTime = p.completionTime - p.arrivalTime;
            p.waitTime = p.turnAroundTime - p.burstTime;
            currentTime = p.completionTime;
        }
    }
}
