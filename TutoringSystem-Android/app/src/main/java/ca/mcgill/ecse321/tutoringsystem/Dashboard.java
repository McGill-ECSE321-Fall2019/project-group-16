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

    public void gotoSession(View view){
        Intent intent = new Intent(this, BookSessionActivity.class);
        startActivity(intent);
    }

    public void gotoManageSession(View view){
        Intent intent = new Intent(this, ManageSessionActivity.class);
        startActivity(intent);
    }
    public void gotoMainPage(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void logout(View view) {
        final View v = view;
        gotoMainPage(v);
//        HttpUtils.put("/logout/", new RequestParams(), new JsonHttpResponseHandler(){
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                gotoMainPage(v);
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
//
//            }
//        });
    }
}
