
import java.util.ArrayList;
import java.util.Scanner;

public class page {
    int[] pages;
    int frameCount;
   
    void fifo()
    {
        ArrayList <Integer> frames = new ArrayList<Integer>();
        int pagefaults = 0;

        for(int page: pages)
        {
            if(!frames.contains(page))
            {
                pagefaults++;
                if(frames.size()<frameCount)
                {
                    frames.add(page);
                }
                else
                {
                    frames.remove(0);
                    frames.add(page);
                }
            }
            System.out.print("Frame\tPage\n");
            for(int i=0; i<frames.size(); i++)
            {
                System.out.println(i+1+"\t"+ frames.get(i));
            }
        }
        System.out.println("Page Faults: "+pagefaults+ " Page Hits: " + (pages.length-pagefaults));
    }

    void lifo()
    {
        ArrayList <Integer> frames = new ArrayList<Integer>();
        int pagefaults = 0;

        for(int page: pages)
        {
            if(!frames.contains(page))
            {
                pagefaults++;
                if(frames.size()<frameCount)
                {
                    frames.add(page);
                }
                else
                {
                    frames.remove(frames.size()-1);
                    frames.add(page);
                }
            }
            System.out.println("Page: " + page);
            System.out.print("Frame\tPage\n");
            for(int i=0; i<frames.size(); i++)
            {
                System.out.println(i+1+"\t"+ frames.get(i));
            }
        }
        System.out.println("Page Faults: "+pagefaults+ " Page Hits: " + (pages.length-pagefaults));
    }

    void lru()
    {
        ArrayList <Integer> frames = new ArrayList<Integer>();
        int pagefaults = 0;

        for(int page: pages)
        {
            if(!frames.contains(page))
            {
                pagefaults++;
                if(frames.size()<frameCount)
                {
                    frames.add(page);
                }
                else
                {
                    frames.remove(0);
                    frames.add(page);
                }
            }
            else
            {
                frames.remove(Integer.valueOf(page));
                frames.add(page);
            }
            System.out.println("Page: " + page);
            System.out.print("Frame\tPage\n");
            for(int i=0; i<frames.size(); i++)
            {
                System.out.println(i+1+"\t"+ frames.get(i));
            }
        }
        System.out.println("Page Faults: "+pagefaults+ " Page Hits: " + (pages.length-pagefaults));
    }

    void optimal()
    {
        ArrayList <Integer> frames = new ArrayList<Integer>();
        int pagefaults = 0;

        for(int i=0; i<pages.length; i++)
        {
            int page = pages[i];
            if(!frames.contains(page))
            {
                pagefaults++;
                if(frames.size()<frameCount)
                {
                    frames.add(page);
                }
                else
                {
                    int farthest = -1;
                    int farthestindex = -1;
                    boolean notfound = false;
                    for(int j=0; j<frames.size(); j++)
                    {
                        int frame = frames.get(j);
                        int occurence = -1;
                        for(int k=i; k<pages.length; k++)
                        {
                            if(pages[k]==frame)
                            {
                                occurence = k;
                                break;
                            }
                        }
                        if(occurence == -1)
                        {
                            farthestindex = j;
                            notfound=true;
                        }
                        else if(occurence>farthest && !notfound)
                        {
                            farthest = occurence;
                            farthestindex = j;
                        }
                    }
                    frames.set(farthestindex, page);
                }
            }
            System.out.println("Page: " + page);
            System.out.print("Frame\tPage\n");
            for(int k=0; k<frames.size(); k++)
            {
                System.out.println(k+1+"\t"+ frames.get(k));
            }
        }
        System.out.println("Page Faults: "+pagefaults+ " Page Hits: " + (pages.length-pagefaults));
    }
    
     public static void main(String[] args)
    {
    		page p = new page();
        	p.pages = new int[]{1, 2, 3, 4, 1, 2, 5, 1, 2, 3, 4, 5};
        	p.frameCount = 3;
       while(true)
       {
    		Scanner sc = new Scanner(System.in);
    		System.out.println("menu");
    		System.out.println("1.FIFO");
    		System.out.println("2.FILO");
    		System.out.println("3.LRU");
    		System.out.println("4.OPTIMAL");
    		System.out.println("5.exit");
    		System.out.println("Enter your choice:");
    		int choice;
    		choice=sc.nextInt();
    		switch(choice)
    		{
    			case 1:
    				p.fifo();
    				break;
    			case 2:
    				p.lifo();
    				break;
    			case 3:
    				p.lru();
    				break;
  
    			case 4:
    				p.optimal();
    				break;
    			case 5:
    				System.exit(0);
    			default:
    				System.out.println("wrong choice...");
    		}
    	
    }
    }
}
