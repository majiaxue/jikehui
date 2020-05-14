package com.example.dbflow;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = AppDataBase.NAME,version = AppDataBase.VERSION)
public class AppDataBase {
    public static final String NAME = "fltk";

    public static final int VERSION = 8;
}