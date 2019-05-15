



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class RadioQuestion extends JPanel implements ActionListener{
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
	JRadioButton[] responses;
	ButtonGroup group=new ButtonGroup();
	//bottom
	JPanel botPanel=new JPanel();
	JButton submit=new JButton("Submit");
	JButton next=new JButton("Next");
	JButton finish=new JButton("Finish");
	
	
	/*	public Questionnaire(String q, Quiz quiz){
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

		
		//answer	

	}*/
	
	public RadioQuestion(String q, String[] options, int ans, Quiz quiz){
		this.quiz=quiz;
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		correctAns=ans;
		qPanel.add(new JLabel(q));
		add(qPanel);
	

		responses=new JRadioButton[options.length];
		for(int i=0;i<options.length;i++){
		
			responses[i]=new JRadioButton(options[i]);
			responses[i].addActionListener(this);
			group.add(responses[i]);
			aPanel.add(responses[i]);
		}
		
		add(aPanel);

		next.addActionListener(this);
		finish.addActionListener(this);
		botPanel.add(next);
		botPanel.add(finish);
		add(botPanel);
		
	}
	
	public void actionPerformed(ActionEvent e){
		Object src=e.getSource();
		//submit button
		//next button
		if(src.equals(next)){
			showResult();
			
				used=true;
				
				quiz.next();

			
		}
		//finish button
		if(src.equals(finish)){
			quiz.showSummary();
		}
		//radio buttons
		for(int i=0;i<responses.length;i++){
			if(src==responses[i]){
				selected=i;
			}
		}
		
	}
	
	public void showResult(){
		String text=responses[selected].getText();
		String aText = responses[correctAns].getText();
		quiz.total++;
		if(selected==correctAns){
			JOptionPane.showMessageDialog(null,text+" is correct! You received 1000 Points\n");
		}else{
			quiz.wrongs++;
			JOptionPane.showMessageDialog(null,text+" is wrong. The answer is " + aText+  ". You got 0 points\n");
		}
	}
}

