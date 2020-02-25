package com.ak.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;

import com.ak.model.Emp;

public class EmpDAOImpl implements EmpDAO {
	
	JdbcTemplate template;
	
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	@Override
	public List<Emp> listEmp() {
		String sql = "select * from emp";
		return template.query(sql, new RowMapper<Emp>(){

			@Override
			public Emp mapRow(ResultSet rs, int rowNum) throws SQLException {
				Emp e = new Emp();
				e.setId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setSalary(rs.getFloat(3));
				e.setDesignation(rs.getString(4));
				return e;
			}
			
		});
	}

	@Override
	public int insertEmp(Emp emp) {
		String sql = "insert into emp values(?,?,?,?)";
		return template.execute(sql, new PreparedStatementCallback<Integer>() {

			@Override
			public Integer doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setInt(1, emp.getId());
				ps.setString(2, emp.getName());
				ps.setFloat(3, emp.getSalary());
				ps.setString(4, emp.getDesignation());
				return ps.executeUpdate();
			}
		});
	}

	@Override
	public int updateEmp(Emp emp) {
		String sql = "update emp set name = ?, salary = ?, designation = ? where id = ?";
		return template.execute(sql, new PreparedStatementCallback<Integer>() {

			@Override
			public Integer doInPreparedStatement(PreparedStatement ps) 
					throws SQLException, DataAccessException {
				ps.setString(1, emp.getName());
				ps.setFloat(2, emp.getSalary());
				ps.setString(3, emp.getDesignation());
				ps.setInt(4, emp.getId());
				return ps.executeUpdate();
			}
			
		});
	}

	@Override
	public int deletetEmp(int id) {
		String sql = "delete from emp where id = ?";
		return template.update(sql, new Object[] {id});
	}

	@Override
	public Emp getEmpByID(int id) {
		String sql = "select * from emp where id=?";
		return template.queryForObject(sql, new Object[] {id}, 
				new BeanPropertyRowMapper<Emp>(Emp.class));
				
	}
}
