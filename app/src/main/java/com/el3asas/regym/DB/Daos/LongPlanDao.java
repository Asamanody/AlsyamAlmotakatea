package com.el3asas.regym.DB.Daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.el3asas.regym.DB.models.LongPlan;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface LongPlanDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertLongPlan(LongPlan longPlan);

    @Query("select * from longplan where id =:id")
    Single<LongPlan> getLongPlan(int id);

    @Query("select COUNT(id) from LongPlan")
    Single<Integer> getCount();

    @Query("delete from longplan where id=:id")
    Completable deleteLongPlan(int id);
}
