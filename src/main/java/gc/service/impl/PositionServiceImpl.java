package gc.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import gc.dao.PositionDao;
import gc.domain.Position;
import gc.service.PositionService;
@Service
public class PositionServiceImpl implements PositionService{
	PositionDao positionDao;
	public List<Position> findPositions(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return positionDao.findPositions(map);
	}

	public Integer getCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return positionDao.getCount(map);
	}

	public Integer addPosition(Position position) {
		// TODO Auto-generated method stub
		return positionDao.addPosition(position);
	}

	public Integer updatePosition(Position position) {
		// TODO Auto-generated method stub
		return positionDao.addPosition(position);
	}

	public Integer deletePosition(Integer id) {
		// TODO Auto-generated method stub
		Integer flag=null;
		try {
			flag=positionDao.deletePosition(id);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException();
		}
		return flag;
	}
	
}
