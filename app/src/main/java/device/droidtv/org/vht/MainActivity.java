package device.droidtv.org.vht;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private ListView mListView;
    ArrayList<Item> myItemList = new ArrayList<Item>();
    public MyAdapter mAdapter = null;
    private final String TAG = MainActivity.class.getName();
    public static String url = "http://www.parallelcodes.com/wp-content/uploads/2015/06/movies2.txt";
    ArrayList<Item> list = new ArrayList<Item>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.mylist);
        mAdapter = new MyAdapter(getApplicationContext(), list);
        mListView.setAdapter(mAdapter);
        FetchAsyncTask fetchData = new FetchAsyncTask();
        fetchData.execute(url);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), Integer.toString(position), Toast.LENGTH_LONG).show();
            }
        });
    }

    class FetchAsyncTask extends AsyncTask<String, Void, ArrayList<Item>> {
        @Override
        protected ArrayList<Item> doInBackground(String... params) {
            Log.d(TAG, "doinbackground called");
            String jsonString = null;
            try {
                URL url = new URL(params[0]);
                Log.d(TAG, "url is: "+ url);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();
                InputStream input = urlConnection.getInputStream();
                byte[] data = new byte[1024];
                ByteArrayOutputStream bOutPut = new ByteArrayOutputStream();
                while (input.read(data) != -1) {
                    bOutPut.write(data);
                }
                jsonString = new String(bOutPut.toByteArray());
                Log.d("amit", "String is: " + jsonString);
                JSONArray jsonArray = new JSONArray(jsonString.toString());
                int arraylength = jsonArray.length();

                for (int i = 0; i < arraylength; i++) {
                    list.add(changeObj(jsonArray.getJSONObject(i)));
                }
                return list;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        private Item changeObj(JSONObject obj) throws JSONException {
            String title = obj.getString("Movie");
            String descriptionName = obj.getString("Category");
            return new Item(title, descriptionName);
        }

        @Override
        protected void onPostExecute(ArrayList<Item> items) {
            Log.d(TAG, "onPostExecute called");
            super.onPostExecute(items);
            mAdapter.setItemList(list);
            mAdapter.notifyDataSetChanged();
        }
    }

}




