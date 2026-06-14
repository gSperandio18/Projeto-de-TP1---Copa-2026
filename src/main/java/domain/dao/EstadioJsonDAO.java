package domain.dao;

import domain.classes.estadios.Estadio;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EstadioJsonDAO implements EstadioDAO {

    private static final String ARQUIVO =
            "estadios.json";

    private final Gson gson =
            new GsonBuilder()

                    .registerTypeAdapter(
                            LocalDateTime.class,
                            new LocalDateTimeAdapter()
                    )

                    .setPrettyPrinting()

                    .create();

    @Override
    public void salvar(List<Estadio> estadios) {

        try(FileWriter writer =
                    new FileWriter(ARQUIVO)) {

            gson.toJson(estadios, writer);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public List<Estadio> carregar() {

        try {

            File arquivo = new File(ARQUIVO);

            if(!arquivo.exists()) {
                return new ArrayList<>();
            }

            FileReader reader =
                    new FileReader(arquivo);

            Type tipo =
                    new TypeToken<List<Estadio>>(){}.getType();

            List<Estadio> lista =
                    gson.fromJson(reader, tipo);

            return lista == null ?
                    new ArrayList<>() :
                    lista;

        } catch(Exception e) {

            return new ArrayList<>();
        }
    }
}