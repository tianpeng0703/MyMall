package mallcollection.joinearn.com.mymall.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Tianpeng on 2017-08-16.
 */
@Entity
public class Test {
    private String name;
    private String value;

    @Generated(hash = 1299820132)
    public Test(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Generated(hash = 372557997)
    public Test() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
