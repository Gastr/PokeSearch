package cz.pavelzeleny.pokeapp;

import org.json.JSONObject;

public class PokemonModel {

    private String pokemonName;
    private String weight;
    private String height;
    private String type;
    private String imageULR;
    private int HP;
    private int DEF;
    private int ATK;
    private int id;

    public PokemonModel(String pokemonName, String weight, String height, String type, String imageULR, int HP, int DEF, int ATK, int id) {
        this.pokemonName = pokemonName;
        this.weight = weight;
        this.height = height;
        this.type = type;
        this.imageULR = imageULR;
        this.HP = HP;
        this.DEF = DEF;
        this.ATK = ATK;
        this.id = id;
    }

    public PokemonModel() {
    }

    @Override
    public String toString() {
        return "PokemonModel{" +
                "pokemonName='" + pokemonName + '\'' +
                ", weight='" + weight + '\'' +
                ", height='" + height + '\'' +
                ", type='" + type + '\'' +
                ", imageULR='" + imageULR + '\'' +
                ", HP=" + HP +
                ", DEF=" + DEF +
                ", ATK=" + ATK +
                ", id=" + id +
                '}';
    }

   /* public JSONObject toJson(){
        return new GsonBuilder
    }*/

    public String getPokemonName() {
        return pokemonName;
    }

    public void setPokemonName(String pokemonName) {
        this.pokemonName = pokemonName;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageULR() {
        return imageULR;
    }

    public void setImageULR(String imageULR) {
        this.imageULR = imageULR;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getDEF() {
        return DEF;
    }

    public void setDEF(int DEF) {
        this.DEF = DEF;
    }

    public int getATK() {
        return ATK;
    }

    public void setATK(int ATK) {
        this.ATK = ATK;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
