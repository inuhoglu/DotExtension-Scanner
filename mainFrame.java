import java.awt.*;
import javax.swing.*;

/**
 * @(#)mainFrame.java
 *	Main frame to drive GUI
 *
 * @inuhoglu
 * @version 1.00 2015/7/13
 */


public class mainFrame {

    public static void main(String[] args) {
    	JFrame mainFrame=new JFrame("DotExtention Scanner");
    	mainFrame.getContentPane().add(new mainPanel());
    	mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	mainFrame.pack();
    	mainFrame.setVisible(true);
    }
    
    
}