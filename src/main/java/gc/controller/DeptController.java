package gc.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gc.domain.Department;
import gc.service.DepartmentService;
import gc.util.ResponseUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/dept")
public class DeptController {
	@Resource
	private DepartmentService departmentService;
	public String list(Department department,HttpServletResponse response) throws IOException{
		  Map<String, Object> map = new HashMap<String, Object>();
	        // 判断查询条件是否为空，如果是，对条件做数据库模糊查询的处理
	        if (department.getName() != null
	                && !"".equals(department.getName().trim())) {
	            map.put("name", "%" + department.getName() + "%");
	        }
	        List<Department> deptList = departmentService.findDepartments(map);
	        Integer total = departmentService.getCount(map);
	        JSONObject result = new JSONObject();
	        JSONArray jsonArray = JSONArray.fromObject(deptList);
	        result.put("rows", jsonArray);
	        result.put("total", total);
	        ResponseUtil.write(response, result);
	        return null;
	}
	//增加部门 或者修改部门
    @RequestMapping("/save")
    public String save(Department department, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        int resultTotal = 0;
        // 如果 id 不为空，则添加部门，否则修改部门
        if (department.getId() == null)
            resultTotal = departmentService.addDepartment(department);
        else
            resultTotal = departmentService.updateDepartment(department);
        JSONObject result = new JSONObject();
        if (resultTotal > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        ResponseUtil.write(response, result);
        return null;
    }
    @RequestMapping("/delete")
    public String delete(@RequestParam(value = "ids") String ids,
            HttpServletResponse response) throws Exception {
        JSONObject result = new JSONObject();
        // 将要删除的部门的 id 进行处理
        String[] idsStr = ids.split(",");
        for (int i = 0; i < idsStr.length; i++) {
            // 捕获 service 层抛出的异常，如果捕获到则置 success 值为 false，返回给前端
            try {
                departmentService.deleteDepartment(Integer.parseInt(idsStr[i]));
                result.put("success", true);
            } catch (Exception e) {
                result.put("success", false);
            }                    
        }
        ResponseUtil.write(response, result);
        return null;
    }
	
}
