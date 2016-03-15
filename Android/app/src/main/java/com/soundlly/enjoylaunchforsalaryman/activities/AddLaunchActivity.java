package com.soundlly.enjoylaunchforsalaryman.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.appyvet.rangebar.RangeBar;
import com.soundlly.enjoylaunchforsalaryman.R;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by soundllydev on 2016. 3. 11..
 */
public class AddLaunchActivity extends AppCompatActivity {

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy년 MM월 DD일 HH시 mm분");
    private DecimalFormat df = new DecimalFormat("###,###.####");

    @Bind(R.id.input_date)
    EditText inputDate;

    @Bind(R.id.input_store_name)
    EditText inputStoreName;

    @Bind(R.id.input_hunger_point)
    RangeBar inputHungerPoint;

    @Bind(R.id.txt_hunger_point)
    TextView txtHungerPoint;

    @Bind(R.id.input_menu)
    EditText inputMenu;

    @Bind(R.id.input_price)
    EditText inputPrice;

    @Bind(R.id.input_satisfaction_point)
    AppCompatRatingBar inputSatisfactionPoint;

    @Bind(R.id.txt_satisfaction_point)
    TextView txtSatisfactionPoint;

    @Bind(R.id.button_add_launch)
    Button btnAddLaunch;

    private String result = "";

    private Date launchTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_launch);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        launchTime = Calendar.getInstance().getTime();
        inputDate.setText(simpleDateFormat.format(launchTime));
        inputDate.setInputType(InputType.TYPE_NULL);
        inputDate.requestFocus();

        inputHungerPoint.setOnRangeBarChangeListener(onRangeBarChangeListener);
        inputHungerPoint.setRangePinsByValue(0, 3);

        inputPrice.addTextChangedListener(textWatcher);

        inputSatisfactionPoint.setOnRatingBarChangeListener(onRatingBarChangeListener);
        inputSatisfactionPoint.setRating(3.0f);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @OnClick(R.id.input_date)
    public void onInputDateClick(View v) {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
            onDateSetListener,
            now.get(Calendar.YEAR),
            now.get(Calendar.MONTH),
            now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }

    @OnClick(R.id.button_add_launch)
    public void onAddLaunchClick(View v) {
        
    }

    private DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog
            .OnDateSetListener() {

        @Override
        public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
            launchTime.setYear(year);
            launchTime.setMonth(monthOfYear);
            launchTime.setDate(dayOfMonth);

            Calendar now = Calendar.getInstance();
            TimePickerDialog tpd = TimePickerDialog.newInstance(
                onTimeSetListener,
                now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE),
                now.get(Calendar.SECOND),
                true
            );
            tpd.show(getFragmentManager(), "Timepickerdialog");
        }
    };

    private TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog
            .OnTimeSetListener() {

        @Override
        public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int second) {
            launchTime.setHours(hourOfDay);
            launchTime.setMinutes(minute);
            launchTime.setSeconds(second);

            inputDate.setText(simpleDateFormat.format(launchTime));
        }
    };

    private RangeBar.OnRangeBarChangeListener onRangeBarChangeListener
            = new RangeBar.OnRangeBarChangeListener() {

        @Override
        public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex, int rightPinIndex,
                String leftPinValue, String rightPinValue) {
            txtHungerPoint.setText(rightPinValue);
        }
    };

    private RatingBar.OnRatingBarChangeListener onRatingBarChangeListener
            = new RatingBar.OnRatingBarChangeListener() {

        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            txtSatisfactionPoint.setText(String.valueOf(rating));
        }
    };

    private TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (!TextUtils.isEmpty(s) && !s.toString().equals(result)) {
                result = df.format(Long.parseLong(s.toString().replaceAll(",", "")));
                inputPrice.setText(result);
                inputPrice.setSelection(result.length());
            }
        }

        @Override
        public void afterTextChanged(Editable s) {}
    };
}
