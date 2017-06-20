package gc.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import gc.dao.AdminDao;
import gc.domain.Admin;
import gc.service.AdminService;
@Service("adminService")
public class AdminServiceImpl implements AdminService {
	@Resource
	private AdminDao adminDao;
	public Admin login(Admin admin) {
		// TODO Auto-generated method stub
		return adminDao.login(admin);
	}

	public List<Admin> findAdmins(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return adminDao.findAdmins(map);
	}

	public Integer getCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return adminDao.getCount(map);
	}

	public Integer addAdmin(Admin admin) {
		// TODO Auto-generated method stub
		return adminDao.addAdmin(admin);
	}

	public Integer updateAdmin(Admin admin) {
		// TODO Auto-generated method stub
		return adminDao.updateAdmin(admin);
	}

	public Integer deleteAdmin(Integer id) {
		// TODO Auto-generated method stub
		return adminDao.deleteAdmin(id);
	}

}
