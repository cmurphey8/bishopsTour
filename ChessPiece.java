//*******************************************************************
//
//   File: KnightsTour.java
//
//   Dependancies: ChessBoard.java, DrawingPanel.java
//
//*******************************************************************

import java.awt.image.BufferedImage;

public class ChessPiece {
    
    // declare chess array - true if index has been visited
    public static final int N = 8;
    protected boolean[][] chess = new boolean[N][N];

    // list of all moves for this piece
    protected int[] xDiff;
    protected int[] yDiff;
    protected int numMoves;

    // chess piece image
    protected BufferedImage sprite;
    protected int spHeight;
    protected int spWidth; 

    // chess piece location
    protected int spRow;
    protected int spCol;
    
    public ChessPiece() {
        // find random initial pos to start the tour
        spRow = (int) Math.round(Math.random() * (N - 1));
        spCol = (int) Math.round(Math.random() * (N - 1));

        // update chessboard && init graphics
        chess[spRow][spCol] = true;
        ChessGraphics.initBoard(spRow, spCol);
    }

    // find next move
    public void move() {   
        // choose by max distance from center
        int direction = findDistMethod();

        // update index of knight on chessboard
        spRow += yDiff[direction];
        spCol += xDiff[direction];

        // update chessboard && graphics
        chess[spRow][spCol] = true;

        ChessGraphics.drawPiece(sprite, spHeight, spWidth, spRow, spCol);
        ChessGraphics.updateBoard(spRow, spCol);
    }  

    // check if at least one move from this cell is possible
    public boolean canMove() {
        for (int i = 0; i < numMoves; i++)
            if(isValid(spRow, spCol, i)) return true;

        return false;
    }

    // check if update from kRow, kCol via moveIndex is valid
    private boolean isValid(int kRow, int kCol, int moveIndex) {
        // new row, col after move i update
        int kRowNew = kRow + yDiff[moveIndex];
        int kColNew = kCol + xDiff[moveIndex];

        // is move inbounds and not yet visited??
        // short-circuit eval solution avoids index out of bounds!
        boolean xInBounds = kColNew < N && kColNew >= 0;
        boolean yInBounds = kRowNew < N && kRowNew >= 0;
        return xInBounds && yInBounds && !chess[kRowNew][kColNew];
    }

    // choose next move by max distance from center of the board after update
    private int findDistMethod()
    {
        int bestMove = Integer.MAX_VALUE;
        double maxDist = Double.MIN_VALUE;

        for (int i = 0; i < numMoves; i++) {
            if (isValid(spRow, spCol, i)) {

                // find distance from new location to center of the board
                double compareDist = distToCenter(spRow + yDiff[i], spCol + xDiff[i]);

                // if move has greater distance from center than maxDist...
                boolean isBest = compareDist > maxDist; // update from false
                if (isBest) { 
                    // update bestMove and maxDist
                    bestMove = i;
                    maxDist = compareDist;
                }
            } 
        }
        return bestMove;
    }

    // find distance from row, col to center of board
    private static double distToCenter(int row, int col)
    {
        return Math.sqrt((row + 0.5 - N / 2.0) * (row + 0.5 - N / 2.0)  + (col + 0.5 - N / 2.0)*(col + 0.5 - N / 2.0));
    }

    // find distance from row, col to center of board
    public boolean allOnes(){return true;}
}   
