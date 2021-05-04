import java.io.*;
import java.util.*;

public class TextSwap {

	private static String readFile(String filename) throws Exception {
//	public static String readFile(String filename) throws Exception {
        String line;
        StringBuilder buffer = new StringBuilder();
        File file = new File(filename);
        BufferedReader br = new BufferedReader(new FileReader(file));
        while ((line = br.readLine()) != null) {
            buffer.append(line);
        }
        br.close();
        return buffer.toString();
    }

    private static Interval[] getIntervals(int numChunks, int chunkSize) {
    	Interval[] inte=new Interval[numChunks];
    	int x=0;
    	int y=0;
    	int num=numChunks;
    	int size=chunkSize;
    	Interval intee;	
        for(int i=0;i<num; i++){
        	x=size*i;
        	y=size*(i+1)-1;
        	intee=new Interval(x,y);
            inte[i]=intee; 
            }
        return inte;  	
        // TODO: Implement me!
    }

    private static List<Character> getLabels(int numChunks) {
 //   public static List<Character> getLabels(int numChunks) {
        Scanner scanner = new Scanner(System.in);
        List<Character> labels = new ArrayList<Character>();
        int endChar = numChunks == 0 ? 'a' : 'a' + numChunks - 1;
        System.out.printf("Input %d character(s) (\'%c\' - \'%c\') for the pattern.\n", numChunks, 'a', endChar);
        for (int i = 0; i < numChunks; i++) {
            labels.add(scanner.next().charAt(0));
        }
        scanner.close();
      //  System.out.println(labels);
       // for(int i=0;i<numChunks;i++) {
      //  	 System.out.println(labels.get(i)); }
        return labels;
    }

    private static char[] runSwapper(String content, int chunkSize, int numChunks) {
        List<Character> labels = getLabels(numChunks);
        Interval[] intervals = getIntervals(numChunks, chunkSize);
        int index=0;
        Interval inter;
        char lab;
        char[] bufferq= new char[content.length()];       
        Thread[] thread= new Thread[numChunks];
        for(int i=0;i<labels.size();i++){
            index=chunkSize*i;
            lab=labels.get(i);
            inter= intervals[lab-97];
            Swapper swap= new Swapper(inter,content,bufferq,index);
            thread[i]=new Thread(swap);
            thread[i].start();
        }  
        for(int i=0;i<labels.size();i++){
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }}
        return bufferq;
        // TODO: Order the intervals properly, then run the Swapper instances.
    }

    private static void writeToFile(String contents, int chunkSize, int numChunks) throws Exception {
  //  public static void writeToFile(String contents, int chunkSize, int numChunks) throws Exception {
        char[] buff = runSwapper(contents, chunkSize, contents.length() / chunkSize);
    //    PrintWriter writer = new PrintWriter("D:\\chrome\\hw1_stub\\output.txt", "UTF-8");
       PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
        writer.print(buff);
        writer.close();
    }

    public static void main(String[] args) {
       if (args.length != 2) {
           System.out.println("Usage: java TextSwap <chunk size> <filename>");
          return;
       }
        String contents = "";
        int chunkSize = Integer.parseInt(args[0]);
        try {
            contents = readFile(args[1]);
            writeToFile(contents, chunkSize, contents.length() / chunkSize);
        } catch (Exception e) {
            System.out.println("Error with IO.");
            return;
        }
    }
}