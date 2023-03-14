public record Student(String id, String lastName, String firstName, String email, String phone) implements Comparable<Student> {
	
	@Override
	public int compareTo(Student s)
    {
        
        return this.lastName.compareTo(s.lastName()); //sorting student lastname by implementing compareTo method from Comparable
    }

} 
