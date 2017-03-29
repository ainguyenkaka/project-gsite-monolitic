package app.discovery.com.idiscovery;

import android.content.DialogInterface;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mActivityName;
    private EditText mReportName;
    private EditText mActivityDate;
    private EditText mLocationName;
    private EditText mAttendingTime;
    private Button mBtnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // find edit fields on view with ids
        mActivityName = (EditText) findViewById(R.id.activity_name);
        mReportName = (EditText) findViewById(R.id.report_name);
        mActivityDate = (EditText) findViewById(R.id.activity_date);
        mLocationName = (EditText) findViewById(R.id.location_name);
        mAttendingTime = (EditText) findViewById(R.id.attending_time);

        // find submit button and set its onClick event
        mBtnSubmit = (Button) findViewById(R.id.btn_submit);
        mBtnSubmit.setOnClickListener(this);
    }

    private void reset() {
        mActivityName.setText("");
        mReportName.setText("");
        mActivityDate.setText("");
        mLocationName.setText("");
        mAttendingTime.setText("");
    }


    @Override
    public void onClick(View v) {
        if (checkRequired(mActivityName) && checkRequired(mReportName) && validateDate(mActivityDate)) {
            if (!mAttendingTime.getText().toString().isEmpty())
                if (!validateTime(mAttendingTime))
                    return;

            addNewEvent();
        }
    }


    private boolean checkRequired(EditText editText) {
        if (!editText.getText().toString().isEmpty())
            return true;
        else
            editText.setError("This field is required!");
        return false;
    }

    private boolean validateDate(EditText editText) {
        Pattern p = Pattern.compile("^\\d{1,2}\\/\\d{1,2}\\/\\d{4}$");
        Matcher m = p.matcher(editText.getText().toString());
        if (m.matches())
            return true;
        else
            editText.setError("This field must be valid date (dd/MM/yyyy)!");
        return false;
    }

    private boolean validateTime(EditText editText) {
        Pattern p = Pattern.compile("^\\d{1,2}:\\d{1,2}$");
        Matcher m = p.matcher(editText.getText().toString());
        if (m.matches())
            return true;
        else
            editText.setError("This field must be valid date (HH:mm)!");
        return false;
    }


    private void addNewEvent() {
        buildDialog().show();
    }

    private AlertDialog buildDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.event_dialog, null);

        loadInfoForEventDialog(view);

        builder.setView(view)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        reset();
                        dialog.dismiss();
                        Toast.makeText(MainActivity.this, "Add new event successfully!", Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        return builder.create();
    }

    private void loadInfoForEventDialog(View view) {
        TextView name = (TextView) view.findViewById(R.id.activity_name);
        name.setText(mActivityName.getText().toString());

        TextView reporter = (TextView) view.findViewById(R.id.report_name);
        reporter.setText(mReportName.getText().toString());

        TextView date = (TextView) view.findViewById(R.id.activity_date);
        date.setText(mActivityDate.getText().toString());

        TextView location = (TextView) view.findViewById(R.id.location_name);
        location.setText(mLocationName.getText().toString());

        TextView time = (TextView) view.findViewById(R.id.attending_time);
        time.setText(mAttendingTime.getText().toString());
    }


}
