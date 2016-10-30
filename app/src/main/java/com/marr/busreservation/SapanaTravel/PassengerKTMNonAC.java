package com.marr.busreservation.SapanaTravel;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.marr.busreservation.Bases.Passenger;
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

public class PassengerKTMNonAC extends AppCompatActivity {

    private ListView lvpassenger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_info);
        PassengerKTMNonAC.this.setTitle("Passenger Detail");
        lvpassenger=(ListView) findViewById(R.id.travel_info_list);
        new JSONTask().execute("http://192.168.137.1/busreservation/SapanaTravel/passengerKTMNonAC.php");
    }
    public class JSONTask extends AsyncTask<String, String, List<Passenger>> {

        @Override
        protected List<Passenger> doInBackground(String... params) {

            HttpURLConnection connection = null;
            BufferedReader reader = null;
            List<Passenger> passengerList = new ArrayList<>();
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
                    Passenger passengers = new Passenger();
                    passengers.setId(j.getString("Id"));
                    passengers.setPassenger_Name(j.getString("Passenger_Name"));
                    passengers.setSeat_No(j.getString("Seat_No"));
                    passengers.setPhone(j.getString("Phone"));
                    passengers.setDestination(j.getString("Destination"));
                    passengers.setBus_No(j.getString("Bus_No"));
                    passengers.setBus_Category(j.getString("Bus_Category"));
                    passengers.setDate(j.getString("Date"));
                    passengers.setTime(j.getString("Time"));

                    passengerList.add(passengers);
                }
                return passengerList;
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
            return passengerList;
        }

//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            dialog.show();
//        }

        @Override
        protected void onPostExecute(List<Passenger> result) {
            super.onPostExecute(result);
//            dialog.dismiss();
            ItemAdapter adapter = new ItemAdapter(getApplicationContext(),R.layout.passenger, result);
            lvpassenger.setAdapter(adapter);

        }
    }

    public class ItemAdapter extends ArrayAdapter {
        public List<Passenger> passenger;
        private int resource;
        private LayoutInflater inflater;
        private int Quantity=0;


        public ItemAdapter(Context context, int resource, List<Passenger> objects) {
            super(context, resource, objects);
            passenger = objects;
            this.resource = resource;
            inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            final ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.passenger, null);
                holder.name=(TextView) convertView.findViewById(R.id.txt_passenger_name);
                holder.seat=(TextView) convertView.findViewById(R.id.txt_seat_number);
                holder.phone=(TextView) convertView.findViewById(R.id.txt_contact);
                holder.destination=(TextView) convertView.findViewById(R.id.txt_destination1);
                holder.bus_no=(TextView) convertView.findViewById(R.id.txt_bus_number1);
                holder.date=(TextView) convertView.findViewById(R.id.txt_date1);
                holder.time=(TextView) convertView.findViewById(R.id.txt_time);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            Log.d("MainActivity", "Passenger_Name = " + passenger.get(position).getPassenger_Name());
            holder.name.setText(passenger.get(position).getPassenger_Name());
            holder.seat.setText(passenger.get(position).getSeat_No());
            holder.phone.setText(passenger.get(position).getPhone());
            holder.destination.setText(passenger.get(position).getDestination());
            holder.bus_no.setText(passenger.get(position).getBus_No());
            holder.date.setText(passenger.get(position).getDate());
            holder.time.setText(passenger.get(position).getTime());
            // Log.d("MainActivity", "Image = " + itemList.get(position).getImage());

            return convertView;
        }




        class ViewHolder {
            private TextView name,seat,phone,destination,bus_no,date,time;
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
