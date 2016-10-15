import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

class Cell extends JLabel{

    boolean alive;
    int numNeighbours;

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
        if(numNeighbours<2){
            setAlive(false);
        }
        else if(numNeighbours>3){
            setAlive(false);
        }
        else if(numNeighbours==2||numNeighbours==3){
            setAlive(true);
        }
        else if(!alive&&numNeighbours==3){
            setAlive(true);
        }
    }

}
