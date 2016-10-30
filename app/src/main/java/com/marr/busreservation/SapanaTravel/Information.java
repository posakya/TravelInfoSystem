package com.marr.busreservation.SapanaTravel;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.marr.busreservation.Bases.Constants;
import com.marr.busreservation.Bases.Info;
import com.marr.busreservation.Bases.Travel_detail;
import com.marr.busreservation.Bus;
import com.marr.busreservation.R;
import com.nostra13.universalimageloader.core.ImageLoader;

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

public class Information extends Fragment {

    private ListView lvdetail;
    private View myView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myView= inflater.inflate(R.layout.activity_travel_info, container, false);
        new JSONTask().execute("http://192.168.137.1/busreservation/SapanaTravel/traveltype.php");
        lvdetail = (ListView) myView.findViewById(R.id.travel_info_list);

        return myView;
    }

    public class JSONTask extends AsyncTask<String, String, List<Info>> {
        Context c;

        @Override
        protected List<Info> doInBackground(String... params) {

            HttpURLConnection connection = null;
            BufferedReader reader = null;
            List<Info> informations = new ArrayList<>();
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
                    Info information = new Info();

                    information.setId(j.getString("Id"));
                    information.setArrival_Time(j.getString("Arrival_Time"));
                    information.setDeparture_Time(j.getString("Departure_Time"));
                    informations.add(information);
                }
                return informations;
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
            return informations;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(List<Info> result) {
            super.onPostExecute(result);


            TravelAdapter adapter = new TravelAdapter(getActivity(), R.layout.information, result);
            lvdetail.setAdapter(adapter);
        }
    }

    public class TravelAdapter extends ArrayAdapter {
        public List<Info> infos;
        private int resource;
        private LayoutInflater inflater;


        public TravelAdapter(Context context, int resource, List<Info> objects) {
            super(context, resource, objects);
            infos = objects;
            this.resource = resource;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;

            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.information, null);
                holder.atime = (TextView) convertView.findViewById(R.id.txt_arrival_time);
                holder.dtime = (TextView) convertView.findViewById(R.id.txt_departure_time);
                holder.call_birgunj=(ImageButton)convertView.findViewById(R.id.brj_call);
                holder.call_kaliya=(ImageButton)convertView.findViewById(R.id.kaliya_call);
                holder.call_sundhara=(ImageButton)convertView.findViewById(R.id.sundhara_call);
                holder.call_gaushala=(ImageButton)convertView.findViewById(R.id.gaushala_call);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.atime.setText(infos.get(position).getArrival_Time());
            holder.dtime.setText(infos.get(position).getDeparture_Time());
            holder.call_birgunj.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:9810310933"));
                    if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    startActivity(callIntent);
                }
            });


            holder.call_gaushala.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:9811106602"));
                    if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    startActivity(callIntent);
                }
            });
            holder.call_sundhara.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:9823000790"));
                    if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    startActivity(callIntent);
                }
            });
            holder.call_kaliya.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:9860306332"));
                    if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    startActivity(callIntent);
                }
            });

            return convertView;
        }

        class ViewHolder {
            private TextView atime,dtime;
            public ImageButton call_kaliya,call_birgunj,call_gaushala,call_sundhara;
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
        }
        return super.onOptionsItemSelected(item);

    }
}
