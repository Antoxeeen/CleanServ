package ru.antoxeeen.cleanserv.Repository;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Data.class, version = 6, exportSchema = false)
public abstract class LocalDB extends RoomDatabase {

    private static LocalDB instance;
    public abstract DataDao dataDao();

    public static synchronized LocalDB getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    LocalDB.class, "data_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
