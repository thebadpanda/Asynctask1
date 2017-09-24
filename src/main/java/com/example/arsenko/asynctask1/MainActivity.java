package com.example.arsenko.asynctask1;

        import android.app.Activity;
        import android.os.AsyncTask;
        import android.os.SystemClock;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ProgressBar;
        import android.widget.TextView;
        import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Button btn;
    ProgressBar prBar;
    TextView percentage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.btn);
        prBar = (ProgressBar) findViewById(R.id.progress);
        percentage = (TextView) findViewById(R.id.percentage);

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                btn.setEnabled(false);

                new DownloadImageTask().execute();
            }
        });
    }

    private class DownloadImageTask extends AsyncTask<Void, Integer, Void> {

        int progress_status;

        protected void onPreExecute() {
            progress_status = 0;
            percentage.setText("Downloading 0%");
        }

        protected Void doInBackground(Void... params) {
            while (progress_status < 100) {

                progress_status += 2;

                publishProgress(progress_status);
                SystemClock.sleep(300);

            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            prBar.setProgress(values[0]);
            percentage.setText("загрузка " + values[0] + "%");

        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            Toast.makeText(MainActivity.this, "work onPostExecute()", Toast.LENGTH_SHORT).show();

            percentage.setText("Download complete");
            btn.setEnabled(true);
        }


    }
}
