package vn.iotstar.dao;

import java.util.List;

import vn.iotstar.entity.Video;

public interface IVideoDao {
	int count();

	List<Video> findAll(int page, int pagesize);

	List<Video> findByVideoname(String vidname);

	List<Video> findAll();

	Video findById(String videoid);

	void delete(String videoid) throws Exception;

	void update(Video video);

	void insert(Video video);
}
