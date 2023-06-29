package com.mhl.service;

import com.mhl.dao.MenuDao;
import com.mhl.domain.Menu;

import java.util.List;

/**
 * @author FAUST
 * @version 1.0
 * @date 2023/6/29 13:08
 */
public class MenuService {
    private MenuDao menuDao = new MenuDao();

    public Menu getMenuById(int menuId){
        return menuDao.querySingle("select price from menu where id = ?",Menu.class,menuId);
    }

    public List<Menu> getMenu(){
        return menuDao.queryMulti("select * from menu",Menu.class);
    }
}
