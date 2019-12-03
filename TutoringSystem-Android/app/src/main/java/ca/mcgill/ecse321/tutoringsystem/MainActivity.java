package ca.mcgill.ecse321.tutoringsystem;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import cz.msebera.android.httpclient.Header;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private String error;
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
        // initialize error message text view
        refreshErrorMessage();
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
    private void refreshErrorMessage() {
        // set the error message
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
        final TextView usernameTV = (TextView)  findViewById(R.id.newstudent_username);
        final TextView passwordTV = (TextView) findViewById(R.id.newstudent_password);
        final TextView confirmedPWTV = (TextView) findViewById(R.id.newstudent_confirmpassword);

        String name = nameTV.getText().toString();
        String username = usernameTV.getText().toString();
        String password = passwordTV.getText().toString();
        String confirmedPW = confirmedPWTV.getText().toString();

        if(username.trim().length() == 0){
            error +="Username can't be empty.\n";
        }
        if(password.trim().length() == 0){
            error +="Password can't be empty.\n";
        }
        if(name.trim().length() == 0){
            error +="Name can't be empty.\n";
        }
        if(password.equals(confirmedPW)) {
            error +="Password must match.\n";
        }
        if(error.length() != 0){
            refreshErrorMessage();
            return;
        }
        HttpUtils.post("persons/" + usernameTV.getText().toString() + "/" + passwordTV.getText().toString()
                + "/" + nameTV.getText().toString(), new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                refreshErrorMessage();
                startActivity(new Intent(MainActivity.this, DummyHomePage.class));
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                refreshErrorMessage();
            }
        });
    }

}
