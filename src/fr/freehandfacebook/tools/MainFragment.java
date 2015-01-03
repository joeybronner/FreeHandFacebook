package fr.freehandfacebook.tools;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.widget.LoginButton;

import fr.freehandfacebook.HomeActivity;
import fr.freehandfacebook.InformationsActivity;
import fr.freehandfacebook.R;

public class MainFragment extends Fragment {
	
	private UiLifecycleHelper uiHelper;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	    View view = inflater.inflate(R.layout.activity_settings, container, false);
		LoginButton authButton = (LoginButton) view.findViewById(R.id.authButton);
		authButton.setFragment(this);
		
		// ImageButton Click Action
		Button button= (Button) view.findViewById(R.id.buttonInfos);
		button.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
		        Intent informationsActivity = new Intent(v.getContext(), InformationsActivity.class);
		        startActivity(informationsActivity);
		    }
		});
		
	    return view;
	}
	
	public boolean isLoggedIn() {
	    Session session = Session.getActiveSession();
	    return (session != null && session.isOpened());
	}
	
	private Session.StatusCallback callback = new Session.StatusCallback() {
	    @Override
	    public void call(Session session, SessionState state, Exception exception) {
	        onSessionStateChange(session, state, exception);
	    }
	};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    uiHelper = new UiLifecycleHelper(getActivity(), callback);
	    uiHelper.onCreate(savedInstanceState);
	}
	
	private void onSessionStateChange(Session session, SessionState state, 
			Exception exception) {
	    if (state.isOpened()) {
	    	Intent intent = new Intent(this.getActivity(), HomeActivity.class);
	    	startActivity(intent);
	    	this.getActivity().finish();
	    } else if (state.isClosed()) {
	    	Toast.makeText(this.getActivity().getApplicationContext(), 
	        		"Non connecté.", Toast.LENGTH_SHORT).show();
	    }
	}

	@Override
	public void onResume() {
	    super.onResume();
	    
	    Session session = Session.getActiveSession();
	    if (session != null && (session.isOpened() || session.isClosed()) ) {
	        onSessionStateChange(session, session.getState(), null);
	    }
	    uiHelper.onResume();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);
	    uiHelper.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onPause() {
	    super.onPause();
	    uiHelper.onPause();
	}

	@Override
	public void onDestroy() {
	    super.onDestroy();
	    uiHelper.onDestroy();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
	    super.onSaveInstanceState(outState);
	    uiHelper.onSaveInstanceState(outState);
	}
}
