package game.saboteur.dao.impl;

import game.saboteur.dbutil.DBHelper;
import game.saboteur.po.User;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class UserDaoImplTest {
    private DBHelper dbHelper =new DBHelper();
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(UserDaoImpl.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void findUser() {
        String sql="select * from user where username=? and password =?";
        ResultSet rst = dbHelper.execQuery(sql,"123","123");
        try {
            if(rst.next()){
                String nickname=rst.getString(3);
                String photo = rst.getString(4);
                int score = rst.getInt(5);
                int wincount = rst.getInt(6);
                int tolcount = rst.getInt(7);
                Vector<Integer> photosIntegers=new Vector<Integer>();
                if(photo!=null){
                    String[] aaStrings=photo.split(" ");
                    for(int i=0;i<aaStrings.length;i++){
                        photosIntegers.add(Integer.parseInt(aaStrings[i]));
                    }
                }
                User user = new User("123","123",photosIntegers,nickname,score,wincount,tolcount);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally{
            dbHelper.closeAll();
        }
    }
}
