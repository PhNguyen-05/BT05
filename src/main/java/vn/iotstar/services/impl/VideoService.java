package vn.iotstar.services.impl;

import java.util.List;
import vn.iotstar.dao.IVideoDao;
import vn.iotstar.dao.impl.VideoDao;
import vn.iotstar.entity.Video;
import vn.iotstar.services.IVideoService;

public class VideoService implements IVideoService {
    IVideoDao dao = new VideoDao();

    @Override
    public void insert(Video video) { dao.insert(video); }
    @Override
    public void update(Video video) { dao.update(video); }
    @Override
    public void delete(String videoId) throws Exception { dao.delete(videoId); }
    @Override
    public Video findById(String videoId) { return dao.findById(videoId); }
    @Override
    public List<Video> findAll() { return dao.findAll(); }
    @Override
    public List<Video> findByTitle(String title) { return dao.findByTitle(title); }
    @Override
    public int count() { return dao.count(); }
    @Override
    public List<Video> findAll(int page, int pageSize) { return dao.findAll(page, pageSize); }
}
