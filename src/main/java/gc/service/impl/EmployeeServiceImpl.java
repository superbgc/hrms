package gc.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import gc.dao.EmployeeDao;
import gc.domain.Employee;
import gc.domain.Post;
import gc.service.EmployeeService;
@Service
public class EmployeeServiceImpl implements EmployeeService {
	EmployeeDao employeeDao;
	public List<Post> findEmployees(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return employeeDao.findEmployees(map);
	}

	public Integer getCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return employeeDao.getCount(map);
	}

	public Integer addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return employeeDao.addEmployee(employee);
	}

	public Integer updateEmployee(Employee employee) {
		// TODO Auto-generated method stubemployeeDao
		return employeeDao.updateEmployee(employee);
	}

	public Integer deleteEmployee(String id) {
		// TODO Auto-generated method stub
		return employeeDao.deleteEmployee(id);
	}

}
