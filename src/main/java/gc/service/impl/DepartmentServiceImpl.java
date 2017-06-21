package gc.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import gc.dao.DepartmentDao;
import gc.domain.Department;
import gc.service.DepartmentService;
@Service
public class DepartmentServiceImpl implements DepartmentService {
	@Resource
	DepartmentDao departmentDao;
	public List<Department> findDepartments(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return departmentDao.findDepartments(map);
	}

	public Integer getCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return departmentDao.getCount(map);
	}

	public Integer addDepartment(Department department) {
		// TODO Auto-generated method stub
		return departmentDao.addDepartment(department);
	}

	public Integer updateDepartment(Department department) {
		// TODO Auto-generated method stub
		return departmentDao.updateDepartment(department);
	}
	public Integer deleteDepartment(Integer id) {
		// TODO Auto-generated method stub
		Integer flag=null;
		try {
			flag = departmentDao.deleteDepartment(id);
		} catch (Exception e) {
			 throw new RuntimeException();
		}
		return flag;
	}
	
}
