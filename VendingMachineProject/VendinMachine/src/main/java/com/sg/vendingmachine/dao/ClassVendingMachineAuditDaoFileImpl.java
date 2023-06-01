
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.service.ClassVendingMachinePersistenceException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ClassVendingMachineAuditDaoFileImpl implements ClassVendingMachineAuditDao {
   public static final String AUDIT_FILE = "audit.txt";
    @Override
    public void writeAuditEntry(String entry) throws ClassVendingMachinePersistenceException {
       PrintWriter out;
       
        try {
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (IOException e) {
            throw new ClassVendingMachinePersistenceException("Could not persist audit information.", e);
        }
 
        LocalDateTime timestamp = LocalDateTime.now();
        String date = timestamp.format(DateTimeFormatter.ofPattern("dd/mm/yyyy"));
        out.println(date + " : " + entry);
        out.flush();
    }
 
}
    
    
    
    
    

