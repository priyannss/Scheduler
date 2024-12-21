package Projects.Schedular.models;

public class Process {
    public int pid;
    public int arrivalTime;
    public int burstTime;
    public int priority;
    public int remainingTime;
    public int completionTime;
    public int waitTime;
    public int turnAroundTime;

    public Process(int pid, int arrivalTime, int burstTime, int priority) {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
        this.remainingTime = burstTime;
    }

    public void reset() {
        this.remainingTime = this.burstTime;
        this.completionTime = 0;
        this.turnAroundTime = 0;
        this.waitTime = 0;
    }
}
