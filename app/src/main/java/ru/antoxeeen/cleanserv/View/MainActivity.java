package ru.antoxeeen.cleanserv.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ru.antoxeeen.cleanserv.R;
import ru.antoxeeen.cleanserv.Repository.Data;
import ru.antoxeeen.cleanserv.ViewModel.DataViewModel;

import android.os.Bundle;

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

        viewModel.getAllData().observe(this, new Observer<List<Data>>() {
            @Override
            public void onChanged(List<Data> data) {
                adapter.submitList(data);
            }
        });
    }

    private void initVariable(){
        adapter = new DataAdapter();
        recyclerView = findViewById(R.id.recycler_view_data);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication())
                .create(DataViewModel.class);
    }
}