package com.el3asas.regym.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.el3asas.regym.DB.Daos.DataDao;
import com.el3asas.regym.DB.Daos.LongPlanDao;
import com.el3asas.regym.DB.Daos.PlanInfoDao;
import com.el3asas.regym.DB.models.DataConverter;
import com.el3asas.regym.DB.models.LongPlan;
import com.el3asas.regym.DB.models.PlanInfo;
import com.el3asas.regym.DB.models.ProfileInfo;

@Database(entities = {ProfileInfo.class, PlanInfo.class, LongPlan.class}, version = 1, exportSchema = false)
@TypeConverters(DataConverter.class)
public abstract class DataBase extends RoomDatabase {
    public abstract DataDao dataDao();

    public abstract LongPlanDao longPlanDao();

    public abstract PlanInfoDao planInfoDao();

    public static DataBase instance;

    public static synchronized void getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext()
                    , DataBase.class, "regymDB")
                    .fallbackToDestructiveMigration()
                    .build();
        }
    }
}
