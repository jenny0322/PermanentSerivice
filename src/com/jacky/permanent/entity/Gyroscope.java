package com.jacky.permanent.entity;

import com.lidroid.xutils.db.annotation.Table;

/**
 * мсбщрг
 * 
 * @author zhangyuhua
 * 
 */
@Table(name="Gyroscope")
public class Gyroscope {
	private int _id;

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	private String gyroscopeX;
	private String gyroscopeY;
	private String gyroscopeZ;

	public String getGyroscopeX() {
		return gyroscopeX;
	}

	public void setGyroscopeX(String gyroscopeX) {
		this.gyroscopeX = gyroscopeX;
	}

	public String getGyroscopeY() {
		return gyroscopeY;
	}

	public void setGyroscopeY(String gyroscopeY) {
		this.gyroscopeY = gyroscopeY;
	}

	public String getGyroscopeZ() {
		return gyroscopeZ;
	}

	public void setGyroscopeZ(String gyroscopeZ) {
		this.gyroscopeZ = gyroscopeZ;
	}

}
