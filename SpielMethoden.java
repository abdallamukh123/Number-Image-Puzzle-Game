import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import java.util.Random;
/**.
 * 
 * @author (Abdalla Mukhaimar) 
 * @MN (1615092)
 */

public class SpielMethoden
{
    private  int size ;
    private  int shuffleArray[];
    private static final Random RANDOM = new Random();
    public int[][] zahlenIndex2DArray;
    private int leeresPositionY ;
    private int leeresPositionX ;
    private boolean spielIsBeendet = true;
    private JLabel labelArray[][];
    JFrame frame ;
    

    public SpielMethoden(int size,int[][] zahlenIndex2DArray,int leeresPositionX,int leeresPositionY )
    {
        this.size=size;
        this.shuffleArray=shuffleArray;
        this.zahlenIndex2DArray=zahlenIndex2DArray;
        this.leeresPositionX=leeresPositionX;
        this.leeresPositionY=leeresPositionY;
    }
    public SpielMethoden(int size,JLabel [][]labelArray,int zahlenIndex2DArray[][],int leeresPositionX,int leeresPositionY  )
    {
        this.size=size;
        this.shuffleArray=shuffleArray;
        this.zahlenIndex2DArray=zahlenIndex2DArray;
        this.leeresPositionX=leeresPositionX;
        this.leeresPositionY=leeresPositionY;
        this.labelArray=labelArray;
    }

    public void shuffle() {

        shuffleArray = new int[size * size];
        int n = size * size - 1;
        for (int i = 0; i <= n; i++) {
            if (n==i)
            {
                shuffleArray[i] = -1; 
            }
            else
            {
                shuffleArray[i] = i;
            }

        }

        while (n > 1) {
            int r = RANDOM.nextInt(n--);
            int tmp = shuffleArray[r];
            shuffleArray[r] = shuffleArray[n];
            shuffleArray[n] = tmp;
        }
        int index = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                zahlenIndex2DArray[i][j] = shuffleArray[index];
                index++;
            }
        }

    }

    public  boolean isSolved() {
        if (leeresPositionY != size - 1 && leeresPositionX != size - 1) 
        {
            return false;
        }
        if (zahlenIndex2DArray[0][0] != 0)
        {
            return false;
        }
        int index = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (zahlenIndex2DArray[i][j] != index) {
                    if (i==size-1 && j==size-1 && zahlenIndex2DArray[size-1][size-1] == -1 ){}
                    else
                    {
                        return false;
                    }

                }
                index++;
            }
        }

        return true;
    }

    private boolean isSolvable() {
        int count = 0;
        for (int i = 0; i < size*size-1; i++) {
            for (int j = 0; j < i; j++) {
                if (shuffleArray[j] > shuffleArray[i])
                    count++;
            }
        }

        return count % 2 == 0;
    }

    public  void spielBeginnen() {
        do {
            reset();
            shuffle();
        } while (!isSolvable());

        spielIsBeendet = false;
    }

    private void reset() {
        int index = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i==size-1 && j==size-1 )
                {
                    zahlenIndex2DArray[i][j] = -1;
                }
                else
                {
                    zahlenIndex2DArray[i][j] = index++;
                }

            }
        }
        leeresPositionX = size - 1;
        leeresPositionY = size - 1;

    }

}
