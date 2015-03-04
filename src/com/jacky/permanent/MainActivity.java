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
 * ������ �ǵ���������񣬵Ƚ����ͻ���ʱ���һ�� ����Ӧ�ó���ʱ��������Ϳ�����
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
		// ��ʼ��
		startButton = (Button) findViewById(R.id.startButton);
		stopButton = (Button) findViewById(R.id.stopButton);
		intent = new Intent(MainActivity.this, MyService.class);
		tv = (TextView) findViewById(R.id.textview1);

		// �����ʼ����
		startButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				startService(intent);
			}
		});

		// ���ֹͣ����
		stopButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				stopService(intent);
			}
		});
	}

	// ������ʵ��onTouchEvent
	public boolean onTouchEvent(MotionEvent event) {
		// �������ж�һ������ǰ��²����ͻ�ȡ����Ȼ��ִ�з���
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			displayXY(event.getX(), event.getY(), event.getPressure());
		}
		return super.onTouchEvent(event);
	}

	// ��ȡ�����꣬�����ж�
	@SuppressLint("SimpleDateFormat")
	private void displayXY(float x, float y, float p) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy��MM��dd��    HH:mm:ss");
			Date curDate = new Date(System.currentTimeMillis());// ��ȡ��ǰʱ��
			String str = formatter.format(curDate);
			TouchEvents touch = new TouchEvents();
			touch.setCoordinateX(String.valueOf(x));
			touch.setCoordinateY(String.valueOf(y));
			touch.setDateStr(str);
			touch.setPressure(String.valueOf(p));
			db.save(touch);
			tv.setText("��ǰ���x���꣺" + x + "\n��ǰ���y���꣺" + y + "\nѹ����" + p
					+ "\nʱ�䣺" + str);
		} catch (DbException e) {
			e.printStackTrace();
		}
	}
}
