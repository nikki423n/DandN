
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Rectangle;

/* 
Building extends JPanel so that we can override the paint method. The paint method is necessary to use the simple
drawing tools of the library! 
Simulator implements an ActionListener which adds the method actionPerformed. This method is invoked by the 
animation timer every timerValue(16ms).
 */
public class Building extends JPanel implements ActionListener{
    // serial suppresses warning
    private static final long serialVersionUID = 1L;

    //simulation control objects/values
    JFrame frame;
    Control control; //
    Timer timer; //Event control	
    int time = 0; //Track time as the simulation runs
    Wall[] walls;  // building is build from walls

    /* constructor will setup our main Graphic User Interface - a simple Frame! */
    public Building(Control ctl, String title) {
        // used for Control callback
        this.control = ctl;
        
        // Build the Wall
        buildWall();

        //Setup the GUI
        frame = new JFrame(title);
        frame.setSize(ctl.frameX,ctl.frameY); //set the size

        //add this so that hitting the x button will actually end the program
        //the program will continue to run behind the scenes and you might end up with 10+ of them
        //without realizing it
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //make it visible
        frame.setVisible(true);
        frame.add(this); //add this class (JPanel) to the JFrame
    }

    //activation of Simulator separated from Constructor 
    public void activate() {
        //Timer for animation
        //Argument 1: timerValue is a period in milliseconds to fire event
        //Argument 2:t any class that "implements ActionListener"
        timer = new Timer(control.timerValue, this); //timer constructor
        timer.restart(); //restart or start

        // frame becomes visible
        frame.setVisible(true);		
    }

    /* This invoked by Timer per period in milliseconds in timerValue  */
    @Override
    public void actionPerformed(ActionEvent e) {
        //Triggers paint call through polymorphism
        repaint();	
    }

    /* paint method for drawing the simulation and animation */
    @Override
    public void paint(Graphics g) {

        //tracking total time manually with the time variable
        time += control.timerValue;

        //events
        super.paintComponent(g); // a necessary call to the parent paint method, required for proper screen refreshing

        paintWalls(g);

        control.paintPersons(g, walls); // repaint all objects in simulation

    } 

    public void paintWalls(Graphics g) {

        //draws vertical walls
        g.drawImage(walls[0].getImage(), walls[0].getX(), walls[0].getY(), this);
        g.drawImage(walls[2].getImage(), walls[2].getX(), walls[2].getY(), this);
        g.drawImage(walls[4].getImage(), walls[4].getX(), walls[4].getY(), this);
        g.drawImage(walls[6].getImage(), walls[6].getX(), walls[6].getY(), this);

        //draws horizontal walls
        g.drawImage(walls[1].getImage(), walls[1].getX(), walls[1].getY(), this);
        g.drawImage(walls[3].getImage(), walls[3].getX(), walls[3].getY(), this);
        g.drawImage(walls[5].getImage(), walls[5].getX(), walls[5].getY(), this);
        g.drawImage(walls[7].getImage(), walls[7].getX(), walls[7].getY(), this);

        //sets text color
        g.setColor(Color.BLACK);
        g.setFont(new Font("Roboto", Font.BOLD, 20));

        g.drawString("Sprouts", 610, 50);
        g.drawString("Scripps Medical", 5, 50);
        g.drawString("Board and Brew", 5, 440);
        g.drawString("Mr. M's House", 590, 440);

    }

    /*
     * Build the walls for this building
     * 
     */

    public void buildWall()
    {
        
        walls = new Wall[8];
        
        //Declares Wall sprites and positions of walls
        walls[0] = new Wall(550, 0, "SocialDistancingImages/wall2.png", true);
        walls[2] = new Wall(200, 0, "SocialDistancingImages/wall2.png", true);
        walls[4] = new Wall(550, 400, "SocialDistancingImages/wall2.png", true);
        walls[6] = new Wall(200, 400, "SocialDistancingImages/wall2.png", true);

        walls[1] = new Wall(620, 160, "SocialDistancingImages/wall1.png", false);
        walls[3] = new Wall(-25, 160, "SocialDistancingImages/wall1.png", false);
        walls[5] = new Wall(620, 400, "SocialDistancingImages/wall1.png", false);
        walls[7] = new Wall(-25, 400, "SocialDistancingImages/wall1.png", false);
        
        //Declares Wall sprites and positions of walls
        /*static Wall vWall1 = new Wall(550, 0, "SocialDistancingImages/wall2.png", true);
        static Wall vWall2 = new Wall(200, 0, "SocialDistancingImages/wall2.png", true);
        static Wall vWall3 = new Wall(550, 400, "SocialDistancingImages/wall2.png", true);
        static Wall vWall4 = new Wall(200, 400, "SocialDistancingImages/wall2.png", true);

        static Wall hWall1 = new Wall(620, 160, "SocialDistancingImages/wall1.png", false);
        static Wall hWall2 = new Wall(-25, 160, "SocialDistancingImages/wall1.png", false);
        static Wall hWall3 = new Wall(620, 400, "SocialDistancingImages/wall1.png", false);
        static Wall hWall4 = new Wall(-25, 400, "SocialDistancingImages/wall1.png", false);
        static Wall[] walls = {vWall1, hWall1, vWall2, hWall2, vWall3, hWall3, vWall4, hWall4};
        */
        //static Rectangle[] r = {vWall1.getBounds(), hWall1.getBounds(), vWall2.getBounds(), hWall2.getBounds(),
        //        vWall3.getBounds(), hWall3.getBounds(), vWall4.getBounds(), hWall4.getBounds()};

    }
}