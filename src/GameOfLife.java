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
    String birthFileName = "birth.txt";
    File birthFile = new File(birthFileName);
    Scanner sc;

    void calculateNumNeighbours(){
        //Iterate over grid
        for(int i=0; i < grid.length; i++){
            for(int j=0; j< grid[i].length;j++){
                //Create an integer to save amount of found neighbouring cells
                int neighbours = 0;
                //This int resets to 0 for every iteration
                //Iterate over positions around chosen grid position
                for(int k=(i-1); k<=(i+1); k++){
                    for(int l=(j-1); l<=(j+1);l++){
                        if(grid[k][l].isAlive()){
                            neighbours++;
                        }
                        //Make sure chosen grid position is not included
                        if(k==i&&j==l){
                            neighbours--;
                        }
                    }
                }
                //Set amount of neighbours for chosen grid position
                grid[i][j].setNumNeighbours(neighbours);
            }
        }
    }
    void readInitial(){
        //Open birthFile and handle exceptions
        try{
            sc = new Scanner(birthFile);
        }catch(FileNotFoundException ex) {
        }
        //Read values for amount of columns and rows
        row = sc.nextInt();
        col = sc.nextInt();
        //Create a Cell object at every grid position
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                grid[i][j] = new Cell();
            }
        }
        //Read grid and set cell states
        while(sc.hasNext()){
            for(int i = 0; i < col; i++){
                for(int j = 0; j < row; j++){
                    if(sc.next()=="*"){
                        grid[i][j].setAlive(true);
                    }
                    else if(sc.next()=="."){
                        grid[i][j].setAlive(false);
                    }
                }
            }
        }
    }
    void nextGeneration(){
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[i].length;j++){
                grid[i][j].update();
            }
        }
    }

}
