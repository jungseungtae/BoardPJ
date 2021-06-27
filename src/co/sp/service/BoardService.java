package co.sp.service;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import co.sp.beans.Content;
import co.sp.beans.Page;
import co.sp.beans.User;
import co.sp.dao.BoardDao;

@Service
@PropertySource("/WEB-INF/properties/option.properties")
public class BoardService {
	
	@Value("${page.listcount}")
	private int page_listcount;
	
	@Value("${page.pa}")
	private int page_pa;
	
	@Autowired
	private BoardDao boardDao;
	
	@Resource(name = "loginBean")
	private User loginBean;
	
	
	public void addContentInfo(Content writeContentBean) {
		
		writeContentBean.setContent_writer_idx(loginBean.getUser_idx());
		
		boardDao.addContentInfo(writeContentBean);
	}
	
	public String getBoardInfoName(int board_info_idx) {
		return boardDao.getBoardInfoName(board_info_idx);
	}
	
	public List<Content> getContentList(int board_info_idx, int page){
		
		int start = (page - 1) * page_listcount;
		RowBounds rowBounds = new RowBounds(start, page_listcount);
		
		return boardDao.getContentList(board_info_idx, rowBounds);
	}
	
	public Content getContentInfo(int content_idx) {
		return boardDao.getContentInfo(content_idx);
	}
	
	public void modifyContentInfo(Content modifyContentBean) {
		
		boardDao.modifyContentInfo(modifyContentBean);
	}
	
	public void deleteContentInfo(int content_idx) {
		boardDao.deleteContentInfo(content_idx);
	}
	
	public Page getContentCnt(int content_board_idx, int currentPage) {
		
		int content_cnt = boardDao.getContentCnt(content_board_idx);
		
		Page pageBean = new Page(content_cnt, currentPage, page_listcount, page_pa);
		
		return pageBean;
	}
}








