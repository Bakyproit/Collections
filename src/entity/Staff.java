package entity;

public class Staff extends Employee {
	private String department; // ten phong ban
	private int numberOfWorkingDay; // so ngay lam viec
	private EPosition position; // chuc vu

	// tinh luong nhan vien
	@Override
	public float getSalary() {
		float sal;
		sal = this.getSalaryRatio() * 730 + this.getAllowance() + this.numberOfWorkingDay * 30;
		return sal;
	}

	// ham in ra thong tin

	@Override
	public String toString() {
		return this.getFullName() + ", " + this.getDepartment() + " , " + this.getPosition() + " , "
				+ this.getSalaryRatio() + ", " + this.getAllowance() + " , " + this.getNumberOfWorkingDay() + "  , "
				+ this.getSalary();

	}
	
	//getter setter

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getNumberOfWorkingDay() {
		return numberOfWorkingDay;
	}

	public void setNumberOfWorkingDay(int numberOfWorkingDay) {
		this.numberOfWorkingDay = numberOfWorkingDay;
	}

	public EPosition getPosition() {
		return position;
	}

	public void setPosition(EPosition position) {
		this.position = position;
	}

}
