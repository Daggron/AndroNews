package com.example.andronews;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<news>> {
    private static final int LOADER_ID = 1;
    View loadingIndicator;


    public static final String LOG_TAG = MainActivity.class.getName();
    private newsAdapter adapter;
    private static final String API = "https://content.guardianapis.com/search?order-by=newest&show-elements=image&q=android&api-key=5f240507-4f2d-439d-98d9-985a00831805";

    private TextView mEmptyStateTextView;

    @Override
    public  Loader<List<news>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        return new newsLoader(this, API);
    }

    @Override
    public void onLoadFinished(Loader<List<news>> loader, List<news> data) {

        GridView newsListView =  findViewById(R.id.cardView);

        mEmptyStateTextView = findViewById(R.id.empty_view);
        newsListView.setEmptyView(mEmptyStateTextView);

        loadingIndicator.setVisibility(View.GONE);
        // Clear the adapter of previous news data
        adapter.clear();
        // If there is a valid list of news then add
        if (data != null && !data.isEmpty()) {
            adapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<news>> loader) {

        adapter.clear();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadingIndicator = findViewById(R.id.loading_indicator);


        // Create a fake list of earthquake locations.
        // Find a reference to the {@link ListView} in the layout
        final GridView newsListView = (GridView) findViewById(R.id.cardView);

        adapter = new newsAdapter(getApplication(), 0, new ArrayList<news>());


        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                news e = adapter.getItem(position);
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(e.getUrl()));
                startActivity(i);


            }
        });

        newsListView.setAdapter(adapter);

        final LoaderManager loaderManager = getLoaderManager();

        // Initialize the loader. Pass in the int ID constant defined above and pass in null for
        // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
        // because this activity implements the LoaderCallbacks interface).

        final ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            loaderManager.initLoader(LOADER_ID, null, this);
            mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
            newsListView.setEmptyView(mEmptyStateTextView);

        } else {

            loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
            mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
            newsListView.setEmptyView(mEmptyStateTextView);
            mEmptyStateTextView.setText(R.string.no_internet);
        }


    }


}
