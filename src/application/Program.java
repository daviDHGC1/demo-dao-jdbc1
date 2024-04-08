package application;

import db.DB;
import model.dao.impl.SellerDaoJDBC;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		Seller seller = new Seller();
		SellerDaoJDBC sellerDaoJDBC = new SellerDaoJDBC(DB.getConnection());
		
		//Finding by id
		seller = sellerDaoJDBC.findById(11);
		System.out.println(seller);
		
		
		
		
	}

}
