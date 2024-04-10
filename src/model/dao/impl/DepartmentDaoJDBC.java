package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {

	private Connection conn = null;

	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Department department) {
		PreparedStatement st = null;
		try {

			st = conn.prepareStatement("INSERT INTO department (Name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
			st.setNString(1, department.getName());
			int affectedRows = st.executeUpdate();
			
			if(affectedRows > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()){
					int id = rs.getInt(1);
					department.setId(id);
			}
			else {
				throw new DbException("Error! None department was inserted");
			}
	    	} 	
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}

	}

	@Override
	public void update(Department department) {
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement("UPDATE department SET Name = ? WHERE Id = ?");	
			st.setString(1, department.getName());
			st.setInt(2, department.getId());
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected < 1) {
				throw new DbException("ERROR: None data updated!");
			}
		}
		catch(SQLException e){
			throw new DbException(e.getMessage());
		}

	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
		
			st = conn.prepareStatement("DELETE FROM department WHERE id = ?");
			st.setInt(1, id);
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected < 1) {
				throw new DbException("Id not found!");
			}
			
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	@Override
	public Department findById(Integer id) {

		PreparedStatement st = null;

		try {

			st = conn.prepareStatement("SELECT * FROM " + "department " + "WHERE " + "id = ?");
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				Department dep = instantiateDepartment(rs);
				return dep;
			} else {
				return null;
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	@Override
	public List<Department> findAll() {
		PreparedStatement st = null;

		try {
			st = conn.prepareStatement("SELECT * " + "FROM department " + "ORDER BY Id");
			ResultSet rs = st.executeQuery();

			List<Department> listDep = new ArrayList<>();

			while (rs.next()) {
				listDep.add(instantiateDepartment(rs));
			}

			return listDep;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department(rs.getInt("Id"), rs.getString("Name"));
		return dep;

	}

}
