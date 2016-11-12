package com.marr.busreservation.SapanaTravel;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.marr.busreservation.Bases.Constants;
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

public class KTMNon_AC extends Fragment {

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
        new JSONTask().execute(Constants.BASE_URL +"/BusReservation/SapanaTravel/traveldetailKTMNonAC.php");
        lvdetail = (ListView) myView.findViewById(R.id.travel_info_list);

        return myView;
    }

    public class JSONTask extends AsyncTask<String, String, List<Travel_detail>> {
        Context c;

        @Override
        protected List<Travel_detail> doInBackground(String... params) {

            HttpURLConnection connection = null;
            BufferedReader reader = null;
            List<Travel_detail> details = new ArrayList<>();
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
                    Travel_detail travel_detail = new Travel_detail();
                    travel_detail.setId(j.getString("Id"));
                    travel_detail.setBus_No(j.getString("Bus_No"));
                    travel_detail.setDriver_No(j.getString("Driver_No"));
                    travel_detail.setAvailable_Seat(j.getString("Available_Seat"));
                    travel_detail.setDestination(j.getString("Destination"));
                    travel_detail.setBus_Category(j.getString("Bus_Category"));
                    travel_detail.setDate(j.getString("Date"));
                    details.add(travel_detail);
                }
                return details;
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
            return details;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(List<Travel_detail> result) {
            super.onPostExecute(result);


            TravelAdapter adapter = new TravelAdapter(getActivity(), R.layout.businfo, result);
            lvdetail.setAdapter(adapter);
        }
    }

    public class TravelAdapter extends ArrayAdapter {
        public List<Travel_detail> travel_detail;
        private int resource;
        private LayoutInflater inflater;


        public TravelAdapter(Context context, int resource, List<Travel_detail> objects) {
            super(context, resource, objects);
            travel_detail = objects;
            this.resource = resource;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;

            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.businfo, null);
                holder.busnumber = (TextView) convertView.findViewById(R.id.txt_bus_number);
                holder.drivernumber = (TextView) convertView.findViewById(R.id.txt_driver_number);
                holder.availableseat = (TextView) convertView.findViewById(R.id.txt_available_seat);
                holder.destination = (TextView) convertView.findViewById(R.id.txt_destination);
                holder.date = (TextView) convertView.findViewById(R.id.txt_date);
                holder.btn_click=(Button) convertView.findViewById(R.id.btn_click);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            Log.d("MainActivity", "Bus_No = " + travel_detail.get(position).getBus_No());

            holder.busnumber.setText(travel_detail.get(position).getBus_No());
            Log.d("MainActivity", "Driver_No = " + travel_detail.get(position).getDriver_No());
            holder.drivernumber.setText(travel_detail.get(position).getDriver_No());
            Log.d("MainActivity", "Available_Seat = " + travel_detail.get(position).getAvailable_Seat());
            holder.availableseat.setText(travel_detail.get(position).getAvailable_Seat());
            Log.d("MainActivity", "Destination = " + travel_detail.get(position).getDestination());
            holder.destination.setText(travel_detail.get(position).getDestination());
            holder.date.setText(travel_detail.get(position).getDate());
            holder.btn_click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), PassengerKTMNonAC.class));
                }
            });
            return convertView;
        }

        class ViewHolder {
            private TextView busnumber, drivernumber, availableseat, destination, date;
            private Button btn_click;

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
