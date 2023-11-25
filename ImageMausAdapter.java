
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
 * @version (1615092)
 */

public class ImageMausAdapter extends java.awt.event.MouseAdapter 
{
    private int size;
    private int leeresPositionX;
    private int leeresPositionY;
    private BufferedImage imageArray[][];
    private int zahlenIndex2DArray[][];
    private JLabel labelArray[][];
    private JPanel panel;
    private JFrame frame;

    
    public ImageMausAdapter(JFrame frame,JPanel panel,int size, int leeresPositionX,int leeresPositionY,BufferedImage imageArray[][], int zahlenIndex2DArray[][],JLabel labelArray[][]  )
    {
        this.frame=frame;
        this.panel=panel;
        this.size=size;
        this.leeresPositionX=leeresPositionX;
        this.leeresPositionY=leeresPositionY;
        this.imageArray=imageArray;
        this.zahlenIndex2DArray=zahlenIndex2DArray;
        this.labelArray=labelArray;

    }

    public void mousePressed(MouseEvent e) 
    {
        int xDim = e.getX();
        int yDim = e.getY();
        int quadrateDim = (600 / size);

        int qXPos = (xDim / quadrateDim);
        int qYPos = (yDim / quadrateDim);

        if (qXPos - 1 == leeresPositionX && qYPos == leeresPositionY) 
        {
            BufferedImage temp2=imageArray[leeresPositionY][leeresPositionX];
            BufferedImage temp = imageArray[qYPos][qXPos];
            imageArray[leeresPositionY][leeresPositionX]=temp;
            imageArray[qYPos][qXPos]=temp2;

            zahlenIndex2DArray[leeresPositionY][leeresPositionX]=zahlenIndex2DArray[qYPos][qXPos];
            zahlenIndex2DArray[qYPos][qXPos]=-1;

            int tempX=leeresPositionX;
            leeresPositionX = qXPos;
            qXPos=tempX;
            
            int tempY=leeresPositionY;
            leeresPositionY = qYPos;
            qYPos=tempY;
            
            repaintMethod(imageArray,leeresPositionX,leeresPositionY,qXPos,qYPos);

        }
        else if (qXPos + 1 == leeresPositionX && qYPos == leeresPositionY)
        {
            BufferedImage temp2=imageArray[leeresPositionY][leeresPositionX];
            BufferedImage temp = imageArray[qYPos][qXPos];
            imageArray[leeresPositionY][leeresPositionX]=temp;
            imageArray[qYPos][qXPos]=temp2;

            zahlenIndex2DArray[leeresPositionY][leeresPositionX]=zahlenIndex2DArray[qYPos][qXPos];
            zahlenIndex2DArray[qYPos][qXPos]=-1;

            int tempX=leeresPositionX;
            leeresPositionX = qXPos;
            qXPos=tempX;
            
            int tempY=leeresPositionY;
            leeresPositionY = qYPos;
            qYPos=tempY;
            
            repaintMethod(imageArray,leeresPositionX,leeresPositionY,qXPos,qYPos);

        }
        else if (qXPos == leeresPositionX && qYPos - 1 == leeresPositionY) {
            BufferedImage temp2=imageArray[leeresPositionY][leeresPositionX];
            BufferedImage temp = imageArray[qYPos][qXPos];
            imageArray[leeresPositionY][leeresPositionX]=temp;
            imageArray[qYPos][qXPos]=temp2;

            zahlenIndex2DArray[leeresPositionY][leeresPositionX]=zahlenIndex2DArray[qYPos][qXPos];
            zahlenIndex2DArray[qYPos][qXPos]=-1;
            
            int tempX=leeresPositionX;
            leeresPositionX = qXPos;
            qXPos=tempX;
            
            int tempY=leeresPositionY;
            leeresPositionY = qYPos;
            qYPos=tempY;
            
            repaintMethod(imageArray,leeresPositionX,leeresPositionY,qXPos,qYPos);

        }
        else if (qXPos == leeresPositionX && qYPos + 1 == leeresPositionY) 
        {
            BufferedImage temp2=imageArray[leeresPositionY][leeresPositionX];
            BufferedImage temp = imageArray[qYPos][qXPos];
            imageArray[leeresPositionY][leeresPositionX]=temp;
            imageArray[qYPos][qXPos]=temp2;
           

            zahlenIndex2DArray[leeresPositionY][leeresPositionX]=zahlenIndex2DArray[qYPos][qXPos];
            zahlenIndex2DArray[qYPos][qXPos]=-1;
            int tempX=leeresPositionX;
            leeresPositionX = qXPos;
            qXPos=tempX;
            int tempY=leeresPositionY;
            leeresPositionY = qYPos;
            qYPos=tempY;
            repaintMethod(imageArray,leeresPositionX,leeresPositionY,qXPos,qYPos);

        }
        SpielMethoden method =new SpielMethoden(size,zahlenIndex2DArray,leeresPositionX,leeresPositionY );

        if (method.isSolved()) {
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
                method.spielBeginnen();
                new SlidingsGame(3);
            }
        }
        panel.repaint();

    }

    public void repaintMethod(BufferedImage[][] imageArray, int lx , int ly, int xnew, int ynew)
    {

        Icon temp = labelArray[ly][lx].getIcon();
        labelArray[ynew][xnew].setIcon(temp);
        labelArray[ly][lx].setIcon(null);

        panel.revalidate();
        panel.repaint();

    }
}

