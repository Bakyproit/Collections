package entity;

public abstract class Employee implements Comparable<Employee> {
	private String fullName; // ten nhan vien
	private float salaryRatio; // he so luong
	private float allowance; // phu cap
    
	
	private float salary ; // tinh luong
	
	public void setSalary(float salary) {
		this.salary = salary;
	}

	// ham tinh luong
	public abstract float getSalary();

	// ham so sanh ten
	@Override
	public int compareTo(Employee emp) {

		return this.fullName.compareTo(emp.fullName);
	}

	// geter setter
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public float getSalaryRatio() {
		return salaryRatio;
	}

	public void setSalaryRatio(float salaryRatio) {
		this.salaryRatio = salaryRatio;
	}

	public float getAllowance() {
		return allowance;
	}

	public void setAllowance(float allowance) {
		this.allowance = allowance;
	}

}
