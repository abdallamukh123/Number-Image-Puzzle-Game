
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Random;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
/**.
 * 
 * @author (Abdalla Mukhaimar) 
 * @MN (1615092)
 */

public class NumbersMausAdapter extends java.awt.event.MouseAdapter 
{
    private static int size;
    private int leeresPositionX;
    private int leeresPositionY;
    private static int zahlenIndex2DArray[][];
    private JLabel label[][];
    private JPanel panel;
    private JFrame frame ;
    public static int[][] zahlenIndexArray;

    public NumbersMausAdapter(){}

    public NumbersMausAdapter(JFrame frame,JPanel panel,int size, int leeresPositionX,int leeresPositionY, int zahlenIndex2DArray[][],JLabel label[][]  )
    {
        this.frame=frame;
        this.panel=panel;

        this.leeresPositionX=leeresPositionX;
        this.leeresPositionY=leeresPositionY;

        this.zahlenIndex2DArray=zahlenIndex2DArray;
        this.label=label;
        this.size=zahlenIndex2DArray[0].length;

    }

    public void mousePressed(MouseEvent e) {
        SpielMethoden spielMethoden =new SpielMethoden(size,zahlenIndex2DArray,leeresPositionX,leeresPositionY );
        int xDim = e.getX();
        int yDim = e.getY();
        int quadrateDim = (600 / size);

        int qXPos = (xDim / quadrateDim);
        int qYPos = (yDim / quadrateDim);

        if (qXPos - 1 == leeresPositionX && qYPos == leeresPositionY)
        {
            String temp = label[qYPos][qXPos].getText();
            label[leeresPositionY][leeresPositionX].setText(temp);
            label[qYPos][qXPos].setText("");

            zahlenIndex2DArray[leeresPositionY][leeresPositionX]=zahlenIndex2DArray[qYPos][qXPos];
            zahlenIndex2DArray[qYPos][qXPos]=-1;

            leeresPositionX = qXPos;
            leeresPositionY = qYPos;

        }
        else if (qXPos + 1 == leeresPositionX && qYPos == leeresPositionY) {
            String temp = label[qYPos][qXPos].getText();
            label[leeresPositionY][leeresPositionX].setText(temp);
            label[qYPos][qXPos].setText("");

            zahlenIndex2DArray[leeresPositionY][leeresPositionX]=zahlenIndex2DArray[qYPos][qXPos];
            zahlenIndex2DArray[qYPos][qXPos]=-1;

            leeresPositionX = qXPos;
            leeresPositionY = qYPos;

        }
        else if (qXPos == leeresPositionX && qYPos - 1 == leeresPositionY) {
            String temp = label[qYPos][qXPos].getText();
            label[leeresPositionY][leeresPositionX].setText(temp);
            label[qYPos][qXPos].setText("");

            zahlenIndex2DArray[leeresPositionY][leeresPositionX]=zahlenIndex2DArray[qYPos][qXPos];
            zahlenIndex2DArray[qYPos][qXPos]=-1;

            leeresPositionX = qXPos;
            leeresPositionY = qYPos;

        }
        else if (qXPos == leeresPositionX && qYPos + 1 == leeresPositionY) {
            String temp = label[qYPos][qXPos].getText();
            label[leeresPositionY][leeresPositionX].setText(temp);
            label[qYPos][qXPos].setText("");

            zahlenIndex2DArray[leeresPositionY][leeresPositionX]=zahlenIndex2DArray[qYPos][qXPos];
            zahlenIndex2DArray[qYPos][qXPos]=-1;

            leeresPositionX = qXPos;
            leeresPositionY = qYPos;

        }
        if (spielMethoden.isSolved()) {
            int index=JOptionPane.showOptionDialog(null, "Sie haben gewonnen","Spiel ist Beendet",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.WARNING_MESSAGE, null, 
                    new String[]{"Rest", "Exit"}, "B");
            if(index==1)
            {
                System.exit(0);
            }else
            {
                frame.setVisible(false);
                spielMethoden.spielBeginnen();
                new SlidingsGame(3);
            }

        }
        panel.repaint();
    }

    static  int[][] getZahlenIndexArray()
    {
        zahlenIndexArray=new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                zahlenIndexArray[i][j] = zahlenIndex2DArray[i][j];

            }
        }
        return zahlenIndexArray;
    }

}
