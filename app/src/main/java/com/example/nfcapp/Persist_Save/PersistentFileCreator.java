package com.example.nfcapp.Persist_Save;

public interface PersistentFileCreator {

    String FILE_PATH_PREFIX = "./temp/";
    String FILE_NAME_SUFFIX = ".bcf";

    void createDesktopFile(UserObject thing, String name) throws Exception;
    UserObject getDesktopFile(String name) throws Exception;
    UserObject[] getAllExistingFiles() throws Exception;
}
