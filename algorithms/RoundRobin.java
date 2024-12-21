package Projects.Schedular.algorithms;

import Projects.Schedular.models.Process;
import Projects.Schedular.visuals.GanttChart;

import java.util.*;

public class RoundRobin {
    public List<GanttChart.ExecutionInterval> schedule(List<Process> processes, int quantum) {
        List<Process> tempProcesses = new ArrayList<>(processes);

        tempProcesses.sort((p1, p2) -> {
            int comparison = Integer.compare(p1.arrivalTime, p2.arrivalTime);
            return (comparison == 0) ? Integer.compare(p1.pid, p2.pid) : comparison;
        });

        Queue<Process> queue = new LinkedList<>(); // process queue
        int currentTime = 0;

        List<GanttChart.ExecutionInterval> executionTimeline = new ArrayList<>(); // for storing timeline of process execution

        while(!tempProcesses.isEmpty() || !queue.isEmpty()) {
            while (!tempProcesses.isEmpty() && tempProcesses.get(0).arrivalTime <= currentTime) {
                queue.add(tempProcesses.remove(0));
            }

            if(!queue.isEmpty()) {
                Process currProcess = queue.poll();

                int timeSpent = Math.min(currProcess.remainingTime, quantum);
                currProcess.remainingTime -= timeSpent;
                executionTimeline.add(new GanttChart.ExecutionInterval(currProcess.pid, currentTime, currentTime + timeSpent));
                currentTime += timeSpent;

                while (!tempProcesses.isEmpty() && tempProcesses.get(0).arrivalTime <= currentTime) {
                    queue.add(tempProcesses.remove(0));
                }

                if(currProcess.remainingTime > 0) {
                    queue.add(currProcess);
                } else {
                    // Process is completed
                    currProcess.completionTime = currentTime;
                    currProcess.turnAroundTime = currProcess.completionTime - currProcess.arrivalTime;
                    currProcess.waitTime = currProcess.turnAroundTime - currProcess.burstTime;
                }
            } else {
                if(!tempProcesses.isEmpty()) {
                    currentTime = tempProcesses.get(0).arrivalTime;
                }
            }
        }

        return executionTimeline;
    }
}
