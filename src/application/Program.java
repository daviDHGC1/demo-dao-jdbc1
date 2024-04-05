package application;

import java.time.LocalDateTime;

import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		Department obj1 = new Department(1, "eletronics");
		Seller seller = new Seller(123, 
				"Francisco", "francisco@gmail.com", LocalDateTime.now(), 1000.00, obj1);
		System.out.println(seller);
		
	}

}
