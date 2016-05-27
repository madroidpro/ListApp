package madroid.listapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        Button camera= (Button)findViewById(R.id.open_camera);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opencam();
            }
        });
        loadJSONFromAsset();

        Button next_but=(Button)findViewById(R.id.next_button);
        next_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,Fragmentexample.class);
                startActivity(intent);
            }
        });


    }

    protected void opencam() {
        File folder = new File(Environment.getExternalStorageDirectory() + "/ListApp");
        if (!folder.exists()) {
            folder.mkdir();
        }

        File file=new File(Environment.getExternalStorageDirectory(),"/ListApp/ListApp.jpg");

        Intent in = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        in.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        startActivityForResult(in,1);
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("serverdatas.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        try {
            JSONObject obj = new JSONObject(json);
            JSONArray m_jArry = obj.getJSONArray("username");


            ListView lv = (ListView) findViewById(R.id.main_listview);

            List<Listitems> items = new ArrayList<Listitems>();

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                final String name_jo=jo_inside.getString("name");
                final String title_jo=jo_inside.getString("title");
                items.add(new Listitems(){{
                    imagename = R.mipmap.ic_launcher;
                    title = name_jo;
                    description=title_jo;
                }
                });
            }
            CustomListAdopter adopter = new CustomListAdopter(getApplicationContext(),this,items);
            lv.setAdapter(adopter);
            Log.d("added","added");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    public class Listitems{
        public int imagename;
        public String title;
        public String description;
    }
}
