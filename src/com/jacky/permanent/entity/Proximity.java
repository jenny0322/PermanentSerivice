package com.jacky.permanent.entity;

import com.lidroid.xutils.db.annotation.Table;

/**
 * æ‡¿Î
 * @author zhangyuhua
 *
 */
@Table(name="Proximity")
public class Proximity {
	private int _id;
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	private String proximityX;
	private String proximityY;
	private String proximityZ;

	public String getProximityX() {
		return proximityX;
	}

	public void setProximityX(String proximityX) {
		this.proximityX = proximityX;
	}

	public String getProximityY() {
		return proximityY;
	}

	public void setProximityY(String proximityY) {
		this.proximityY = proximityY;
	}

	public String getProximityZ() {
		return proximityZ;
	}

	public void setProximityZ(String proximityZ) {
		this.proximityZ = proximityZ;
	}

}
