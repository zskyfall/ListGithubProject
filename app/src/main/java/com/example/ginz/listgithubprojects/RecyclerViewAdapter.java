package com.example.ginz.listgithubprojects;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {
    private List<Project> mProjects;
    private Context mContext;
    private static final String NAME = "NAME: ";
    private static final String DESCRIPTION = "DESCRIPTION: ";
    private static final String OWNER = "OWNER: ";
    private static final String FORKS = "FORKS: ";
    private static final String HTML_URL = "URL: ";
    private static final String LANGUAGE = "LANGUAGE: ";

    public RecyclerViewAdapter(Context context, List<Project> projects){
        this.mContext = context;
        this.mProjects = projects;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.project_item, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.textProjectName.setText(NAME + mProjects.get(position).getName());
        holder.textProjectLanguage.setText(LANGUAGE + mProjects.get(position).getLanguage());
        holder.textFork.setText(FORKS + mProjects.get(position).getFork());
        holder.textProjectDescription.setText(DESCRIPTION + mProjects.get(position).getDescription());
        holder.textProjectHomeUrl.setText(HTML_URL + mProjects.get(position).getHomeUrl());
        holder.textOwnerName.setText(OWNER + mProjects.get(position).getOwner());
    }

    @Override
    public int getItemCount() {
        return mProjects.size();
    }

    public class RecyclerViewHolder extends  RecyclerView.ViewHolder{
        TextView textProjectName;
        TextView textOwnerName;
        TextView textProjectDescription;
        TextView textFork;
        TextView textProjectLanguage;
        TextView textProjectHomeUrl;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            textProjectName = itemView.findViewById(R.id.text_project_name);
            textOwnerName = itemView.findViewById(R.id.text_owner_name);
            textProjectDescription = itemView.findViewById(R.id.text_project_description);
            textProjectHomeUrl = itemView.findViewById(R.id.text_home_url);
            textFork = itemView.findViewById(R.id.text_fork);
            textProjectLanguage = itemView.findViewById(R.id.text_project_language);
        }
    }
}
