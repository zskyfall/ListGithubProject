package com.example.ginz.listgithubprojects;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Project> mProjects;
    private RecyclerView mRecyclerProject;
    private RecyclerViewAdapter mAdapter;
    private static final String LINK = "https://api.github.com/users/google/repos";
    private static final String NAME = "name";
    private static final String HTML_URL = "html_url";
    private static final String DESCRIPTION = "description";
    private static final String LANGUAGE = "language";
    private static final String FORKS = "forks";
    private static final String OWNER = "owner";
    private static final String LOGIN = "login";
    private static final int NUMB_ZERO = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerProject = findViewById(R.id.recycler_project_github);
        mProjects = new ArrayList<>();
        mAdapter = new RecyclerViewAdapter(this, mProjects);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerProject.setLayoutManager(linearLayoutManager);

        mRecyclerProject.setAdapter(mAdapter);

        new JSONTask().execute(LINK);

    }

    public class JSONTask extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... urls) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(urls[NUMB_ZERO]);
                connection = (HttpURLConnection) url.openConnection();

                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();

                String line = "";

                while((line = reader.readLine()) != null){
                    buffer.append(line);
                }

                return buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(connection != null) {
                    connection.disconnect();
                }
                try {
                    if(reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONArray jsonArray = new JSONArray(s);
                for(int i = NUMB_ZERO;i < jsonArray.length(); i++){
                    JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                    String name = jsonObject.getString(NAME);
                    String htmlUrl = jsonObject.getString(HTML_URL);
                    String description = jsonObject.getString(DESCRIPTION);
                    String language = jsonObject.getString(LANGUAGE);
                    int fork = jsonObject.getInt(FORKS);
                    JSONObject owner = jsonObject.getJSONObject(OWNER);
                    String ownerName = owner.getString(LOGIN);

                    Project project = new Project(name, ownerName, language, htmlUrl, fork, description);
                    mProjects.add(project);
                }
                mAdapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
