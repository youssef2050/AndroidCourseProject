package com.menu.androidcourseproject.general;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class AddImages {
    public static final int requestCodeImage = 100;
    public static final int requestCodePermission = 101;
    private Activity activity;

    public AddImages(Activity activity) {
        this.activity = activity;
    }

    public String saveImageInStorage(Bitmap bitmap, String file, String name) {
        ContextWrapper cw = new ContextWrapper(activity);
        File directory = cw.getDir(file, Context.MODE_PRIVATE);
        File path = new File(directory, name + ".jpg");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(path);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                assert fos != null;
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return path.getAbsolutePath();
    }

    public Bitmap loadImageFromStorage(String path) {
        System.out.println("path : " + path);
        if (path != null && !path.equals("")) {
            try {
                File f = new File(path);
                return BitmapFactory.decodeStream(new FileInputStream(f));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
