package ru.antoxeeen.cleanserv.view;

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
import ru.antoxeeen.cleanserv.repository.DataKT;

class DataAdapter extends ListAdapter<DataKT, DataAdapter.DataHolder> {
    private onItemClickListener listener;

    protected DataAdapter() {
        super(DIFF_CALLBACK);
    }

    public static final DiffUtil.ItemCallback<DataKT> DIFF_CALLBACK = new DiffUtil.ItemCallback<DataKT>() {
        @Override
        public boolean areItemsTheSame(@NonNull DataKT oldItem, @NonNull DataKT newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull DataKT oldItem, @NonNull DataKT newItem) {
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
        DataKT currentData = getItem(position);
        holder.textViewAddress.setText(currentData.getAddress());
        holder.editTextGarbageBasketCount.setText(String.valueOf(currentData.getBasketCount()));
        holder.editTextVolume.setText(String.valueOf(currentData.getGarbageVolume()));
        holder.editTextWeight.setText(String.valueOf(currentData.getGarbageWeight()));
    }

    public interface onItemClickListener{
        void onItemClick(DataKT dataKT);
    }

    public void setItemOnClickListener(onItemClickListener listener){
        this.listener = listener;
    }

    class DataHolder extends RecyclerView.ViewHolder {

        private TextView textViewAddress;
        private EditText editTextGarbageBasketCount;
        private EditText editTextVolume;
        private EditText editTextWeight;

        public DataHolder(@NonNull View itemView) {
            super(itemView);
            textViewAddress = itemView.findViewById(R.id.text_view_address);
            editTextGarbageBasketCount = itemView.findViewById(R.id.edit_text_garbageBasketCount);
            editTextVolume = itemView.findViewById(R.id.edit_text_volume);
            editTextWeight = itemView.findViewById(R.id.edit_text_weight);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }
}
