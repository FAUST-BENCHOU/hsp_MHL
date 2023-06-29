package com.mhl.service;

import com.mhl.dao.BillDao;
import com.mhl.domain.Bill;

import java.util.List;
import java.util.UUID;

/**
 * @author FAUST
 * @version 1.0
 * @date 2023/6/29 14:48
 */
public class BillService {
    private BillDao billDao=new BillDao();
    private MenuService menuService=new MenuService();
    private DinningTableService dinningTableService = new DinningTableService();

    public boolean orderMenu(int menuId,int nums,int dinningTableId){
        String billId = UUID.randomUUID().toString();
        double money=menuService.getMenuById(menuId).getPrice()*nums;
        int update =
                billDao.update("insert into bill values (null,?,?,?,?,?,now(),'unpaid')", billId, menuId, nums,
                        money, dinningTableId);
        if (update<=0){
            return false;
        }

        return dinningTableService.updateStateUsing(dinningTableId);

    }

    public List<Bill> getAllBills(){
        return billDao.queryMulti("SELECT bill.*,menu.`name`,menu.`price` FROM bill,menu WHERE bill.`id`=menu.`id`",Bill.class);
    }

    public List<Bill> getBillByTableId(int tableId){
        return billDao.queryMulti("SELECT bill.*,menu.`name`,menu.`price` FROM bill,menu WHERE bill.`id`=menu.`id` and bill.`diningTableId`=?",Bill.class,tableId);
    }

    public boolean hasUnpaidBill(int tableId){
        Bill bill = billDao.querySingle("select * from bill where diningTableId =? and state ='unpaid'", Bill.class, tableId);
        return bill!=null;
    }

    public boolean payBills(int tableId,String state){
        int update = billDao.update("update bill set state=? where diningTableId =?", state, tableId);
        return update>0;
    }

}
