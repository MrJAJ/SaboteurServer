package game.saboteur.dao.impl;

import java.sql.ResultSet;


import java.sql.SQLException;
import java.util.Vector;

import game.saboteur.dao.UserDao;
import game.saboteur.dbutil.*;
import game.saboteur.po.User;
public class UserDaoImpl implements UserDao {
	private DBHelper dbHelper =new DBHelper();
	@Override
	public User findUser(String username, String password) {
		// TODO Auto-generated method stub
		String sql="select * from user where username=? and password =?"; 
		ResultSet rst = dbHelper.execQuery(sql,username,password);
		try {
			if(rst.next()){
				String nickname=rst.getString(3);
				String photo = rst.getString(4);
				int score = rst.getInt(5);
				int wincount = rst.getInt(6);
				int tolcount = rst.getInt(7);
				Vector<Integer>photosIntegers=new Vector<Integer>();
				if(photo!=null){
					String[] aaStrings=photo.split(" ");
					for(int i=0;i<aaStrings.length;i++){
						photosIntegers.add(Integer.parseInt(aaStrings[i]));
					}
				}
				User user = new User(username,password,photosIntegers,nickname,score,wincount,tolcount);
				return user;
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			dbHelper.closeAll();
		}
		return null;
	}
	@Override
	public int addUser(String username, String password, String nickname,
			String photo) {
		// TODO Auto-generated method stub
		String sql="select * from user where username=?";
		ResultSet rst = dbHelper.execQuery(sql,username);
		try {
			if(rst.next())
				return 0;
			else {
				String sql3="select * from user where nickname=?";
				ResultSet rst1=dbHelper.execQuery(sql3, nickname);
				if(rst1.next())
					return 2;
				String sql2 = "insert into user values(?,?,?,?,0,0,0)";
				int n=dbHelper.execOthers(sql2,username,password,nickname,photo);
				if(n>0){
					return 1;
				}
				else {
					return -1;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dbHelper.closeAll();
		}
		return -1;
	}
	@Override
	public int updateUserScore(String nickname, int if_win,
			int tempcount) {
		// TODO Auto-generated method stub
		String sql1="select * from user where nickname=?";
		ResultSet rst=dbHelper.execQuery(sql1,nickname);
		int n=-1;
		try {
			if(rst.next()){
				int newcount = rst.getInt(7)+1;
				int newwin =rst.getInt(6)+if_win;
				int newscore = rst.getInt(5)+tempcount;
				
				String sqlString="update user set integral=?,wincount=?,totalcount=? where nickname=?";
				n=dbHelper.execOthers(sqlString, newscore,newwin,newcount,nickname);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return n;
	}
	public int updateUserPhoto(String nickname, Vector<Integer> userPhoto,int price) {
		// TODO Auto-generated method stub
		String sql1="select * from user where nickname=?";
		ResultSet rst=dbHelper.execQuery(sql1,nickname);
		int n=-1;
		try {
			if(rst.next()){
				String photo="";
				for(int i=0;i<userPhoto.size();i++){
					photo+=(userPhoto.get(i)+" ");
				}
				String sqlString="update user set integral=?,userPhoto=? where nickname=?";
				n=dbHelper.execOthers(sqlString,price,photo,nickname);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return n;
	}
	@Override
	public void forceExit(String nickname) {
		// TODO Auto-generated method stub
		String sql1="select * from user where nickname=?";
		ResultSet rst=dbHelper.execQuery(sql1,nickname);
		try {
			if(rst.next()){
				 int newcount = rst.getInt(5)-10;
				 String sqlString="update user set integral=? where nickname=?";
				 dbHelper.execOthers(sqlString,newcount,nickname);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
