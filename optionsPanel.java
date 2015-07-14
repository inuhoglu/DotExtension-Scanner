import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

/**
 * @(#)optionsPanel.java
 *
 *
 * @inuhoglu
 * @version 1.00 2015/7/13
 */


public class optionsPanel extends Observable{
	JPanel optionsPanel;
	JFileChooser fileChooser=new JFileChooser();
	JButton fileChoose, search;
	File choosenFile;
	JLabel fileName;
	JTextField fileExt, keyword;
	String extension, keyString, temp;
	JRadioButton include, exclude;
	Boolean ie=true;
	ArrayList<String> qualifiedFiles=new ArrayList<String>();
	Scanner scan;
		
    public optionsPanel() {
    	optionsPanel=new JPanel(new GridLayout(3,2));
    	fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);	//Information about dir
    	fileName=new JLabel("Please choose a directory...");
    	
    	JPanel fileChoosePanel=new JPanel();	//File choose
    	fileChoose=new JButton("Choose Directory");
    	fileChoose.addActionListener(new ButtonListener());
    	fileChoosePanel.add(fileChoose);
    	
    	JPanel extensionPanel=new JPanel();	//Extension chooser
    	fileExt=new JTextField("java",10);
    	extension="java";
    	fileExt.addActionListener(new FieldListener());
    	extensionPanel.add(new JLabel("File extension "));
    	extensionPanel.add(fileExt);
    	
    	JPanel keyPanel=new JPanel();	//Key value to search
    	keyword=new JTextField(10);
    	keyword.addActionListener(new FieldListener());
    	keyPanel.add(new JLabel("Search parameter "));
    	keyPanel.add(keyword);	
    	
    	JPanel selectionPanel=new JPanel();	//Include/exclude option
    	include=new JRadioButton("Include", true);
    	exclude=new JRadioButton("Exclude");
    	RBListener rbl=new RBListener();
    	include.addActionListener(rbl);
    	exclude.addActionListener(rbl);
    	ButtonGroup group=new ButtonGroup();	//Radio button config
    	group.add(include);
    	group.add(exclude);
    	selectionPanel.add(include);
    	selectionPanel.add(exclude);
    	
    	JPanel searchPanel=new JPanel();	//Search Button
    	search=new JButton("Search");
    	search.addActionListener(new ButtonListener());
    	searchPanel.add(search);
    	
    	optionsPanel.add(fileName);
    	optionsPanel.add(fileChoosePanel);
    	optionsPanel.add(keyPanel);
    	optionsPanel.add(extensionPanel);
    	optionsPanel.add(selectionPanel);
    	optionsPanel.add(searchPanel);
    }
    
    private class ButtonListener implements ActionListener{
    	public void actionPerformed(ActionEvent event){
    		if(event.getSource()==fileChoose){
    			fileChooser.showOpenDialog(optionsPanel);
	    		if(JFileChooser.APPROVE_OPTION==0){
	    			choosenFile=fileChooser.getSelectedFile();
	    			fileName.setText("Choosen directory; "+choosenFile.getPath());	//update dir info
	  		}
    		}
    		else{	//start search
    			FileHandler fH=new FileHandler(choosenFile.getPath(),extension);
    			for(int i=0;i<fH.getFileNames().size();i++){
    				try{
    					scan=new Scanner(new File((String)(fH.getFileNames().get(i))));	//create file scanner to scan inside the file
    				}
    				catch(IOException except){	//continue process in case of previously occured error
    					System.out.println(except);
    				}
    				temp="";
    				while(scan.hasNext())	//get file name
    					temp+=scan.next();
    				if(ie){
    					if(temp.indexOf(keyString)>=0)	//if include option is selected
    						qualifiedFiles.add(( new File((String)fH.getFileNames().get(i))).getPath());
    				}
    				else
    					if(temp.indexOf(keyString)<0)	//if exclude option is selected
    						qualifiedFiles.add(( new File((String)fH.getFileNames().get(i))).getPath());	
    			}
    			setChanged();	//update mainPanel
    			notifyObservers();  				
    		}    				
    	}
    }
    
    private class FieldListener implements ActionListener{
    	public void actionPerformed(ActionEvent event){
    			extension=fileExt.getText();	//Get text from textFields
    			keyString=keyword.getText();
    	}
    }
    
    private class RBListener implements ActionListener{
    	public void actionPerformed(ActionEvent event){	//Listen the radio buttons
    		if(event.getSource()==include)
    			ie=true;
    		else
    			ie=false;
    	}
    }
    
    public JPanel getPanel(){
    	return optionsPanel;
    }
    
    public ArrayList getFiles(){
    	return qualifiedFiles;
    }
    
    
}