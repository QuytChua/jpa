package vn.iotstar.services;

import java.util.List;

import vn.iotstar.entity.Video;

public interface IVideoService {
	int count();

	List<Video> findAll(int page, int pagesize);

	List<Video> findByVideoname(String vidname);

	List<Video> findAll();

	Video findById(String videoid);

	void delete(String videoid) throws Exception;

	void update(Video Video);

	void insert(Video Video);
}
