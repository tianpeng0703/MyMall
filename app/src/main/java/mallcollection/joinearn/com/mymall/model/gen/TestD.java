package mallcollection.joinearn.com.mymall.model.gen;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Tianpeng on 2017-08-16.
 */
@Entity
public class TestD {
    private String name;

    @Generated(hash = 407811087)
    public TestD(String name) {
        this.name = name;
    }

    @Generated(hash = 637523011)
    public TestD() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
