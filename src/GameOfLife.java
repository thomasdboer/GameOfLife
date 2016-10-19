//Thomas Willems, Thomas den boer 10-2016
//This program plays the "Game of Life"

//Import some stuff
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import javax.swing.*;
import java.io.*;

public class GameOfLife implements ActionListener {
    //Instance variables
    int row;
    int col;
    Cell[][] grid = new Cell[row][col];
    Cell[][] grid2 = new Cell[row][col];
    String birthFileName = "src\\birth.txt";
    File birthFile = new File(birthFileName);
    Scanner sc;
    int width = 900;
    int height = 900;
    JFrame frame = new JFrame("Game Of Life");
    JPanel panel = new JPanel();
    Timer timer;

    void initJFrame(){
        readInitial();

        timer = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                nextGeneration();
            }
        });

        JPanel buttons = new JPanel();
        frame.add(buttons, BorderLayout.SOUTH);

        JButton start = new JButton("Start");
        JButton stop = new JButton("Stop");
        JButton nextgen = new JButton("Next Generation");

        buttons.add(start);
        buttons.add(stop);
        buttons.add(nextgen);

        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                timer.start();
            }
        });
        stop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                timer.stop();
            }
        });
        nextgen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                nextGeneration();

            }
        });


        frame.add(panel);
        panel.setPreferredSize(new Dimension(width, height));
        panel.setLayout(new GridLayout(row, col, 4, 4)); // the 2, 2 stand for the gaps between cells
        panel.setBackground(Color.BLACK);
        for(int i=0; i< row; i++){
            for(int j=0; j<col; j++){
                grid[i][j].setOpaque(true);
                panel.add(grid[i][j]);
            }
        }
        frame.setSize(width, height);
        frame.setVisible(true);
    }




    void calculateNumNeighbours() {
        //Iterate over grid
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                //Create an integer to save amount of found neighbouring cells
                int neighbours = 0;
                //This int resets to 0 for every grid position
                //Iterate over positions around chosen grid position
                for (int k = (i - 1); k <= (i + 1); k++) {
                    for (int l = (j - 1); l <= (j + 1); l++) {
                        try {
                            if (grid[k][l].isAlive()) {
                                neighbours++;
                            }
                            //Make sure chosen grid position is not included
                            if (k == i && j == l) {
                                neighbours--;
                            }
                        }
                        catch (ArrayIndexOutOfBoundsException ex) {

                            }
                        }
                    }
                //Set amount of neighbours for chosen grid position
                grid[i][j].setNumNeighbours(neighbours);
                grid2[i][j].setNumNeighbours(neighbours);
            }
        }
    }

    void readInitial() {
        //Open birthFile and handle exceptions
        try {
            sc = new Scanner(birthFile);
        }
        catch (FileNotFoundException ex) {
            System.out.println("Did not find file!");
        }
        //Read values for amount of columns and rows and throw exceptions if not given
        try{row=sc.nextInt();}catch(NullPointerException e){System.out.println("No row given in file!");};
        try{col=sc.nextInt();}catch(NullPointerException e){System.out.println("No column given in file!");};
        //Initialize the grid to the size given
        grid = new Cell[row][col];
        grid2 = new Cell[row][col];
        //Create a Cell object at every grid position
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                grid[i][j] = new Cell();
                grid2[i][j] = new Cell();
            }
        }
        //Read grid and set cell states
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                String next = sc.next();
                if (next.equals("*")) {
                    grid[i][j].setAlive(true);
                    grid2[i][j].setAlive(true);
                } else if (next.equals(".")) {
                    grid[i][j].setAlive(false);
                    grid2[i][j].setAlive(false);
                }
            }
        }
    }

    void nextGeneration() {
        calculateNumNeighbours();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                grid2[i][j].update();
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                grid[i][j].setAlive(grid2[i][j].isAlive());
            }
        }
    }




    public static void main(String[] args) {
        new GameOfLife().readInitial();
        new GameOfLife().initJFrame();
    }


    public void actionPerformed(ActionEvent actionEvent) {

    }
}

