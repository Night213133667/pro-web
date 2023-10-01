package com.chen.fruit.service;

import com.chen.fruit.pojo.Fruit;

import java.util.List;

/**
 * ClassName: FruitService
 * Package: com.chen.fruit.biz
 * Description:
 *
 * @Author: Night
 * @Create: 2023/10/1 - 16:46
 * @Version: 1.0
 */
public interface FruitService {
    //获取指定库存的列表信息
    List<Fruit> getFruitList(String keyword, Integer pageNum);

    //添加库存记录信息
    void addFruit(Fruit fruit);

    //根据id查看指定库存记录
    Fruit getFruitById(Integer fid);

    //删除指定库存记录
    void delFruitById(Integer fid);

    //获取总页数
    Integer getPageCount(String keyword);

    //修改特定库存记录
    void updateFruit(Fruit fruit);
}
