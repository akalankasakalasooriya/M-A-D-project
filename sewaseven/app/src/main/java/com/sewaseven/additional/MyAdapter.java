//package com.sewaseven.additional;
//
//import android.view.LayoutInflater;
//import android.view.ViewGroup;
//
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.sewaseven.sewaseven.R;
//
//public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
//
//    private final ClickListener listener;
//    private final List<MyItems> itemsList;
//
//    public MyAdapter(List<MyItems> itemsList, ClickListener listener) {
//        this.listener = listener;
//        this.itemsList = itemsList;
//    }
//
//    @Override public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_row_layout), parent, false), listener);
//    }
//
//    @Override public void onBindViewHolder(MyViewHolder holder, int position) {
//        // bind layout and data etc..
//    }
//
//    @Override public int getItemCount() {
//        return itemsList.size();
//    }
//
//    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
//
//        private ImageView iconImageView;
//        private TextView iconTextView;
//        private WeakReference<ClickListener> listenerRef;
//
//        public MyViewHolder(final View itemView, ClickListener listener) {
//            super(itemView);
//
//            listenerRef = new WeakReference<>(listener);
//            iconImageView = (ImageView) itemView.findViewById(R.id.myRecyclerImageView);
//            iconTextView = (TextView) itemView.findViewById(R.id.myRecyclerTextView);
//
//            itemView.setOnClickListener(this);
//            iconTextView.setOnClickListener(this);
//            iconImageView.setOnLongClickListener(this);
//        }
//
//        // onClick Listener for view
//        @Override
//        public void onClick(View v) {
//
//            if (v.getId() == iconTextView.getId()) {
//                Toast.makeText(v.getContext(), "ITEM PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(v.getContext(), "ROW PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
//            }
//
//            listenerRef.get().onPositionClicked(getAdapterPosition());
//        }
//
//
//        //onLongClickListener for view
//        @Override
//        public boolean onLongClick(View v) {
//
//            final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
//            builder.setTitle("Hello Dialog")
//                    .setMessage("LONG CLICK DIALOG WINDOW FOR ICON " + String.valueOf(getAdapterPosition()))
//                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//
//                        }
//                    });
//
//            builder.create().show();
//            listenerRef.get().onLongClicked(getAdapterPosition());
//            return true;
//        }
//    }
//}