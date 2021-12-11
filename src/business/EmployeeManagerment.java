package business;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import entity.EDegree;
import entity.EPosition;
import entity.Employee;
import entity.Staff;
import entity.Teacher;
import exception.MyValidateException;

/*
 * them nhan vien
 * search nhan vien theo ten
 * search nhan vien theo khoa
 * sap xep nhan vien
 */
public class EmployeeManagerment {
	public static final String FILE_NAME = "src/data.txt" ; 
	
	public static final String NEW_LINE = "\n" ;
	
	public static final String EMP_STAFF = "Staff" ; 
	
	public static final String EMP_TEACHER = "Teacher" ;
	
	
	
	
	private ArrayList<Employee> listEmployee;

	public EmployeeManagerment() throws Exception,IOException,MyValidateException {
		listEmployee = new ArrayList<>();
		
		// load noi dung file data len
		this.load(FILE_NAME)  ;
	}


	public void addEmployee(Employee emp)throws Exception,IOException {
		
		// ghi noi dung ra file
		save(emp , FILE_NAME) ;
		
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
	
	// DOC DU LIEU TU FILE LEN
	private void load(String fileName) throws IOException ,MyValidateException {
		// TODO Auto-generated method stub
		BufferedReader br = null ; 
		FileReader fr = null ;
		String sCunrrent = null ;
		
		try {
			fr = new FileReader(fileName) ;
			br = new BufferedReader(fr) ; 
			
			String[] words ;
			while((sCunrrent = br.readLine()) != null) { // doc 1 dong cho den khi het
				words = sCunrrent.split(",") ;     // tach chuoi thanh cac tu dua vaop ky tu ,
				if(words.length != 8  ) {
					throw new MyValidateException("chuoi khong du du lieu "+ sCunrrent) ;
				}
				
				if(EMP_STAFF.equals(words[0].trim())) {
					// tao 1 staff moi roi cap nhat lai set
					Staff s = new Staff() ; 
					s.setFullName(words[1].trim());
					s.setDepartment(words[2].trim());
					s.setPosition(EPosition.valueOf(words[3].trim()));
					s.setSalaryRatio(Float.parseFloat(words[4].trim()));
					s.setAllowance(Float.parseFloat(words[5].trim()));
					s.setNumberOfWorkingDay(Integer.parseInt(words[6].trim()));
					s.setSalary(Float.parseFloat(words[7].trim()));
					// add vao list
					this.listEmployee.add(s) ; 
					
				} else if(EMP_TEACHER.equals(words[0].trim())) {
					// tao 1 staff moi roi cap nhat lai set
					Teacher s = new Teacher() ; 
					s.setFullName(words[1].trim());
					s.setFaculty(words[2].trim());
					s.setDegree(EDegree.valueOf(words[3].trim()));
					s.setSalaryRatio(Float.parseFloat(words[4].trim()));
					s.setAllowance(Float.parseFloat(words[5].trim()));
					s.setTeachingHour(Integer.parseInt(words[6].trim()));
					s.setSalary(Float.parseFloat(words[7].trim()));
					// add vao list
					this.listEmployee.add(s) ; 
					
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			throw new MyValidateException("loi nhap sai dinh dang " + sCunrrent) ;
		}
		finally {
			if(br != null) {
				br.close();
			}
			if(fr != null ) {
				fr.close();
			}
		}
		
	}
	// GHI RA FILE
	private void save(Employee emp, String fileName) throws Exception,IOException {
		// TODO Auto-generated method stub
		BufferedWriter bw = null ; 
		FileWriter fw = null ;
		
		try {
			String data = emp.toString() ; 
			
			if(emp instanceof Staff) {
				data = EMP_STAFF + " , " + data  ; 
			}else if(emp instanceof Teacher) {
				data = EMP_TEACHER + " , " + data  ; 
			}
			
			if(listEmployee.size() > 0 ) {
				data =NEW_LINE + "" + data ;
			}
			
			File file = new File(fileName) ; // mo file 
			fw = new FileWriter(file.getAbsoluteFile() ,true) ; // cho  phep append them du lieu
			bw = new BufferedWriter(fw) ;
			
			fw.write(data) ;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new Exception("loi") ;
		} finally {
			if(bw != null) {
				bw.close();
			}
			if(fw != null) {
				fw.close();
			}
		}
		
	}

}
