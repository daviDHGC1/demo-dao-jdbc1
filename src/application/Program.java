package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		Seller seller = new Seller();
		SellerDao sellerDaoJDBC = DaoFactory.createSellerDao();
		
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
		
		/*System.out.println("Inserting new datas!");
		Department department = new Department(2, null);
		Seller newSeller = new Seller(null, "Greg", "greg@gmail.com",  new Date(), 4000.0, department);
		sellerDaoJDBC.insert(newSeller);
		System.out.println("Inserted! New id = " + newSeller.getId());*/
		
		System.out.println("Updating seller!");
		Seller updateSeller = sellerDaoJDBC.findById(10);
		updateSeller.setName("John Árias");
		updateSeller.setBaseSalary(9000.0);
		sellerDaoJDBC.update(updateSeller);
		System.out.println("Update seller completed!");
		
		row();
		
		//System.out.println("Deleting seller by id!");
		//sellerDaoJDBC.deleteById(6);
		//System.out.println("Delete seller completed!");
	}
	public static void row() {
		System.out.println("=================================================================================================================================================");
	}
}
