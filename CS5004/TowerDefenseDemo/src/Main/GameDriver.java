package Main;

//Demo code to demonstrate GUI Building with Swing
//3-7-19
//Updated 11/29/2020
//Updated for new course 7/22/2021
//Dr. G

 


import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import java.awt.Graphics;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.border.BevelBorder;
import javax.swing.JTextPane;
import java.util.ArrayList;

//What's a JFrame?
public class GameDriver extends JFrame 
{
    
    private Bullet1 b1;
    private ArrayList<Tower> t1;
    
    private int x; 
    private int y;
    
    //constructor
    public GameDriver() 
    {
        x = 0;
        y = 0;
        
        t1 = new ArrayList<Tower>();
        
        getContentPane().setLayout(null);
        
        JPanel cont_panel = new JPanel();
        cont_panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        cont_panel.setBounds(10, 10, 149, 277);
        getContentPane().add(cont_panel);
        cont_panel.setLayout(null);
        
        JLabel label_lives = new JLabel("Lives : 0");
        label_lives.setBounds(10, 10, 45, 13);
        cont_panel.add(label_lives);
        
        JLabel lbl_money = new JLabel("Money : 0");
        lbl_money.setBounds(10, 33, 45, 13);
        cont_panel.add(lbl_money);
        
        JButton btn_start = new JButton("Start");
    
        btn_start.setBounds(10, 66, 85, 21);
        cont_panel.add(btn_start);
        
        JTextPane textPane_output = new JTextPane();
        textPane_output.setBounds(10, 155, 129, 112);
        cont_panel.add(textPane_output);
        
        JButton btn_addTower = new JButton("Add Tower");
        
        btn_addTower.setBounds(10, 97, 85, 21);
        cont_panel.add(btn_addTower);
        
        JPanel map_panel = new MyCanvas(4,4);
        map_panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                x = e.getX();
                y = e.getY();
                
                System.out.println(x + " " + y);
            }
        });
        
         for (int x = 0; x< 4; x++)
            for (int y = 0; y < 4; y++)
                ((MyCanvas)map_panel).addPicture(x, y,"/tiles/Grnd1.png");
         
        map_panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        map_panel.setBounds(169, 10, 335, 277);
        getContentPane().add(map_panel);
        map_panel.setLayout(null);
        
        
        /*Timer Object*/
        
        Timer timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                
                map_panel.paint(map_panel.getGraphics());
                
                
                if (b1 != null) {
                    b1.drawImage(map_panel.getGraphics());
                }
                
                if (t1 != null)
                    {
                        for (Tower temp : t1)
                        {
                            temp.drawImage(map_panel.getGraphics());
                            temp.fire(map_panel.getGraphics());
                        }
                    }
            }});
        
        
        /*Event Handlers*/
        
        btn_start.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
        });
        
        
        btn_addTower.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {        
                try {
                    t1.add(new Tower(x,y,ImageIO.read(getClass().getResourceAsStream("/towers/Crystal.png")), 50 , 50));
                }
                catch(IOException ie)
                {
                    System.out.println("File Not Found");
                }
            }
            
        });
    
        
        timer.start();
    }
    
    public static void main(String[] args) {
        
        //Examine this and the associated files. See what you can make sense of before we start. 
        
        
        //1. Try to open the file with WindowsBuilder
        //If you need to install, it go to Help -> Marketplace and do a search
        //for "windows builder." To work, the file has to be a JFrame.
        
        
        //2. Uncomment and discuss the code at the bottom of this file
        
        //What is the purpose of this main?
        //Where does this fit in MVC design?
            
        //Separate into break-out rooms and help each other get this far
        
        //3. Explore the different layouts within WB and then add a layout and observe what happens to your code
        //Change to absolute layout for this demonstration
        
        //4. Create two panels 
            //1. Controls - take up about 1/3 of the screen
            //2. Map - take up the rest of the screen
        
        //For this demonstration settle on absolute layout for each panel
        //Make some visual changes:
            //Add a border
        //Compile and see the results
        //What's up with the size?
        //Take a look at the panel boundaries.
        
    
        //Explore the different things you can add to the design
        
        //5. Add labels for lives and money to the control panel
        //6. Add a start button to the control panel
        //7. Add a text pane for output
        
        //Compile and test
        
        
        //8. Notice how you can adjust things in the code or with the GUI
        
        //Try a different layout and see the results
        //How could you make the generated code look better?
        
        //9. Make the program print something with System.out when you click the start button
        
        //Modify this to send it to the text pane instead
        //Add 1 to money each time you click
        //Notice that order matters
        //How are GUIs event driven?
        
        
                //Separate into break-out rooms and help each other get this far
                //Go on to step 11 if there's time

        
        //11. Review MyCanvas
        //12. Make MyCanvas your 2nd JPanel and add this code in the appropriate place
        
        /*
         for (int x = 0; x< 4; x++)
            for (int y = 0; y < 4; y++)
                ((MyCanvas)panel_map).addPicture(x, y,"A.png");
         */
        
        //You can control the size and bounds as well if desired
        //How big should the tiles be for this to work properly?
        //Test out a few different ground tiles
        //Check out:https://opengameart.org/textures/all
        
        //But wait, when was paint called?
        
        //13. Explore the Bullet1 file and associated class structure
        
        //What's a graphics object?
        //What all can you do with a graphics object?
        
        //14. Have the start button draw a Bullet1 object starting at 0,0
        //You'll have to send it the graphics object for the panel you want to draw on
        //Where do you have to create the bullet for it to actually "move"
        //How could we abstract this out?
        //Make a change to bullet1 and observe the results
        
        
        //ANIMATION...
        //What do we need for animation?
        
        //15. Add a timer object and listener
        //Add a print statement to the timer object
        //Start the timer when the user hits start
        //How do we make the animation faster or smoother?
        //Move the bullet drawing to timer
        /*
        Timer timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                
                //do stuff
                
            }});
         */
        
        //16. Make it even better by forcing everything to repaint.
        //panel_map.paint(panel_map.getGraphics());
        
        
        //17. Review everything so far
        
                //Separate into break-out rooms and help each other get this far
                //Go on to step 18 if there's time
        
        //18. Review MapObject, Tower, and MovingObject
        //19. Create an addTower button.
        //When pushed, place a tower on the map at position 10,10
        //Don't draw null objects.
        //Move the bullets and towers out as instance variables
        //Where should the draw be?
        
        //20. Restructure the code
        //Tower and bullet are set to null initially
        //The timer is responsible for drawing everything not null
        //Add tower creates a tower object
        //Start creates a bullet1 object
        //timer is started at the end of the constructor
        
        //What happens when you hit start again?
        
        //21. Let the tower object handle bullets with the fire method
        //Call fire from start
        
        //22. Add x and y instance variables with 0,0 default values. Update them based on mouse clicks. 
        //23. Place a tower at a clicked location and start it firing
        
        //What happens if you click and add tower again?
        
        //24. Allow for multiple towers. 
        //25. Allow for multiple bullets and have fire add a bullet instead.  
        
        //26. Using moving object to make a goblin go across the screen on start
        
        //Review the code. How could we make it better?
        //    How could we make the bullets smarter?
        //    What would make the graphics better?
        //    How would we add multiple waves of enemies?
        //    How could we abstract this out further?
        //    How could we more closely match MVC design?
        //    How could we have stopped drawing bullets as they exit the field?
        //     How could we "kill" the goblin?
        //    What could we do with the images to save memory?
        //    For this part, I ignored the labels and text box. What could we have used them for?
        
        
        /*
        For your ICE
        1. Create a working, organized and commented version of this code file
        2. Have the tower fire a fire ball of an appropriate size instead of a black circle
        3. Make three goblins walk across the screen instead of one and don't reset after each time the user hits start.
        */
        
        //System.out.println("Everything is commented out :(");
        
        
        //Make an instance of yourself because you are a JFrame
        GameDriver m = new GameDriver();
        //Set some default values and actions
        m.setTitle("Generic Tower Defense Game");
        m.setSize(600, 600);
        m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //We have to make the JFrame visible. Why?
        m.setVisible(true);
        
    }
}