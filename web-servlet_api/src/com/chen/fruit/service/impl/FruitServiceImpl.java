package com.chen.fruit.service.impl;

import com.chen.fruit.service.FruitService;
import com.chen.fruit.dao.FruitDAO;
import com.chen.fruit.pojo.Fruit;
import com.chen.myssm.util.ConnUtil;

import java.util.List;

/**
 * ClassName: FruitServiceImpl
 * Package: com.chen.fruit.biz.impl
 * Description:
 *
 * @Author: Night
 * @Create: 2023/10/1 - 16:50
 * @Version: 1.0
 */
public class FruitServiceImpl implements FruitService {
    private FruitDAO fruitDAO = null;

    @Override
    public List<Fruit> getFruitList(String keyword, Integer pageNum) {
        return fruitDAO.getFruitList(keyword,pageNum);
    }

    @Override
    public void addFruit(Fruit fruit) {
        fruitDAO.addFruit(fruit);
    }

    @Override
    public Fruit getFruitById(Integer fid) {
        return fruitDAO.getFruitById(fid);
    }

    @Override
    public void delFruitById(Integer fid) {
        fruitDAO.delFruitById(fid);
    }

    @Override
    public Integer getPageCount(String keyword) {
        System.out.println(ConnUtil.getConn());
        int count = fruitDAO.getFruitCount(keyword);
        return (count +5 - 1) / 5;
    }

    @Override
    public void updateFruit(Fruit fruit) {
        fruitDAO.updateFruit(fruit);
    }
}
