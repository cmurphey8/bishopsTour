//*******************************************************************
//
//   File: KnightsTour.java
//
//   Dependancies: ChessPiece.java, ChessBoard.java, ChessGraphics.java, DrawingPanel.java
//
//*******************************************************************

import java.io.IOException;

public class ChessTour {
    public static void main(String[] args) throws IOException {
        // run sims until we find a valid knight's tour!
        int count = 0;
        do {
            System.out.println("Round: " + count++);
        } while (!runSim());
        
        System.out.println("got one!");
    }

    // run a knight's tour simulation
    public static boolean runSim() throws IOException {  
        // declare chess board array
        ChessPiece knight = new Knight();

        // continue the tour so long as a valid move is possible
        while(knight.canMove()) knight.move();

        // report whether the tour was completed, or stopped short
        return knight.allOnes();
    }  
}   
