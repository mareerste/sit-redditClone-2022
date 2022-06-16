package com.example.redditcloneapp.ui.home;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.redditcloneapp.MainActivity;
import com.example.redditcloneapp.R;
import com.example.redditcloneapp.adapters.PostAdapter;
import com.example.redditcloneapp.model.Community;
import com.example.redditcloneapp.model.Mokap;
import com.example.redditcloneapp.model.Post;
import com.example.redditcloneapp.model.User;
import com.example.redditcloneapp.service.PostApiService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends ListFragment {
//    static final String ALL_POSTS_URL = "http://192.168.0.29:8080/RedditClone/post/all";
    static final String TAG = MainActivity.class.getSimpleName();
    private SwipeRefreshLayout refresh;
    private ArrayList<Post> posts = new ArrayList<>();
    private JsonArrayRequest arrayRequest;
    static Retrofit retrofit = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
//        ((MainActivity)getActivity()).drawerLocked();
        return inflater.inflate(R.layout.post_map_layout,container,false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Post post = Mokap.getPosts().get(position);
        Toast toast = Toast.makeText(getContext(),post.toString(), Toast.LENGTH_SHORT);
        toast.show();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        getData();
        getPosts();
//        PostAdapter adapter = new PostAdapter(getActivity(), ((MainActivity)getActivity()).getUser());
//        setListAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
//        getData();
    }

//    private void getData(){
//        arrayRequest = new JsonArrayRequest(ALL_POSTS_URL, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                System.out.println("RESPONSE: " + response);
//                JSONObject jsonObject = null;
//                for (int i = 0; i < response.length(); i++) {
//                    try {
//                        jsonObject = response.getJSONObject(i);
//
//                        Post post = new Post();
//                        post.setId(jsonObject.getInt("id"));
//                        post.setTitle(jsonObject.getString("title"));
//                        post.setText(jsonObject.getString("text"));
//                        post.setDeleted(jsonObject.getBoolean("deleted"));
//
//                        posts.add(post);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
////                adapter
//                PostAdapter adapter = new PostAdapter(getActivity(), ((MainActivity)getActivity()).getUser(),posts);
//                setListAdapter(adapter);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//    }

    private void getPosts(){
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(MainActivity.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        PostApiService postApiService = retrofit.create(PostApiService.class);
        Call<List<Post>> call = postApiService.getAllPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, retrofit2.Response<List<Post>> response) {
                PostAdapter adapter = new PostAdapter(getActivity(), ((MainActivity)getActivity()).getUser(),response.body());
                setListAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

}