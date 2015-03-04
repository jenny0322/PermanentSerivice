package com.jacky.permanent.entity;

import com.lidroid.xutils.db.annotation.Table;

/**
 * ¶¨Î»
 * 
 * @author zhangyuhua
 * 
 */
@Table(name="Orientation")
public class Orientation {
	private int _id;

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	private String orientationX;
	private String orientationY;
	private String orientationZ;

	public String getOrientationX() {
		return orientationX;
	}

	public void setOrientationX(String orientationX) {
		this.orientationX = orientationX;
	}

	public String getOrientationY() {
		return orientationY;
	}

	public void setOrientationY(String orientationY) {
		this.orientationY = orientationY;
	}

	public String getOrientationZ() {
		return orientationZ;
	}

	public void setOrientationZ(String orientationZ) {
		this.orientationZ = orientationZ;
	}

}
