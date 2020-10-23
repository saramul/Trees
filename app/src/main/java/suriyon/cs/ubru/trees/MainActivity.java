package suriyon.cs.ubru.trees;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import suriyon.cs.ubru.trees.adapter.TreeAdapter;
import suriyon.cs.ubru.trees.model.Tree;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rcvTree;
    private TreeAdapter adapter;
    private ArrayList<Tree> trees;
    private RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        matchView();
        trees = new ArrayList<>();
        rcvTree.setHasFixedSize(true);
        rcvTree.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        parseJSON();
    }

    private void parseJSON() {
        String url = "http://suriyon.cs.ubru.ac.th/ubrutree/api/get_trees.php";
        JsonArrayRequest request = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONObject jsonObject = null;

                        for(int i=0; i<response.length(); i++){
                            try {
                                jsonObject = (JSONObject) response.get(i);
                                int id = jsonObject.getInt("id");
                                String name = jsonObject.getString("name");
                                String description = jsonObject.getString("description");
                                trees.add(new Tree(id, name, description));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            adapter = new TreeAdapter(MainActivity.this, trees);
                            rcvTree.setAdapter(adapter);
                            rcvTree.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });

        queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(request);
    }

    private void matchView() {
        rcvTree = findViewById(R.id.rcv_tree);
    }
}