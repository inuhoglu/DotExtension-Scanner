import java.awt.*;
import javax.swing.*;
import java.util.*;

/**
 * @(#)mainPanel.java
 *
 *
 * @inuhoglu
 * @version 1.00 2015/7/13
 */


public class mainPanel extends JPanel implements Observer {
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();	//Set approprite resolution
	final int XDIMENSION=(int)screenSize.getWidth()*3/5;
	final int YDIMENSION=(int)screenSize.getHeight()*3/5;
	optionsPanel oP=new optionsPanel();  
	JPanel iP=new JPanel();
		
    public mainPanel() {
    	super(new BorderLayout() );  	
    	setPreferredSize(new Dimension(XDIMENSION,YDIMENSION));
    	oP.addObserver(this);	//Adding observer to button in optionsPanel
    	add(oP.getPanel(),BorderLayout.NORTH);
    	add(iP);
    }
    
    public void update(Observable o, Object arg){
    	String temp="";
    	for(int i=0;i<oP.getFiles().size();i++)
    		temp+="\n"+oP.getFiles().get(i);	//Add file names to text area
    	JTextArea tA=new JTextArea(temp);
    	JScrollPane sP=new JScrollPane(tA);	
    	sP.setPreferredSize(new Dimension(XDIMENSION,YDIMENSION-oP.getPanel().getPreferredSize().height-10));
    	iP.add(sP);
    	updateUI();	//refresh interface
    	repaint();
    }    
}