package com.example.nfcapp.Persist_Save;

import com.example.nfcapp.BCardObject.UserObject;

public class PersistentFileCreatorMock implements PersistentFileCreator {
    @Override
    public void createDesktopFile(UserObject thing, String name) throws Exception {
        throw new UnsupportedOperationException("Mock Class, no Implementation here");
    }

    @Override
    public UserObject getDesktopFile(String name) throws Exception {
        throw new UnsupportedOperationException("Mock Class, no Implementation here");
    }

    @Override
    public UserObject[] getAllExistingFiles() throws Exception {
        throw new UnsupportedOperationException("Mock Class, no Implementation here");
    }
}
