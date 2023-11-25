import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.GridLayout;
/**.
 * 
 * @author (Abdalla Mukhaimar) 
 * @MN (1615092)
 */
public class BildSlidungsGame extends JPanel  {
    
    private int size;
    private JLabel labelArray[][];
    private JPanel panel=new JPanel();
    private int leeresPositionX;
    private int leeresPositionY;
    private  int zahlenIndex2DArray[][];
    private BufferedImage[][] zweiDImageArray;
    private BufferedImage EinDimImageArray[];
    private JFrame frame;
    public int []shuffleArray;
    private static final Random RANDOM = new Random();

    public BildSlidungsGame()
    {
       
    }

    public BildSlidungsGame(int size, JFrame frame,boolean var) {
        panel=this;
        this.size=size;
        this.frame=frame;
        shuffleArray = new int[size * size];
        EinDimImageArray=new BufferedImage [size*size];
        leeresPositionX=size-1;
        leeresPositionY=size-1;
        labelArray = new JLabel[size][size];
        zahlenIndex2DArray=new int[size][size];
        zweiDImageArray=new BufferedImage[size][size];
        this.setLayout(new GridLayout(size, size, 5, 5));

        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        // filtering files
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "png");
        file.addChoosableFileFilter(filter);
        int res = file.showSaveDialog(null);
        
        if (res == JFileChooser.APPROVE_OPTION)
        {
            File selFile = file.getSelectedFile();
            String path = selFile.getAbsolutePath();
            EinDimImageArray=getSubimage(read(selFile),size);
        }
        if(!var)
        {
            zahlenIndex2DArray=NumbersMausAdapter.getZahlenIndexArray();
        }
        else 
        {
            SpielMethoden spielMethoden =new SpielMethoden(size,labelArray,zahlenIndex2DArray,leeresPositionX,leeresPositionY );
            spielMethoden.spielBeginnen();
        }

        for(int i=0;i<size;i++) 
        {
            for(int j=0;j<size;j++)
            {
                if(zahlenIndex2DArray[i][j]==-1)
                {
                    labelArray[i][j]=new JLabel(" "); 
                    this.add(labelArray[i][j]); 
                    leeresPositionX=j;
                    leeresPositionY=i;
                    //break;
                }
                else
                {
                    zweiDImageArray[i][j]=EinDimImageArray[zahlenIndex2DArray[i][j]];
                    labelArray[i][j]=new JLabel(new ImageIcon(zweiDImageArray[i][j]));
                    this.add(labelArray[i][j]);
                }
            }
        }
        ImageMausAdapter imageMausAdapter=new ImageMausAdapter(frame,panel,size,leeresPositionX,leeresPositionY,zweiDImageArray,zahlenIndex2DArray,labelArray);
        addMouseListener(imageMausAdapter);

    }

    public static BufferedImage read(File input)
    {
        BufferedImage  image= new BufferedImage (300,300,BufferedImage.TYPE_INT_RGB);
        try
        {
            image= ImageIO.read(input);
        }
        catch (java.io.IOException ioe)
        {
            ioe.printStackTrace();
        }
        BufferedImage scaledImage = new BufferedImage(600, 600, BufferedImage.TYPE_INT_RGB);
        scaledImage.getGraphics().drawImage(image,0,0, 600, 600, null);
        return scaledImage;
    }

    public static BufferedImage [] getSubimage(BufferedImage image,int size)
    {
        BufferedImage EinDimImageArray[] = new BufferedImage[size*size];
        int index=0;
        for(int i=0;i<size;i++) 
        {
            for(int j=0;j<size;j++)
            {
                EinDimImageArray[index++] =image.getSubimage((600/size)*j, (600/size)*i, 600/size, 600/size);
            }
        }
        return EinDimImageArray;
    }
}

