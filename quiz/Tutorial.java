

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class Tutorial extends JPanel implements ActionListener{

	Quiz quiz;	

	JPanel qPanel = new JPanel();

	JPanel aPanel = new JPanel();
	
	ButtonGroup group=new ButtonGroup();
	//bottom
	JPanel botPanel=new JPanel();
	JButton submit=new JButton("Submit");
	JButton next=new JButton("Next");
	JButton prev=new JButton("Finish");
	

	public Tutorial(String q){
		
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		qPanel.add(new JLabel(q));
		add(qPanel);
	


		next.addActionListener(this);
		prev.addActionListener(this);
		botPanel.add(prev);
		botPanel.add(next);
		add(botPanel);
		
	


	}

	public void actionPerformed(ActionEvent e){
		Object src=e.getSource();
		//submit button
		if(src.equals(next)){
		
		
			 	//quiz.nextTutorial();
				
					
				
		}
	}

}

