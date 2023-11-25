import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Random;

import javax.swing.border.Border;
import javax.swing.border.BevelBorder;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**.
 * 
 * @author (Abdalla Mukhaimar) 
 * @MN (1615092)
 */

public class NumbersSlidingGame extends JPanel {
    private int size;
    private int qIndex;
   
    private static final Random RANDOM = new Random();
    public int[][] zahlenIndex2DArray;
    public int[][] array2;
    private int[] shuffleArray;
    private int quadrateDim;
    private Border bevelBorder;
    private int leeresPositionX;
    private int leeresPositionY;
    private boolean spielIsBeendet = true;
    JFrame frame;

    public NumbersSlidingGame() {
        
    }

    public NumbersSlidingGame(int size, JFrame frame) {
        this.size = size;
        this.frame = frame;
        leeresPositionX = size - 1;
        leeresPositionY = size - 1;
        zahlenIndex2DArray = new int[size][size];
        array2 = new int[size][size];
        bevelBorder = BorderFactory.createBevelBorder(BevelBorder.RAISED);
        this.setBackground(Color.WHITE);
        int index = 0;
        int temp = 0;
        SpielMethoden spielMethoden =new SpielMethoden(size,zahlenIndex2DArray,leeresPositionX,leeresPositionY );
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i != leeresPositionY || j != leeresPositionX) {
                    zahlenIndex2DArray[i][j] = index++;
                }
            }
        }
        spielMethoden.spielBeginnen();
        JLabel label[][] = new JLabel[size][size];
        setLayout(new GridLayout(size, size, 5, 5));
        
        index = 0;
        int K = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (j == leeresPositionX && i == leeresPositionY) {
                    label[leeresPositionY][leeresPositionX] = new JLabel(" ", SwingConstants.CENTER);
                    label[i][j].setBackground(Color.LIGHT_GRAY);
                    label[i][j].setForeground(Color.blue);
                    label[i][j].setOpaque(true);
                    label[i][j].setFont(new Font("SansSerif", Font.BOLD, 55));
                    add(label[size - 1][size - 1]);
                } else {
                    label[i][j] = new JLabel(String.valueOf(zahlenIndex2DArray[i][j]), SwingConstants.CENTER);
                    label[i][j].setBackground(Color.LIGHT_GRAY);
                    label[i][j].setBorder(bevelBorder);
                    label[i][j].setForeground(Color.blue);
                    add(label[i][j]);
                    label[i][j].setFont(new Font("SansSerif", Font.BOLD, 60));
                    label[i][j].setOpaque(true);
                    K++;

                }

            }

        }
        NumbersMausAdapter numbersMausAdapter=new NumbersMausAdapter(frame,this,size,leeresPositionX,leeresPositionY,zahlenIndex2DArray,label);
        addMouseListener(numbersMausAdapter);

    }

}

