package com.izqisoft.uytoufa;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

public class ImageViewActivity extends Activity {

	Gallery gallery;
	LayoutInflater inflater;
	int count;
	ImageLoader imageLoader;
	String type = "";
	TextView sel_textView;
	int index = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_imageview);
		type = getIntent().getStringExtra("type");
		if (getIntent().getStringExtra("count") != null) {
			count = Integer.parseInt(getIntent().getStringExtra("count"));
		}
		TextView title = (TextView) findViewById(R.id.title);
		title.setTypeface(Global.typeface);
		title.setText(Global.uyConvert.getUyPFStr(getIntent().getStringExtra("title")));
		sel_textView = (TextView) findViewById(R.id.text_count);
		gallery = (Gallery) findViewById(R.id.gallery);
		inflater = LayoutInflater.from(this);
		imageLoader = ImageLoader.getInstance();
		myAdapter adapter = new myAdapter();

		gallery.setAdapter(adapter);
		gallery.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				System.out.println("---------------" + arg2);
				sel_textView.setText("(" + count + "/" + (arg2 + 1) + ")");
				index = arg2;
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
		gallery.setSelection(index, true);
	}

	public void btn_LeftClick(View view) {

		index--;
		if (index < 0) {
			index = 0;
		}
		gallery.setSelection(index, true);

	}

	public void btn_RightClick(View view) {
		index++;
		if (index >= count) {
			index = count - 1;
		}
		gallery.setSelection(index, true);
	}

	public void back(View view) {
		imageLoader.clearMemoryCache();
		finish();
	}

	class myAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return count;
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int pos, View view, ViewGroup arg2) {

			HolderView holder = null;
			if (view == null) {
				holder = new HolderView();
				view = inflater.inflate(R.layout.imgview, null);
				holder.img = (ImageView) view.findViewById(R.id.itemimage);
				holder.img.setScaleType(ImageView.ScaleType.FIT_XY);
				// holder.img.setLayoutParams(new Gallery.LayoutParams(700,
				// 800));
				view.setTag(holder);
			} else {
				holder = (HolderView) view.getTag();
			}
			imageLoader.displayImage("assets://images/" + type + "/a ("
					+ (pos + 1) + ").jpg", holder.img);
			return view;
		}

	}

	public static class HolderView {
		ImageView img;

	}

}
