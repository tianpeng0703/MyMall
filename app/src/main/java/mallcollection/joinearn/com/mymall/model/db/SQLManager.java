package mallcollection.joinearn.com.mymall.model.db;

import android.database.sqlite.SQLiteDatabase;

import mallcollection.joinearn.com.mymall.App.MyApplication;
import mallcollection.joinearn.com.mymall.model.gen.DaoMaster;
import mallcollection.joinearn.com.mymall.model.gen.DaoSession;

/**
 * Created by Tianpeng on 2017-08-16.
 */

public class SQLManager {
    private final String dbName = "mall_db";
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase mDb;
    private DaoMaster mDaoMaster;
    private DaoSession mSession;
    private static SQLManager mSqlManager;

    private SQLManager(){
        getDaoMaster();
        getDaoSession();
    }
    public static SQLManager getInstance(){
        if(mSqlManager == null){
            synchronized (SQLManager.class) {
                mSqlManager = new SQLManager();
            }
        }
        return mSqlManager;
    }
    public DaoMaster getDaoMaster() {
        if (null == mDaoMaster) {
            synchronized (SQLManager.class) {
                if (null == mDaoMaster) {
                    mDaoMaster = new DaoMaster(getWritableDatabase());
                }
            }
        }
        return mDaoMaster;
    }
    public DaoSession getDaoSession() {
        if (null == mSession) {
            synchronized (SQLManager.class) {
                mSession = getDaoMaster().newSession();
            }
        }
        return mSession;
    }
    /**
     * 获取可读数据库
     */
    private SQLiteDatabase getReadableDatabase() {
        if (mHelper == null) {
            mHelper = new DaoMaster.DevOpenHelper(MyApplication.getInstance(), dbName, null);
        }
        SQLiteDatabase db = mHelper.getReadableDatabase();
        return db;
    }

    /**
     * 获取可写数据库
     */
    private SQLiteDatabase getWritableDatabase() {
        if (mHelper == null) {
            mHelper = new DaoMaster.DevOpenHelper(MyApplication.getInstance(), dbName, null);
        }
        SQLiteDatabase db = mHelper.getWritableDatabase();
        return db;
    }
}
