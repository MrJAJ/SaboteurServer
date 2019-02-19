package game.saboteur.dao;

import java.util.Vector;

import game.saboteur.po.User;

public interface UserDao {
	User findUser(String username,String passward);
	int addUser(String username,String password,String nickname,String photo);
	int updateUserScore(String nickname,int if_win,int tempcount);
	void forceExit(String nickname);
	int updateUserPhoto(String nickname, Vector<Integer> userPhoto,int price); 
}
