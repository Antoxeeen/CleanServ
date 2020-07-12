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
                int id = data.getId();
                int dbId = data.getDbId();
                intent.putExtra(EditDataActivity.EXTRA_ID, id);
                intent.putExtra(EditDataActivity.EXTRA_DBID, dbId);
                intent.putExtra(EditDataActivity.EXTRA_ADDRESS, data.getAddress());
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
            int id = data.getIntExtra(EditDataActivity.EXTRA_ID, -1);
            int dbId = data.getIntExtra(EditDataActivity.EXTRA_DBID, -1);
            if (id == -1 && dbId == -1) {
                Toast.makeText(this, "Не может быть обновлено", Toast.LENGTH_SHORT).show();
                return;
            }
            String currentAddress = data.getStringExtra(EditDataActivity.EXTRA_ADDRESS);
            double currentVolume = data.getDoubleExtra(EditDataActivity.EXTRA_VOLUME, 0.0);
            double currentWeight = data.getDoubleExtra(EditDataActivity.EXTRA_WEIGHT, 0.0);
            Data currentData = new Data(dbId, currentAddress, currentVolume, currentWeight);
            currentData.setId(id);
            viewModel.updateData(currentData);
        } else {
            Toast.makeText(this, "Нет изменений для сохранения", Toast.LENGTH_SHORT).show();
        }
    }
}