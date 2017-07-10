package arksoft.com.asnyctask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {

    ProgressBar progressBar;
    ListView listView;
    private static String [] isimler={"Geleceği","Yazan","Kadınlar","Egitimi"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView)findViewById(R.id.listView);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);

        listView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                new ArrayList<String>()));
                new MyTask().execute();

    }
    class MyTask extends AsyncTask<Void,String,String>{
        ArrayAdapter<String> adapter;
        ProgressBar progressBar;

        int count;

        @Override
        protected void onPreExecute() {
            adapter=(ArrayAdapter<String>)listView.getAdapter();
            progressBar=(ProgressBar)findViewById(R.id.progressBar);
            progressBar.setMax(4);
            progressBar.setProgress(0);
            count=0;
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            adapter.add(values[0]);
            count++;
            progressBar.setProgress(count);
        }

        @Override
        protected String doInBackground(Void... voids) {
            for(String name:isimler){
                publishProgress(name);
                try {
                    Thread.sleep(Long.parseLong("2000"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            return "İşlem Bitti";
        }

        @Override
        protected void onPostExecute(String s) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(MainActivity.this, "Veriler Eklendi", Toast.LENGTH_SHORT).show();
        }
    }
}
