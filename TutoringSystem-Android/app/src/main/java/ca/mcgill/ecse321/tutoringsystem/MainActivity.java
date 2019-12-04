package ca.mcgill.ecse321.tutoringsystem;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    public String error;


    //displayes what is specified in the method when the page is created.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    /**
     *
     * @param view
     *
     * redirects view to registration activity
     */
    public void gotoRegistration(View view) {
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);
    }

    /**
     *
     * @param view
     *
     * redirects view to dashboard activity
     */
    public void gotoDashboard(View view) {
        Intent intent = new Intent(this, Dashboard.class);
        startActivity(intent);
    }

    /**
     *
     *
     * refreshes error message in error text view.
     */
    private void refreshErrorMessage(){
        TextView tvError = (TextView) findViewById(R.id.error);
        tvError.setText(error);

        if (error == null || error.length() == 0) {
            tvError.setVisibility(View.GONE);
        } else {
            tvError.setVisibility(View.VISIBLE);
        }
    }


    /**
     * Method for login.
     * This method invokes call to the backend to log in the user
     * Username and password are the text entered in the text view
     *
     * @param v
     *
     *
     */
    public void login(View v) {
        error = "";

        //locating text view for username and password
        final TextView usernameTV = (TextView) findViewById(R.id.username);
        final TextView passwordTV = (TextView) findViewById(R.id.password);
        final View view = v;

        //assigning string username and password to variables
        String username = usernameTV.getText().toString();
        String password = passwordTV.getText().toString();

        if (username.trim().length() == 0){
            error += "Username can't be empty.\n";
        }
        if (password.trim().length() == 0) {
            error += "Password can't be empty.\n";
        }

        if(error.length() != 0 ){
            refreshErrorMessage();
            return;
        }
        //invoking call to backend with username and password to login.
        HttpUtils.post("/student/" + username + "/" + password, new RequestParams(), new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                gotoDashboard(view);
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
