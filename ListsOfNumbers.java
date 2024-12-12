
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Collections;

public class ListsOfNumbers {
    private ArrayList<Integer> levels;
    private ArrayList<Integer> flawedLevels;

    public ListsOfNumbers() {
        this.levels = new ArrayList<>(10);
        this.flawedLevels = new ArrayList<>(10);
    }

    public void addLevel(int n) {
        levels.add(n);
    }

    public void switchReports() {
        this.levels = this.flawedLevels;
    }

    public boolean isValid() {
        boolean isValid = false;
        boolean increasing = false;
        boolean decreasing = false;

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

    public boolean noMoreThanOneFlaw() {
        boolean isValid = false;
        boolean increasing = false;
        boolean decreasing = false;
        int numberOfFlaws = 0;
        int dups = 0;

        for (int i=0; i<levels.size(); i++) {
            boolean flawed = false;
            if ( Collections.frequency(levels, levels.get(i)) > 1 ) {
                dups ++;
                flawed = true;
                //numberOfFlaws++;
            }
            if (i == 0) {
                isValid = true;
            } else {
                if (abs(levels.get(i) - levels.get(i-1)) > 3) {
                        numberOfFlaws++;
                        flawed = true;
                    }
                if (levels.get(i) > levels.get(i-1)) {    
                    if (decreasing) {
                        numberOfFlaws++;
                        flawed = true;
                    }      
                    increasing = true;
                } else {
                    decreasing = true;
                    if (increasing) {
                        numberOfFlaws++;
                        flawed = true;
                        }
                    }
                }
                if (flawed) {
                    System.out.println("removing the flawed item" + levels.get(i));
                } else {
                    flawedLevels.add(levels.get(i));
                }
        }
        
        if (numberOfFlaws > 0) {
            return false;
        } else {
            return true;
        }
    }
    public ListsOfNumbers getFixedReport() {
        return this;
    }

    private boolean valid(){
        boolean isValid = false;
        boolean increasing = false;
        boolean decreasing = false;

        for (int i=0; i<flawedLevels.size(); i++) {
            if ( Collections.frequency(flawedLevels, flawedLevels.get(i)) > 1 ) {
                isValid = false;
                break;
            }
            if (i == 0) {
                isValid = true;
            } else {
                if (abs(flawedLevels.get(i) - flawedLevels.get(i-1)) > 3) {
                        isValid = false;
                        break;
                    }
                if (flawedLevels.get(i) > flawedLevels.get(i-1)) {    
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
    
}