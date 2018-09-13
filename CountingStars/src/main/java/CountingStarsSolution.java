
import java.io.PrintStream;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author clayt
 */
public class CountingStarsSolution {
    
    Scanner in;
    PrintStream out;
    
    int rows, columns, stars, caseCounter;
    boolean [][] picture;
    
    
    public static void main(String[] args) throws Exception {
        CountingStarsSolution countingStars = new CountingStarsSolution();
        countingStars.go();
    }
    
    public CountingStarsSolution() {}
    
    private void go() throws Exception{
        in = new Scanner(System.in);
        out = System.out;
        caseCounter = 0;
        for(;;) {
            if (HaveTestCase()) {
                readTestCase();
                solve();
                writeTestCase();
                caseCounter++;
            } else {
                break;
            }
        }
        
    }

    private boolean HaveTestCase() {
        return in.hasNextInt();
    }

    private void readTestCase() {
        rows = in.nextInt();
        columns = in.nextInt();
        picture = new boolean[rows][columns];
        for(int row = 0; row < rows; ++row) {
            String line = in.nextLine();
            picture[row] = parseRow(line);
        }
    }

    public void solve() {
        stars = 0;
        for(int row = 0; row < rows; ++row) {
            for(int column = 0; column < columns; ++column) {
                if(picture[row][column]) {
                    ++stars;
                    erase(row, column);
                }
            }
        }
    }

    private void writeTestCase() {
        out.print("Case " + caseCounter + ": " + stars);
    }

    private void erase(int row, int column) {
        picture[row][column] = false;
        //Need to account for overflowing on buffer
        if (row + 1 < rows && picture[row+1][column]) {erase(row+1, column); }
        if (row - 1 >= 0 && picture[row-1][column]) {erase(row-1, column); }
        if (column + 1 < columns && picture[row][column+1]) {erase(row, column+1); }
        if (column - 1 >= 0 && picture[row][column-1]) {erase(row, column-1); }
        
    }

    boolean[] parseRow(String string) {
        boolean[] row = new boolean[string.length()];
        for(int column = 0; column < string.length(); ++column) {
            row[column] = (string.charAt(column) == '-');
        }
        return row;
    }
}
