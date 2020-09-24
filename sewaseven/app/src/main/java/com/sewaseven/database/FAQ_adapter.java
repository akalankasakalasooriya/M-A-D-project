package com.sewaseven.database;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sewaseven.sewaseven.FAQUpdate;
import com.sewaseven.sewaseven.R;

import java.util.List;

public class FAQ_adapter extends RecyclerView.Adapter<FAQ_adapter.FAQ_view_holder> {
    private Context mCtx;
    private List<FAQModel> FAQ_list;

    public FAQ_adapter(Context mCtx, List<FAQModel> FAQ_list) {
        this.mCtx = mCtx;
        this.FAQ_list = FAQ_list;
    }


    @NonNull
    @Override
    public FAQ_view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new FAQ_view_holder(
                LayoutInflater.from(mCtx).inflate(R.layout.recyle_asked_questions_single, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull FAQ_view_holder holder, int position) {
        FAQModel faqModel = FAQ_list.get(position);
        holder.answer.setText(faqModel.getAnswer());
        holder.question.setText(faqModel.getQuestion());

    }

    @Override
    public int getItemCount() {
        return FAQ_list.size();
    }

    class FAQ_view_holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView question, answer;

        public FAQ_view_holder(@NonNull View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.asked_questions_single_question);
            answer = itemView.findViewById(R.id.asked_questions_single_answer);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            FAQModel faqModel = FAQ_list.get(getAdapterPosition());
            Intent intent = new Intent(mCtx, FAQUpdate.class);
            intent.putExtra("docID", faqModel.getDocID());
            mCtx.startActivity(intent);


        }
    }
}
