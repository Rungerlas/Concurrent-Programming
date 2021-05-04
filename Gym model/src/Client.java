/*
name: Ruohuan Xu £¬ Zehui Zhao
*/
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
public class Client implements Runnable {
    private int id;
    private List<Exercise> routine;

    public Client(int id) {
        this.id = id;
        this.routine = new ArrayList<Exercise>();
    }

    public void addExercise(Exercise e) {
        routine.add(e);
    }

    public static Client generateRandom(int id, Map<WeightPlateSize, Integer> noOfWeightPlates) {
        Random r = new Random(); 
        Client client = new Client(id);
        int counte = r.nextInt(5)+15;
		for(int j=0; j<counte; j++){
			Exercise new_exercise = new Exercise(null, null, 1); 
			new_exercise = Exercise.generateRandom(null);		
			client.addExercise(new_exercise);
		}
        return client;
    }
    public void run() {
        try {
            for (Exercise exe : routine) {
                
                Gym.m_grab.acquire();
                System.out.println("Client "+this.id+" enter the Gym.");
                if (exe.getApparatusType() == ApparatusType.LEGPRESSMACHINE) 
                {
                	Gym.S_LEGPRESSMACHINE.acquire();
                }
                else if (exe.getApparatusType() == ApparatusType.BARBELL)
                {
                	Gym.S_BARBELL.acquire();
                }
                else if (exe.getApparatusType() == ApparatusType.HACKSQUATMACHINE)
                {
                	Gym.S_HACKSQUATMACHINE.acquire();
                }
                else if (exe.getApparatusType() == ApparatusType.LEGEXTENSIONMACHINE)
                {
                	Gym.S_LEGEXTENSIONMACHINE.acquire();
                }
                else if (exe.getApparatusType() == ApparatusType.LEGCURLMACHINE)
                {
                	Gym.S_LEGCURLMACHINE.acquire();
                }
                else if (exe.getApparatusType() == ApparatusType.LATPULLDOWNMACHINE) 
                {
                	Gym.S_LATPULLDOWNMACHINE.acquire();
                }
                else if (exe.getApparatusType() == ApparatusType.PECDECKMACHINE)
                {
                	Gym.S_PECDECKMACHINE.acquire();
                }
                else if (exe.getApparatusType() == ApparatusType.CABLECROSSOVERMACHINE) 
                {
                	Gym.S_CABLECROSSOVERMACHINE.acquire();
                }

                for (int i = 0; i < exe.getWeight().get(WeightPlateSize.SMALL_3KG); i++)
                {   
                	Gym.S_3KG.acquire();
                
                }
                for (int i = 0; i < exe.getWeight().get(WeightPlateSize.MEDIUM_5KG); i++)
                {  
                	Gym.S_5KG.acquire();
                
                }
                for (int i = 0; i < exe.getWeight().get(WeightPlateSize.LARGE_10KG); i++)
                {   
                	Gym.S_10KG.acquire();
                
                }               
                Gym.m_grab.release();
                
                System.out.println("Client "+this.id+" is working on "+exe.getApparatusType()+" and weight is combine with "+exe.getWeight());
                Thread.sleep(exe.getDuration());
                System.out.println("Client "+this.id+" completed doing  "+exe.getApparatusType()+" and weight is combine with "+exe.getWeight()+" for "+exe.getDuration()+" minutes");
                // System.out.println(e.getduration());

                Gym.m_release.acquire();
                if (exe.getApparatusType() == ApparatusType.LEGPRESSMACHINE) 
                {
                	Gym.S_LEGPRESSMACHINE.release();
                }
                else if (exe.getApparatusType() == ApparatusType.BARBELL)
                {
                	Gym.S_BARBELL.release();
                }
                else if (exe.getApparatusType() == ApparatusType.HACKSQUATMACHINE)
                {
                	Gym.S_HACKSQUATMACHINE.release();
                }
                else if (exe.getApparatusType() == ApparatusType.LEGEXTENSIONMACHINE)
                {
                	Gym.S_LEGEXTENSIONMACHINE.release();
                }
                else if (exe.getApparatusType() == ApparatusType.LEGCURLMACHINE)
                {
                	Gym.S_LEGCURLMACHINE.release();
                }
                else if (exe.getApparatusType() == ApparatusType.LATPULLDOWNMACHINE) 
                {
                	Gym.S_LATPULLDOWNMACHINE.release();
                }
                else if (exe.getApparatusType() == ApparatusType.PECDECKMACHINE)
                {
                	Gym.S_PECDECKMACHINE.release();
                }
                else if (exe.getApparatusType() == ApparatusType.CABLECROSSOVERMACHINE) 
                {
                	Gym.S_CABLECROSSOVERMACHINE.release();
                }

                for (int i = 0; i < exe.getWeight().get(WeightPlateSize.SMALL_3KG); i++)
                {
                	Gym.S_3KG.release();
                }
                for (int i = 0; i < exe.getWeight().get(WeightPlateSize.MEDIUM_5KG); i++)
                {
                	Gym.S_5KG.release();
                }
                for (int i = 0; i < exe.getWeight().get(WeightPlateSize.LARGE_10KG); i++)
                {
                	Gym.S_10KG.release();               
                }
                Gym.m_release.release();

                System.out.println("Client "+this.id+" stops using "+exe.getApparatusType()+" and weight is combine with "+exe.getWeight());
                System.out.println("Client "+this.id+" left the Gym.");
            }
        } 
        catch (InterruptedException e) 
        {
        	System.out.println("Client failed to do exercise");
        }
    }
}
