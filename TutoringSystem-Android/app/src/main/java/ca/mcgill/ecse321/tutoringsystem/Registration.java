package ca.mcgill.ecse321.tutoringsystem;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Registration extends AppCompatActivity {
    private String error="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        refreshErrorMessage();
    }

    private void refreshErrorMessage(){
        TextView tvError = (TextView) findViewById(R.id.error);
        tvError.setText(error);

        if (error == null || error.length() == 0) {
            tvError.setVisibility(View.GONE);
        } else {
            tvError.setVisibility(View.VISIBLE);
        }
    }

    public void register(View v) {
        error = "";
        final TextView nameTV = (TextView) findViewById(R.id.newstudent_name);
        final TextView usernameTV = (TextView) findViewById(R.id.newstudent_username);

        final TextView passwordTV = (TextView) findViewById(R.id.newstudent_password);

        final TextView confirmedPWTV = (TextView) findViewById(R.id.newstudent_confirmpassword);

        String name = nameTV.getText().toString();
        String username = usernameTV.getText().toString();
        String password = passwordTV.getText().toString();
        String confirmedPW = confirmedPWTV.getText().toString();

        if (username.trim().length() == 0){
            error += "Username can't be empty.\n";
        }
        if (password.trim().length() == 0) {
            error += "Password can't be empty.\n";
        }
        if (name.trim().length() == 0 ) {
            error += "Name can't be empty\n";
        }
        if (password.length() < 8) {
            error += "Password must be at least 8 characters long.\n";
        }
        if(password.compareTo(confirmedPW) != 0) {
            error += "Passwords must match.\n";
        }
        if(error.length() != 0 ){
            refreshErrorMessage();
            return;
        }

        HttpUtils.post("/student/" + username + "/" + password + "/" + name, new RequestParams(), new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                error += "success\n";
                refreshErrorMessage();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error+= errorResponse.get("message").toString();

                } catch (JSONException e) {
                    error += e.getMessage();
                }
                refreshErrorMessage();
            }
        });
    }
}
