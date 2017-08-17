package mallcollection.joinearn.com.mymall.model.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;

@Entity(nameInDb = "brand")
public class Brand implements Serializable{
	static final long serialVersionUID = 42L;
	@Id(autoincrement = true)
	private long id;
	@Unique
	private String name;
	private String icon;
	private String point;//品牌特点
	private String location;//公司
	private String address; //地址
	private String description;//品牌简介
	private String eng_name; //英文名称
	private String item_kind;//种类
	private String main_kind;//大类
	private String status; //
	private String change_time; //编辑时间

	@Generated(hash = 1716912267)
	public Brand(long id, String name, String icon, String point, String location,
			String address, String description, String eng_name, String item_kind,
			String main_kind, String status, String change_time) {
		this.id = id;
		this.name = name;
		this.icon = icon;
		this.point = point;
		this.location = location;
		this.address = address;
		this.description = description;
		this.eng_name = eng_name;
		this.item_kind = item_kind;
		this.main_kind = main_kind;
		this.status = status;
		this.change_time = change_time;
	}
	@Generated(hash = 128156227)
	public Brand() {
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEng_name() {
		return eng_name;
	}
	public void setEng_name(String eng_name) {
		this.eng_name = eng_name;
	}
	public String getItem_kind() {
		return item_kind;
	}
	public void setItem_kind(String item_kind) {
		this.item_kind = item_kind;
	}
	public String getMain_kind() {
		return main_kind;
	}
	public void setMain_kind(String main_kind) {
		this.main_kind = main_kind;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getChange_time() {
		return change_time;
	}
	public void setChange_time(String change_time) {
		this.change_time = change_time;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
