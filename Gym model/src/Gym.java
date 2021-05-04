/*
name: Ruohuan Xu £¬ Zehui Zhao
*/
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Semaphore;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.HashSet;
import java.util.Random;
import java.util.HashMap;
public class Gym implements Runnable {
    private static final int GYM_SIZE = 30;
    private static final int GYM_REGISTERED_CLIENTS = 10000;
    private Map<WeightPlateSize,Integer> noOfWeightPlates;
    private Set<Integer> clients; 
    private ExecutorService executor;
    static Semaphore m_grab = new Semaphore(1);
    static Semaphore m_release = new Semaphore(1);
    private static final int COUNT3 = 110;
    private static final int COUNT5 = 90;
    private static final int COUNT10 = 75;
    static Semaphore S_LEGPRESSMACHINE = new Semaphore(5);
    static Semaphore S_BARBELL = new Semaphore(5);
    static Semaphore S_HACKSQUATMACHINE = new Semaphore(5);
    static Semaphore S_LEGEXTENSIONMACHINE = new Semaphore(5);
    static Semaphore S_LEGCURLMACHINE = new Semaphore(5);
    static Semaphore S_LATPULLDOWNMACHINE = new Semaphore(5);
    static Semaphore S_PECDECKMACHINE = new Semaphore(5);
    static Semaphore S_CABLECROSSOVERMACHINE = new Semaphore(5);
    static Semaphore S_3KG = new Semaphore(COUNT3);
    static Semaphore S_5KG = new Semaphore(COUNT5);
    static Semaphore S_10KG = new Semaphore(COUNT10);       
    public Gym() {
        this.clients = new HashSet<Integer>();
        this.noOfWeightPlates = new HashMap<>();
        this.noOfWeightPlates.put(WeightPlateSize.SMALL_3KG,COUNT3);
		this.noOfWeightPlates.put(WeightPlateSize.MEDIUM_5KG,COUNT5);
        this.noOfWeightPlates.put(WeightPlateSize.LARGE_10KG,COUNT10);
    }
    private int generateClientID () {
        Random ClientID = new Random();
        int cid=0;
        while (clients.contains(cid)) {
            cid = ClientID.nextInt(GYM_REGISTERED_CLIENTS) + 1;
        }
        clients.add(cid);
        return cid;
    }
    public void run(){
        executor = Executors.newFixedThreadPool (GYM_SIZE);
        for(int i = 0; i < GYM_REGISTERED_CLIENTS; i++) {
            Client client = Client.generateRandom(generateClientID(), noOfWeightPlates); 
            executor.execute((Runnable) client);
        }
        executor.shutdown ();
    }

}
