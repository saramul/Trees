package suriyon.cs.ubru.trees;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import suriyon.cs.ubru.trees.adapter.ImageAdapter;
import suriyon.cs.ubru.trees.model.Image;

public class ShowImageActivity extends AppCompatActivity {
    private RecyclerView rcvTreeImages;
    private ImageAdapter adapter;
    private ArrayList<Image> images;
    private RequestQueue queue;
    private TextView tvTreeName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);

        matchView();
        images = new ArrayList<>();

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        tvTreeName.setText("ชื่อต้นไม้: " + name);
        parseJSON(id);
    }

    private void parseJSON(String id) {
        String url = "http://suriyon.cs.ubru.ac.th/ubrutree/api/get_images.php";
        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("images");
                            for(int i=0; i<jsonArray.length(); i++){
                                JSONObject image = jsonArray.getJSONObject(i);

                                String treeName = image.getString("name");
                                String imgName = image.getString("image_name");

                                images.add(new Image(treeName, imgName));
                            }
                            adapter = new ImageAdapter(ShowImageActivity.this, images);
                            rcvTreeImages.setAdapter(adapter);
                            rcvTreeImages.setLayoutManager(new LinearLayoutManager(ShowImageActivity.this));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<>();
                param.put("id", id);
                return param;
            }
        };
        queue = Volley.newRequestQueue(ShowImageActivity.this);
        queue.add(request);
    }

    private void matchView() {
        rcvTreeImages = findViewById(R.id.rcv_image_tree);
        tvTreeName = findViewById(R.id.tv_tree_name_title);
    }
}