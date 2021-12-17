package com.el3asas.regym.DB.Daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.el3asas.regym.DB.models.ProfileInfo;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface DataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Completable insertInfo(ProfileInfo profileInfo);

    @Query("select * from ProfileInfo where id =:id")
    public Single<ProfileInfo> getInfoData(int id);

    @Query("select COUNT(id) FROM PlanInfo")
    public Single<Integer> getInfoCount();
}