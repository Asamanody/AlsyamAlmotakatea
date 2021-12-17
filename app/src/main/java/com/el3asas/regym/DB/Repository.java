package com.el3asas.regym.DB;

import android.content.Context;

import com.el3asas.regym.DB.Daos.DataDao;
import com.el3asas.regym.DB.Daos.LongPlanDao;
import com.el3asas.regym.DB.Daos.PlanInfoDao;
import com.el3asas.regym.DB.models.LongPlan;
import com.el3asas.regym.DB.models.PlanInfo;
import com.el3asas.regym.DB.models.ProfileInfo;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public class Repository {
    private DataDao dataDao;
    private PlanInfoDao planInfoDao;
    private LongPlanDao longPlanDao;


    public static Repository repository;

    public static Repository getInstance(Context context) {
        DataBase.getInstance(context);
        if (repository != null)
            return repository;
        else
            return new Repository();
    }

    public void initProfileDao() {
        dataDao = DataBase.instance.dataDao();
    }

    public void initPlanDao() {
        planInfoDao = DataBase.instance.planInfoDao();
    }

    public void initLongPlanDao() {
        longPlanDao = DataBase.instance.longPlanDao();
    }

    public Completable insertInfo(ProfileInfo profileInfo) {
        return dataDao.insertInfo(profileInfo);
    }

    public Single<ProfileInfo> getInfoData(int id) {
        return dataDao.getInfoData(id);
    }

    public Completable setPlanInfo(PlanInfo planInfo) {
        return planInfoDao.setPlanInfo(planInfo);
    }

    public Single<PlanInfo> getPlanInfo() {
        return planInfoDao.getPlanInfo();
    }

    public Completable deletePlan(int id) {
        return planInfoDao.deletePlan(id);
    }

    public Completable setLongPlanInfo(LongPlan longPlan) {
        return longPlanDao.insertLongPlan(longPlan);
    }

    public Completable deleteLongPlan(int id) {
        return longPlanDao.deleteLongPlan(id);
    }

    public Single<LongPlan> getLongPlanInfo(int id) {
        return longPlanDao.getLongPlan(id);
    }


    public Single<Integer> getLongPlanCount() {
        return longPlanDao.getCount();
    }

}