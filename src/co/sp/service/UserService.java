package co.sp.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.sp.beans.User;
import co.sp.dao.UserDao;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Resource(name = "loginBean")
	private User loginBean;
	
	public boolean checkuserIdExist(String user_id) {
		
		String user_name = userDao.checkUserIdExist(user_id);
		
		if(user_name == null) {
			return true;
		} else {
			return false;
		}
	}
	
	public void addUserInfo(User joinUserBean) {
		userDao.addUserInfo(joinUserBean);
	}
	
	public void getLoginUserInfo(User tempLoginUserBean) {
		
		User tempLoginUserBean2 = userDao.getLoginUserInfo(tempLoginUserBean);
		
		if(tempLoginUserBean2 != null) {
			loginBean.setUser_idx(tempLoginUserBean2.getUser_idx());
			loginBean.setUser_name(tempLoginUserBean2.getUser_name());
			loginBean.setUserLogin(true);
		}
	}
	
	public void getModifyUserInfo(User modifyUserBean) {
		User tempModifyUserBean = userDao.getModifyUserInfo(loginBean.getUser_idx());
		
		modifyUserBean.setUser_id(tempModifyUserBean.getUser_id());
		modifyUserBean.setUser_name(tempModifyUserBean.getUser_name());
		modifyUserBean.setUser_idx(loginBean.getUser_idx());
	}
	
	public void modifyUserInfo(User modifyUserBean) {
		
		modifyUserBean.setUser_idx(loginBean.getUser_idx());
		
		userDao.modifyUserInfo(modifyUserBean);
	}
}











