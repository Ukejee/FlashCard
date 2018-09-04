package com.flash.cards;
import java.io.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class QuizCardBuilder {
	
	private JTextArea question;
	private JTextArea answer;
	private ArrayList<QuizCard> cardList;
	private JFrame frame;

	public static void main(String[] args) {
		QuizCardBuilder cb = new QuizCardBuilder();
		cb.go();
		
	}
	
	public void go() {
		
		 frame = new JFrame("Quiz Card Builder");
		JPanel panel = new JPanel();
		Font bigFont = new Font("sanserif", Font.BOLD, 24);
		question = new JTextArea(6,20);
		question.setLineWrap(true);
		question.setWrapStyleWord(true);
		question.setFont(bigFont);
		answer = new JTextArea(6,20);
		answer.setLineWrap(true);
		answer.setWrapStyleWord(true);
		answer.setFont(bigFont);
		
		
		JScrollPane qScroller = new JScrollPane(question);
		qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JScrollPane aScroller = new JScrollPane(answer);
		aScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		aScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JButton nextButton = new JButton("Next Card");
		
		cardList = new ArrayList<QuizCard>();
		
		JLabel alabel = new JLabel("Question:");
		JLabel qlabel = new JLabel("Answers:");
		
		JMenuBar menu = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem newMenuItem = new JMenuItem("New");
		JMenuItem saveMenuItem = new JMenuItem("Save");
		newMenuItem.addActionListener(new NewMenuListener());
		saveMenuItem.addActionListener(new SaveMenuListener());
		fileMenu.add(newMenuItem);
		fileMenu.add(saveMenuItem);
		menu.add(fileMenu);
		frame.setJMenuBar(menu);
		
		alabel.setForeground(Color.white);
		qlabel.setForeground(Color.white);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(Color.DARK_GRAY);
		panel.add(alabel);
		panel.add(question);
		panel.add(qScroller);
		panel.add(qlabel);
		panel.add(answer);
		panel.add(aScroller);
		panel.add(nextButton);
		nextButton.addActionListener(new NextCardListener());
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.setSize(500,600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public class NextCardListener implements ActionListener{
		public void actionPerformed(ActionEvent ev) {
			
			QuizCard card = new QuizCard(question.getText(),answer.getText());
			cardList.add(card);
			clearCard();
		}
	}
	
	public class SaveMenuListener implements ActionListener{
		public void actionPerformed(ActionEvent ex) {
			
			QuizCard card = new QuizCard(question.getText(),answer.getText());
			cardList.add(card);
			
			JFileChooser fileSave = new JFileChooser();
			fileSave.showSaveDialog(frame);
			saveFile(fileSave.getSelectedFile());
		}
	}
	
	public class NewMenuListener implements ActionListener{
		public void actionPerformed(ActionEvent ex) {
			
			cardList.clear();
			clearCard();
		}
	}
	
	private void clearCard() {
		
		question.setText(" ");
		answer.setText(" ");
		question.requestFocus();
	}
	
	private void saveFile(File file) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			
			for(QuizCard card:cardList) {
				writer.write(card.getQuestion() + "/");
				writer.write(card.getAnswer() + "\n");
				
			}
			writer.close();
		}
		catch(IOException ex) {
			System.out.println("couldn't write the cardlist out");
			ex.printStackTrace();
		}
		
	}
}
