package com.jacky.permanent.entity;

import com.lidroid.xutils.db.annotation.Table;

/**
 * ´Å³¡
 * @author zhangyuhua
 *
 */
@Table(name="Magnetic")
public class Magnetic {
	private int _id;
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	private String magneticX;
	private String magneticY;
	private String magneticZ;
	public String getMagneticX() {
		return magneticX;
	}
	public void setMagneticX(String magneticX) {
		this.magneticX = magneticX;
	}
	public String getMagneticY() {
		return magneticY;
	}
	public void setMagneticY(String magneticY) {
		this.magneticY = magneticY;
	}
	public String getMagneticZ() {
		return magneticZ;
	}
	public void setMagneticZ(String magneticZ) {
		this.magneticZ = magneticZ;
	}

	

}
