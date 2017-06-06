package project2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class FileEdit extends JFrame {

    static boolean state=false;
    static boolean cap=false;
    static String s2="";//temp string

    public FileEdit( )
    {   JPanel container = new JPanel();
		container.setLayout(new BorderLayout());
    
		Options opbtns = new Options();
		container.add(opbtns, BorderLayout.NORTH);
		opbtns.setPreferredSize(new Dimension(1000, 50));
		OutView out=new OutView(); 
		container.add(out, BorderLayout.SOUTH);
		out.setPreferredSize(new Dimension(1000, 500));
       
        add(container);
    }
	
    public static void action(String e)
    {
		if (e.equals("Show Contents"))
		    showLine( );
		else if (e.equals("Copy"))
		    copyFile( );
		else if (e.equals("Correct & Copy"))
		    correctFile();
		else
		    OutView.status.setText("error.");
    }
	
    private static void showLine( )// show the entire file
    {
       String s=fileToString(); 
       //OutView.status.setText(s);
        OutView.display.append(s);
    }
    public static void writeToNewFile(String fileName, String text) {
		PrintWriter outStream = null;
		try{
			outStream = new PrintWriter(fileName);
			outStream.println(text);	
			
		}catch(FileNotFoundException fnfe){
			System.err.println("COULD NOT WRITE TO NEW FILE " + fileName + " " + fnfe.getMessage());
		}
		finally{
			outStream.close();
		}
	}
	
private static void correctFile(){
	//boolean state=false;

    	String s=fileToStringTrim();//remove extra white space
    	System.out.println("without the white space"+s);
    	String []st=s.split(" ");
    	
    	for(int i=0;i<st.length;i++){
    		System.out.println(st[i]+" after edit");
    		updateFile(edit(st[i]));
    		//System.out.println(st[i]+" after edit");

    	}
    	

        
        
//System.out.println(s2);
//copyFile(s2);
//writeToNewFile(OutView.fileOut.getText(),s2) ; 	

    	OutView.status.setText("Copy is done");
		OutView.fileIn.setText("Enter source file name here.");
		OutView.fileOut.setText("Enter destinaton file name here.");
    }
public static void appendToFile(String fileName, String text) {
	PrintWriter	outStream = null;
	try{
		 outStream = new PrintWriter(new FileOutputStream(fileName, true));//true to append
		 outStream.println(text);
					
	}catch(FileNotFoundException fnfe){
		System.err.println("COULD NOT FIND / APPEND TO FILE " + fileName + " " + fnfe.getMessage());
	}
	finally{
		outStream.close();
	}
}
    private static void updateFile(String e) {
    	if(state&&cap){
    		//update capital
    		//appendToFile(OutView.fileOut.getText(), (e.substring(0, 1).toUpperCase() + e.substring(1)));
    		s2=s2+" "+(e.substring(0, 1).toUpperCase() + e.substring(1));
    		System.out.println(e.substring(0, 1).toUpperCase() + e.substring(1));

    		state=false;
    		cap=false;
    	}
    	else if(state){
    		//update
    		s2=s2+" "+e;
    		System.out.println(e);
    		appendToFile(OutView.fileOut.getText(), s2);
    		OutView.display.append(s2);
    		s2="";
    		//state=false;
    		cap=true;
    	}
    	
    	else{
    		//appendToFile(OutView.fileOut.getText(), e);
    		System.out.println(e);

    		s2=s2+" "+e;
    	}
	
	
}

	private static String edit(String st) {
    	 if(st.equalsIgnoreCase("lol")){
     		return "laughing out loud";
     	}
     	else if(st.equalsIgnoreCase("ttyl")){
     		//st[i]="Talk to you late";
     		return "talk to you later";
     	}
     	else if(st.equalsIgnoreCase("brb")){
     		return "be right back";
     		}
     	
       
     	else if(st.endsWith(".")||st.endsWith("?")||st.endsWith("!")){
     		state=true;
     		if(st.equalsIgnoreCase("lol.")){
         		return "laughing out loud.";
         	}
     		else if(st.equalsIgnoreCase("ttyl.")){
         		//st[i]="Talk to you late";
         		return "talk to you later.";
         	}
         	else if(st.equalsIgnoreCase("brb.")){
         		return "be right back.";
         		}
     		if(st.equalsIgnoreCase("lol?")){
         		return "laughing out loud?";
         	}
     		else if(st.equalsIgnoreCase("ttyl?")){
         		//st[i]="Talk to you late";
         		return "talk to you later?";
         	}
         	else if(st.equalsIgnoreCase("brb?")){
         		return "be right back?";
         		}
     		if(st.equalsIgnoreCase("lol!")){
         		return "laughing out loud!";
         	}
     		else if(st.equalsIgnoreCase("ttyl!")){
         		//st[i]="Talk to you late";
         		return "talk to you later!";
         	}
         	else if(st.equalsIgnoreCase("brb!")){
         		return "be right back!";
         		}
         	else
         		return st;
 	}
 	
 	
    else return st;}


	/*public static String[] stringToArray(String wordString) {
        String[] result;
        
        int i = 0;     
        StringTokenizer st = new StringTokenizer(wordString);
        
        result = new String[st.countTokens()];
        while (st.hasMoreTokens()) {
            result[i++] = st.nextToken();
        System.out.println(st.nextToken());
        }
        
        return result;
    }*/
    private static String fileToStringTrim()
    {
    	String contents = null;
		try {
			contents = new String(Files.readAllBytes(Paths.get(OutView.fileIn.getText()))).trim().replaceAll("\\s{2,}", " ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println("File content : " + contents);	
return contents;
    }
    private static String fileToString()
    {
    	String contents = null;
		try {
			contents = new String(Files.readAllBytes(Paths.get(OutView.fileIn.getText())));
			}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println("File content : " + contents);	
return contents;
    }
    public static void createNewFile(String fileName) {
		PrintWriter outStream = null;
		try{
		 outStream = new PrintWriter(fileName);//create a new file
		}
		catch(FileNotFoundException fnfe){
			System.err.println("COULD NOT WRITE TO NEW FILE " + fileName + " " + fnfe.getMessage());
		}
		finally{
		outStream.close(); // close the connection to the file
		}
	}
    private static void copyFile( )
    {
       
    	Scanner inStream = null;
		PrintWriter outStream = null;
		try{
			inStream = new Scanner(new File(OutView.fileIn.getText()));//connect to incoming file
			createNewFile(OutView.fileOut.getText());
			outStream = new PrintWriter(OutView.fileOut.getText());//overwrite anything that existed with that file name
			while(inStream.hasNextLine()){
				outStream.println( inStream.nextLine()  );
			}
		}catch(FileNotFoundException fnfe){
			OutView.status.setText("Can not copy");		}
		finally{
			inStream.close();
			outStream.close();
			OutView.status.setText("Copy is done");
			OutView.fileIn.setText("Enter source file name here.");
			OutView.fileOut.setText("Enter destinaton file name here.");
    }}
    /*private static void copyFile(String s )
    {
       
    	
		PrintWriter outStream = null;
		try{
			String []inStream = stringToArray(s); 
			createNewFile(OutView.fileOut.getText());
			outStream = new PrintWriter(OutView.fileOut.getText());//overwrite anything that existed with that file name
			for(int i=0;inStream.length>0;i++){
				outStream.println( inStream[i]);
			}
		}catch(FileNotFoundException fnfe){
			OutView.status.setText("Can not copy");		}
		finally{
			//inStream.close();
			outStream.close();
			OutView.status.setText("Copy is done");
			OutView.fileIn.setText("Enter source file name here.");
			OutView.fileOut.setText("Enter destinaton file name here.");
    }}*/
	
    
    public static void main(String [] args){
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				FileEdit gui = new FileEdit();
				//boolean state=false;
				gui.setDefaultCloseOperation(EXIT_ON_CLOSE);
				gui.setSize(500,500);
				gui.pack();
				gui.setVisible(true);
				
			}
		});
	}

	
}
