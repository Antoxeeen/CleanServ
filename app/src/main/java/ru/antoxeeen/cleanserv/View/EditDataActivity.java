package ru.antoxeeen.cleanserv.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import ru.antoxeeen.cleanserv.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Objects;

public class EditDataActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "ru.antoxeeen.cleanserv.View.EXTRA_ID";
    public static final String EXTRA_DBID = "ru.antoxeeen.cleanserv.View.EXTRA_DBID";
    public static final String EXTRA_ADDRESS = "ru.antoxeeen.cleanserv.View.EXTRA_ADDRESS";
    public static final String EXTRA_VOLUME = "ru.antoxeeen.cleanserv.View.EXTRA_VOLUME";
    public static final String EXTRA_WEIGHT = "ru.antoxeeen.cleanserv.View.EXTRA_WEIGHT";

    private TextView textView_address;
    private EditText editText_volume;
    private EditText editText_weight;

    private int currentId;
    private int currentDbId;
    private String currentAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);
        Objects.requireNonNull(getSupportActionBar())
                .setHomeAsUpIndicator(R.drawable.ic_close_black);

        initVariable();


    }

    private void initVariable() {
        setTitle("Изменение объема и веса");
        textView_address = findViewById(R.id.edit_activity_tv_address);
        editText_volume = findViewById(R.id.edit_activity_et_volume);
        editText_weight = findViewById(R.id.edit_activity_et_weight);

        Intent intent = getIntent();
        currentId = intent.getIntExtra(EXTRA_ID, -1);
        currentDbId = intent.getIntExtra(EXTRA_DBID, -1);
        currentAddress = intent.getStringExtra(EXTRA_ADDRESS);
        textView_address.setText(currentAddress);
        editText_volume.setText(String.valueOf(intent.getDoubleExtra(EXTRA_VOLUME, -1)));
        editText_weight.setText(String.valueOf(intent.getDoubleExtra(EXTRA_WEIGHT, -1)));

    }

    private void save() {
        Intent data = new Intent();
        data.putExtra(EXTRA_ID, currentId);
        data.putExtra(EXTRA_DBID, currentDbId);
        data.putExtra(EXTRA_ADDRESS, currentAddress);
        data.putExtra(EXTRA_VOLUME, Double.valueOf(editText_volume.getText().toString()));
        data.putExtra(EXTRA_WEIGHT, Double.valueOf(editText_weight.getText().toString()));
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.save_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save_menu) {
            save();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}