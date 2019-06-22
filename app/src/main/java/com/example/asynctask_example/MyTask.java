package com.example.asynctask_example;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Button;

public class MyTask extends AsyncTask<Void,Integer,String> {
    private Context context;
    TextView textView;
    Button button;
    ProgressDialog progressDialog;
    MyTask(Context context, TextView textView, Button button){
        this.context = context;
        this.textView = textView;
        this.button = button;
    }


    @Override
    protected String doInBackground(Void... voids) {
        int i = 0;
        synchronized (this){
            while (i<10){
                try {
                    wait(1500);
                    i++;
                    publishProgress(i);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }

        return "Download complete.....";
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Download in progress....");
        progressDialog.setMax(10);
        progressDialog.setProgress(0);
        progressDialog.setProgressStyle(progressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(String result) {
        textView.setText(result);
        button.setEnabled(true);
        progressDialog.hide();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress = values[0];
        progressDialog.setProgress(progress);
        textView.setText("Download in progress.....");
    }
}
