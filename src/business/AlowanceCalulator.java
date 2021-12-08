package business;

import entity.EDegree;
import entity.EPosition;
import entity.Employee;
import entity.Staff;
import entity.Teacher;

/*
 * xu ly nghiep vu
 * class dung de tinh phu cap luong cua doi tuong
 */
public class AlowanceCalulator {
	public static float calculateAllowance(Employee emp) {
    	 float allowance = 0 ;
    	 if(emp instanceof Teacher) {
    		 Teacher s = (Teacher) emp ; // ep ve cung 1 kieu doi tuong giao vien
    		 if(s.getDegree()==EDegree.BACHELOR ) { // cu nhan
    			 allowance = 300 ; 
    		 }else if(s.getDegree()== EDegree.MASTER) { // thac si
    			 allowance = 500 ;
    		 }else if(s.getDegree() == EDegree.DOCTOR) { // tien si
    			 allowance = 1000 ; 
    		 }else {
    			 allowance = 0  ; 
    		 }
    	 }else if(emp instanceof Staff) {
    		 Staff s = (Staff) emp ; // ep ve cung 1 kieu doi tuong nhan vien
    		 if(s.getPosition() == EPosition.HEAD) { //truong phong
    			 allowance = 2000 ; 
    		 }else if(s.getPosition() == EPosition.VICE_HEAD) {// pho phong
    			 allowance = 1000 ; 
    		 }else if(s.getPosition() == EPosition.STAFF) {//nhan vien
    			 allowance = 500 ; 
    		 }else {
    			 allowance = 0 ;
    		 }
    		 
    	 } 
    	 
    	 return allowance ; 
     }
}
