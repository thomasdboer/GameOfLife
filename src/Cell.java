//Thomas Willems, Thomas den Boer
//15-10-2016
//This class represents a cell within the Game of Life
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

class Cell extends JLabel{

    boolean alive;
    int numNeighbours = 0;


    //isAlive method
    boolean isAlive(){
        return alive;
    }

    //setAlive method
    void setAlive(boolean state){
        //check if given state is true and set alive appropriately
        if(state){
            alive = true;
        }
        else{
            alive = false;
        }
    }

    void setNumNeighbours(int n){
        //amount of alive neighbouring cells
        numNeighbours = n;
    }

    void update(){
        //If an alive cell has less than two neighbours, it dies
        if(alive && numNeighbours<2){
            setAlive(false);
        }
        //If an alive cell has more than three neighbours, it dies
        else if(alive && numNeighbours>3){
            setAlive(false);
        }
        //If an alive cell has two or three neighbours, it will live on
        else if(alive && (numNeighbours==2||numNeighbours==3)){
            setAlive(true);
        }
        //If a dead cell has three neighbours, it comes back to life
        else if(!alive && numNeighbours==3){
            setAlive(true);
        }
    }

}
