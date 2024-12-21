# Scheduler

Scheduler is a Java-based application that implements various CPU scheduling algorithms. The project includes First-Come, First-Served (FCFS), Shortest Job First (SJF), Priority (Non-Preemptive), Round Robin, Shortest Remaining Time First (SRTF), and Priority (Preemptive) scheduling algorithms. The application reads process data from a file, schedules the processes according to the selected algorithm, and displays the results.

## Getting Started

### Prerequisites

- Java Development Kit (JDK)
- An IDE or text editor (Recommended - IntelliJ IDEA or VS Code)

### Running the Application

1. Ensure that the input file `SchedularInput.txt` is located in the `src/Projects/Schedular/utils/` or in correct directory. The file should have the following format:
    ```
    ProcessID, ArrivalTime, BurstTime, Priority
    1,          5,          5,          0
    2,          4,          6,          0
    3,          3,          7,          0
    4,          1,          9,          0
    5,          2,          2,          0
    6,          6,          3,          0
    ```

2. Run the `SchedulerMain` class from your IDE.

3. Follow the on-screen instructions to select a scheduling algorithm and view the results.

### Scheduling Algorithms

The following scheduling algorithms are implemented in this project:

1. **First-Come, First-Served (FCFS) or (FIFO)**
2. **Shortest Job First (SJF)**
3. **Priority (Non-Preemptive)**
4. **Round Robin**
5. **Shortest Remaining Time First (SRTF) or (Preemptive SJF)**
6. **Priority (Preemptive)**

### Example Usage

1. Run the application.
2. Select an algorithm by entering the corresponding number (1-6).
3. For Round Robin, enter the time quantum when prompted.
4. View the scheduled processes, average waiting time, and average turnaround time.
5. A Gantt chart will be displayed to visualize the scheduling.

### Utilities

- **SchedulerUtils**: Contains utility methods for displaying processes and calculating average times.
- **GanttChart**: Displays a Gantt chart for visualizing the scheduling.

## Contributing

Contributions are welcome! Please fork the repository and create a pull request with your changes.

## Acknowledgements

- Inspired by various CPU scheduling algorithm tutorials and resources.
