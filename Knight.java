//*******************************************************************
//
//   File: KnightsTour.java
//
//   Dependancies: ChessTour.java, ChessPiece.java, DrawingPanel.java
//
//*******************************************************************

import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;

public class Knight extends ChessPiece {
    
    public Knight() throws IOException {
        // call ChessPiece constructor
        super();

        // define moves
        int[] xTmp = {1, -1, 2, 2, 1, -1, -2, -2};
        int[] yTmp = {2, 2, 1, -1, -2, -2, 1, -1};

        // define numMoves
        numMoves = xTmp.length;

        // copy into ChessPiece arrays
        deepCopy(xTmp, yTmp);

        // define Sprite size
        spHeight = 50;
        spWidth = 35;

        // draw Sprite
        sprite = ImageIO.read(new File("knight.png"));
        ChessGraphics.drawPiece(sprite, spHeight, spWidth, spRow, spCol);
    }

    // WE DO: deep copy into protected parent class vars xDiff, yDiff
    protected void deepCopy(int[] xTmp, int[] yTmp) {   
        
    }

    // check for a complete knight's tour
    public boolean allOnes() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!chess[i][j]) return false;
            }
        }
        return true;
    }

}   
