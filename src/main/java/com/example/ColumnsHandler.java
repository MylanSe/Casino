package com.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ColumnsHandler {
    public List<List<String>> deserialisation()throws FileNotFoundException, IOException { 
        Gson gson = new GsonBuilder().create();
        Reader reader = new FileReader("C:\\Users\\Utilisateur\\Documents\\Cours\\Licence\\java\\Chevalier\\casino\\src\\main\\resources\\liste.json");
        List<List<String>> listeSymbole = gson.fromJson(reader, ArrayList.class);
        return listeSymbole;
    }
}
