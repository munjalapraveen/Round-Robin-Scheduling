/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os_rr_ass;


import java.util.*;
import java.io.*;

public class Driver
{
    public static void main(String[] args) throws IOException {
//        if (args.length != 2) {
//            System.err.println("Usage: java Driver <algorithm> <schedule>");
//            System.exit(0);
//        }

        //BufferedReader file = new BufferedReader(new FileReader(args[1]));
        BufferedReader file = new BufferedReader(new FileReader("F:\\Masters\\CourseMaterial\\OS\\Project\\OS_RR_Ass\\dist\\schedule.txt"));

        String schedule;

        // create the queue of tasks
        List<Task> queuelist = new ArrayList<Task>();

        // read in the tasks and populate the ready queue
        while ( (schedule = file.readLine()) != null) {
            String[] parameters = schedule.split(",\\s*");
            queuelist.add(new Task(parameters[0], Integer.parseInt(parameters[1]), Integer.parseInt(parameters[2])));
        }

        file.close();
        
        Algorithm scheduler=new PriorityRR(queuelist);

       // Algorithm scheduler = null;
//        String choice = args[0].toUpperCase();
//
//        switch(choice) {
//
//            case "PRI-RR":
//                scheduler = new PriorityRR(queuelist);
//                break;
//            default:
//                System.err.println("Invalid algorithm");
//                System.exit(0);
//        }

        // start the scheduler
        scheduler.schedule();
    }
}

