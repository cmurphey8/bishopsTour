//*******************************************************************
//
//   File: ChessBoard.java
//
//*******************************************************************

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class ChessGraphics {

    // chess constants
    static final int EDGE = 10;
    static final int SIZE = 60;

    // init Panel
    static final int WIDTH = 2 * EDGE + ChessPiece.N * SIZE;
    static final int HEIGHT = 2 * EDGE + ChessPiece.N * SIZE;
    
    static DrawingPanel panel = new DrawingPanel(WIDTH, HEIGHT);
    static Graphics2D g = panel.getGraphics();

    // enable double buffering
    static BufferedImage offscreen = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    static Graphics2D osg = offscreen.createGraphics();

    
    // track last move to update from
    static int rowOld;
    static int colOld;
    static Color lineColor = Color.GREEN;

    public static int SLEEP = 100;

    // draw a line from the last knight to the next and SLEEP
    public static void updateBoard(int row, int col) {        
        osg.setColor(lineColor);
        osg.drawLine(scaleIndex(colOld) + SIZE / 2, scaleIndex(rowOld) + SIZE / 2, scaleIndex(col) + SIZE / 2, scaleIndex(row) + SIZE / 2);
        
        rowOld = row;
        colOld = col;
        
        g.drawImage(offscreen, 0, 0, null);   
        panel.sleep(SLEEP);    
    }

    // init knight image && draw the board
    public static void initBoard(int row, int col) 
    {
        osg.setColor(Color.DARK_GRAY);
        osg.fillRect(0, 0, WIDTH, HEIGHT);

        drawGrid(EDGE, EDGE, ChessPiece.N / 2, SIZE);
        g.drawImage(offscreen, 0, 0, null);  
        
        rowOld = row;
        colOld = col;
    }

    // make checkered grid
    public static void drawGrid(int x, int y, int numPairs, int SIZE) {
        for (int i = 0; i < 2 * numPairs; i+=2) {
            drawRow(x, y + SIZE * i, Color.WHITE, Color.GRAY);
            drawRow(x, y + SIZE * (i + 1), Color.GRAY, Color.WHITE);
        }
    }

    // make alternating boxes in a row
    public static void drawRow(int x, int y, Color first, Color second) {
        for (int i = 0; i < ChessPiece.N; i+=2) {
            osg.setColor(first);
            osg.fillRect(x + SIZE * i, y, SIZE, SIZE);
            osg.setColor(second);
            osg.fillRect(x + SIZE *(i + 1), y, SIZE, SIZE);
        }
    }

    // draw a chess piece in the current cell
    public static void drawPiece(BufferedImage image, int height, int width, int row, int col) {
        osg.drawImage(image, scalePiece(col, width), scalePiece(row, height), null);
    }

    // scale from index to CENTERED panel coordinates
    public static int scalePiece(int index, int dimSize) {
        return scaleIndex(index) + (int) ((SIZE - dimSize) / 2.0);
    }

    // scale from index to panel coordinates
    public static int scaleIndex(int index)
    {
        return index * SIZE + EDGE;
    }
}   