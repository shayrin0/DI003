
package com.sg.vendingmachine.dao;

public interface ClassVendingMachineAuditDao {
    public void writeAuditEntry(String entry) throws ClassVendingMachinePersistenceException;
}
