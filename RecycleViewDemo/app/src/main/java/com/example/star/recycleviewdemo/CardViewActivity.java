package com.example.star.recycleviewdemo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class CardViewActivity extends ActionBarActivity {
  @InjectView(R.id.toolbar) Toolbar mToolbar;
  @InjectView(R.id.recycler_view) RecyclerView mRecyclerView;

  private List<Student> studentList;

  private int currentPage = 1;

  private int ival = 1;
  private int loadLimit = 10;

  CardViewDataAdapter mAdapter;

  LinearLayoutManager mLayoutManager;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_card_view);
    ButterKnife.inject(this);

    studentList = new ArrayList<>();

    loadData(currentPage);

    if (mToolbar != null) {

      setSupportActionBar(mToolbar);

      getSupportActionBar().setTitle("Android Students");
    }

    mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

    // use this setting to improve performance if you know that changes
    // in content do not change the layout size of the RecyclerView
    mRecyclerView.setHasFixedSize(true);

    mLayoutManager = new LinearLayoutManager(this);

    // use a linear layout manager
    mRecyclerView.setLayoutManager(mLayoutManager);

    // create an Object for Adapter
    mAdapter = new CardViewDataAdapter(studentList);

    // set the adapter object to the Recyclerview
    mRecyclerView.setAdapter(mAdapter);

    mRecyclerView.setOnScrollListener(new EndlessRecyclerOnScrollListener(mLayoutManager) {
      @Override public void onLoadMore(int current_page) {
        // do somthing...

        loadMoreData(current_page);
      }
    });
  }

  private void loadData(int current_page) {

    // I have not used current page for showing demo, if u use a webservice
    // then it is useful for every call request

    for (int i = ival; i <= loadLimit; i++) {
      Student st = new Student("Student " + i, "androidstudent" + i + "@gmail.com", false);

      studentList.add(st);
      ival++;
    }
  }

  private void loadMoreData(int current_page) {

    // I have not used current page for showing demo, if u use a webservice
    // then it is useful for every call request

    loadLimit = ival + 10;

    for (int i = ival; i <= loadLimit; i++) {
      Student st = new Student("Student " + i, "androidstudent" + i + "@gmail.com", false);

      studentList.add(st);
      ival++;
    }
    mAdapter.notifyDataSetChanged();
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_card_view, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
