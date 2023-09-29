package com.chen.fruit.dao.impl;

import com.chen.fruit.dao.FruitDAO;
import com.chen.fruit.pojo.Fruit;
import com.chen.myssm.basedao.BaseDAO;

import java.util.List;

/**
 * ClassName: FruitDAOImpl
 * Package: com.chen.fruit.dao.impl
 * Description:
 *
 * @Author: Night
 * @Create: 2023/9/25 - 19:19
 * @Version: 1.0
 */
public class FruitDAOImpl extends BaseDAO<Fruit> implements FruitDAO {

    @Override
    public List<Fruit> getFruitList(String keyword, Integer pageNum) {
        return super.executeQuery("SELECT * FROM t_fruit WHERE fname like ? OR remark like ? LIMIT ?,5"
                ,"%"+keyword+"%","%"+keyword+"%",(pageNum-1)*5);
    }

    @Override
    public Fruit getFruitById(Integer fid) {
        return super.load("SELECT * FROM t_fruit WHERE fid = ?",fid);
    }

    @Override
    public void updateFruit(Fruit fruit) {
        String sql = "UPDATE t_fruit SET fname = ? , price = ?, fcount = ?, remark = ? WHERE fid = ?";
        super.executeUpdate(sql,fruit.getFname(),fruit.getPrice(),fruit.getFcount(),fruit.getRemark(),fruit.getFid());
    }

    @Override
    public void delFruitById(Integer fid) {
        super.executeQuery("DELETE FROM t_fruit WHERE fid = ?", fid);
    }

    @Override
    public void addFruit(Fruit fruit) {
        super.executeUpdate("INSERT INTO t_fruit(fname,price,fcount,remark) values(?,?,?,?)",
                fruit.getFname(),fruit.getPrice(),fruit.getFcount(),fruit.getRemark());
    }

    @Override
    public int getFruitCount(String keyword) {
        return ((Long) super.executeComplexQuery("SELECT COUNT(*) FROM t_fruit WHERE fname like ? OR remark like ?"
                ,"%"+keyword+"%","%"+keyword+"%")[0]).intValue();
    }
}
