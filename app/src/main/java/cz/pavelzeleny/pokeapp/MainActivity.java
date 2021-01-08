package cz.pavelzeleny.pokeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btn_search;
    TextView btn_history;
    EditText pokemonSearchBar;
    RecyclerView pokemonResult;
    SharedPreferences sp;
    final String sharedFileName = "PokeFile";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().show();



        btn_search = findViewById(R.id.btn_search);
        btn_history = findViewById(R.id.btn_history);
        pokemonSearchBar = findViewById(R.id.pokemonSearchBar);
        pokemonResult = findViewById(R.id.pokemonResult);

        final PokemonDataService pokemonDataService = new PokemonDataService(MainActivity.this);

        sp = getSharedPreferences(sharedFileName, Context.MODE_PRIVATE);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pokemonDataService.getPokemon(pokemonSearchBar.getText().toString(), new PokemonDataService.VolleyResponseListener() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this,"Pokemon not found",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(List<PokemonModel> response) {

                        MainViewAdapter mainViewAdapter = new MainViewAdapter(MainActivity.this,response);
                        pokemonResult.setAdapter(mainViewAdapter);
                        pokemonResult.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                        Gson gson = new Gson();
                        String pokemonListString = sp.getString("pokemons", "No history found");

                        if(!pokemonListString.equals("No history found")){
                            //Gson gson = new Gson();
                            List<PokemonModel> pokemons = new ArrayList<>();
                            String pokeName = response.get(0).getPokemonName();
                            boolean already = false;
                            try {
                                JSONArray ja = new JSONArray(pokemonListString);
                                for(int i = 0;i<ja.length();i++){
                                    String weight = "";
                                    String pokemonName = "";
                                    String height = "";
                                    String type = "";
                                    String imageULR = "";
                                    int HP = 0;
                                    int DEF = 0;
                                    int ATK = 0;
                                    int id = 0;
                                    JSONObject pokemon = (JSONObject) ja.get(i);
                                    weight = pokemon.getString("weight");
                                    pokemonName = pokemon.getString("pokemonName");
                                    imageULR = pokemon.getString("imageULR");
                                    id = pokemon.getInt("id");
                                    HP = pokemon.getInt("HP");
                                    ATK = pokemon.getInt("ATK");
                                    DEF = pokemon.getInt("DEF");
                                    height = pokemon.getString("height");
                                    type = pokemon.getString("type");
                                    PokemonModel pM = new PokemonModel(pokemonName,weight,height,type,imageULR,HP,DEF,ATK,id);
                                    pokemons.add(pM);
                                    if(pokeName.equals(pokemonName)){
                                        already = true;
                                    }
                                }
                                if(!already){
                                pokemons.add(response.get(0));
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putString("pokemons",gson.toJson(pokemons));
                                editor.commit();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }else{
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("pokemons",gson.toJson(response));
                            editor.commit();
                        }
                    }
                });
            }
        });

        btn_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        });
    }
}