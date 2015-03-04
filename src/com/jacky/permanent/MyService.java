package com.jacky.permanent;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.widget.Toast;

import com.jacky.permanent.entity.Accelerometer;
import com.jacky.permanent.entity.Gyroscope;
import com.jacky.permanent.entity.Magnetic;
import com.jacky.permanent.entity.Orientation;
import com.jacky.permanent.entity.Proximity;
import com.lidroid.xutils.DbUtils;

@SuppressWarnings("deprecation")
public class MyService<T> extends Service implements SensorListener {
	AlarmManager mAlarmManager = null;
	PendingIntent mPendingIntent = null;
	SensorManager sm = null;
	DbUtils db = null;
	List<T> list=new ArrayList<T>();

	@Override
	public void onCreate() {
		db = DbUtils.create(this);
		Intent intent = new Intent(getApplicationContext(), MyService.class);
		mAlarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
		mPendingIntent = PendingIntent.getService(this, 0, intent,
				Intent.FLAG_ACTIVITY_NEW_TASK);
		long now = System.currentTimeMillis();
		mAlarmManager.setInexactRepeating(AlarmManager.RTC, now, 60000,
				mPendingIntent);
		sm = (SensorManager) getSystemService(SENSOR_SERVICE);
		super.onCreate();
	}

	@SuppressLint("InlinedApi")
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		sm.registerListener(this,
				Sensor.TYPE_ACCELEROMETER | Sensor.TYPE_MAGNETIC_FIELD
						| Sensor.TYPE_ORIENTATION | Sensor.TYPE_GYROSCOPE
						| Sensor.TYPE_LIGHT | Sensor.TYPE_PRESSURE
						| Sensor.TYPE_TEMPERATURE | Sensor.TYPE_PROXIMITY
						| Sensor.TYPE_GRAVITY | Sensor.TYPE_LINEAR_ACCELERATION
						| Sensor.TYPE_ROTATION_VECTOR,
				SensorManager.SENSOR_DELAY_NORMAL);

		Toast.makeText(getApplicationContext(), "Callback Successed!",
				Toast.LENGTH_LONG).show();

		return START_STICKY;
	}

	int i = 0;

	@Override
	public void onSensorChanged(int sensor, float[] values) {
		try {
			Thread.sleep(3000);
			synchronized (this) {
				String str = "X：" + values[0] + "，Y：" + values[1] + "，Z："
						+ values[2];
				switch (sensor) {
				// 加速度
				case Sensor.TYPE_ACCELEROMETER:
					saveObject(Accelerometer.class, values);
					
					list=(List<T>) db.findAll(Accelerometer.class);
					for (T a : list) {
						Accelerometer accelerometer=(Accelerometer) a;
						System.out.println("Accelerometer=========="+accelerometer.getAccelerometerX());
					}
					break;
				// 磁场
				case Sensor.TYPE_MAGNETIC_FIELD:
					
					saveObject(Magnetic.class, values);
					
					list=(List<T>) db.findAll(Magnetic.class);
					
					for (T a : list) {
						Magnetic accelerometer=(Magnetic) a;
						System.out.println("Magnetic=========="+accelerometer.getMagneticX());
					}
					break;
				// 定位
				case Sensor.TYPE_ORIENTATION:
					saveObject(Orientation.class, values);
//					
					list=(List<T>) db.findAll(Orientation.class);
					for (T a : list) {
						Orientation accelerometer=(Orientation) a;
						System.out.println("Orientation=========="+accelerometer.getOrientationX());
					}
					break;
				// 距离
				case Sensor.TYPE_PROXIMITY:
					saveObject(Proximity.class, values);
					list=(List<T>) db.findAll(Proximity.class);
					for (T a : list) {
						Proximity accelerometer=(Proximity) a;
						System.out.println("Proximity=========="+accelerometer.getProximityX());
					}
//					
					break;
				// // 陀螺仪
				case Sensor.TYPE_GYROSCOPE:
					saveObject(Gyroscope.class, values);
//					
					list=(List<T>) db.findAll(Gyroscope.class);
					for (T a : list) {
						Gyroscope accelerometer=(Gyroscope) a;
						System.out.println("Gyroscope=========="+accelerometer.getGyroscopeX());
					}
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	

	/**
	 * 保存数据
	 * 
	 * @param clazz
	 *            实体
	 * @param values
	 *            传感器传回的数值
	 */

	@SuppressWarnings("hiding")
	public <T> void saveObject(Class<T> clazz, float[] values) {
		if (values == null || values.equals("") || values.length == 0) {
			return;
		}
		T t = null;
		try {
			t = clazz.newInstance();

			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				String fileName = field.getName();
				String methodName = getMethodName("set", fileName);
				Method method = t.getClass().getMethod(methodName,
						field.getType());
				String typeName = field.getType().getSimpleName();
				String str = fileName.substring(fileName.length() - 1);
				if ("String".equals(typeName) && str.equals("X")) {
					method.invoke(t, String.valueOf(values[0]));
				}
				if ("String".equals(typeName) && str.equals("Y")) {
					method.invoke(t, String.valueOf(values[1]));
				}
				if ("String".equals(typeName) && str.equals("Z")) {
					method.invoke(t, String.valueOf(values[2]));
				}
			}
			db.save(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获得getset方法名
	 * 
	 * @param getterOrsetter
	 * @param column
	 * @return
	 */
	private String getMethodName(String getterOrsetter, String column) {
		return new StringBuffer().append(getterOrsetter)
				.append(Character.toUpperCase(column.charAt(0)))
				.append(column.substring(1)).toString();

	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onDestroy() {
		sm.unregisterListener(this);
		super.onDestroy();
	}

	public void onAccuracyChanged(int sensor, int accuracy) {
	}

}
