package ui;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import business.AllowanceCalulator;
import business.AllowanceCalulator;
import business.EmployeeManagerment;
import entity.EDegree;
import entity.EPosition;
import entity.Employee;
import entity.Staff;
import entity.Teacher;
import exception.MyValidateException;

public class StaffMain {

	public static void main(String[] args) throws Exception,MyValidateException {
		// TODO Auto-generated method stub
		// lam menu
		Scanner sc = new Scanner(System.in);

		
		int choice = 0;
		Boolean kiemTra = true;
		do {
			try {
				EmployeeManagerment empMan = new EmployeeManagerment();
				while (kiemTra) {
					System.out.println("University Staff Mannagement 1.0");
					System.out.println("1. add staff");
					System.out.println("2. search staff by name");
					System.out.println("3. search staff by faculty/department");
					System.out.println("4. Display all staff");
					System.out.println("5. exit");
					System.out.println("Belect function(1,2,3,4 or 5 ) :");
					choice = sc.nextInt();
					switch (choice) {
					case 1: // add nhan vien
						Employee emp = createNewEmployee();
						// cap nhat luong phu cap
						float allowance = AllowanceCalulator.calculateAllowance(emp);
						emp.setAllowance(allowance);
						empMan.addEmployee(emp);
						break;
					case 2:// search nhan vien dua vao ten
						System.out.println("Moi ban nhap ten can tim kiem : ");
						sc = new Scanner(System.in);
						String name = null;
						do {
							try {
								name = sc.nextLine();
								if (name.isEmpty()) {
									throw new MyValidateException("Name khong duoc de trong...");
								} else if (!name.matches("^[a-zA-Z\\s]+$")) {
									throw new MyValidateException("name khong duoc chua ky tu vs so ...");
								}
							} catch (MyValidateException e) {
								System.out.println(e.getErrorMessage());
								sc.next() ;
							}
						} while (name == null || !name.matches("^[a-zA-Z\\s]+$"));
						ArrayList<Employee> foundByName = empMan.searchByName(name);
						System.out.println("Danh sach : ");
						display(foundByName);
						break;

					case 3:// search nhan vien dua vao khoa / phong ban
						System.out.println("moi ban nhap vao khoa || phong ban");
						String dept = sc.nextLine();
						do {
							try {
								dept = sc.nextLine();
								if (dept.isEmpty()) {
									throw new MyValidateException("dept khong duoc de trong...");
								} else if (!dept.matches("^[\\w\\s]+$")) {
									throw new MyValidateException("dept khong duoc chua ky tu ...");
								}
							} catch (MyValidateException e) {
								System.out.println(e.getErrorMessage());
								sc.next() ;
							}
						} while (dept == null || !dept.matches("^[\\w\\s]+$"));
						ArrayList<Employee> foundByDept = empMan.searchByFacultyAndDepartment(dept);
						System.out.println("Danh sach : ");
						display(foundByDept);
						break;
					case 4: // hien thi tat ca danh sach
						ArrayList<Employee> listAll = empMan.sortListAll();
						display(listAll);
						break;

					case 5:
						kiemTra = false;
						System.out.println("Chuong trinh da ket thuc...Hen gap lai!");

					}
				}
			} catch (InputMismatchException e) {
				System.out.println("nhap sai dinh dang...");
				sc.next();
			} catch (MyValidateException e) {
				System.out.println(e.getErrorMessage());
				sc.next() ;
			}
		} while (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5);

		sc.close();
	}

	private static void display(ArrayList<Employee> listE) {
		System.out.println("Results : ");
		System.out.println(
				"Name , faculty/departmenbt , degree/Position  , allowance ,teachingHour / numberOfWorkingDay , salryRatio");
		for (Employee employee : listE) {
			System.out.println(employee);
		}

	}

