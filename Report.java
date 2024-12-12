import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Report {
    private ArrayList<Integer> levels;
    private ArrayList<Boolean> levelValid;
    private final Day2 master;

    public Report(Day2 d) {
        this.levels = new ArrayList<>(10);
        this.levelValid = new ArrayList<>(10);
        this.master = d;
    }

    public void addLevel(int n) {
        levels.add(n);
    }

    public boolean isValid() {
        boolean isValid = false;
        boolean increasing = false;
        boolean decreasing = false;

        // Find duplicates, if there are more than one, the report is invalid
        int numberOfDups = 0;
        int positionOfDup = 0;
        Set<Integer> set = new HashSet<>();
        for(int i =0; i < levels.size(); i++) {
            if (set.contains(levels.get(i))) {
                numberOfDups++;
                positionOfDup = i;
                //System.out.println(levels.get(i)+" is duplicated");
            } else set.add(levels.get(i));
        }
        if (numberOfDups > 1) {
            return false;
        }
        if (numberOfDups == 1) {
            levels.remove(positionOfDup);
        }

        int postitionOfFlaw = 0;
        
        for (int i=0; i<levels.size(); i++) {
            if ( Collections.frequency(levels, levels.get(i)) > 1 ) {
                isValid = false;
                break;
            }
            if (i == 0) {
                isValid = true;
            } else {
                if (abs(levels.get(i) - levels.get(i-1)) > 3) {
                        isValid = false;
                        postitionOfFlaw = i;
                        break;
                    }
                if (levels.get(i) > levels.get(i-1)) {    
                    if (decreasing) {
                        isValid = false;
                        postitionOfFlaw = i;
                        break;
                    }      
                    increasing = true;
                } else {
                    decreasing = true;
                    if (increasing) {
                        isValid = false;
                        postitionOfFlaw = i;
                        break;
                        }
                    }
                }
        }

        if (!isValid) {
            levels.remove(postitionOfFlaw-1);
            master.addFixedReport(this);
        }

        return isValid;
    }

    public boolean isValidAfterRemoval() {
        boolean isValid = false;
        boolean increasing = false;
        boolean decreasing = false;
  
        for (int i=0; i<levels.size(); i++) {
            if (i == 0) {
                isValid = true;
            } else {
                if (abs(levels.get(i) - levels.get(i-1)) > 3) {
                        isValid = false;
                        break;
                    }
                if (levels.get(i) > levels.get(i-1)) {    
                    if (decreasing) {
                        isValid = false;
                        break;
                    }      
                    increasing = true;
                } else {
                    decreasing = true;
                    if (increasing) {
                        isValid = false;
                        break;
                        }
                    }
                }
        }
        return isValid;
    }

    public String toString() {
        String ret = "";
        for (Integer i : levels) {
            ret += i.toString() + " ";
        }
        return ret; 
    }

    public ArrayList<Integer> getLevels() {
        return levels;
    }
}