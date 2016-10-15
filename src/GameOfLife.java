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

    void initGrid() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                grid[i][j] = new Cell();
            }
        }
    }
    void calculateNumNeighbours(){
                for(int i=0; i < grid.length; i++){
                    for(int j=0; j< grid[i].length;j++){
                        if(grid[i][j-1].isAlive()){
                            grid[i][j].setNumNeighbours(1);
                        }
                    }
                }
            }
    void readInitial(){
        try{
            sc = new Scanner(birthFile);
        }catch(FileNotFoundException ex) {
            System.out.println("Whoops, couldn't find the birth file");
        }
        row = sc.nextInt();
        col = sc.nextInt();
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
}
