
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.BorderLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
/**.
 * 
 * @author (Abdalla Mukhaimar) 
 * @MN (1615092)
 */

public class SlidingsGame extends JFrame {

    private JPanel menuePanel;
    private JButton button;
    private JLabel label;
    private Image source;
    private Image image;
    private int size;

    private int selectedSize;
    public boolean imageGame = false;
    JRadioButton size3Button;
    JRadioButton size4Button;
    JRadioButton size5Button;
    ButtonGroup group;
    JCheckBox selectImageGame;
    JButton spielStarten;
    JFrame frame;
    public NumbersSlidingGame numbersPanel;
    public BildSlidungsGame bildPanel;
    public boolean var;
    public boolean letzteSpielIstImageSlidingGame;

    public SlidingsGame(int size) {
        frame=this;
        numbersPanel=new NumbersSlidingGame(3,this);
        //this.add(numbersPanel);

        menuePanel = new JPanel();
        this.selectedSize=size;
        setLayout(new BorderLayout());
        add(menuePanel, BorderLayout.NORTH);

        menuePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuePanel.setBackground(Color.LIGHT_GRAY);

        // Radio Buttons
        size3Button = new JRadioButton("Size:3");
        size4Button = new JRadioButton("Size:4");
        size5Button = new JRadioButton("Size:5");
        group = new ButtonGroup();
        // add Radio Buttons to one group
        group.add(size3Button);
        group.add(size4Button);
        group.add(size5Button);
        // select default radio button
        size3Button.setSelected(true);

        // checkbox T or F
        selectImageGame = new JCheckBox("Image Game?");

        // button SpielStarten
        spielStarten = new JButton("Spiel Starten");

        menuePanel.add(size3Button);
        menuePanel.add(size4Button);
        menuePanel.add(size5Button);
        menuePanel.add(selectImageGame);
        menuePanel.add(spielStarten);

        size3Button.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    selectedSize = 3;
                }
            });

        size4Button.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    selectedSize = 4;
                }
            });

        size5Button.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {selectedSize = 5; }
            });

        selectImageGame.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    imageGame = !imageGame;
                }
            });
        
            if(imageGame){letzteSpielIstImageSlidingGame=true;}else{letzteSpielIstImageSlidingGame=false;}

        spielStarten.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { 
                    //frame.getContentPane().remove(panel);

                    if(var)
                    {
                        frame.getContentPane().remove(bildPanel);

                        var=false;

                    }
                    if(!var)
                    {
                        frame.getContentPane().remove(numbersPanel);

                    }
                    if(imageGame) 
                    { 

                        bildPanel =new BildSlidungsGame(selectedSize, frame,letzteSpielIstImageSlidingGame); 
                        frame.add(bildPanel);
                        var=true;

                    }
                    else 
                    {
                        numbersPanel =new NumbersSlidingGame(selectedSize,frame);//, 550); }
                        frame.add(numbersPanel);

                    }

                    setVisible(true);

                }
            });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setTitle("Abdalla_Mukhaimar_1615092_OOP_Prof_Vendl");
        setResizable(false);
        setVisible(true);
    }

}

