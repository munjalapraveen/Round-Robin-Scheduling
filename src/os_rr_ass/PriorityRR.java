
package os_rr_ass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Comparator;

public class PriorityRR implements Algorithm {
    public List<Task> QueueList;

    public PriorityRR(List<Task> queue) {
        this.QueueList = queue;
    }


    @Override
    public void schedule() {
        System.out.println("Priority with RR Scheduling \n");
        
        //Sorting the Queue List
         QueueList.sort((Task t1, Task t2) -> Integer.toString(t2.getPriority()).compareTo(Integer.toString(t1.getPriority())));

         //Creating the New Priority List 
        Map<Integer, List<Task>> PriorityQueueList = new TreeMap<Integer, List<Task>>(Collections.reverseOrder());
        
        //Iterating the loop to add the task to the Queue to process the Task
        for (Task task : QueueList) {
            //This will check whether the Given Task priority is already in the Queue or not and 
            //if it is true then it will add to the queue
            if (PriorityQueueList.containsKey(task.getPriority())) {
                PriorityQueueList.get(task.getPriority()).add(task);
            } else {
                
                //if the priority is not present in loop creating another list to add the task
                List<Task> PriorityList = new ArrayList<Task>();
                PriorityList.add(task);
                PriorityQueueList.put(task.getPriority(), PriorityList);
            }
        }
        
        
        // Now the Proceess begins.
        //Iterating the loop of the queue that we created and getting the task name based on the priority 
        for(Integer queueMap :  PriorityQueueList.keySet()) {
            List<Task> TaskList =  PriorityQueueList.get(queueMap);
            
            //Sorting the priority using the lamda expression
            TaskList.sort((Task t1, Task t2) -> Integer.toString(t1.getTid()).compareTo(Integer.toString(t2.getTid())));
            
// Round Robin Algorithm Implementation for the Task with Same priority
            if (TaskList.size() > 1) {
                boolean completed = true;
                while (completed) {
                    List<Task> newList = new ArrayList<Task>();
                    newList.addAll(TaskList);
                    for (Task task : newList) {
                        CPU.run(task, task.getTid());
                        if (task.getBurst() <= 10 ) {
                            task.setBurst(task.getBurst() - task.getBurst());
                        } else {
                            task.setBurst(task.getBurst() - 10);
                        }
                        if (task.getBurst() == 0) {

                            System.out.println("Task " + task.getName() + " Finished.\n" );
                            TaskList.remove(task);
                            if (TaskList.size() == 0) {
                                completed = false;
                            }
                        }
                    }
                }
            }
            //  Process will execute With the Unique Priority in the Schedule
            else {
                CPU.run(TaskList.get(0), TaskList.get(0).getTid());
                System.out.println("Task " + TaskList.get(0).getName() + " Finished.\n" );
            }
        }
    }
    @Override
    public Task pickNextTask() {
        return null;
    }
}
