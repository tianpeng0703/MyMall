package mallcollection.joinearn.com.mymall.model.db;

import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

import mallcollection.joinearn.com.mymall.model.bean.Brand;
import mallcollection.joinearn.com.mymall.model.gen.BrandDao;

/**
 * Created by Tianpeng on 2017-08-17.
 */

public class BrandDbManager {
    private static BrandDbManager mManager;
    private BrandDbManager(){

    }
    public static BrandDbManager getInstance(){
        if(mManager == null){
            synchronized (BrandDbManager.class){
                if(mManager == null){
                    mManager = new BrandDbManager();
                }
            }
        }
        return mManager;
    }

    public BrandDao getDao(){
        return SQLManager.getInstance().getDaoSession().getBrandDao();
    }

    public void insertBrand(Brand brand){
        if(brand != null) {
            getDao().insertOrReplace(brand);
        }
    }

    public void insertBrandList(List<Brand> list){
        if(list != null){
            getDao().insertOrReplaceInTx(list);
        }
    }

    public List<Brand> getBrandList(int id){
        List<Brand> list = null;
        list = getDao().queryBuilder().where(new WhereCondition.StringCondition("_Id>"+id+" limit 20")).list();
        return list;
    }
}
