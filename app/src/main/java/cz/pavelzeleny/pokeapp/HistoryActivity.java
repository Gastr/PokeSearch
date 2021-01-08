package cz.pavelzeleny.pokeapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        final String sharedFileName = "PokeFile";
        RecyclerView pokemonHistory = findViewById(R.id.pokemonHistory);
        TextView noFound = findViewById(R.id.textNoFound);
        TextView MainText = findViewById(R.id.mainText);

        SharedPreferences sp = getApplicationContext().getSharedPreferences(sharedFileName, Context.MODE_PRIVATE);
        String pokemonListString = sp.getString("pokemons", "No history found");

        if(!pokemonListString.equals("No history found")){
            //Gson gson = new Gson();
            List<PokemonModel> pokemons = new ArrayList<>();
            noFound.setText("");
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
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            MainViewAdapter mainViewAdapter = new MainViewAdapter(HistoryActivity.this,pokemons);
            pokemonHistory.setAdapter(mainViewAdapter);
            pokemonHistory.setLayoutManager(new LinearLayoutManager(HistoryActivity.this));
            //ArrayAdapter arrayAdapter = new ArrayAdapter(HistoryActivity.this, android.R.layout.simple_list_item_1,pokemons);
            //pokemonHistory.setAdapter(arrayAdapter);
        }else{
            //List<String> pokemons = new ArrayList<>();
            //ArrayAdapter arrayAdapter = new ArrayAdapter(HistoryActivity.this, android.R.layout.simple_list_item_1,pokemons);
            //pokemonHistory.setAdapter(arrayAdapter);
        }

        MainText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
