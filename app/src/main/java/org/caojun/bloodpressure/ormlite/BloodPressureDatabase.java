package org.caojun.bloodpressure.ormlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by fourm on 2017/5/9.
 */

public class BloodPressureDatabase extends OrmLiteSqliteOpenHelper {

    private static BloodPressureDatabase bloodPressureDatabase;
    private static Dao<BloodPressure, Integer> bloodPressureDao;

    public static BloodPressureDatabase getInstance(Context context) {
        if(bloodPressureDatabase == null) {
            bloodPressureDatabase = new BloodPressureDatabase(context);
            try {
                bloodPressureDao = bloodPressureDatabase.getDao(BloodPressure.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return bloodPressureDatabase;
    }

    public BloodPressureDatabase(Context context) {
        super(context, "bloodpressure-db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, BloodPressure.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, BloodPressure.class, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        onCreate(database, connectionSource);
    }

    public boolean insert(BloodPressure bloodPressure) {
        try {
            return bloodPressureDao.create(bloodPressure) == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 服药记录
     * @param time
     * @return
     */
    public boolean insert(long time) {
        BloodPressure bloodPressure = new BloodPressure(time);
        try {
            return bloodPressureDao.create(bloodPressure) == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 体重记录
     * @param time
     * @param weight
     * @return
     */
    public boolean insert(long time, float weight) {
        BloodPressure bloodPressure = new BloodPressure(time, weight);
        try {
            return bloodPressureDao.create(bloodPressure) == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 血压记录
     * @param time
     * @param high
     * @param low
     * @param pulse
     * @param isLeft
     * @return
     */
    public boolean insert(long time, int high, int low, int pulse, boolean isLeft) {
        BloodPressure bloodPressure = new BloodPressure(time, high, low, pulse, isLeft);
        try {
            return bloodPressureDao.create(bloodPressure) == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(BloodPressure bloodPressure) {
        try {
            return bloodPressureDao.update(bloodPressure) == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<BloodPressure> query() {
        try {
            return bloodPressureDao.queryBuilder().orderBy("time", true).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean delete(BloodPressure bloodPressure) {
        try {
            return bloodPressureDao.delete(bloodPressure) == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
