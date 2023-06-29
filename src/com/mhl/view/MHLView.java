package com.mhl.view;

import com.mhl.domain.Bill;
import com.mhl.domain.DinningTable;
import com.mhl.domain.Employee;
import com.mhl.domain.Menu;
import com.mhl.service.BillService;
import com.mhl.service.DinningTableService;
import com.mhl.service.EmployeeService;
import com.mhl.service.MenuService;
import com.mhl.utils.Utility;

import java.util.List;

/**
 * @author FAUST
 * @version 1.0
 * @date 2023/6/27 21:31
 */
public class MHLView {
    //控制是否退出菜单
    private boolean loop = true;
    //接收用户的输入
    private String key = "";
    private EmployeeService employeeService = new EmployeeService();
    private DinningTableService dinningTableService = new DinningTableService();
    private MenuService menuService = new MenuService();
    private BillService billService = new BillService();

    public static void main(String[] args) {
        new MHLView().mainMenu();
    }

    public void showTableState() {
        List<DinningTable> list = dinningTableService.showDinningTable();
        System.out.println("\n餐桌编号\t\t餐桌状态");
        for (DinningTable dinningTable : list) {
            System.out.println("\t" + dinningTable.getId() + "\t\t" + dinningTable.getState());
        }
    }

    public void orderTable() {
        System.out.println("==============预定餐桌==============");
        System.out.print("请选择要预定的餐桌编号(-1 退出): ");
        int orderId = Utility.readInt();
        if (orderId == -1) {
            System.out.println("==============取消预定餐桌==============");
            return;
        }
        DinningTable dinningTable = dinningTableService.getDinningTableById(orderId);
        if (dinningTable != null) {
            if (dinningTable.getState().equals("reserved")) {
                System.out.println("餐桌已被预订");
                return;
            } else if (dinningTable.getState().equals("using")) {
                System.out.println("餐桌已被使用");
                return;
            }
            char key = Utility.readConfirmSelection();
            if (key == 'Y') {
                System.out.print("预定人的名字: ");
                String orderName = Utility.readString(50);
                System.out.print("预定人的电话: ");
                String orderTel = Utility.readString(50);
                if (dinningTableService.updateDinningTable(orderId, orderName, orderTel)) {
                    System.out.println("==============预定成功==============");
                } else {
                    System.out.println("==============预定失败==============");
                }
            }
        } else {
            System.out.println("餐桌编号不存在");
            return;
        }

    }

    public void showMenu() {
        System.out.println("\n菜品编号\t\t菜品名\t\t类别\t\t\t价格");
        for (Menu menu : menuService.getMenu()) {
            System.out.println(menu);
        }
        System.out.println("==============显示完毕==============");
    }

    public void orderMenu() {
        System.out.println("==============点餐服务==============");
        System.out.print("请输入点餐的桌号(-1退出): ");
        int orderDiningTableId = Utility.readInt();
        if (orderDiningTableId == -1) {
            System.out.println("==============取消点餐==============");
            return;
        } else if (dinningTableService.getDinningTableById(orderDiningTableId) == null) {
            System.out.println("餐桌号不存在");
            return;
        }

        System.out.print("请输入点餐的菜品号(-1退出): ");
        int orderMenuId = Utility.readInt();
        if (orderMenuId == -1) {
            System.out.println("==============取消点餐==============");
            return;
        } else if (menuService.getMenuById(orderMenuId) == null) {
            System.out.println("菜品号不存在");
            return;
        }

        System.out.print("请输入点餐的菜品量(-1退出): ");
        int orderNums = Utility.readInt();
        if (orderNums == -1) {
            System.out.println("==============取消点餐==============");
            return;
        }

        if (billService.orderMenu(orderMenuId, orderNums, orderDiningTableId)) {
            System.out.println("点餐成功");
        } else {
            System.out.println("点餐失败");
        }
    }

    public void showAllBills() {
        System.out.println("\n编号\t\t菜品号\t\t菜品量\t\t金额\t\t桌号\t\t日期\t\t\t\t\t\t\t状态\t\t\t菜名\t\t\t单价");
        for (Bill bill : billService.getAllBills()) {
            System.out.println(bill);
        }
    }

