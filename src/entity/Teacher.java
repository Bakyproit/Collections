package entity;

public class Teacher extends Employee {

	private String faculty;// ten khoa
	private int teachingHour;// so tiet giang day
	private EDegree degree; // chuc vu

	// tinh luong giao vien
	@Override
	public float getSalary() {
		float sal;
		sal = this.getSalaryRatio() * 730 + this.getAllowance() + teachingHour * 45;
		return sal;
	}
	// in thong tin giang vien

	@Override
	public String toString() {
		return this.getFullName() + " , " + this.getFaculty() + " , " + this.getDegree() + " , " + this.getSalaryRatio() + " , "
				+ this.getAllowance() + " , " + this.getTeachingHour() + " , " + this.getSalary();

	}

	
	//geter setter
	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public int getTeachingHour() {
		return teachingHour;
	}

	public void setTeachingHour(int teachingHour) {
		this.teachingHour = teachingHour;
	}

	public EDegree getDegree() {
		return degree;
	}

	public void setDegree(EDegree degree) {
		this.degree = degree;
	}

}
