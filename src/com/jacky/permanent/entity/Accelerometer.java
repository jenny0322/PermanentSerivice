package com.jacky.permanent.entity;

import com.lidroid.xutils.db.annotation.Table;


/**
 * º”ÀŸ∂»
 * @author zhangyuhua
 *
 */
@Table(name="Accelerometer")
public class Accelerometer {

	private int _id;
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}

	private String accelerometerX;
	private String accelerometerY;
	private String accelerometerZ;
	
	public Accelerometer() {
		super();
	}
	public String getAccelerometerX() {
		return accelerometerX;
	}

	public void setAccelerometerX(String accelerometerX) {
		this.accelerometerX = accelerometerX;
	}

	public String getAccelerometerY() {
		return accelerometerY;
	}

	public void setAccelerometerY(String accelerometerY) {
		this.accelerometerY = accelerometerY;
	}

	public String getAccelerometerZ() {
		return accelerometerZ;
	}

	public void setAccelerometerZ(String accelerometerZ) {
		this.accelerometerZ = accelerometerZ;
	}

}
