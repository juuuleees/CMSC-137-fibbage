

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class Questionnaire extends JPanel implements ActionListener{
	int correctAns;
	Quiz quiz;	
	int selected;
	boolean used;
	//questions
	JPanel qPanel = new JPanel();
	//text field
	JPanel bPanel = new JPanel();
	JLabel aLie = new JLabel("Your Lie:");
	JTextField tBluff = new JTextField(15);
	
	//answers
	JPanel aPanel = new JPanel();
	
	ButtonGroup group=new ButtonGroup();
	//bottom
	JPanel botPanel=new JPanel();
	JButton submit=new JButton("Submit");
	
	

	public Questionnaire(String q, Quiz quiz){
		this.quiz=quiz;
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		aLie.setLabelFor(tBluff);
		//question
		qPanel.add(new JLabel(q));
		add(qPanel);
		bPanel.add(aLie);
		bPanel.add(tBluff);

		submit.addActionListener(this);
		bPanel.add(submit);
		add(bPanel);
		
	
	/*int tim = 10;
	long delay = tim * 1000;

	do{
		Thread.sleep(1000);
		System.out.println(tim / 1);
		tim = tim - 1;
		delay = delay - 1000;

	}while (delay != 0);

		System.out.println("Time's Up!");
	
	*/
		
	

		

	}

	public void actionPerformed(ActionEvent e){
		Object src=e.getSource();
		//submit button
		if(src.equals(submit)){
		
		// String bluff = tBluff.getText();
			 	quiz.addBluff(tBluff.getText());
				
					
				
		}
	}

}

