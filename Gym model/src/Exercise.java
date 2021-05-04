/*
name: Ruohuan Xu £¬ Zehui Zhao
*/
import java.util.Random;
import java.util.HashMap;
import java.util.Map;
public class Exercise {
    private ApparatusType at;
    private Map<WeightPlateSize,Integer> weight;
    private int duration;
    public Exercise(ApparatusType at, Map<WeightPlateSize,Integer> weight, int duration) {
        this.at = at;
        this.weight = weight;
        this.duration = duration;
    }    
    public int getDuration() {
		return this.duration;
    }
    
    public ApparatusType getApparatusType() {
		return this.at;
    }
    
    public Map<WeightPlateSize,Integer> getWeight() {
        return this.weight;
    }
    public static Exercise generateRandom(Map<WeightPlateSize, Integer> weight) {
        int count3 = 0;
        int count5 = 0;
        int count10 = 0;
        Random random = new Random();
        Map<WeightPlateSize, Integer> finalweight = new HashMap<WeightPlateSize, Integer>();
        count3 = random.nextInt(11);
        count5 = random.nextInt(11);
        count10 = random.nextInt(11);
        if (count3 == 0 && count5 == 0 && count10 == 0) {
                    count3 = random.nextInt(11);
                    count5 = random.nextInt(11);
                    count10 = random.nextInt(11);
        }
        finalweight.put(WeightPlateSize.SMALL_3KG, count3);
        finalweight.put(WeightPlateSize.MEDIUM_5KG, count5);
        finalweight.put(WeightPlateSize.LARGE_10KG, count10); 
        int duration = random.nextInt(10)+1;
        Exercise newExercise = new Exercise(ApparatusType.getRandomApparatusType(),finalweight,duration);
        return newExercise; 
    }

}
