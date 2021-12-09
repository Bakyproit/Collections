package ui;

import java.util.ArrayList;
import java.util.Scanner;

import business.AllowanceCalulator;
import business.AllowanceCalulator;
import business.EmployeeManagerment;
import entity.EDegree;
import entity.EPosition;
import entity.Employee;
import entity.Staff;
import entity.Teacher;

public class StaffMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EmployeeManagerment empMan = new EmployeeManagerment();
		// lam menu
		Scanner sc = new Scanner(System.in);
		Boolean kiemTra = true;
		while (kiemTra) {
			System.out.println("University Staff Mannagement 1.0");
			System.out.println("1. add staff");
			System.out.println("2. search staff by name");
			System.out.println("3. search staff by faculty/department");
			System.out.println("4. Display all staff");
			System.out.println("5. exit");
			System.out.println("Belect function(1,2,3,4 or 5 ) :");
			int choice = sc.nextInt();
			switch (choice) {
			case 1: // add nhan vien
				Employee emp = createNewEmployee();
				// cap nhat luong phu cap
				float allowance = AllowanceCalulator.calculateAllowance(emp) ; 
				emp.setAllowance(allowance) ;
				empMan.addEmployee(emp) ; 
				break;
			case 2:// search nhan vien dua vao ten
				System.out.println("Moi ban nhap ten can tim kiem : ");
				sc = new Scanner(System.in) ; 
				String name = sc.nextLine() ; 
				ArrayList<Employee> foundByName = empMan.searchByName(name) ;
				System.out.println("Danh sach : ");
				display(foundByName) ; 
				break;

			case 3:// search nhan vien dua vao khoa / phong ban
				System.out.println("moi ban nhap vao khoa || phong ban");
				String dept = sc.nextLine() ; 
				ArrayList<Employee> foundByDept = empMan.searchByFacultyAndDepartment(dept) ;
                System.out.println("Danh sach : ");
				display(foundByDept) ; 
				break;
			case 4: // hien thi tat ca danh sach
				ArrayList<Employee> listAll = empMan.sortListAll() ; 
				display(listAll) ; 
				break;

			case 5:
				kiemTra = false;
				System.out.println("Chuong trinh da ket thuc...Hen gap lai!");
			}
		}
		sc.close();
	}

	private static void display(ArrayList<Employee> listE) {
		System.out.println("Results : ");
		System.out.println("Name , faculty/departmenbt , degree/Position  , allowance ,teachingHour / numberOfWorkingDay , salryRatio");
		for (Employee employee : listE) {
			System.out.println(employee);
		}
		
	}

	private static Employee createNewEmployee() {
		System.out.println("Neu ban muon them nhan vien an phim 's' , them giao vien an phim 't'");
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		if (str.equalsIgnoreCase("s")) {
			Staff s = new Staff();

			// nhap thong tin nhan vien
			System.out.println("Moi ban nhap thong tin nhan vien");
			System.out.println("Ten nhan vien : ");
			String tenNV = sc.nextLine();

			System.out.println("Ten phong ban : ");
			String tenPhongBan = sc.nextLine();

			System.out.println("Nhap chuc vu  : //1. truong phong 2.pho phong 3.nhan vien ");
			int chucVu = sc.nextInt();

			System.out.println("So ngay lam viec: ");
			int soNgayLamViec = sc.nextInt();

			System.out.println("He so luong : ");
			float heSoLuong = sc.nextFloat();

			// set lai thong tin ;
			s.setFullName(tenNV);
			s.setDepartment(tenPhongBan);
			s.setNumberOfWorkingDay(soNgayLamViec);
			s.setSalaryRatio(heSoLuong);

			if (chucVu == 1) {
				s.setPosition(EPosition.HEAD);
			} else if (chucVu == 2) {
				s.setPosition(EPosition.VICE_HEAD);
			} else if (chucVu == 3) {
				s.setPosition(EPosition.STAFF);
			}
			return s;

		} else if (str.equalsIgnoreCase("t")) {
			Teacher t = new Teacher();
			// nhap thong tin giao vien
			System.out.println("Moi ban nhap thong tin giao vien");
			System.out.println("Ten giao vien : ");
			String tenGV = sc.nextLine();

			System.out.println("Ten khoa : ");
			String tenKhoa = sc.nextLine();

			System.out.println("Nhap chuc vu  : //1. cu nhan 2.thac si 3.tien si ");
			int trinhDo = sc.nextInt();

			System.out.println("So tiet giang day : ");
			int soTietGiangDay = sc.nextInt();

			System.out.println("He so luong : ");
			float heSoLuong = sc.nextFloat();
            
			// set lai thong tin
			t.setFullName(tenGV) ;
			t.setFaculty(tenKhoa) ;
			t.setTeachingHour(soTietGiangDay) ; 
			t.setSalaryRatio(heSoLuong) ; 
			//
			if(trinhDo == 1) {
				t.setDegree(EDegree.BACHELOR) ; 
			}else if(trinhDo == 2 ) {
				t.setDegree(EDegree.MASTER) ; 
			}else if(trinhDo == 3 ) {
				t.setDegree(EDegree.DOCTOR) ; 
			}
			
			return t;
		}else {
			
		}
		return null;
	}

}
