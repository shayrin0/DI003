package com.sg.dvdlibrary.service;

import com.sg.dvdlibrary.dao.DvdLibraryAuditDao;
import com.sg.dvdlibrary.dao.DvdLibraryPersistenceException;

class DvdLibraryAuditDaoStupImp implements DvdLibraryAuditDao {
    @Override
    public void writeAuditEntry(String entry) throws DvdLibraryPersistenceException {
        //do nothing . . .
    }

}
