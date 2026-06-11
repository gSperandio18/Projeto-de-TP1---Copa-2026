package dao;

import classes.estadios.Arbitro;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ArbitroJsonDAO implements ArbitroDAO {

    private static final String ARQUIVO =
            "arbitros.json";

    private final Gson gson =
            new GsonBuilder()

                    .registerTypeAdapter(
                            LocalDateTime.class,
                            new LocalDateTimeAdapter()
                    )

                    .setPrettyPrinting()

                    .create();
    @Override
    public void salvar(List<Arbitro> arbitros) {

        try(FileWriter writer =
                    new FileWriter(ARQUIVO)) {

            gson.toJson(arbitros, writer);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public List<Arbitro> carregar() {

        try {

            File arquivo = new File(ARQUIVO);

            if(!arquivo.exists()) {
                return new ArrayList<>();
            }

            FileReader reader =
                    new FileReader(arquivo);

            Type tipo =
                    new TypeToken<List<Arbitro>>(){}.getType();

            List<Arbitro> lista =
                    gson.fromJson(reader, tipo);

            return lista == null ?
                    new ArrayList<>() :
                    lista;

        } catch(Exception e) {

            return new ArrayList<>();
        }
    }
}