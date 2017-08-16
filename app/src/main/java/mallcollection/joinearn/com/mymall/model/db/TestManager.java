package mallcollection.joinearn.com.mymall.model.db;

import mallcollection.joinearn.com.mymall.model.Test;
import mallcollection.joinearn.com.mymall.model.gen.DaoSession;

/**
 * Created by Tianpeng on 2017-08-16.
 */

public class TestManager {
    private static TestManager mManager;
    private static DaoSession mSession;

    private  TestManager(){
        mSession = SQLManager.getInstance().getDaoSession();
    }

    public static TestManager getInstance(){
        if(mManager == null){
            mManager = new TestManager();
        }
        return mManager;
    }

    public void insert(Test test){
        mSession.getTestDao().insertOrReplace(test);
    }

}
