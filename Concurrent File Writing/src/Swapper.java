public class Swapper implements Runnable {
    private int offset;
    private Interval interval;
    private String content;
    private char[] buffer;

    public Swapper(Interval interval, String content, char[] buffer, int offset) {
        this.offset = offset;
        this.interval = interval;
        this.content = content;
        this.buffer = buffer;
    }

    @Override
    public void run() {
    //	StringBuilder bufferp = new StringBuilder();
    	int a=interval.getX();
    	int b=interval.getY();
    	int c=b-a+1;   //c should be the chunksize?
        char[] contentarray=content.toCharArray();
        for(int i=0;i< c;i++){
            buffer[offset+i]= contentarray[i+interval.getX()];
    //        bufferp.append( buffer[offset+i]);
          //  System.out.println(buffer[i+offset]);
        // TODO: Implement me!
    }// System.out.println(bufferp);
}}