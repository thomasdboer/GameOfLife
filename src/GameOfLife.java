//Thomas Willems, Thomas den boer 10-2016
//This program plays the "Game of Life"

//Import some stuff
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.io.*;

public class GameOfLife{
    //Instance variables
    int row;
    int col;
    Cell[][] grid = new Cell[row][col];
    String birthFileName = "src\\birth.txt";
    File birthFile = new File(birthFileName);
    Scanner sc;
    int width = 700;
    int length = 700;
    private Object g;

    void initJFrame(){
        JFrame frame = new JFrame("Game Of Life");
        JPanel panel = new JPanel(new GridLayout(width/20,length/20));
        frame.add(panel);
        frame.setSize(width, length);
        frame.setVisible(true);




    }



    void calculateNumNeighbours() {
        //Iterate over grid
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                //Create an integer to save amount of found neighbouring cells
                int neighbours = 0;
                //This int resets to 0 for every grid position
                //Iterate over positions around chosen grid position
                for (int k = (i - 1); k <= (i + 1); k++) {
                    for (int l = (j - 1); l <= (j + 1); l++) {
                        if (grid[k][l].isAlive()) {
                            neighbours++;
                        }
                        //Make sure chosen grid position is not included
                        if (k == i && j == l) {
                            neighbours--;
                        }
                    }
                }
                //Set amount of neighbours for chosen grid position
                grid[i][j].setNumNeighbours(neighbours);
            }
        }
    }

    void readInitial() {
        //Open birthFile and handle exceptions
        try {
            sc = new Scanner(birthFile);
        } catch (FileNotFoundException ex) {
            System.out.println("Did not find file!");
        }
        //Read values for amount of columns and rows and throw exceptions if not given
        try{row=sc.nextInt();}catch(NullPointerException e){System.out.println("No row given in file!");};
        try{col=sc.nextInt();}catch(NullPointerException e){System.out.println("No column given in file!");};
        //Initialize the grid to the size given
        grid = new Cell[row][col];
        //Create a Cell object at every grid position
        for (int i = 0; i < (row-1); i++) {
            for (int j = 0; j < (col-1); j++) {
                grid[i][j] = new Cell();
            }
        }
        //Read grid and set cell states
        for (int i = 0; i < (row-1); i++) {
            for (int j = 0; j < (col-1); j++) {
                String next = sc.next();
                if (next.equals("*")) {
                    grid[i][j].setAlive(true);
                } else if (next.equals(".")) {
                    grid[i][j].setAlive(false);
                }
            }
        }
    }

    void nextGeneration() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j].update();
            }
        }
    }


   
    public void draw(Graphics g) {
        int k;
        int rows = length/20;
        int columns = width/20;


        int htOfRow = length / (rows);
        for (k = 0; k < (rows); k++)
            g.drawLine(0, k * htOfRow , width, k * htOfRow );

        int wdOfRow = width / (columns);
        for (k = 0; k < columns; k++)
            g.drawLine(k*wdOfRow , 0, k*wdOfRow , length);
    }

    public static void main(String[] args) {
        new GameOfLife().initJFrame();
        new GameOfLife().readInitial();
        new GameOfLife().calculateNumNeighbours();
        new GameOfLife().nextGeneration();
        new GameOfLife().draw();

    }
}

