package ru.antoxeeen.cleanserv.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import ru.antoxeeen.cleanserv.R;
import ru.antoxeeen.cleanserv.Repository.Data;

class DataAdapter extends ListAdapter<Data, DataAdapter.DataHolder> {

    protected DataAdapter() {
        super(DIFF_CALLBACK);
    }

    public static final DiffUtil.ItemCallback<Data> DIFF_CALLBACK = new DiffUtil.ItemCallback<Data>() {
        @Override
        public boolean areItemsTheSame(@NonNull Data oldItem, @NonNull Data newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Data oldItem, @NonNull Data newItem) {
            return oldItem.getAddress().equals(newItem.getAddress());
        }
    };

    @NonNull
    @Override
    public DataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new DataHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DataHolder holder, int position) {
        Data currentData = getItem(position);
        holder.textViewAddress.setText(currentData.getAddress());
        holder.editTextVolume.setText(String.valueOf(currentData.getGarbageVolume()));
        holder.editTextWeight.setText(String.valueOf(currentData.getGarbageWeight()));
    }

    public Data getDataAt(int position){
        return getItem(position);
    }

    static class DataHolder extends RecyclerView.ViewHolder {

        private TextView textViewAddress;
        private EditText editTextVolume;
        private EditText editTextWeight;

        public DataHolder(@NonNull View itemView) {
            super(itemView);
            textViewAddress = itemView.findViewById(R.id.text_view_address);
            editTextVolume = itemView.findViewById(R.id.edit_text_volume);
            editTextWeight = itemView.findViewById(R.id.edit_text_weight);
        }
    }
}
