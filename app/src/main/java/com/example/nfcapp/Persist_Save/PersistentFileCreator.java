package com.example.nfcapp.Persist_Save;

import com.example.nfcapp.BCardObject.BCardObject;

public interface PersistentFileCreator {

    String FILE_PATH_PREFIX = "./temp/";
    String FILE_NAME_SUFFIX = ".bcf";

    void createDesktopFile(BCardObject thing, String name) throws Exception;
    BCardObject getDesktopFile(String name) throws Exception;
    BCardObject[] getAllExistingFiles() throws Exception;
}
