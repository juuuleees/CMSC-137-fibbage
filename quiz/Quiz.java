
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.*;
import java.util.Random;
import java.util.*;
import javax.swing.JOptionPane;

public class Quiz extends JFrame{
	JPanel p=new JPanel();
	
	CardLayout cards=new CardLayout();

	int j;
	int numQs;
	int wrongs=0;
	int total=0;
	
	
	
	String[][] answers = {
		{"Cube","Sphere","Liquified","Diamond"," "," "},
		{"Bling","Backrub","Look Up","Genius"," "," "},
		{"Rebels","Monkeys","Cats","Yakuzas"," "," "},
		{"Sammy","Ham","Mr. Pickles","Tarzan"," "," "},
		{"Scurry","Sleuth","Prickle","Pack"," "," "},
		{"Dentist","Killer","Inventor","Handyman"," "," "},
		{"Football","Chess","Swimming","Poker"," "," "},
		{"Teacher","Plumber","Bouncer","Store Clerk"," "," "},
		{"Michael","Andrew","Jackson","Jordan"," "," "},
		{"Sodas","Babies","Meat","Names"," "," "},
		{"Oranges","Bread","Guitars","Insults"," "," "},
		{"The moon","College","The electric chair","Hollywood"," "," "},
		{"Birds","Cows","Elderly people","The US President"," "," "},
		{"Place","Pets","Vacancy","to Drugs"," "," "},
		{"Hot Coal","Jesus Christ","A couch","Babies"," "," "},
		{"Anthony Starks","Tony Starks","Tony Stark","Tony Stark Jr."," "," "},
		{"I'm pregnant","Bad Hair Day","Justice","Stupidity"," "," "},
	};

	Tutorial instruction[] = {
		new Tutorial("WELCOME TO FIBBAGE 127!\n\nFibbage is a trivia/quiz game where 2 or more players \nwhere players give each false answers to a trivia question. \n"),
		new Tutorial("The game will consist of 3 rounds with 3 questions each round.\n Each player will be given a few seconds to input their false answer,\n and will be given points if another player chose that answer."),
		new Tutorial("Since this game is a bluffing game, the more convincing \n the lie the most likely for it to be chosen."),
		new Tutorial("Together with the answers given by the players is \n the real answer for the trivia and if chosen by a player  that player will be given \n1000 points, and 500 for each player fooled. "),
		new Tutorial("The final question of the game is called \n the final fibbage where points will be doubled. \nThe player with the most points at the end of 3 rounds wins."),
		};
	
	Questionnaire askInput[]= { 
		new Questionnaire("The shape of wombat poop.",this), 
		new Questionnaire("The original name for the search engine that became Google.",this), 
		new Questionnaire("Tashirojima is an island off of Japan that is complete overrun by _____.",this), 
		new Questionnaire("The name of the first chimp sent into space.",this), 
		new Questionnaire("The name for a group of porcupines.",this), 
		new Questionnaire("The electric chair was invented by a professional _____ named Alfred Southwick.",this), 
		new Questionnaire("Leo Granit Kraft is a world champion in an unusual sport that combines boxing and _____.",this), 
		new Questionnaire("As a young student in Buenos Aires, Pope Francis worked as a _____.",this), 
		new Questionnaire("Michael J. Fox's middle name.",this), 
		new Questionnaire("A study published in the journal Anthrozoo reported that cows produce 5% more milk when they are given _____.",this), 
		new Questionnaire("Every year residents in Ivrea, Italy reenact a historicalbattle of their region, and instead of replica weapons, they use _____.", this), 
		new Questionnaire("	In school, Syvester Stallone was voted by his teachers as Most Likely to Go To ______.",this), 
		new Questionnaire("Advanced Comfort Technology makes waterbeds for _____.",this), 
		new Questionnaire("Located near the town of Stanley, there's a small village in England called No _____.",this), 
		new Questionnaire("El Colacho is a Spanish festival where people dress up like the devil and jump over _____.",this), 
		new Questionnaire("What is Iron Man's real name?",this),
		new Questionnaire("~~8-0 is the obscure emoticon that stands for _____.",this)
				
	};
	
