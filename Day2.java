import java.io.File;
import java.io.FileNotFoundException;
import static java.lang.Math.abs;
import java.util.*;

public class Day2 {

    private long difference;
    private final ArrayList<ListsOfNumbers> reports;
    private final ArrayList<Report> reports_part2;
    private final ArrayList<Report> fixedReports;

    public Day2() {
        reports = new ArrayList<>();
        reports_part2 = new ArrayList<>();
        fixedReports = new ArrayList<>();

        difference = 0;
    }

    public void loadInput(boolean full) {
        try {
            Scanner scanner = null;

            if (full) {
                scanner = new Scanner(new File("input_full.txt"));
            }
            else {
                scanner = new Scanner(new File("input_test.txt"));
            }

            while (scanner.hasNextLine()) {
                ListsOfNumbers report = new ListsOfNumbers();
                Report report_p2 = new Report(this);

                String line = scanner.nextLine();
                //System.out.println(line);

                String s[] = line.split(" ");
                for (int i=0; i < s.length; i++) {
                    int bid = Integer.parseInt(s[i]);
                    report.addLevel(bid);  
                    report_p2.addLevel(bid);
                }
                reports.add(report);
                reports_part2.add(report_p2);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addFixedReport(Report r) {
        fixedReports.add(r);
    }

    public int getValidReports() {
        int numberOfValidReports = 0;

        for (Report l : reports_part2) {
            if (l.isValid()) {
                System.out.println("Report: " + l.toString() + " is valid");
                numberOfValidReports++;
            } else {
                System.out.println("Report: " + l.toString() + " is not valid");
            }  
        }
        for (Report l : fixedReports) {
            if (l.isValidAfterRemoval()) {
                System.out.println("Report: " + l.toString() + " is valid");
                numberOfValidReports++;
            } else {
                //System.out.println("Report: " + l.toString() + " is not valid");
            }
        }
        return numberOfValidReports;
    }

    public int getNumberOfValidReports() {
        int numberOfValidReports = 0;

        for (ListsOfNumbers l : reports) {
            if (l.isValid()) {
                System.out.println("Report: " + l.toString() + " is valid");
                numberOfValidReports++;
            } else {
                System.out.println("Report: " + l.toString() + " is not valid");
            }
            
        }
        return numberOfValidReports;
    }

    public int getNumberOfValidReportsWith1Flaw() {
        int numberOfValidReports = 0;

        for (ListsOfNumbers l : reports) {
            if (l.noMoreThanOneFlaw()) {
                System.out.println("Report: " + l.toString() + " is valid");
                numberOfValidReports++;
            } else {
                System.out.println("Report: " + l.toString() + " is not valid");
                ListsOfNumbers corrected = l.getFixedReport();
                corrected.switchReports();
                if ( corrected.isValid()) {
                    numberOfValidReports++;
                }
            }
            
        }
        return numberOfValidReports;
    }

    public boolean isValid() {
        boolean isValid = true;

        for (Report r : reports_part2) {
            ArrayList<Integer> levels = r.getLevels();

            int currNumber = -1;
            boolean increasing = false;
            boolean decreasing = false;

            for (int i=0; i< levels.size(); i++)  {
                if (currNumber == -1) {
                    currNumber = i;
                    break;
                } 
                if ( abs(i - currNumber) > 3 ) {
                    isValid = false;
                    break;
                } else {
                    if (i > currNumber) {
                        // increasing
                        if (decreasing) {
                            isValid = false;
                            break;
                        }
                        increasing = true;
                    } else if (i < currNumber) {
                        // descreasubg
                        if (increasing) {
                            isValid = false;
                            break;
                        }
                        decreasing = true;
                    } else {
                        // duplicates
                        isValid = false;
                        break;
                    }
                }
            }
        }
        return true;
    }
}