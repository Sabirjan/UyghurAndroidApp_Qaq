package com.izqisoft.uytoufa;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

	ImageView myView;
	TextView tv_text;
	ObjectAnimator fadein;
	ObjectAnimator mover;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initImageLoader(this);
		Global.typeface = Typeface.createFromAsset(this.getAssets(),
				"UkijTuzTom.ttf");

		setContentView(R.layout.activity_main);
		myView = (ImageView) findViewById(R.id.welcomeimage);
		tv_text = (TextView) findViewById(R.id.text);
		ObjectAnimator fadeOut = ObjectAnimator.ofFloat(myView, "alpha", .2f,
				1f);
		tv_text.setVisibility(View.INVISIBLE);
		tv_text.setTypeface(Global.typeface);
		tv_text.setText(Global.uyConvert.getUyPFStr(tv_text.getText()==null?"":tv_text.getText().toString()));
		fadein = ObjectAnimator.ofFloat(tv_text, "alpha", 0.0f, 1.0f);
		fadeOut.setDuration(2500);
		fadein.setDuration(1500);
		final AnimatorSet mAnimationSet = new AnimatorSet();

		final AnimatorSet mAnimationSet2 = new AnimatorSet();
		mAnimationSet.play(fadeOut);
		mover = ObjectAnimator.ofFloat(myView, "translationY", -150f);
		mover.setDuration(1500);
		mAnimationSet2.play(mover);
		mAnimationSet.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator animation) {
				super.onAnimationEnd(animation);
				// mAnimationSet.start();

				// myView.animate().translationY(-150).setDuration(1000).start();
				mAnimationSet2.start();
				fadein.start();
				tv_text.setVisibility(View.VISIBLE);
			}
		});

		mAnimationSet2.addListener(new AnimatorListenerAdapter() {

			@Override
			public void onAnimationEnd(Animator arg0) {

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				Intent intent = new Intent();
				intent.setClass(MainActivity.this, HomeActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.activity_open_translate,
						R.anim.activity_close_scale);
				finish();
			}

		});
		mAnimationSet.start();
	}

	public static void initImageLoader(Context context) {
		// This configuration tuning is custom. You can tune every option, you
		// may tune some of them,
		// or you can create default configuration by
		// ImageLoaderConfiguration.createDefault(this);
		// method.
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context).threadPriority(Thread.MAX_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.memoryCache(new LruMemoryCache(5 * 1024 * 1024))
				.memoryCacheSize(5 * 1024 * 1024).memoryCacheSizePercentage(30)
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
		com.nostra13.universalimageloader.utils.L.disableLogging();

		// ImageLoader.getInstance().pause();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}

}
