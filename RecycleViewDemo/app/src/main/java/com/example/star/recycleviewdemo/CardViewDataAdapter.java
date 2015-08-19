package com.example.star.recycleviewdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;

/**
 * Created by xiongxingxing on 15/8/18.
 */

public class CardViewDataAdapter extends RecyclerView.Adapter<CardViewDataAdapter.ViewHolder> {

  private List<Student> stList;

  public CardViewDataAdapter(List<Student> students) {
    this.stList = students;
  }

  // Create new views
  @Override public CardViewDataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    // create a new view
    View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_row, null);

    // create ViewHolder

    ViewHolder viewHolder = new ViewHolder(itemLayoutView);
    return viewHolder;
  }

  @Override public void onBindViewHolder(ViewHolder viewHolder, int position) {

    viewHolder.tvName.setText(stList.get(position).getName());

    viewHolder.tvEmailId.setText(stList.get(position).getEmailId());

    viewHolder.singlestudent = stList.get(position);
  }

  // Return the size arraylist
  @Override public int getItemCount() {
    return stList.size();
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {

    public TextView tvName;
    public TextView tvEmailId;

    public Student singlestudent;

    public ViewHolder(View itemLayoutView) {
      super(itemLayoutView);

      tvName = (TextView) itemLayoutView.findViewById(R.id.tvName);

      tvEmailId = (TextView) itemLayoutView.findViewById(R.id.tvEmailId);

      itemLayoutView.setOnClickListener(new View.OnClickListener() {

        @Override public void onClick(View v) {

          Toast.makeText(v.getContext(), "Data : \n" + singlestudent.getName() + " \n" + singlestudent.getEmailId(),
              Toast.LENGTH_SHORT).show();
        }
      });
    }
  }
}
