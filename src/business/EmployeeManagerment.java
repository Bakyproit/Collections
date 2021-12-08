package business;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import entity.Employee;
import entity.Staff;
import entity.Teacher;

/*
 * them nhan vien
 * search nhan vien theo ten
 * search nhan vien theo khoa
 * sap xep nhan vien
 */
public class EmployeeManagerment {
	private ArrayList<Employee> listEmployee;

	public EmployeeManagerment() {
		listEmployee = new ArrayList<>();
	}

	public void addEmployee(Employee emp) {
		listEmployee.add(emp);
	}

	// search staff/teacher by name ;
	public ArrayList<Employee> searchByName(String name) {
		ArrayList<Employee> empFound = new ArrayList<>();

		// check xem ten truyen vao co rong khong
		if (name == null || name.trim() == "") {
			return empFound;
		}

		// duyet tat ca phan tu co trong arrayList

		for (Employee employee : listEmployee) {
			if (employee.getFullName().toUpperCase().contains(name.toUpperCase())) {
				empFound.add(employee);

			}
		}

		return empFound;
	}

	// search staff/ teacher by faculty-khoa/department-phong ban
	public ArrayList<Employee> searchByFacultyAndDepartment(String dept) {
		ArrayList<Employee> empFound = new ArrayList<>();

		// khoi tao ;
		Staff s = null;
		Teacher t = null;
		String nameFacultyAndDepartment = null;

		for (Employee employee : listEmployee) {
			if (employee instanceof Staff) {
				s = (Staff) employee; // ep kieu
				// lay ve ten phong ban
				nameFacultyAndDepartment = s.getDepartment();
			} else if (employee instanceof Teacher) {
				t = (Teacher) employee; // ep kieu
				// lay ve ten khoa
				nameFacultyAndDepartment = t.getFaculty();
			}

			// check dept xem co chua trong list ko
			if (nameFacultyAndDepartment.toUpperCase().contains(dept.toUpperCase())) {
				empFound.add(employee);
			}
		}

		return empFound;
	}
	// sap xep toan bo danh sach theo ten 
	public ArrayList<Employee> sortListAll(){
		Collections.sort(listEmployee);
		return listEmployee ; 
	}

}
