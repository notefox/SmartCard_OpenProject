package com.example.nfcapp.Persist_Save;

import com.example.nfcapp.BCardObject.BCardObject;

public class PersistentFileCreatorMock implements PersistentFileCreator {
    @Override
    public void createDesktopFile(BCardObject thing, String name) throws Exception {
        throw new UnsupportedOperationException("Mock Class, no Implementation here");
    }

    @Override
    public BCardObject getDesktopFile(String name) throws Exception {
        throw new UnsupportedOperationException("Mock Class, no Implementation here");
    }

    @Override
    public BCardObject[] getAllExistingFiles() throws Exception {
        throw new UnsupportedOperationException("Mock Class, no Implementation here");
    }
}
