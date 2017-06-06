package project2;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import cal_GUI2.CalcJFrame;
import cal_GUI2.OutPut;

public class Options extends JPanel implements ActionListener {
	public Options() {
			setLayout(new GridLayout(1,3));
			
			for(int i=0; i<opsBtns.length; i++){
				opsBtns[i].addActionListener(this);//make the button work
				opsBtns[i].setFont(new Font("Serif",Font.ITALIC,40));
				
				add(opsBtns[i]);
			}
		}

	
	private JButton showButton = new JButton("Show Contents");	
    private JButton copyButton = new JButton("Copy");
    private JButton correctButton = new JButton("Correct & Copy");
    protected final JButton [] opsBtns = {showButton,copyButton, correctButton};
	@Override
	public void actionPerformed(ActionEvent e) {
		String face = e.getActionCommand().trim();
		//String equation = OutPut.lblOut.getText().trim();
		switch(face){
		case "Show Contents":{FileEdit.action( face);
		System.out.println("The button "+face+" was clicked");}break;
		case "Copy":{FileEdit.action( face);
		System.out.println("The button "+face+" was clicked");}break;
		case "Correct & Copy":{FileEdit.action( face);
		System.out.println("The button "+face+" was clicked");}break;
		
	}
   
}}
