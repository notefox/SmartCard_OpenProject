package com.example.nfcapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import static android.content.Context.MODE_PRIVATE;


public class DataFetchFunctions {

private static final String FILE_NAME= "local.txt";

   void fetchLocalID(View view) {

   }

   void writeLocalID(View view) {
        String save = "textToSave";
        FileOutputStream fos = null;
   }
}