	RadioQuestion questions[]={
		
		new RadioQuestion(
			"The shape of wombat poop.",
			answers[0],
			0,this
		),
		new RadioQuestion(
			"The original name for the search engine that became Google.",
			answers[1],
			1,this
		),
		new RadioQuestion(
			"Tashirojima is an island off of Japan that is complete overrun by _____.",
			answers[2],
			2,this
		),
		new RadioQuestion(
			"The name of the first chimp sent into space.",
			answers[3],
			1,this
		),
		new RadioQuestion(
			"The name for a group of porcupines.",
			answers[4],
			2,this
		),
		new RadioQuestion(
			"The electric chair was invented by a professional _____ named Alfred Southwick.",
			answers[5],
			0,this
		),
		new RadioQuestion(
			"Leo Granit Kraft is a world champion in an unusual sport that combines boxing and _____.",
			answers[6],
			1,this
		),
		new RadioQuestion(
			"As a young student in Buenos Aires, Pope Francis worked as a _____.",
			answers[7],
			2,this
		),
		new RadioQuestion(
			"Michael J. Fox's middle name.",
			answers[8],
			1,this
		),
		new RadioQuestion(
			"A study published in the journal Anthrozoo reported that cows produce 5% more milk when they are given _____.",
			answers[9],
			3,this
		),
		new RadioQuestion(
			"Every year residents in Ivrea, Italy reenact a historicalbattle of their region, and instead of replica weapons, they use _____.",
			answers[10],
			0,this
		),
		new RadioQuestion(
			"	In school, Syvester Stallone was voted by his teachers as Most Likely to Go To ______.",
			answers[11],
			2,this
		),
		new RadioQuestion(
			"Advanced Comfort Technology makes waterbeds for _____.",
			answers[12],
			1,this
		),
		new RadioQuestion(
			"Located near the town of Stanley, there's a small village in England called No _____.",
			answers[13],
			0,this
		),
		new RadioQuestion(
			"El Colacho is a Spanish festival where people dress up like the devil and jump over _____.",
			answers[14],
			3,this
		),
		new RadioQuestion(
			"What is Iron Man's real name?",
			answers[15],
			2,this
		)
		,
		new RadioQuestion(
			"~~8-0 is the obscure emoticon that stands for _____.",
			answers[16],
			1,this
		)
	};

	public static void main(String args[]){
		new Quiz();
	}
	
	public Quiz(){
		super("Fibbage137");
		setResizable(true);
		setSize(650,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		p.setLayout(cards);
		for(int i=0; i<5;i++){
			p.add(instruction[i],"t"+i);
		}
				
		numQs=questions.length;
		for(int i=0;i<numQs;i++){
			p.add(askInput[i],"a"+i);
			p.add(questions[j],"q"+j);
			j++;
				
		
		}
		//cards.show(p,"t"+i);
		Random r=new Random();
		int i=r.nextInt(numQs);
		cards.show(p,"a"+i);
		j=i;
		add(p);
		setVisible(true);
		
	}
	public void showChoices(){
				
		
		cards.show(p,"q"+j);
		
		
		
	} 

	public void next(){
		if((total+wrongs)==numQs){
			showSummary();
		}
		
		else {
			Random r=new Random();
			boolean found=false;
			int i=0;
			while(!found){
				i=r.nextInt(numQs);
				if(!questions[i].used){
					found=true;
				}
			}
			cards.show(p,"a"+i);
			j=i;
		}
	}
	
	public void addBluff(String b){
		//System.out.println(b);
		//return null;
		for(int i = 0; i<6;i++){
			if(answers[j][i] == " "){
				answers[j][i] = b;
					break;
			}
		}
		for(int k = 0;k<6;k++){
			if(answers[j][k]==" "){
				break;
			}
			System.out.println(answers[j][k]);				
		}
		
		p.add(questions[j],"q"+j);
		add(p);
		//System.out.println(answers[j].length);
		/*for(int i = 0; i<answers[j].length;i++){
		System.out.println(answers[j][i]);
		}*/
		showChoices();
	}	

	
	public void showSummary(){
		JOptionPane.showMessageDialog(null,"That's it! Here is your summary:"+
			
			"\ntotal: \t\t"+(int)((total)*1000)+"Points"
		);
		System.exit(0);
	}
}
