import java.io.*;
import java.util.ArrayList;

/**
 * @(#)FileHandler.java
 *
 *
 * @Ilkcan Nuhoglu
 * @version 1.00 2015/7/13
 */


public class FileHandler {
	File file;
	String extension;
	ArrayList<String> fileNames;

    public FileHandler(String directory, String extension) {
    	file = new File(directory);
    	this.extension=extension;
    	fileNames=new ArrayList<String>();
    	findExtFile(file);
    }
    
    public void findExtFile(File f){
    	for(int i=0; i<f.listFiles().length;i++)
    		if(f.listFiles()[i].isDirectory()) //if directory look inside recursive
    			findExtFile(f.listFiles()[i]);
    		else
    			if(f.listFiles()[i].getName().lastIndexOf(".")+extension.length()==f.listFiles()[i].getName().length()-1 && f.listFiles()[i].getName().substring(f.listFiles()[i].getName().lastIndexOf(".")).indexOf(extension)>=0)	//check for extension
    				fileNames.add(f.listFiles()[i].getPath());	//add to list if requirements satisfied
    }
    
    public ArrayList getFileNames(){
    	return fileNames;
    }
    
    
}