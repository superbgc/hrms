package gc.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gc.domain.Admin;
import gc.domain.Post;
import gc.service.PostService;
import gc.util.DateUtil;
import gc.util.JsonDateValueProcessor;
import gc.util.ResponseUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@RequestMapping("/post")
public class PostController {
	@Resource
	private PostService postService;
	//处理查询公告请求
	@RequestMapping("/list")
	public String list(Post post,HttpServletResponse response) throws IOException{
		Map<String,Object> map=new HashMap<String,Object>();
		//判断查询条件是否为空  如果是，对条件做数据库模糊查询的处理
		if(post.getTitle()!=null&&!"".equals(post.getTitle().trim())){
			map.put("title","%"+post.getTitle()+"%");	
		}
		List<Post> postList=postService.findPosts(map);
		Integer total=postService.getCount(map);
		//处理日期之前能在easyUI的datagrid中正常显示
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		//将数据以JSON格式返回给前端
		JSONObject result=new JSONObject();
		JSONArray jsonArray=JSONArray.fromObject(postList,jsonConfig);
		result.put("rows", jsonArray);
		result.put("total", total);
		ResponseUtil.write(response, result);
		return null;
		}
	//保存公告
	public String save(Post post,HttpServletRequest request,HttpServletResponse response, HttpSession session) throws ParseException, IOException{
		Admin admin =(Admin)session.getAttribute("currentAdmin");
		post.setAdmin(admin);
		post.setDate(DateUtil.getDate());
		int resultTotal=0;
		//如果id不为空 则添加公告  否则修改公告
		if(post.getId()==null)
		    resultTotal = postService.addPost(post);
        else
            resultTotal = postService.updatePost(post);
        JSONObject result = new JSONObject();
        if (resultTotal > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        ResponseUtil.write(response, result);
        return null;
	}
	//处理删除公告请求
	@RequestMapping(value="/delete")
	public String delete(@RequestParam(value="ids") String ids,HttpServletResponse response,HttpSession session) throws IOException{
		JSONObject result=new JSONObject();
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			postService.deletePost(Integer.parseInt(idsStr[i]));
		}
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
	//根据id处理查询公告
	@RequestMapping("/getById")
	public String getById(@RequestParam(value="id") Integer id,HttpServletResponse response,HttpServletRequest request){
		Post post=postService.getPostById(id);
		request.setAttribute("postContent", post.getContent());
		return null;
	}
	
}
