package ru.antoxeeen.cleanserv.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ru.antoxeeen.cleanserv.R;
import ru.antoxeeen.cleanserv.repository.DataKT;
import ru.antoxeeen.cleanserv.viewModel.DataViewModel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LiveData<List<DataKT>> currentDataList;
    private DataViewModel viewModel;
    private DataAdapter adapter;
    private RecyclerView recyclerView;
    private long currentId;
    private String currentAddress;
    private String currentDate;
    private int currentRoute;
    private int currentBasketCount;
    private double currentBasketVolume;
    private double currentVolume;
    private double currentWeight;
    public static final int EDIT_DATA_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initVariable();

        adapter.setItemOnClickListener(new DataAdapter.onItemClickListener() {
            @Override
            public void onItemClick(DataKT data) {
                Intent intent = new Intent(MainActivity.this, EditDataActivity.class);
                currentId = data.getId();
                intent.putExtra(EditDataActivity.EXTRA_ID, currentId);
                intent.putExtra(EditDataActivity.EXTRA_ADDRESS, data.getAddress());
                intent.putExtra(EditDataActivity.EXTRA_DATE, data.getDate());
                intent.putExtra(EditDataActivity.EXTRA_ROUTE, data.getRoute());
                intent.putExtra(EditDataActivity.EXTRA_BASKET_COUNT, data.getBasketCount());
                intent.putExtra(EditDataActivity.EXTRA_BASKET_VOLUME, data.getBasketVolume());
                intent.putExtra(EditDataActivity.EXTRA_VOLUME, data.getGarbageVolume());
                intent.putExtra(EditDataActivity.EXTRA_WEIGHT, data.getGarbageWeight());
                startActivityForResult(intent, EDIT_DATA_REQUEST);
            }
        });
        viewModel.getAllDataServerDB();
        currentDataList.observe(this, new Observer<List<DataKT>>() {
            @Override
            public void onChanged(List<DataKT> data) {
                adapter.submitList(data);
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
        viewModel.deleteAllData();
        currentDataList = viewModel.getAllDataFromLocalDB();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == EDIT_DATA_REQUEST && resultCode == RESULT_OK) {
            currentId = data.getLongExtra(EditDataActivity.EXTRA_ID, -1);
            if (currentId == -1) {
                Toast.makeText(this, "Не может быть обновлено", Toast.LENGTH_SHORT).show();
                return;
            }
            currentAddress = data.getStringExtra(EditDataActivity.EXTRA_ADDRESS);
            currentDate = data.getStringExtra(EditDataActivity.EXTRA_DATE);
            currentRoute = data.getIntExtra(EditDataActivity.EXTRA_ROUTE, -1);
            currentBasketCount = data.getIntExtra(EditDataActivity.EXTRA_BASKET_COUNT, -1);
            currentBasketVolume = data.getDoubleExtra(EditDataActivity.EXTRA_BASKET_VOLUME, -1.0);
            currentVolume = data.getDoubleExtra(EditDataActivity.EXTRA_VOLUME, 0.0);
            currentWeight = data.getDoubleExtra(EditDataActivity.EXTRA_WEIGHT, 0.0);
            DataKT currentData = new DataKT(currentAddress, currentDate, currentRoute,
                    currentBasketCount, currentBasketVolume, currentVolume, currentWeight,currentId);
            viewModel.updateData(currentData);
        } else {
            Toast.makeText(this, "Нет изменений для сохранения", Toast.LENGTH_SHORT)
                    .show();
        }
    }
}