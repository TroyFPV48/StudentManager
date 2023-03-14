

public class studentTest2{
	static StudentManager students= new StudentManager();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		System.out.println("Get student count for course 0: "+students.getStudentCount(1));
		//Get student count for course 0: 27
		
		System.out.println("Get course count: "+students.getCourseCount());
		//Get course count: 15
		
		System.out.println("Get student count: "+students.getStudentCount());
		//Get student count: 403
		
		System.out.println("Student count in CSC110a: "+students.getStudentCount("CSC110b"));
		//Student count in CSC110a: 27
		
	    System.out.println("Course name of index 1: "+students.getCourseName(1));
	    //Course name of index 1: CSC110b
		
	    Student array = students.getStudent(1,2);
		System.out.println("ID: "+array.id()+" First Name: "+ array.firstName() + " Last Name: "+ array.lastName() +" phone: "+ array.phone()+" email: "+array.email());
		//ID: 196719 First Name: Vasili Last Name: Brosius phone: 625-900-9507 email: vbrosiusy@unesco.org
		
		System.out.println(students.getStudents(2).length); //get the length of the student array from that course (2)
		//24
		
		System.out.println("Get course index by student id 125212: "+ students.findStudentCourse("125212")); //from CSC110b
		//Get course index by student id 125212: 1
	}

}
