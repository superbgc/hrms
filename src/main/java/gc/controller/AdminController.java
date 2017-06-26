package gc.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gc.domain.Admin;
import gc.service.AdminService;
import gc.util.ResponseUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Resource
	private AdminService adminService;
	/**
	 * 
	 * @param admin
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/login")
	public String login(Admin admin,HttpServletRequest request,HttpSession session){
		Admin resultadmin=adminService.login(admin);
		if(resultadmin==null){
			request.setAttribute("admin", admin);
			request.setAttribute("errorMsg", "Please check your username and password!");
			return "login";
		}else{
			//登录成功 Session保存该管理员的信息
			session.setAttribute("currentAdmin", resultadmin);
			session.setAttribute("username", resultadmin.getUsername());
			return "redirect:main";
		}
		
	}
	//处理跳转至主页
	@RequestMapping("/main")
	public String test(Model model){
		
		return "home_page";
	}
	//处理查询管理员请求
	@RequestMapping("/list")
	public String list(Admin admin,HttpServletResponse response) throws IOException{
		Map<String,Object> map=new HashMap<String,Object>();
		//判断查询条件是否为空 如果是，对条件做数据库模糊查询的处理
		if(admin.getUsername()!=null&&!"".equals(admin.getUsername().trim())){
			map.put("username","%"+admin.getUsername()+"%");
		}
		List<Admin> adminList=adminService.findAdmins(map);
		Integer total=adminService.getCount(map);
		//将查询到的数据已json形式返回给前端
		JSONObject result=new JSONObject();
		JSONArray jsonArray=JSONArray.fromObject(adminList);
		result.put("rows", jsonArray);
		result.put("total",total);
		ResponseUtil.write(response, result);
		return null;
	}
	//save
	@RequestMapping("/delete")
	public String save(Admin admin,HttpServletRequest request,HttpServletResponse response) throws IOException{
		int resultTotal=0;
		//如果id不为空 则添加管理员 否则修改管理员
		if(admin.getId()==null){
			resultTotal=adminService.addAdmin(admin);
		}else{
			resultTotal=adminService.updateAdmin(admin);
		}
		JSONObject result=new JSONObject();
		if(resultTotal>0){
			result.put("success", true);
		}else{
			result.put("success", false);
		}
		ResponseUtil.write(response, result);
		return null;
	}
	@RequestMapping(value="/delete")
	public String delete(@RequestParam(value="ids")String ids,HttpServletResponse response,HttpSession session) throws IOException{
		JSONObject result=new JSONObject();
		//将要删除的管理员的id进行处理
		String[] idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			//不能删除超级管理员（superadmin）和当前登录的管理员
			if(idsStr[i].equals("1")||idsStr[i].equals(((Admin)session.getAttribute("currentAdmin")).getId().toString())){
				result.put("success", false);
				continue;
			}else{
				adminService.deleteAdmin(Integer.parseInt(ids));
				result.put("success", true);
			}		
		}
		ResponseUtil.write(response, result);
		return null;
	}
	//退出接口
	public String logout(HttpSession session) throws Exception {
		//去除session里面保存的数据
		session.invalidate();
		return "redirect:/login.jsp";
	}
}
