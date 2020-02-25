package com.ak.dao;

import java.util.List;

import com.ak.model.Emp;

public interface EmpDAO {
	public List<Emp> listEmp();
	public int insertEmp(Emp emp);
	public int updateEmp(Emp emp);
	public int deletetEmp(int id);
	public Emp getEmpByID(int id);
}
