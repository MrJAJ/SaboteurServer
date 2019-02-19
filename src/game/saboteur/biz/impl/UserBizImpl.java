package game.saboteur.biz.impl;

import java.util.Vector;

import game.saboteur.biz.UserBiz;
import game.saboteur.dao.UserDao;
import game.saboteur.dao.impl.UserDaoImpl;
import game.saboteur.po.User;

public class UserBizImpl implements UserBiz{
	private UserDao userDao = new UserDaoImpl();
	@Override
	public User userLogin(String username, String password) {
		// TODO Auto-generated method stub			
			return userDao.findUser(username, password);
		
	}

	


/*
	public int addUser(String username, String password, String nickname,String photo) {
		// TODO Auto-generated method stub		
		return userDao.addUser(username, password, nickname, photo);
	}


*/


	@Override
	public void forceExit(String nickname) {
		// TODO Auto-generated method stub
		userDao.forceExit(nickname);
	}

	



	@Override
	public int updateUserScore(String nickname, int if_win, int tempcount) {
		// TODO Auto-generated method stub
		return userDao.updateUserScore(nickname, if_win, tempcount);
	}

	public int updateUserPhoto(String nickname, Vector<Integer> userPhoto,int price) {
		return userDao.updateUserPhoto(nickname, userPhoto,price);
	}


	@Override
	public int addUser(String username, String password, String nickname,
			String photo) {
		// TODO Auto-generated method stub
		return userDao.addUser(username, password, nickname, photo);
	}
}
