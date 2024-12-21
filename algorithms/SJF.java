package Projects.Schedular.algorithms;

import Projects.Schedular.models.Process;

import java.util.List;
import java.util.PriorityQueue;

public class SJF {
    public void schedule(List<Process> processes) {
        processes.sort((p1, p2) -> Integer.compare(p1.arrivalTime, p2.arrivalTime));
        PriorityQueue<Process> pq = new PriorityQueue<>((p1, p2) -> Integer.compare(p1.burstTime, p2.burstTime));

        int currentTime = 0;
        int completed = 0;

        boolean[] visited = new boolean[processes.size()];

        while (completed < processes.size()) {
            for (int i = 0; i < processes.size(); i++) {
                Process p = processes.get(i);
                if (!visited[i] && p.arrivalTime <= currentTime) {
                    pq.add(p);
                    visited[i] = true;
                }
            }

            if (!pq.isEmpty()) {
                Process p = pq.poll();
                p.completionTime = currentTime + p.burstTime;
                p.turnAroundTime = p.completionTime - p.arrivalTime;
                p.waitTime = p.turnAroundTime - p.burstTime;
                currentTime = p.completionTime;
                completed++;
            } else {
                currentTime++;
            }
        }
    }
}
