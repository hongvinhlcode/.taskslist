package hongvinhlcode.app.todolist.Main.Main.Create;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import hongvinhlcode.app.todolist.Main.Main.MainActivity.MainActivity;
import hongvinhlcode.app.todolist.R;

public class CreateActivity extends AppCompatActivity {

    TextView txtDate, txtTime;
    Button btnApply, btnCancel;
    EditText edtTitle, edtDes;
    CheckBox cbRemind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.darktheme);
        } else {
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        getSupportActionBar().setTitle("Create New Task");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtDate = findViewById(R.id.txtDate);
        txtTime = findViewById(R.id.txtTime);
        btnApply = findViewById(R.id.btnApply);
        btnCancel = findViewById(R.id.btnCancel);
        edtTitle = findViewById(R.id.edtInputTitle);
        edtDes = findViewById(R.id.edtInputDes);
        cbRemind = findViewById(R.id.cbRemind);

        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PickDate();
            }
        });
        txtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PickTime();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean checkRM = false;
                String title = edtTitle.getText().toString().trim();
                String des = edtDes.getText().toString().trim();
                String remind;
                String time = txtTime.getText().toString().trim();
                String date = txtDate.getText().toString().trim();
                if (cbRemind.isChecked()) {
                    remind = "Remind me" + " at " + time + " (" + date + ")";
                    checkRM = true;
                } else {
                    remind = "Not remind me";
                    checkRM = false;
                }
                if (title.equals("")) {
                    Toast.makeText(CreateActivity.this, "Set the title", Toast.LENGTH_SHORT).show();
                } else {
                    if (date.equals("Date")) {
                        Toast.makeText(CreateActivity.this, "Set the date", Toast.LENGTH_SHORT).show();
                    } else if (time.equals("Hour")) {
                        Toast.makeText(CreateActivity.this, "Set the time", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(CreateActivity.this, "Create Succesful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CreateActivity.this, MainActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("title", title);
                        bundle.putString("des", des);
                        bundle.putString("remind", remind);
                        bundle.putString("date", date);
                        bundle.putString("time", time);
                        bundle.putBoolean("checkRM",checkRM);
                        intent.putExtra("data", bundle);
                        startActivity(intent);
                    }
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateActivity.this, MainActivity.class));
            }
        });
    }

    private void PickDate() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int date = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);

        DatePickerDialog datePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                c.set(year, month, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                txtDate.setText(simpleDateFormat.format(c.getTime()));
            }
        }, year, month, date);
        datePicker.show();
    }

    private void PickTime() {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minutes = c.get(Calendar.MINUTE);
        TimePickerDialog timePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                c.set(0, 0, 0, hourOfDay, minute);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                txtTime.setText(simpleDateFormat.format(c.getTime()));
            }
        }, hour, minutes, true);
        timePicker.show();
    }
}
