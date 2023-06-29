package com.mhl.service;

import com.mhl.dao.DinningTableDao;
import com.mhl.domain.DinningTable;

import java.util.List;

/**
 * @author FAUST
 * @version 1.0
 * @date 2023/6/27 22:19
 */
public class DinningTableService {
    private DinningTableDao dinningTableDao = new DinningTableDao();

    public List<DinningTable> showDinningTable(){
        return dinningTableDao.queryMulti("select id,state from dinningtable",DinningTable.class);
    }

    public DinningTable getDinningTableById(int id){
        return dinningTableDao.querySingle("select * from dinningtable where id = ?",DinningTable.class,id);
    }

    public boolean updateDinningTable(int id,String orderName,String orderTel){
        int update =
                dinningTableDao.update("update dinningtable set state='reserved',orderName=?,orderTel=? where id = ?", orderName, orderTel,id);
        return update>0;
    }

    public boolean updateStateUsing(int dinningTableId){
        int update = dinningTableDao.update("update dinningtable set state = 'using' where id = ?", dinningTableId);
        return update>0;
    }

    public boolean updateStateEmpty(int dinningTableId){
        int update = dinningTableDao.update("update dinningtable set state = 'empty',orderName='',orderTel='' where id = ?", dinningTableId);
        return update>0;
    }
}
