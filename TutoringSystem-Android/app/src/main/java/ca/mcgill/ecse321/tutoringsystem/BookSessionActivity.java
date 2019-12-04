package ca.mcgill.ecse321.tutoringsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class BookSessionActivity extends AppCompatActivity {

    private String error = null;

    //private boolean variables for the university, course , and tutor drop downs
    private boolean createUni = true;
    private boolean createCourse = true;
    private boolean createTutor = true;
    private boolean createSession = true;

    //private variables for the selected uni,course and tutor from the spinners
    private String selectedUni = "";
    private String courseString = "";
    private String selectedTutor = "";

    //ArrayLists and Adapters used to populate spinners for Uni, course and Tutors
    private List<String> uniNames = new ArrayList<>();
    private ArrayAdapter<String> uniAdapter;
    private List<String> courseCodes = new ArrayList<>();
    private ArrayAdapter<String> courseAdapter;
    private List<String> tutorNames = new ArrayList<>();
    private ArrayAdapter<String> tutorAdapter;

    //display under this method when the page is created.
    //the page is loaded from activity_book_session
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_session);

        //creating the objects for the three spinners
        Spinner uniSpinner = (Spinner) findViewById(R.id.unispinner);
        Spinner courseSpinner = (Spinner) findViewById(R.id.coursespinner);
        Spinner tutorSpinner = (Spinner) findViewById(R.id.tutorspinner);

        //creating adapters for the spinners and setting.
        uniAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, uniNames);
        uniAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        uniSpinner.setAdapter(uniAdapter);

        //creating adapters for the spinners and setting.
        courseAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, courseCodes);
        courseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseSpinner.setAdapter(courseAdapter);

        //creating adapters for the spinners and setting.
        tutorAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tutorNames);
        tutorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tutorSpinner.setAdapter(tutorAdapter);


        //adding action listener for university spinner.
        //Action specifies what to do once a university is selected
        uniSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // listener code is run twice - once at creation and once when selection is made
                // ensures that the selection is not requested upon creation
                if(createUni == true){
                    createUni = false;
                }
                else {
                    Object uni = parentView.getItemAtPosition(position);
                    selectedUni = uni.toString();
                    // populate the course list based on the selected university
                    refreshList(courseAdapter, courseCodes, "/courses/" + selectedUni, "courseCode");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {}

        });

        //adding action listener for course spinner
        //action specifies what to do when a course is selected
        courseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // listener code is run twice - once at creation and once when selection is made
                // ensures that the selection is not requested upon creation
                if(createCourse == true){
                    createCourse = false;
                }
                else {
                    Object course = parentView.getItemAtPosition(position);
                    courseString = course.toString();
                    // populate the tutor list according to the selected course

                    refreshList(tutorAdapter, tutorNames, "/courses/tutors/"+ courseString, "username");
                  //  refreshCourseOfferingList(courseOfferingAdapter, courseOfferingNames, courseString, selectedUni);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {}

        });

        //adding action listener for tutor spinner
        //action specifies what to do when a tutor is selected
        tutorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // listener code is run twice - once at creation and once when selection is made
                // ensures that the selection is not requested upon creation
                if(createTutor == true){
                    createTutor = false;
                }
                else {
                    String username = parentView.getItemAtPosition(position).toString();
                    selectedTutor = username;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {}

        });



        //refreshes the lists with the content
        refreshLists(this.getCurrentFocus());
    }

    public void refreshLists(View view) {
        refreshList(uniAdapter ,uniNames, "universities", "name");
    }

    /**
     *
     * @param adapter
     * @param names
     * names is the ArrayList which will store the data to be displayed in the spinner
     * @param restFunctionName
     * restFunctionName is used to access the correct REST controller method
     * @param identifier
     * The identifier is used to identify the field from the JSON object.
     * This method refreshes the list according to the provided parameters.
     */
    private void refreshList(final ArrayAdapter<String> adapter, final List<String> names, final String restFunctionName , final String identifier) {
        HttpUtils.get(restFunctionName, new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                names.clear();
                names.add("Please select...");
                for (int i = 0; i < response.length(); i++) {
                    try {
                        names.add(response.getJSONObject(i).getString(identifier));
                    } catch (Exception e) {
                        error += e.getMessage();
                    }
                    refreshErrorMessage();
                }
                adapter.notifyDataSetChanged();
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

    /**
     * This method refreshes the field for the error message.
     */
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


    /**
     *
     * @param v
     *
     * This method invokes call to the backend with the data provided from the 'view' and creates the session with the provided data.
     */
    public void createSession(View v){

        // make sure that all items are selected
        final View view = v;

        //start time time picker

        TextView tv = (TextView) findViewById(R.id.starttime);
        String startTime = tv.getText().toString();
        startTime = startTime + ":00";

        //end time tiem picker
        tv = (TextView) findViewById(R.id.endtime);
        String endtime = tv.getText().toString();
        endtime = endtime + ":00";

        // date picker
        tv = (TextView) findViewById(R.id.newevent_date);
        String text = tv.getText().toString();
        String comps[] = text.split("-");

        int year = Integer.parseInt(comps[2]);
        int month = Integer.parseInt(comps[1]);
        int day = Integer.parseInt(comps[0]);

        String date = "" + year + "-" + month + "-" + day;

        if(this.selectedTutor == "" || this.selectedTutor == null ){

            error = "Please select all fields before creating a session!";
            refreshErrorMessage();
        }
        // send the HTTP request to create a session based on the previously saved information
        else {
            HttpUtils.post("/session/" + this.selectedTutor + "/" + startTime + "/" + endtime + "/"
                    + date + "/" + courseString + "/" + false , new RequestParams(), new JsonHttpResponseHandler() {

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    // reset the dropdown boolean
                    createSession = true;
                    // reset all the chosen fields
                    selectedTutor = "";
                    courseString = "";
                    selectedUni = "";
                    gotoDashboard(view);
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    try {
                        //set error message
                        error += errorResponse.get("message").toString();
                    } catch (JSONException e) {
                        error += e.getMessage();
                    }
                    refreshErrorMessage();
                }
            });
        }
    }


    /**
     *
     * @param view
     *
     * Loads the dashboard
     */
    public void gotoDashboard(View view) {
        Intent intent = new Intent(this, Dashboard.class);
        startActivity(intent);
    }
    //Date & Time helpers

    /**
     *
     * @param text
     * @return
     *
     * Returns the selected time from the time picker on the book sessions screen.
     */
    private Bundle getTimeFromLabel(String text) {
        Bundle rtn = new Bundle();
        String comps[] = text.toString().split(":");
        int hour = 12;
        int minute = 0;

        if (comps.length == 2) {
            hour = Integer.parseInt(comps[0]);
            minute = Integer.parseInt(comps[1]);
        }

        rtn.putInt("hour", hour);
        rtn.putInt("minute", minute);

        return rtn;
    }

    /**
     *
     * @param text
     * @return
     *
     * Returns the selected data from the date picker on the book sessions screen.
     */
    private Bundle getDateFromLabel(String text) {
        Bundle rtn = new Bundle();
        String comps[] = text.toString().split("-");
        int day = 1;
        int month = 1;
        int year = 1;

        if (comps.length == 3) {
            day = Integer.parseInt(comps[0]);
            month = Integer.parseInt(comps[1]);
            year = Integer.parseInt(comps[2]);
        }

        rtn.putInt("day", day);
        rtn.putInt("month", month-1);
        rtn.putInt("year", year);

        return rtn;
    }

    /**
     *
     * @param v
     *
     * Displays the time-picker element on the view provided by the parameter.
     */
    public void showTimePickerDialog(View v) {
        TextView tf = (TextView) v;
        Bundle args = getTimeFromLabel(tf.getText().toString());
        args.putInt("id", v.getId());

        TimePickerFragment newFragment = new TimePickerFragment();
        newFragment.setArguments(args);
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    /**
     *
     * @param v
     *
     * Displays the time-picker element on the view provided by the parameter.
     */
    public void showDatePickerDialog(View v) {
        TextView tf = (TextView) v;
        Bundle args = getDateFromLabel(tf.getText().toString());
        args.putInt("id", v.getId());

        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.setArguments(args);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    /**
     *
     * @param id
     * @param h
     * @param m
     *
     * id identifies time picker textview
     * sets time to hours h and minutes m
     *
     */
    public void setTime(int id, int h, int m) {
        TextView tv = (TextView) findViewById(id);
        tv.setText(String.format("%02d:%02d", h, m));
    }

    /**
     *
     * @param id
     * @param d
     * @param m
     * @param y
     *
     * id identifies date picker textview
     * sets date to day d month m and year y
     */

    public void setDate(int id, int d, int m, int y) {
        TextView tv = (TextView) findViewById(id);
        tv.setText(String.format("%02d-%02d-%04d", d, m + 1, y));
    }
}
