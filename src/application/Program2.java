package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		
		DepartmentDao depDao = DaoFactory.createDepartmentDao();
		
		System.out.println("=== TEST 1: findById =======");
		Department depId = depDao.findById(2);
		System.out.println(depId);
		System.out.println("Found!");
		
		System.out.println("\n=== TEST 2: findAll =======");
		List<Department> listDep = depDao.findAll();
		listDep.stream().forEach(System.out::println);
		System.out.println("All were found!");
		
		/*System.out.println("\n=== TEST 3: insert =======");
		Department depInsert = new Department(null, "Music");
		depDao.insert(depInsert);
		System.out.println("The new department was inserted! Its new id now is " + depInsert.getId());
		*/
		
		System.out.println("\n=== TEST 4: update =======");
		Department depUpdate = depDao.findById(9);
		depUpdate.setName("Cooking");
		depDao.update(depUpdate);
		System.out.println("Updated! New updates: " + depUpdate);
		
		System.out.println("\n=== TEST 5: delete =======");
		depDao.deleteById(8);
		System.out.println("Delete completed!");
	}

}
