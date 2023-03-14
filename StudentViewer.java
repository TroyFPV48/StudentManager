import java.awt.event.*;
import java.util.Arrays;

import javax.swing.*;

public class StudentViewer { 
	// frame
     static JFrame f; //JFrame variable
	// label
	 static JLabel l, l1, l2, l3, l4; //label variables
	//textfield
	 static JTextField t, t1, t2, t3, t4; //textfield variables
	 // combobox
	 static JComboBox <String> c1; //ComboBox variable 
	 static int courseIndex; //initalize course and student index which always get updated by any methods below
	 static int studentIndex;	 
	 static StudentManager sm = new StudentManager(); //calling Student Manager constructor
	 static String[] ourCourses;
		
	public StudentViewer() //student viewer default constructor
	{		
		//create 3 buttons
		JButton b1 = new JButton("Load Data");  
		JButton b2 = new JButton("Prev");
		JButton b3 = new JButton("Next");
		ourCourses = new String[sm.getCourseCount()];
		//populating ourCourses array using instance method form student manager
		for(int i = 0; i < sm.getCourseCount(); i++) {
			ourCourses[i] = sm.getCourseName(i);
			
		}
		
		c1 = new JComboBox<>(ourCourses); 

		// create labels for student textfields
		l = new JLabel("Id:");
		l1 = new JLabel("Last:");
		l2 = new JLabel("First:");
		l3 = new JLabel("Email:");
		l4 = new JLabel("Phone:");
		
		//create textfield
		t= new JTextField(7);
		t1= new JTextField(20);
		t2= new JTextField(20);
		t3= new JTextField(30);
		t4= new JTextField(10);

		// create a new panel
		JPanel p = new JPanel();	

		// create a new frame
		f = new JFrame("Student Viewer");
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit on close
		
		// set the size of frame
		f.setSize(400, 300);
		f.setVisible(true);
				
		// add panel to frame
		f.add(p);
			
		p.setLayout(null);
		
		//locate the gui elements
		b1.setBounds(10,20,90,30); 
		b2.setBounds(200,20,90,30);
		b3.setBounds(290,20,90,30);
		
		c1.setBounds(10,70,120,40);
		
		l.setBounds(20,100, 90, 40);
		l1.setBounds(20,130, 90, 40);
		l2.setBounds(20,160, 90, 40);
		l3.setBounds(20,190, 90, 40);
		l4.setBounds(20,220, 90, 40);
		
		t.setBounds(70,105, 200, 30);
		t1.setBounds(70,135, 200, 30);
		t2.setBounds(70,165, 200, 30);
		t3.setBounds(70,195, 200, 30);
		t4.setBounds(70,225, 200, 30);

		// add combobox to panel
		p.add(c1);
		
		//add button to panel
		p.add(b1);
		p.add(b2);
		p.add(b3);
		
		//add labels to panel
		p.add(l);
		p.add(l1);
		p.add(l2);
		p.add(l3);
		p.add(l4);
		
		//add textfield to panel
	    p.add(t);
	    p.add(t1);
	    p.add(t2);
	    p.add(t3);
	    p.add(t4);
	    
	    //assign buttons to functions
	    buttonFunction(c1);
	    loadButton(b1);
	    prevButton(b2);
	    nextButton(b3);
	    
		f.show();		
	}
	
	//next button function
	public void nextButton(JButton nbutton) {
		 //this button only work when its in bound
		nbutton.addActionListener(new ActionListener() { 
 	    	public void actionPerformed(ActionEvent a) { 
 	    		if (studentIndex < sm.getStudentCount(courseIndex) - 1){ //the next button only works when it's it in bound
 	    		studentIndex++;//go next by increasing student index
 	    		}
 	    		Student array = sm.getStudent(courseIndex,studentIndex); //assigning student object to an array	    		
 				t.setText(array.id()); //adding object reference to textfield everytime when it runs
 				t1.setText(array.lastName());
 				t2.setText(array.firstName());
 				t3.setText(array.email());
 				t4.setText(array.phone()); 	  
 	    		
 	    	}
 	    });
	
 	}
	
	//previous button function
	public void prevButton(JButton pbutton) {
	 //this button only work when its in bound
		pbutton.addActionListener(new ActionListener() { 
 	    	public void actionPerformed(ActionEvent a) {
 	    		if (studentIndex > 0){ //the previous button only works when it's it in bound
 	    		studentIndex--;//go back one index by increasing studentIndex
 	    		}
 	    		Student array = sm.getStudent(courseIndex,studentIndex); //assigning student object to an array	    		
 				t.setText(array.id()); //adding object references to textfield everytime when it runs
 				t1.setText(array.lastName());
 				t2.setText(array.firstName());
 				t3.setText(array.email());
 				t4.setText(array.phone());
 	    			
 	    	}
 	    });
		
 	}
	
	//this method will scan the selected course and go to course list and get its index
	public void buttonFunction(JComboBox c){ 
			c.addActionListener(new ActionListener() { //c is ComboBox which contains 15 courses
	     	    	public void actionPerformed(ActionEvent a) {
	     	    	        //Execute when a selection has been made
	     	    	        @SuppressWarnings("unchecked")
	     	    	        JComboBox<String> combo = (JComboBox<String>) a.getSource(); //find a string array of course name (for example: "CSC110a")
	     	    	        String selectedCourse = (String) combo.getSelectedItem(); //initiate a string
	     	    	        courseIndex = Arrays.asList(ourCourses).indexOf(selectedCourse); //searching course index with the string selected in Combo Box	     	    		    	      	       
	     	    	 }	     	    	
	     	 });
	}
	
	//this method will load data from the selected course and to display them
	public void loadButton(JButton pbutton) {
		pbutton.addActionListener(new ActionListener() { //everytime when it's presses, it will start from the first student index from this course
 	    	public void actionPerformed(ActionEvent a) {
 	    		studentIndex = 0; //start from index 0
 	    		Student array = sm.getStudent(courseIndex,studentIndex); //assigning student object to an array	    		
 				t.setText(array.id());//adding object references to textfield everytime when it runs
 				t1.setText(array.lastName());
 				t2.setText(array.firstName());
 				t3.setText(array.email());
 				t4.setText(array.phone());
 	    	}
 	    });
 	 }
	
	public static void main (String[]args) {
		new StudentViewer(); //calling StudentViewer constructor
	}
 
}

