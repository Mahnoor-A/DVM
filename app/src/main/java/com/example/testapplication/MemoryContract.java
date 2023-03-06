package com.example.testapplication;

import android.provider.BaseColumns;
import com.example.testapplication.MemoriesAdapter;


public class MemoryContract {
    //An empty private constructor makes sure that the class is not going to be initialised.
    private MemoryContract() {}

    public static final class MemoryEntry implements BaseColumns {
        public static final String TABLE_NAME = "memories";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_IMAGE = "image";
    }
}
