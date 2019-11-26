package com.example.nfcapp.Persist_Save;

import com.example.nfcapp.BCardObject.BCardObject;
import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

public class FileReadWrite implements PersistentFileCreator {
    private Gson gson;

    @Override
    public void createDesktopFile(BCardObject thing, String name) throws Exception {

        if (new File("./temp/" + name).exists())
            throw new Exception("File already exists");

        File create = new File(FILE_PATH_PREFIX + name + FILE_NAME_SUFFIX);

        gson = new Gson();

        try {
            FileOutputStream fos = new FileOutputStream(create);
            DataOutputStream dos = new DataOutputStream(fos);
            dos.writeUTF(gson.toJson(thing));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BCardObject getDesktopFile(String name) throws Exception {

        gson = new Gson();

        File get = new File(FILE_PATH_PREFIX + name + FILE_NAME_SUFFIX);

        FileInputStream fis = new FileInputStream(get);
        DataInputStream dis = new DataInputStream(fis);

        return gson.fromJson(dis.readUTF(), BCardObject.class);
    }

    @Override
    public BCardObject[] getAllExistingFiles() throws Exception {
        Collection<BCardObject> all = new ArrayList<>();
        throw new UnsupportedOperationException("not implemented yet");
    }


}