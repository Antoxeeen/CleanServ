package ru.antoxeeen.cleanserv.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import ru.antoxeeen.cleanserv.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.Objects;

public class EditDataActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "ru.antoxeeen.cleanserv.View.EXTRA_ID";
    public static final String EXTRA_ADDRESS = "ru.antoxeeen.cleanserv.View.EXTRA_ADDRESS";
    public static final String EXTRA_DATE = "ru.antoxeeen.cleanserv.View.EXTRA_DATE";
    public static final String EXTRA_ROUTE = "ru.antoxeeen.cleanserv.View.EXTRA_ROUTE";
    public static final String EXTRA_BASKET_COUNT = "ru.antoxeeen.cleanserv.View.EXTRA_BASKETCOUNT";
    public static final String EXTRA_BASKET_VOLUME = "ru.antoxeeen.cleanserv.View.EXTRA_BASKETVOLUME";
    public static final String EXTRA_VOLUME = "ru.antoxeeen.cleanserv.View.EXTRA_VOLUME";
    public static final String EXTRA_WEIGHT = "ru.antoxeeen.cleanserv.View.EXTRA_WEIGHT";
    private static final double FOR_WEIGHT_COUNT = 0.144;

    private TextView textView_address;
    private EditText editText_volume;
    private EditText editText_weight;
    private NumberPicker numberPicker_basketCount;
    private long currentId;
    private String currentAddress;
    private String currentDate;
    private int currentRoute;
    private int currentBasketCount;
    private double currentBasketVolume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);
        Objects.requireNonNull(getSupportActionBar())
                .setHomeAsUpIndicator(R.drawable.ic_close_black);

        initVariable();
        numberPicker_basketCount.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                editText_volume.setText(String.valueOf(newVal * currentBasketVolume));
                editText_weight.setText(String.valueOf(newVal * currentBasketVolume * FOR_WEIGHT_COUNT));
            }
        });
    }

    private void initVariable() {
        setTitle("Изменение объема и веса");
        textView_address = findViewById(R.id.edit_activity_tv_address);
        editText_volume = findViewById(R.id.edit_activity_et_volume);
        editText_weight = findViewById(R.id.edit_activity_et_weight);
        numberPicker_basketCount = findViewById(R.id.number_picker_basket_count);
        numberPicker_basketCount.setWrapSelectorWheel(false);
        numberPicker_basketCount.setMinValue(0);
        numberPicker_basketCount.setMaxValue(10);

        Intent intent = getIntent();
        currentId = intent.getLongExtra(EXTRA_ID, -1);
        currentAddress = intent.getStringExtra(EXTRA_ADDRESS);
        currentDate = intent.getStringExtra(EXTRA_DATE);
        currentRoute = intent.getIntExtra(EXTRA_ROUTE, -1);
        currentBasketCount = intent.getIntExtra(EXTRA_BASKET_COUNT, -1);
        currentBasketVolume = intent.getDoubleExtra(EXTRA_BASKET_VOLUME, -1.0);
        textView_address.setText(currentAddress);
        numberPicker_basketCount.setValue(currentBasketCount);
        editText_volume.setText(String.valueOf(intent.getDoubleExtra(EXTRA_VOLUME, -1)));
        editText_weight.setText(String.valueOf(intent.getDoubleExtra(EXTRA_WEIGHT, -1)));

    }

    private void save() {
        Intent data = new Intent();
        currentBasketCount = numberPicker_basketCount.getValue();
        data.putExtra(EXTRA_ID, currentId);
        data.putExtra(EXTRA_ADDRESS, currentAddress);
        data.putExtra(EXTRA_DATE, currentDate);
        data.putExtra(EXTRA_ROUTE, currentRoute);
        data.putExtra(EXTRA_BASKET_COUNT, currentBasketCount);
        data.putExtra(EXTRA_BASKET_VOLUME, currentBasketVolume);
        data.putExtra(EXTRA_VOLUME, Double.valueOf(editText_volume.getText().toString()));
        data.putExtra(EXTRA_WEIGHT, Double.valueOf(editText_weight.getText().toString()));
        setResult(RESULT_OK, data);
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