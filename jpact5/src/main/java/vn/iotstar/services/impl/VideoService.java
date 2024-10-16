package vn.iotstar.services.impl;

import java.util.List;

import vn.iotstar.dao.IVideoDao;
import vn.iotstar.dao.impl.VideoDao;
import vn.iotstar.entity.Video;
import vn.iotstar.services.IVideoService;

public class VideoService implements IVideoService {
	IVideoDao vidDao = new VideoDao();
	@Override
	public int count() {
		return vidDao.count();
	}

	@Override
	public List<Video> findAll(int page, int pagesize) {
		return vidDao.findAll(page,pagesize);

	}

	@Override
	public List<Video> findByVideoname(String vidname) {
		return vidDao.findByVideoname(vidname);
	}

	@Override
	public List<Video> findAll() {
		return vidDao.findAll();
	}

	@Override
	public Video findById(String videoid) {
		return vidDao.findById(videoid);
	}

	@Override
	public void delete(String videoid) throws Exception {
		vidDao.delete(videoid);

	}

	@Override
	public void update(Video Video) {
		vidDao.update(Video);
		
	}

	@Override
	public void insert(Video Video) {
		vidDao.insert(Video);
	}
}
