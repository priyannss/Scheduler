package Projects.Schedular.algorithms;

import Projects.Schedular.models.Process;
import Projects.Schedular.visuals.GanttChart;

import java.util.*;

public class PriorityPreemptive {
    public List<GanttChart.ExecutionInterval> schedule(List<Process> processes) {
        List<Process> tempProcesses = new ArrayList<>(processes);

        tempProcesses.sort(Comparator.comparingInt(p -> p.arrivalTime));

        PriorityQueue<Process> queue = new PriorityQueue<>((p1, p2) -> {
            int comparison = Integer.compare(p1.priority, p2.priority);
            return (comparison == 0) ? Integer.compare(p1.arrivalTime, p2.arrivalTime) : comparison;
        });

        int currentTime = 0;

        List<GanttChart.ExecutionInterval> executionTimeline = new ArrayList<>();

        while(!tempProcesses.isEmpty() || !queue.isEmpty()) {
            Iterator<Process> iterator = tempProcesses.iterator();
            while(iterator.hasNext()) {
                Process p = iterator.next();
                if(p.arrivalTime <= currentTime) {
                    queue.add(p);
                    iterator.remove();
                }
            }

            if(!queue.isEmpty()) {
                Process currProcess = queue.poll();

                executionTimeline.add(new GanttChart.ExecutionInterval(currProcess.pid, currentTime, currentTime + 1));
                currProcess.remainingTime--;
                currentTime++;

                if(currProcess.remainingTime == 0) {
                    currProcess.completionTime = currentTime;
                    currProcess.turnAroundTime = currProcess.completionTime - currProcess.arrivalTime;
                    currProcess.waitTime = currProcess.turnAroundTime - currProcess.burstTime;
                } else {
                    queue.add(currProcess);
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
