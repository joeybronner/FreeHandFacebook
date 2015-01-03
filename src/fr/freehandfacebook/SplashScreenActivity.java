package fr.freehandfacebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreenActivity extends Activity {

	/* splash screen timer */
	private static int SPLASH_TIME_OUT = 2800; // 2.8 seconds

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);

		// Hide Action Bar
		getActionBar().hide();

		new Handler().postDelayed(new Runnable()
		{
			@Override
			public void run() {
				Intent i = new Intent(SplashScreenActivity.this, SettingsActivity.class);
				startActivity(i);

				// Closing this Activity
				finish();
			}
		}, SPLASH_TIME_OUT);
	}
}
