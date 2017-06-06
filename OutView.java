package project2;

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

	public class OutView extends JPanel {
		protected static JTextField fileIn=new JTextField("Enter source file name here. ");
		protected static JTextField fileOut=new JTextField("Enter destinaton file name here.");
		protected static JTextField status=new JTextField(" status");
		protected static JTextArea display=new JTextArea(" ");

		protected final JTextField[] out={fileIn,fileOut,status};
		protected final String [] c={"yellow","blue","grey"};

		public OutView(){
			setLayout(new GridLayout(4,1));
			for(int i=0; i<out.length; i++){
				out[i].setFont(new Font("Serif",Font.PLAIN,40));
				out[i].setHorizontalAlignment(SwingConstants.LEFT);
				add(out[i]);
			}
			display.setFont(new Font("Serif",Font.PLAIN,30));
			//((JTextField) display).setHorizontalAlignment(SwingConstants.LEFT);
			add(display);
	}
		
	}

