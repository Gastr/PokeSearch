package cz.pavelzeleny.pokeapp;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PokemonDataService {

    public static final String QUERY_POKEMON = "https://pokeapi.co/api/v2/pokemon/";

    Context context;

    public PokemonDataService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener{
        void onError(String message);

        void onResponse(List<PokemonModel> response);
    }

    /*public void testik(String pokemonName, VolleyResponseListener volleyResponseListener){
        // Instantiate the RequestQueue.

        String url =QUERY_POKEMON + pokemonName;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String weight = "";
                        try {
                            weight = response.getString("weight");
                        }catch(JSONException e){
                            e.printStackTrace();
                        }
                        //Toast.makeText(context,weight,Toast.LENGTH_LONG).show();
                        volleyResponseListener.onResponse(weight);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(context,"Pokemon not found",Toast.LENGTH_SHORT).show();
                volleyResponseListener.onError("Pokemon not found");
            }
        });
        RequestSingleton.getInstance(context).addToRequestQueue(request);
    }*/
    public void getPokemon(String pokemonName, VolleyResponseListener volleyResponseListener){
        List<PokemonModel> result = new ArrayList<>();

        String url =QUERY_POKEMON + pokemonName;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String weight = "";
                        String pokemonName = "";
                        String height = "";
                        String type = "";
                        String imageULR = "";
                        int HP = 0;
                        int DEF = 0;
                        int ATK = 0;
                        int id = 0;
                        PokemonModel pokemonModel = null;
                        try {
                            weight = response.getString("weight");
                            pokemonName = response.getString("name");
                            id = response.getInt("id");
                            height = response.getString("height");
                            JSONObject images = response.getJSONObject("sprites");
                            imageULR = images.getString("front_default");
                            JSONObject types = (JSONObject) response.getJSONArray("types").get(0);
                            type = types.getJSONObject("type").getString("name");
                            JSONArray stats = response.getJSONArray("stats");
                            JSONObject ATKStat = (JSONObject) stats.get(1);
                            ATK = ATKStat.getInt("base_stat");
                            JSONObject HPStat = (JSONObject) stats.get(0);
                            HP = HPStat.getInt("base_stat");
                            JSONObject DEFStat = (JSONObject) stats.get(2);
                            DEF = DEFStat.getInt("base_stat");
                            PokemonModel pM = new PokemonModel(pokemonName,weight,height,type,imageULR,HP,DEF,ATK,id);
                            pokemonModel = pM;


                        }catch(JSONException e){
                            e.printStackTrace();
                        }
                        List<PokemonModel> pokeList = new ArrayList<>();
                        pokeList.add(pokemonModel);
                        //pokeList.add(pokemonModel);
                        //Toast.makeText(context,weight,Toast.LENGTH_LONG).show();
                        volleyResponseListener.onResponse(pokeList);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(context,"Pokemon not found",Toast.LENGTH_SHORT).show();
                volleyResponseListener.onError("Pokemon not found");
            }
        });
        RequestSingleton.getInstance(context).addToRequestQueue(request);

    }

}
