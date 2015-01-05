package fr.freehandfacebook;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.Session;

public class HomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		// Action Bar Color
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#5c97bf")));
	}

	// --------------------------------------------------------------------- //
	// Facebook Methods														 //
	// --------------------------------------------------------------------- //

	public void logOUT()
	{
		Session session = Session.getActiveSession();
		if (session != null) {
			session.close();
			session.closeAndClearTokenInformation();
		}
		else {
			Session session2 = Session.openActiveSession(this, false, null);
			if(session2 != null)
				session2.close();
			session2.closeAndClearTokenInformation();
		}
		Session.setActiveSession(null);
	}

	// --------------------------------------------------------------------- //
	// Action Bar															 //
	// --------------------------------------------------------------------- //

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_logout) {
			logOUT();
			Intent intent = new Intent(this, SettingsActivity.class);
			startActivity(intent);
			this.finish();
		}
		return super.onOptionsItemSelected(item);
	}
}
