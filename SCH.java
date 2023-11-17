import java.util.*;
class process 
{
	  public int id, at, bt, wt, tat, rt, st, ct, rest, priority;
}
class SCH
{
	void fcfs(int nop, process pro[]) 
	{
	    process temp;
	    for (int i = 0; i < nop - 1; i++) 
	    {
	    	for (int j = i + 1; j < nop; j++) 
	    	{
        		if (pro[i].at > pro[j].at) 
        		{
          			temp = pro[i];
          			pro[i] = pro[j];
         			 pro[j] = temp;
        		}
       		 	if (pro[i].at == pro[j].at) 
       		 	{
          			if (pro[i].bt > pro[j].bt) 
          			{
					temp = pro[i];
       				     	pro[i] = pro[j];
            				pro[j] = temp;
          			}
        		}
      		}
    	}
    	
    	int time = 0;
	for (int i = 0; i < nop; i++) 
    	{
      		while (pro[i].at > time) 
      		{
        		time++;
      		}
		pro[i].st = time;
      		time += pro[i].bt;
     		pro[i].ct = time;
		pro[i].wt = pro[i].st - pro[i].at;
		pro[i].tat = pro[i].wt + pro[i].bt;
		pro[i].rest = pro[i].wt;
    	}
    	print(nop, pro);
  	}
  	
  	void sjf(int nop,process pro[])
	{
		int time=0;
		int executed=0;
		while(executed<nop)
		{
			int minindex=-1;
			for(int i=0;i<nop;i++)
			{
				if(pro[i].rt>0 && pro[i].at<=time &&(minindex==-1||pro[i].rt<pro[minindex].rt))
				{
					minindex=i;
				}
			}
			if(minindex==-1)
			{
				time++;
				continue;
			}
			pro[minindex].rt--;
			time++;
			if(pro[minindex].rt==0)
			{
				pro[minindex].ct=time;
				pro[minindex].tat=pro[minindex].ct-pro[minindex].at;
				pro[minindex].wt=pro[minindex].tat-pro[minindex].bt;
				executed++;
			}
		}
		print(nop,pro);
	}
		
  
	void roundRobin(int nop, process pro[]) 
	{
		    Scanner sc = new Scanner(System.in);
		    System.out.print("Enter time quantum: ");
		    int quantum = sc.nextInt();
		    int rem_bt[] = new int[nop];
		    for (int i = 0; i < nop; i++) 
		    {
		     	 rem_bt[i] = pro[i].bt;
		    }
	   	 int t = 0;
	   	 while (true) 
	   	 {
	      		boolean done = true;
	      		for (int i = 0; i < nop; i++) 
	      		{
				if (rem_bt[i] > 0 && pro[i].at<=t) 
				{
		  			done = false;
		  			if (rem_bt[i] > quantum) 
		  			{
		   			 t += quantum;
		    			rem_bt[i] -= quantum;
		  			}
		  			else 
		  			{
		   				 t = t + rem_bt[i];
		    				pro[i].ct = t;
		    				rem_bt[i] = 0;
		  			}
				}
	      		}
	      		if (done == true)
			break;
	    	}

	    	for (int i = 0; i < nop; i++) 
	    	{
	      		pro[i].tat = pro[i].ct - pro[i].at;
	      		pro[i].wt = pro[i].tat - pro[i].bt;
	    	}
	    	print(nop, pro);
	}

	void priorityScheduling(int nop, process pro[]) 
	{
		    Scanner sc = new Scanner(System.in);
		    for (int i = 0; i < nop; i++) 
		    {
		      	System.out.print("Enter priority for process " + (i + 1) + ": ");
		      	pro[i].priority = sc.nextInt();
		    }
		
	    		process temp;
	    		for (int i = 0; i < nop - 1; i++) 
	    		{
	      			for (int j = i + 1; j < nop; j++) 
	      			{
					if (pro[i].priority > pro[j].priority) 
					{
		  				temp = pro[i];
		  				pro[i] = pro[j];
		  				pro[j] = temp;
					}
	      			}
	    		}
	    		int time = 0;
	    		for (int i = 0; i < nop; i++) 
	    		{
	     			 while (pro[i].at > time) 
	     			 {
					time++;
	      			}
	      			pro[i].st = time;
	      			time += pro[i].bt;
	      			pro[i].ct = time;
	      			pro[i].tat = pro[i].ct - pro[i].at;
	      			pro[i].wt = pro[i].tat - pro[i].bt;
	    		}
	    		print(nop, pro);
	  }
	  public static void main(String[] args) 
	  {
	    SCH obj = new SCH();
	    Scanner sc = new Scanner(System.in);
	    int nop;

	    System.out.println("Enter no of processes:");
	    nop = sc.nextInt();
	    process pro[] = new process[nop];
	    for (int i = 0; i < nop; i++) 
	    {
	      int at, bt;
	      System.out.print("Arrival time of process " + i + " : ");
	      at = sc.nextInt();
	      System.out.print("Burst time of process " + i + " : ");
	      bt = sc.nextInt();

	      pro[i] = new process();
	      pro[i].id = i+1;
	      pro[i].at = at;
	      pro[i].bt = bt;
	      pro[i].rt = bt;
	    }
	    int choice;
	    do 
	    {
	      System.out.println("\nMenu");
	      System.out.println("1. FCFS");
	      System.out.println("2. SJF");
	      System.out.println("3. Round Robin");
	      System.out.println("4. Priority");
	      System.out.println("5. Exit");
	      System.out.print("Enter your choice: ");
	      choice = sc.nextInt();
	      switch (choice) 
	      {
		      case 1:
			obj.fcfs(nop, pro);
			break;
		      case 2:
			obj.sjf(nop, pro);
			break;
		      case 3:
			obj.roundRobin(nop, pro);
			break;
		      case 4:
			obj.priorityScheduling(nop, pro);
			break;
		      case 5:
			System.out.println("Exiting...");
			System.exit(0);
			break;
		      default:
			System.out.println("Invalid choice");
	      }
	    } while (choice != 5);
	   }
	   static void print(int nop, process pro[]) {
	    process sum = new process();
	    System.out.println("id"+"\tat"+"\tbt"+ "\twt"+ "\ttat");
	    for (int i = 0; i < nop; i++) {
	      System.out.println(pro[i].id + "\t" + pro[i].at + "\t" + pro[i].bt + "\t" + pro[i].wt + "\t" +
		pro[i].tat + "\t");
	      sum.wt += pro[i].wt;
	      sum.tat+=pro[i].tat;
	    }
	    System.out.println("Average wait time = " + sum.wt / nop);
	    System.out.println("Average turn around  time = " + sum.tat/ nop);
	    
	  }
}
