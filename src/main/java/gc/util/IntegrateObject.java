package gc.util;

import gc.domain.Department;
import gc.domain.Employee;
import gc.domain.Position;

public class IntegrateObject {
	/*
	 * 由于部门和职位在Employee中是对象关联映射
	 * 所以不能直接接受参数，需要创建department对象和position对象
	 */
	public static void genericAssociation(Integer dept_id,Integer pos_id,Employee employee){
		Department department = new Department();
        department.setId(dept_id);
        Position position = new Position();
        position.setId(pos_id);
        employee.setDepartment(department);
        employee.setPosition(position);
	}
}
