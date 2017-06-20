package gc.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import gc.dao.PostDao;
import gc.domain.Post;
import gc.service.PostService;
@Service("postService")
public class PostServiceImpl implements PostService {

    // 自动注入 DAO 对象
    @Resource
    private PostDao postDao;

	public List<Post> findPosts(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return postDao.findPosts(map);
	}

	public Integer getCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return postDao.getCount(map);
	}

	public Integer addPost(Post post) {
		// TODO Auto-generated method stub
		return postDao.addPost(post);
	}

	public Integer updatePost(Post post) {
		// TODO Auto-generated method stub
		return postDao.updatePost(post);
	}

	public Integer deletePost(Integer id) {
		// TODO Auto-generated method stub
		return postDao.deletePost(id);
	}

	public Post getPostById(Integer id) {
		// TODO Auto-generated method stub
		return postDao.getPostById(id);
	}





}
