//*******************************************************************
//
//   File: Bishop.java
//
//   Dependancies: ChessTour.java, ChessPiece.java, DrawingPanel.java
//
//   YOU DO: complete the child class!
//
//*******************************************************************

import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;

public class Bishop extends ChessPiece {
    
    public Bishop() throws IOException {
        // call ChessPiece constructor
        super();

        // define number of moves
        numMoves = (N - 1) * 4;

        // init xDiff, yDiff arrays to size numMoves
        xDiff = new int[numMoves];
        yDiff = new int[numMoves];
        
        // assign all moves
        for(int i = 0; i < N - 1; i++) {
            xDiff[i] = i;
            yDiff[i] = i;

            xDiff[N - 1 + i] = -i;
            yDiff[N - 1 + i] = -i;

            xDiff[2*(N - 1) + i] = -i;
            yDiff[2*(N - 1) + i] = i;

            xDiff[3*(N - 1) + i] = i;
            yDiff[3*(N - 1) + i] = -i;
        } 

        // define Sprite size
        spHeight = 53;
        spWidth = 23;

        // draw Sprite
        sprite = ImageIO.read(new File("bishop.png"));
        ChessGraphics.drawPiece(sprite, spHeight, spWidth, spRow, spCol);
    }

    // check for a "complete" bishop's tour
    public boolean allOnes() {
        int count = 0;
        for (int i = 0; i < N; i++) 
            for (int j = 0; j < N; j++) 
                if (chess[i][j]) count++;

        return count == (N * N / 2) ;
    }

}   
