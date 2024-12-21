package Projects.Schedular.main;

import Projects.Schedular.algorithms.*;
import Projects.Schedular.utils.SchedularUtils;
import Projects.Schedular.visuals.GanttChart;
import Projects.Schedular.models.Process;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SchedularMain {

    public static void main(String[] args) {
        List<Process> processes = new ArrayList<>();

        String fileName = "src/Projects/Schedular/utils/SchedularInput.txt";

        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            br.readLine();

            while((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int processID = Integer.parseInt(parts[0].trim());
                int arrivalTime = Integer.parseInt(parts[1].trim());
                int burstTime = Integer.parseInt(parts[2].trim());
                int priority = Integer.parseInt(parts[3].trim());

                processes.add(new Process(processID, arrivalTime, burstTime, priority));
            }
        } catch (IOException e) {
            System.out.println("Error reading the file : " + e.getMessage());
        }

        boolean keepRunning = true;
        while(keepRunning) {
            for(Process process: processes) {
                process.reset();
            }

            System.out.println("\n++++++++++ Algorithms ++++++++++");
            System.out.println("1. First-Come First Served (FCFS)");
            System.out.println("2. Shortest Job First (SJF)");
            System.out.println("3. Priority (Non Preemptive)");
            System.out.println("4. Round Robin");
            System.out.println("5. Shortest Remaining Time First (SRTF) or (preemptive SJF)");
            System.out.println("6. Priority (Preemptive)");
            System.out.println("Enter a number between 1-6 to choose an algorithm, or any other number to exit.");

            Scanner scanner = new Scanner(System.in);
            System.out.print("\nEnter your choice : ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1-> {
                    System.out.println("\n++++++++++ FCFS Scheduling ++++++++++");
                    FCFS fifo = new FCFS();
                    fifo.schedule(processes);
                    SchedularUtils.displayProcesses(processes);
                    SchedularUtils.calculateAverageTimes(processes);
                    GanttChart.displayGanttChart(processes);
                }

                case 2-> {
                    System.out.println("\n++++++++++ SJF Scheduling ++++++++++");
                    SJF sjf = new SJF();
                    sjf.schedule(processes);
                    SchedularUtils.displayProcesses(processes);
                    SchedularUtils.calculateAverageTimes(processes);
                    GanttChart.displayGanttChart(processes);
                }

                case 3-> {
                    System.out.println("\n++++++++++ Priority Scheduling ++++++++++");
                    Priority priority = new Priority();
                    priority.schedule(processes);
                    SchedularUtils.displayProcesses(processes);
                    SchedularUtils.calculateAverageTimes(processes);
                    GanttChart.displayGanttChart(processes);
                }

                case 4-> {
                    System.out.print("Input time quantum : ");
                    int quantum = scanner.nextInt();
                    System.out.println("\n++++++++++ Round Robin Scheduling ++++++++++");
                    RoundRobin rr = new RoundRobin();
                    List<GanttChart.ExecutionInterval> executionTimeline = rr.schedule(processes, quantum);
                    SchedularUtils.displayProcesses(processes);
                    SchedularUtils.calculateAverageTimes(processes);
                    GanttChart.displayGanttChart(processes);
                    GanttChart.displayGanttChartPreemptive(executionTimeline);
                }

                case 5-> {
                    System.out.println("\n++++++++++ SRTF Scheduling ++++++++++");
                    SRTF srtf = new SRTF();
                    List<GanttChart.ExecutionInterval> executionTimeline = srtf.schedule(processes);
                    SchedularUtils.displayProcesses(processes);
                    SchedularUtils.calculateAverageTimes(processes);
                    GanttChart.displayGanttChart(processes);
                    GanttChart.displayGanttChartPreemptive(executionTimeline);
                }

                case 6-> {
                    System.out.println("\n++++++++++ Priority Scheduling (preemptive) ++++++++++");
                    PriorityPreemptive priorityPreemptive = new PriorityPreemptive();
                    List<GanttChart.ExecutionInterval> executionTimeline = priorityPreemptive.schedule(processes);
                    SchedularUtils.displayProcesses(processes);
                    SchedularUtils.calculateAverageTimes(processes);
                    GanttChart.displayGanttChart(processes);
                    GanttChart.displayGanttChartPreemptive(executionTimeline);
                }

                default -> {
                    keepRunning = false;
                    System.out.println("Exiting...");
                }
            }
        }
    }
}
