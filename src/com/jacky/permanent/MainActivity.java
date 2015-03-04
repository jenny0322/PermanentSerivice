package com.jacky.permanent;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.jacky.permanent.entity.TouchEvents;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.DbUtils.DaoConfig;
import com.lidroid.xutils.exception.DbException;

/**
 * 我这里 是点击后开启服务，等交给客户的时候改一下 启动应用程序时启动服务就可以了
 * 
 * @author zhangyuhua
 * 
 */
public class MainActivity extends Activity {
	private Button startButton = null;
	private Button stopButton = null;
	private Intent intent = null;
	private TextView tv;
	DaoConfig config;
	DbUtils db = null;
	File file;
//	private static String path = Environment.getExternalStorageDirectory()
//			.toString();
//	private static String sdState = Environment.getExternalStorageState();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		file = new File(path + "/Huai/TicketsPic");
		
//		db = DbUtils.create(this, sdState, "test.db");
		
		db = DbUtils.create(this,  "test.db");
		// db = DbUtils.create(this);
		// 初始化
		startButton = (Button) findViewById(R.id.startButton);
		stopButton = (Button) findViewById(R.id.stopButton);
		intent = new Intent(MainActivity.this, MyService.class);
		tv = (TextView) findViewById(R.id.textview1);

		// 点击开始服务
		startButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				startService(intent);
			}
		});

		// 点击停止服务
		stopButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				stopService(intent);
			}
		});
	}

	// 在这里实现onTouchEvent
	public boolean onTouchEvent(MotionEvent event) {
		// 在这里判断一下如果是按下操作就获取坐标然后执行方法
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			displayXY(event.getX(), event.getY(), event.getPressure());
		}
		return super.onTouchEvent(event);
	}

	// 获取到坐标，进行判断
	@SuppressLint("SimpleDateFormat")
	private void displayXY(float x, float y, float p) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy年MM月dd日    HH:mm:ss");
			Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
			String str = formatter.format(curDate);
			TouchEvents touch = new TouchEvents();
			touch.setCoordinateX(String.valueOf(x));
			touch.setCoordinateY(String.valueOf(y));
			touch.setDateStr(str);
			touch.setPressure(String.valueOf(p));
			db.save(touch);
			tv.setText("当前点击x坐标：" + x + "\n当前点击y坐标：" + y + "\n压力：" + p
					+ "\n时间：" + str);
		} catch (DbException e) {
			e.printStackTrace();
		}
	}
}
