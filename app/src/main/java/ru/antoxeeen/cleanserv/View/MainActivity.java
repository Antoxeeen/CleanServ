package ru.antoxeeen.cleanserv.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ru.antoxeeen.cleanserv.R;
import ru.antoxeeen.cleanserv.Repository.Data;
import ru.antoxeeen.cleanserv.ViewModel.DataViewModel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DataViewModel viewModel;
    private DataAdapter adapter;
    private RecyclerView recyclerView;
    private int currentId;
    private int currentDbId;
    private String currentAddress;
    private String currentDate;
    private int currentRoute;
    private double currentVolume;
    private double currentWeight;
    public static final int EDIT_DATA_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initVariable();
        viewModel.getAllData().observe(this, new Observer<List<Data>>() {
            @Override
            public void onChanged(List<Data> data) {
                adapter.submitList(data);
            }
        });

        adapter.setItemOnClickListener(new DataAdapter.onItemClickListener() {
            @Override
            public void onItemClick(Data data) {
                Intent intent = new Intent(MainActivity.this, EditDataActivity.class);
                currentId = data.getId();
                currentDbId = data.getDbId();
                intent.putExtra(EditDataActivity.EXTRA_ID, currentId);
                intent.putExtra(EditDataActivity.EXTRA_DBID, currentDbId);
                intent.putExtra(EditDataActivity.EXTRA_ADDRESS, data.getAddress());
                intent.putExtra(EditDataActivity.EXTRA_DATE, data.getDate());
                intent.putExtra(EditDataActivity.EXTRA_ROUTE, data.getRoute());
                intent.putExtra(EditDataActivity.EXTRA_VOLUME, data.getGarbageVolume());
                intent.putExtra(EditDataActivity.EXTRA_WEIGHT, data.getGarbageWeight());
                startActivityForResult(intent, EDIT_DATA_REQUEST);
            }
        });
    }

    private void initVariable() {
        adapter = new DataAdapter();
        recyclerView = findViewById(R.id.recycler_view_data);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication())
                .create(DataViewModel.class);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == EDIT_DATA_REQUEST && resultCode == RESULT_OK) {
            currentId = data.getIntExtra(EditDataActivity.EXTRA_ID, -1);
            currentDbId = data.getIntExtra(EditDataActivity.EXTRA_DBID, -1);
            if (currentId == -1 && currentDbId == -1) {
                Toast.makeText(this, "Не может быть обновлено", Toast.LENGTH_SHORT).show();
                return;
            }
            currentAddress = data.getStringExtra(EditDataActivity.EXTRA_ADDRESS);
            currentDate = data.getStringExtra(EditDataActivity.EXTRA_DATE);
            currentRoute = data.getIntExtra(EditDataActivity.EXTRA_ROUTE, -1);
            currentVolume = data.getDoubleExtra(EditDataActivity.EXTRA_VOLUME, 0.0);
            currentWeight = data.getDoubleExtra(EditDataActivity.EXTRA_WEIGHT, 0.0);
            Data currentData = new Data(currentDbId, currentAddress, currentDate, currentRoute,
                    currentVolume, currentWeight);
            currentData.setId(currentId);
            viewModel.updateData(currentData);
        } else {
            Toast.makeText(this, "Нет изменений для сохранения", Toast.LENGTH_SHORT)
                    .show();
        }
    }
}