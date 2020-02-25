package com.ak.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ak.dao.EmpDAO;
import com.ak.dao.EmpDAOImpl;
import com.ak.model.Emp;

@Controller
public class EmpController {

	@Autowired
	EmpDAO dao = new EmpDAOImpl();
	
	//RequestMapping(value = "/EmpList", method = RequestMethod.GET)
	//@RequestMapping(value = "/EmpList", method = RequestMethod.POST)
	@RequestMapping("/EmpList")
	public String listEmp(Model m) {
		List<Emp> list = dao.listEmp();
		m.addAttribute("list", list);
		return "EmpList";
	}
	
	@RequestMapping("/EmpEntry")
	public String empEntry(Model m) {
		m.addAttribute("command",new Emp());
		return "EmpEntry";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("emp") Emp emp) {
		dao.insertEmp(emp);
		return "redirect:/EmpList";
	}
	
	@RequestMapping(value = "/editsave", method = RequestMethod.POST)
	public String editsave(@ModelAttribute("emp") Emp emp) {
		dao.updateEmp(emp);
		return "redirect:/EmpList";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable int id) {
		dao.deletetEmp(id);
		return "redirect:/EmpList";
	}
	
	@RequestMapping(value="/update/{id}")    
    public String update(@PathVariable int id, Model m){    
        Emp emp = dao.getEmpByID(id);  
        m.addAttribute("command",emp);  
        return "EmpEditForm";    
    } 

}
