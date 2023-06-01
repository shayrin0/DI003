package com.sg.vendingmachine;

import com.sg.vendingmachine.controller.VendingMachineController;
import com.sg.vendingmachine.dao.ClassVendingMachineAuditDao;
import com.sg.vendingmachine.dao.ClassVendingMachineAuditDaoFileImpl;
import com.sg.vendingmachine.ui.UserIO;
import com.sg.vendingmachine.ui.UserIOImplementation;
import com.sg.vendingmachine.ui.ClassVendingMachineUserView;
import com.sg.vendingmachine.dao.ClassVendingMachineDao;
import com.sg.vendingmachine.dao.ClassVendingMachineDaoImpl;
import com.sg.vendingmachine.service.ClassVendingMachinePersistenceException;
import com.sg.vendingmachine.dao.VendingMachineDaoException;
import com.sg.vendingmachine.service.ClassInsufficientFundsException;
import com.sg.vendingmachine.service.ClassNoItemInventoryException;
import com.sg.vendingmachine.service.ClassVendingMachineServiceImpl;

public class App {

    public static void main(String[] args) throws VendingMachineDaoException, ClassVendingMachinePersistenceException, ClassInsufficientFundsException, ClassNoItemInventoryException, ClassNotFoundException {
        UserIO myIo = new UserIOImplementation();
        ClassVendingMachineUserView myView = new ClassVendingMachineUserView(myIo);
        ClassVendingMachineDao myDao = new ClassVendingMachineDaoImpl();
        ClassVendingMachineAuditDao myAuditDao = new ClassVendingMachineAuditDaoFileImpl();
        ClassVendingMachineServiceImpl myService = new ClassVendingMachineServiceImpl((ClassVendingMachineDaoImpl) myDao, myAuditDao);
        VendingMachineController controller = new VendingMachineController(myView, myService);
        controller.run();
        
    }
}
