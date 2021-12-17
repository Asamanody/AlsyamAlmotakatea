package com.el3asas.regym.DB.Daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.el3asas.regym.DB.models.PlanInfo;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;


@Dao
public interface PlanInfoDao {

    @Query("select * from PlanInfo")
    public Single<PlanInfo> getPlanInfo();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Completable setPlanInfo(PlanInfo planInfo);

    @Query("delete from planinfo where id=:id")
    public Completable deletePlan(int id);
}
