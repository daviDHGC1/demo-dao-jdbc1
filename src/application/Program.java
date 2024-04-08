package application;

import java.util.Date;
import java.util.List;

import db.DB;
import model.dao.impl.SellerDaoJDBC;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		Seller seller = new Seller();
		SellerDaoJDBC sellerDaoJDBC = new SellerDaoJDBC(DB.getConnection());
		
		//Finding by id
		System.out.println("Finding by Id");
		seller = sellerDaoJDBC.findById(10);
		System.out.println(seller);
		
		row();
		
		/*System.out.println("Finding by Department");
		//Finding by department
		Department dep = new Department(10, null);
		List<Seller> list = sellerDaoJDBC.findByDepartment(dep);
		for(Seller sellers : list){
			System.out.println(sellers);
		}*/
		
		List<Seller> list = sellerDaoJDBC.findAll();
		list.stream().forEach(System.out::println );
		
		row();

		Department department = new Department(2, null);
		Seller newSeller = new Seller(null, "Greg", "greg@gmail.com",  new Date(), 4000.0, department);
		sellerDaoJDBC.insert(newSeller);
		System.out.println("Inserted! New id = " + newSeller.getId());
		
	}
	public static void row() {
		System.out.println("=================================================================================================================================================");
	}
}
