package mallcollection.joinearn.com.mymall.model.gen;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import mallcollection.joinearn.com.mymall.model.bean.Brand;
import mallcollection.joinearn.com.mymall.model.gen.TestD;
import mallcollection.joinearn.com.mymall.model.Test;

import mallcollection.joinearn.com.mymall.model.gen.BrandDao;
import mallcollection.joinearn.com.mymall.model.gen.TestDDao;
import mallcollection.joinearn.com.mymall.model.gen.TestDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig brandDaoConfig;
    private final DaoConfig testDDaoConfig;
    private final DaoConfig testDaoConfig;

    private final BrandDao brandDao;
    private final TestDDao testDDao;
    private final TestDao testDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        brandDaoConfig = daoConfigMap.get(BrandDao.class).clone();
        brandDaoConfig.initIdentityScope(type);

        testDDaoConfig = daoConfigMap.get(TestDDao.class).clone();
        testDDaoConfig.initIdentityScope(type);

        testDaoConfig = daoConfigMap.get(TestDao.class).clone();
        testDaoConfig.initIdentityScope(type);

        brandDao = new BrandDao(brandDaoConfig, this);
        testDDao = new TestDDao(testDDaoConfig, this);
        testDao = new TestDao(testDaoConfig, this);

        registerDao(Brand.class, brandDao);
        registerDao(TestD.class, testDDao);
        registerDao(Test.class, testDao);
    }
    
    public void clear() {
        brandDaoConfig.clearIdentityScope();
        testDDaoConfig.clearIdentityScope();
        testDaoConfig.clearIdentityScope();
    }

    public BrandDao getBrandDao() {
        return brandDao;
    }

    public TestDDao getTestDDao() {
        return testDDao;
    }

    public TestDao getTestDao() {
        return testDao;
    }

}