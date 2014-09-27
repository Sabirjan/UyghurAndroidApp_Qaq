package com.izqisoft.uytoufa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends Activity {

	UyghurCharUtilities uyConvert = UyghurCharUtilities.getUtilities();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		initView();

	}

	private void initView() {
		TextView tv_1 = (TextView) findViewById(R.id.textView1);
		tv_1.setTypeface(Global.typeface);
		tv_1.setText(Global.uyConvert.getUyPFStr(tv_1.getText() == null ? ""
				: tv_1.getText().toString()));

		TextView tv_2 = (TextView) findViewById(R.id.textView2);
		tv_2.setTypeface(Global.typeface);
		tv_2.setText(Global.uyConvert.getUyPFStr(tv_2.getText() == null ? ""
				: tv_2.getText().toString()));

		TextView tv_3 = (TextView) findViewById(R.id.textView3);
		tv_3.setTypeface(Global.typeface);
		tv_3.setText(Global.uyConvert.getUyPFStr(tv_3.getText() == null ? ""
				: tv_3.getText().toString()));

		TextView tv_4 = (TextView) findViewById(R.id.textView4);
		tv_4.setTypeface(Global.typeface);
		tv_4.setText(Global.uyConvert.getUyPFStr(tv_4.getText() == null ? ""
				: tv_4.getText().toString()));

		TextView txt_des = (TextView) findViewById(R.id.txt_des);
		txt_des.setTypeface(Global.typeface);
		txt_des.setText(Global.uyConvert.getUyPFStr(txt_des.getText() == null ? ""
				: txt_des.getText().toString()));

	}

	public void btn_Click(View view) {
		if (view != null) {
			int id = view.getId();
			String type = null;
			String count = "0";
			String title = "";

			switch (id) {
			case R.id.relativeLayout1:
				type = "s";
				count = "58";
				title = getString(R.string.btn_1);
				break;
			case R.id.relativeLayout2:
				type = "l";
				count = "31";
				title = getString(R.string.btn_2);
				break;
			case R.id.relativeLayout3:
				type = "c";
				count = "19";
				title = getString(R.string.btn_3);
				break;
			case R.id.relativeLayout4:
				type = "m";
				count = "35";
				title = getString(R.string.btn_4);
				break;
			default:
				break;
			}
			if (type != null) {
				Intent intent = new Intent();
				intent.setClass(this, ImageViewActivity.class);
				intent.putExtra("type", type);
				intent.putExtra("count", count);
				intent.putExtra("title", title);

				startActivity(intent);
				overridePendingTransition(R.anim.activity_open_translate,
						R.anim.activity_close_scale);
			}

		}
	}

	long time = 0;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			try {
				long now = System.currentTimeMillis();
				if (time + 2000 < now) {
					time = now;
					Toast.makeText(HomeActivity.this,
							"Yena Bir Besip Qekinig~!", 2000).show();
					return true;
				} else {
					finish();
					return false;
				}
			} catch (Exception ex) {
			}
		}
		return super.onKeyDown(keyCode, event);
	}

}