	private static Employee createNewEmployee() throws MyValidateException {
		System.out.println("Neu ban muon them nhan vien an phim 's' , them giao vien an phim 't'");
		Scanner sc = new Scanner(System.in);
		String str = null;
		do {
			try {
				str = sc.nextLine();
				if (!str.equalsIgnoreCase("s") && !str.equalsIgnoreCase("t")) {
					throw new MyValidateException("ban chi co the chon s hoac t ...");
				}

			} catch (MyValidateException e) {
				System.out.println(e.getErrorMessage());
				
			}
		} while (!str.equalsIgnoreCase("s") && !str.equalsIgnoreCase("t"));

		if (str.equalsIgnoreCase("s")) {
			Staff s = new Staff();

			// nhap thong tin nhan vien
			System.out.println("Moi ban nhap thong tin nhan vien");
			// nhap ten nhan vien
			System.out.println("Ten nhan vien : ");
			String tenNV = null ;
			do {
				try {
				    tenNV = sc.nextLine();
				    
					if (tenNV.isEmpty()) {
						throw new MyValidateException("tenNV khong duoc de trong...");
					} else if (!tenNV.matches("^[a-zA-Z\\s]+$")) {
						throw new MyValidateException("tenNV khong duoc chua ky tu va so...");
					}
				} catch (MyValidateException e) {
					System.out.println(e.getErrorMessage());
				} 
			} while (tenNV == null || !tenNV.matches("^[a-zA-Z\\s]+$"));
			System.out.println(tenNV);
			// nhap ten phong ban
			System.out.println("Ten phong ban : ");
			String tenPhongBan = null;
			do {
				try {
					tenPhongBan = sc.nextLine();
					if (tenPhongBan.isEmpty()) {
						throw new MyValidateException("tenPhongBan khong duoc de trong...");
					} else if (!tenPhongBan.matches("^[\\w\\s]+$")) {
						throw new MyValidateException("tenPhongBan khong duoc chua ky tu ...");
					}
				} catch (MyValidateException e) {
					System.out.println(e.getErrorMessage());
				}
			} while (tenPhongBan == null || !tenPhongBan.matches("^[\\w\\s]+$"));

			// nhap chucvu
			int chucVu = 0;
			do {
				try {
					System.out.println("Nhap chuc vu  : //1. truong phong 2.pho phong 3.nhan vien ");
					chucVu = sc.nextInt();

				} catch (Exception e) {
					System.out.println(e);
					System.out.println("ban nhap sai dinh dang");
					sc.next();
				}
			} while (chucVu != 1 && chucVu != 2 && chucVu != 3);

			// nhap so ngay lam viec
			int soNgayLamViec = 0;
			do {
				try {
					System.out.println("So ngay lam viec: ");
					soNgayLamViec = sc.nextInt();

				} catch (Exception e) {
					System.out.println(e);
					sc.next();
				}
			} while (soNgayLamViec < 0 || soNgayLamViec == 0);
			// nhap he so luong
			float heSoLuong = 0;
			do {
				try {
					System.out.println("He so luong : ");
					heSoLuong = sc.nextFloat();
				} catch (Exception e) {
					System.out.println(e);
					sc.next();
				}

			} while (heSoLuong <= 0);

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

			// nhap ten giao vien
			
			System.out.println("Ten giao vien : ");
			String tenGV = null;
			do {
				try {
					tenGV = sc.nextLine();
					if (tenGV.isEmpty()) {
						throw new MyValidateException("tenGV khong duoc de trong...");
					} else if (!tenGV.matches("^[a-zA-Z\\s]+$")) {
						throw new MyValidateException("tenGV khong duoc chua ky tu va so...");
					}
				} catch (MyValidateException e) {
					System.out.println(e.getErrorMessage());
				}
			} while (tenGV == null || !tenGV.matches("^[a-zA-Z\\s]+$"));

			// ten khoa

			System.out.println("Ten khoa : ");
			String tenKhoa = null;
			do {
				try {
					tenKhoa = sc.nextLine();
					if (tenKhoa.isEmpty()) {
						throw new MyValidateException("tenKhoa khong duoc de trong...");
					} else if (!tenKhoa.matches("^[\\w\\s]+$")) {
						throw new MyValidateException("tenKhoa khong duoc chua ky tu ...");
					}
				} catch (MyValidateException e) {
					System.out.println(e.getErrorMessage());
				}
			} while (tenKhoa == null || !tenKhoa.matches("^[\\w\\s]+$"));

			// nhap chuc vu

			int trinhDo = 0;
			do {
				try {
					System.out.println("Nhap chuc vu  : //1. cu nhan 2.thac si 3.tien si ");
					trinhDo = sc.nextInt();

				} catch (Exception e) {
					System.out.println(e);
					System.out.println("ban nhap sai dinh dang");
					sc.next();
				}
			} while (trinhDo != 1 && trinhDo != 2 && trinhDo != 3);

			// so tiet giang day
		
			
			int soTietGiangDay = 0;
			do {
				try {
					System.out.println("So tiet giang day : ");
					soTietGiangDay = sc.nextInt();

				} catch (Exception e) {
					System.out.println(e);
					sc.next();
				}
			} while (soTietGiangDay < 0 || soTietGiangDay == 0);
			// nhap he so luong
			float heSoLuong = 0;
			do {
				try {
					System.out.println("He so luong : ");
					heSoLuong = sc.nextFloat();
				} catch (Exception e) {
					System.out.println(e);
					sc.next();
				}

			} while (heSoLuong <= 0);

			// set lai thong tin
			t.setFullName(tenGV);
			t.setFaculty(tenKhoa);
			t.setTeachingHour(soTietGiangDay);
			t.setSalaryRatio(heSoLuong);
			//
			if (trinhDo == 1) {
				t.setDegree(EDegree.BACHELOR);
			} else if (trinhDo == 2) {
				t.setDegree(EDegree.MASTER);
			} else if (trinhDo == 3) {
				t.setDegree(EDegree.DOCTOR);
			}

			return t;
		} else {

		}
		return null;
	}

}
