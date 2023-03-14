import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
* This object will retrieves two csv file and break them down into two arrays
* which are Courses and Students array from local folder. After storing all the data in correct
* category (to Student record), it will override instance methods for StudentManager interface.
*
* @param  Student.csv
* @param  Courses.csv
* @return a course array and an array of Student object
* @see   none
*/

public class StudentManager implements StudentManagerInterface{
	private String stdPath = "/Users/h0rnypony/Desktop/AD300/Project1_TuongPham_AD300/src/Students.csv"; //students.csv pathname
	private String crsPath = "/Users/h0rnypony/Desktop/AD300/Project1_TuongPham_AD300/src/Courses.csv"; //courses.csv pathname
	static Student[][] ourStudents; //create empty 2d array for students
    static String[] ourCourses; //create 1d array for course
	private int crsIdx = 0;  //course index or course count
	private int stdIdx = 0; //student index or student count
	
	
	
	//default constructor
	public StudentManager(){
	
		
		Scanner scCourse = null , scStudent = null; //initialize course and student as null scanners objects
		
		//scan Courses.csv
		try {
			scCourse = new Scanner(new File(crsPath));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//scan Students.csv
		try {
			scStudent = new Scanner(new File(stdPath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		String line = scCourse.nextLine(); // this line of string will contain the first line of Course.csv
		String[] tempArray = line.split(","); //this temporary array will store every string separated by the comma
		int courseNum = (Integer.parseInt(tempArray[0])); //convert the course number from String into int so we can use it as index
		
		ourCourses = new String[courseNum]; //create "ourCourse" array with 15 indexes 
		ourStudents = new Student[ourCourses.length][]; //first dimension with have the same index number as the course array
		
		scCourse.nextLine(); //skipping 2nd line in Courses.csv
		
		while(scCourse.hasNextLine()) {
			line = scCourse.nextLine(); //"csc110a,24"
			String[] tempArray1 = line.split(","); //splitting text by comma and store them to String array
			ourCourses[crsIdx]= tempArray1[0]; //stores first column to Course indexes
			ourStudents[crsIdx] = new Student[Integer.parseInt(tempArray1[1])]; //index number of each array(course) is the enrollment number
			crsIdx++;//update course count
		}
		
		String line2 = scStudent.nextLine(); //skip first line in Students.csv
		while(scStudent.hasNextLine()) {
			for(int crsIdx2 = 0; crsIdx2 < ourCourses.length; crsIdx2++) { //15 courses loop
				for(int stdIdx2 = 0; stdIdx2 < ourStudents[crsIdx2].length; stdIdx2++) { //loop of each courses based on the course index
					line2 = scStudent.nextLine();
					String[] tempArray2 = line2.split(","); //temporary string array just to populate indexes per line
					ourStudents[crsIdx2][stdIdx2]= new Student(tempArray2[1],tempArray2[2],tempArray2[3],tempArray2[4],tempArray2[5]); //store in order of id, lastname, firstname, phone number and email
					stdIdx++; //update student count
				}
				Arrays.sort(ourStudents[crsIdx2]); //before jumping out of the course loop, I sort student by last name
			}
		}	
	}	
	

	@Override
	public int getCourseCount() { 
		return ourCourses.length; //15 courses
	}

	@Override
	public int getStudentCount(int courseIndex) {
		return ourStudents[courseIndex].length;
	}

	@Override
	public int getStudentCount() { 
		return stdIdx;
	}


	@Override
	public int getStudentCount(String courseName) { 
		return ourStudents[Arrays.asList(ourCourses).indexOf(courseName)].length; 
		//inside the [], it will search for index of the course by course name, then will get the length of student array using that index
	}

	@Override
	public String getCourseName(int courseIndex) {  
		return ourCourses[courseIndex];
	}

	@Override
	public Student getStudent(int courseIndex, int studentIndex) { 
		return ourStudents[courseIndex][studentIndex];
	}

	@Override
	public Student[] getStudents(int courseIndex) { 
		return ourStudents[courseIndex];
	} 

	
	@Override
	public int findStudentCourse(String id) { //this method will find course index with student id which is an object reference
		int result = 0;
		for(int num1 = 0; num1 < getCourseCount(); num1++) {
			for(int num2 = 0; num2 < getStudentCount(num1); num2++) {
				if(id.equals(ourStudents[num1][num2].id())) { //comparing given id and student id
					result = num1; //the result will be the course index which is the loop count (num1)
				}
			}
		}
		return result;	
	}
	
	
}