    public void showBillByTable(int tableId) {
        System.out.println("\n编号\t\t菜品号\t\t菜品量\t\t金额\t\t桌号\t\t日期\t\t\t\t\t\t\t状态\t\t\t菜名\t\t\t单价");
        for (Bill bill : billService.getBillByTableId(tableId)) {
            System.out.println(bill);
        }
    }

    public void payBill() {
        double money = 0;
        System.out.println("==============结账服务==============");
        System.out.print("请选择要结账的餐桌编号(-1退出): ");
        int diningTableId = Utility.readInt();
        if (diningTableId == -1) {
            System.out.println("==============取消结账==============");
            return;
        }
        if (dinningTableService.getDinningTableById(diningTableId) == null) {
            System.out.println("餐桌号不存在");
            return;
        }

        if (!billService.hasUnpaidBill(diningTableId)){
            System.out.println("没有未支付账单");
            return;
        }

        System.out.print("结账方式(现金/微信)回车表示退出: ");
        String payMode = Utility.readString(20, "");//说明如果是回车，就是返回 ""
        if ("".equals(payMode)) {
            System.out.println("==============取消结账==============");
            return;
        }
        for (Bill bill : billService.getBillByTableId(diningTableId)) {
            if (bill.getState().equals("unpaid")) {
                money += bill.getMoney();
            }
        }
        System.out.println("一共消费" + money + "元");
        char key = Utility.readConfirmSelection();
        if (key == 'Y') {//结账
            if (billService.payBills(diningTableId, payMode) && dinningTableService.updateStateEmpty(diningTableId)) {

                System.out.println("结账成功");
            } else {
                System.out.println("结账失败");
            }
            return;
        }
        System.out.println("==============取消结账==============");
        return;
    }

    //显示主菜单方法
    public void mainMenu() {
        while (loop) {
            System.out.println("==============满汉楼==============");
            System.out.println("\t\t 1 登录满汉楼");
            System.out.println("\t\t 2 退出满汉楼");
            System.out.print("请输入你的选择: ");
            key = Utility.readString(1);
            switch (key) {
                case "1":
                    System.out.print("输入员工号: ");
                    String empId = Utility.readString(50);
                    System.out.print("输入密  码: ");
                    String pwd = Utility.readString(50);
                    Employee employee = employeeService.getEmployeeByIdAndPwd(empId, pwd);

                    if (employee != null) {
                        System.out.println("==============登录成功(" + employee.getName() + ")==============\n");
                        //显示二级菜单,这里二级菜单是循环操作，所以做成while
                        while (loop) {
                            System.out.println("\n==============满汉楼二级菜单==============");
                            System.out.println("\t\t 1 显示餐桌状态");
                            System.out.println("\t\t 2 预定餐桌");
                            System.out.println("\t\t 3 显示所有菜品");
                            System.out.println("\t\t 4 点餐服务");
                            System.out.println("\t\t 5 查看账单");
                            System.out.println("\t\t 6 结账");
                            System.out.println("\t\t 9 退出");
                            System.out.print("请输入你的选择: ");
                            key = Utility.readString(1);
                            switch (key) {
                                case "1":
                                    showTableState();
                                    break;
                                case "2":
                                    orderTable();
                                    break;
                                case "3":
                                    showMenu();
                                    break;
                                case "4":
                                    orderMenu();
                                    break;
                                case "5":
                                    System.out.print("查看所有账单？（y/n）：");
                                    char c = Utility.readChar();
                                    if (c == 'Y' || c == 'y') {
                                        showAllBills();
                                    } else {
                                        System.out.print("输入桌号：");
                                        int i = Utility.readInt();
                                        showBillByTable(i);
                                    }
                                    break;
                                case "6":
                                    payBill();
                                    break;
                                case "9":
                                    loop = false;
                                    break;
                                default:
                                    System.out.println("你输入有误，请求重新输入");
                            }
                        }

                    } else {
                        System.out.println("==============登录失败==============");
                    }
                    break;
                case "2":
                    loop = false;
                    break;
                default:
                    System.out.println("你输入有误，请求重新输入");
            }
        }
        System.out.println("您退出了满汉楼系统~");

    }
}
