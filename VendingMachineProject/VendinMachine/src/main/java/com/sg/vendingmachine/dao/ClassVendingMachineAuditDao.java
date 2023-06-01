
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.service.ClassVendingMachinePersistenceException;

public interface ClassVendingMachineAuditDao {
    public void writeAuditEntry(String entry) throws ClassVendingMachinePersistenceException;
}
