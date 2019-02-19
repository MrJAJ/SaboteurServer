package game.saboteur.biz;

import java.util.Vector;

import game.saboteur.po.User;

public interface UserBiz {
	User userLogin(String username,String password);
	int addUser(String username,String password,String nickname,String photo);
	void forceExit(String nickname);
	int updateUserScore(String nickname,int if_win,int tempcount);
	int updateUserPhoto(String nickname, Vector<Integer> userPhoto,int price);
}
