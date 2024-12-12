public class Part2 extends Day2 {
        
    public Part2() {
    }
    public static void main(String[] args) {
        System.out.println("AOC Day 1 Part 1");
        long start = System.currentTimeMillis();

        boolean loadFull = true;

        Part2 part2 = new Part2();
        part2.loadInput(loadFull);
        boolean valid = part2.isValid();

        System.out.println("Number of valid Reports: " + part2.getValidReports());

        long end = System.currentTimeMillis();
        System.out.println("Milliseconds: " + (end - start));
    }
}