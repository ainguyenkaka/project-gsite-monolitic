package app.discovery.com.idiscovery;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        if (checkRequired(mActivityName) &&
                checkRequired(mReportName) &&
                checkDate(mActivityDate)) {
            if (!mAttendingTime.getText().toString().isEmpty())
                if (!checkTime(mAttendingTime))
                    return;

            addNewEvent();
        }
    }

    private boolean checkRequired(EditText editText){
        if (!MainValidation.checkEmpty(editText.getText().toString()))
            return true;
        else
            editText.setError(MainValidError.REQUIRED);
        return false;
    }


    private boolean checkDate(EditText editText){
        if (MainValidation.validateDate(editText.getText().toString()))
            return true;
        else
            editText.setError(MainValidError.DATE);
        return false;
    }

    private boolean checkTime(EditText editText){
        if (MainValidation.validateTime(editText.getText().toString()))
            return true;
        else
            editText.setError(MainValidError.TIME);
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
