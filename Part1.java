
public class Part1 extends Day2 {
        
    public Part1() {
    }
    public static void main(String[] args) {
        System.out.println("AOC Day 1 Part 1");
        long start = System.currentTimeMillis();

        boolean loadFull = true;

        Part1 part1 = new Part1(); 
        part1.loadInput(loadFull);

        long end = System.currentTimeMillis();

        System.out.println("Number of valid Reports: " + part1.getNumberOfValidReports());
        System.out.println("Milliseconds: " + (end - start));
    }
}