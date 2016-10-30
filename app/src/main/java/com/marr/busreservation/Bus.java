package com.marr.busreservation;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.marr.busreservation.Bases.Constants;
import com.marr.busreservation.Bases.Item;
import com.marr.busreservation.SapanaTravel.Sapana;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

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

public class Bus extends AppCompatActivity {
    private ListView lvItem;
    private  List<Item> itemList = new ArrayList<Item>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);
        new JSONTask().execute(Constants.BASE_URL +"/busreservation/api.php");
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .build();
        ImageLoader.getInstance().init(config);
        lvItem = (ListView) findViewById(R.id.item_list);
        setTitle(getResources().getText(R.string.Travels));
        lvItem.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Item model = (Item) parent.getItemAtPosition(position);
                        switch (model.getType()) {
                            //type= 0
//                          case 0:
//                               startActivity(new Intent(getApplicationContext(), SapanaAC.class));
//                              break;
                            case 0:
                                startActivity(new Intent(getApplicationContext(), Sapana.class));
                                break;
//                            case 2:
//                                startActivity(new Intent(getApplicationContext(), SamarTravel.class));
//                                break;
//                            case 3:
//                                startActivity(new Intent(getApplicationContext(), SulavTravel.class));
//                                break;
//                            case 4:
//                                startActivity(new Intent(getApplicationContext(), PanasDeluxe.class));
//                                break;
//                            case 5:
//                                startActivity(new Intent(getApplicationContext(), JaiHoDeluxe.class));
//                                break;
//                            case 6:
//                                startActivity(new Intent(getApplicationContext(), AnmolAC.class));
//                                break;
//                            case 7:
//                                startActivity(new Intent(getApplicationContext(), AnmolNonAC.class));
//                                break;
//                            case 8:
//                                startActivity(new Intent(getApplicationContext(), AppleTravel.class));
//                                break;

                                default:
                                break;
                        }
                    }
                }
        );

    }

    public class JSONTask extends AsyncTask<String, String, List<Item>> {

        @Override
        protected List<Item> doInBackground(String... params) {

            HttpURLConnection connection = null;
            BufferedReader reader = null;
            List<Item> itemList = new ArrayList<>();
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                String finaljson = buffer.toString();

                Log.i("JSON", "String = " + finaljson);
                JSONArray array = new JSONArray(finaljson);

                int size = array.length();
                for (int i = 0; i < size; i++) {
                    JSONObject j = array.getJSONObject(i);
                    Item itemList1 = new Item();
                    itemList1.setImage("http://192.168.137.1/busreservation/image/" + j.getString("Image"));
                    itemList1.setId(j.getString("Id"));
                    itemList1.setName(j.getString("Name"));
                    itemList1.setType(j.getInt("Type"));
                    itemList.add(itemList1);
                }
                return itemList;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return itemList;
        }

//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            dialog.show();
//        }

        @Override
        protected void onPostExecute(List<Item> result) {
            super.onPostExecute(result);
//            dialog.dismiss();
            ItemAdapter adapter = new ItemAdapter(getApplicationContext(),R.layout.item, result);
            lvItem.setAdapter(adapter);

        }

    }

    public class ItemAdapter extends ArrayAdapter {
        public List<Item> itemList;
        private int resource;
        private LayoutInflater inflater;
        private int Quantity=0;


        public ItemAdapter(Context context, int resource, List<Item> objects) {
            super(context, resource, objects);
            itemList = objects;
            this.resource = resource;
            inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            final ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.item, null);
                holder.imageicon = (ImageView) convertView.findViewById(R.id.imageicon);
                holder.tvIname = (TextView) convertView.findViewById(R.id.tvIname);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.tvIname.setText(itemList.get(position).getName());
            Log.d("MainActivity", "Image = " + itemList.get(position).getImage());
            ImageLoader.getInstance().displayImage(itemList.get(position).getImage(), holder.imageicon);
            return convertView;
        }

        class ViewHolder {
            private ImageView imageicon;
            private TextView tvIname;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
