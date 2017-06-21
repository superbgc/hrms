package gc.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import gc.domain.Admin;
import gc.service.AdminService;
/*
 * 配置spring和junit的整合 junit启动时加载springIOC容器 spring-test,junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring 配置文件
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class SpringMybatisTest {
	@Autowired
	private AdminService adminService;
	@Test
	public void testLogin(){
		Admin admin=new Admin();
		admin.setUsername("su");
		admin.setPassword("123456");
		System.out.println(adminService.login(admin).toString());
	}
	@Test
	public void test(){
		Admin admin=new Admin();
		admin.setUsername("666");
		admin.setPassword("123456");
		adminService.addAdmin(admin);
	}
}
