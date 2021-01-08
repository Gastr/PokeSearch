package cz.pavelzeleny.pokeapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MainViewAdapter extends RecyclerView.Adapter<MainViewAdapter.MainViewHolder> {

    Context context;
    List<PokemonModel> pokemons;

    public MainViewAdapter(Context context, List<PokemonModel> pokemons) {
        this.context = context;
        this.pokemons = pokemons;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.main_row,parent,false);
        return new MainViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        PokemonModel pokemon = pokemons.get(position);
        holder.PokemonName.setText(pokemon.getPokemonName());
        holder.WeightText.setText(pokemon.getWeight());
        holder.HeightText.setText(pokemon.getHeight());
        holder.TypeText.setText(pokemon.getType());
        holder.HPText.setText(Integer.toString(pokemon.getHP()));
        holder.ATKText.setText(Integer.toString(pokemon.getATK()));
        holder.DEFText.setText(Integer.toString(pokemon.getDEF()));
        Picasso.get().load(pokemon.getImageULR()).into(holder.imagePokemon);
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder{

        TextView PokemonName,WeightText,HeightText,TypeText,HPText,ATKText,DEFText;
        ImageView imagePokemon;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            imagePokemon = itemView.findViewById(R.id.imagePokemon);
            PokemonName = itemView.findViewById(R.id.PokemonName);
            WeightText = itemView.findViewById(R.id.WeightText);
            HeightText = itemView.findViewById(R.id.HeightText);
            TypeText = itemView.findViewById(R.id.TypeText);
            HPText = itemView.findViewById(R.id.HPText);
            ATKText = itemView.findViewById(R.id.ATKText);
            DEFText = itemView.findViewById(R.id.DEFText);
        }
    }
}
