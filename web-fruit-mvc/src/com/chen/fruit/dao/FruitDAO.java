package com.chen.fruit.dao;

import com.chen.fruit.pojo.Fruit;

import java.util.List;

/**
 * ClassName: FruitDAO
 * Package: com.chen.fruit.dao
 * Description:
 *
 * @Author: Night
 * @Create: 2023/9/25 - 19:18
 * @Version: 1.0
 */
public interface FruitDAO {
    //获取指定数量的水果的列表信息
    List<Fruit> getFruitList(String keyword, Integer pageNum);

    //根据fid获取特定的水果信息
    Fruit getFruitById(Integer fid);

    void updateFruit(Fruit fruit);

    //删除指定id的水果信息
    void delFruitById(Integer fid);

    //添加库存信息
    void addFruit(Fruit fruit);

    //获取总记录条数
    int getFruitCount(String keyword);
}
