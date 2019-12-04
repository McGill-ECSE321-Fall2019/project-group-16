package ca.mcgill.ecse321.tutoringsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    /**
     *
     * @param view
     *
     * redirects view to Booking session activity
     */
    public void gotoSession(View view){
        Intent intent = new Intent(this, BookSessionActivity.class);
        startActivity(intent);
    }

    /**
     *
     * @param view
     *
     * redirects view to manage session activity
     */
    public void gotoManageSession(View view){
        Intent intent = new Intent(this, ManageSessionActivity.class);
        startActivity(intent);
    }

    /**
     *
     * @param view
     *
     * Redirects view to MainActivity
     */
    public void gotoMainPage(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     *
     * @param view
     * Redirects view to MainActivity
     */
    public void logout(View view) {
        final View v = view;
        gotoMainPage(v);

    }
}
