package domain.dao;

import domain.classes.estadios.DesignacaoArbitroPartida;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DesignacaoJsonDAO implements DesignacaoDAO {

    private static final String ARQUIVO =
            "designacao.json";

    private final Gson gson =
            new GsonBuilder()

                    .registerTypeAdapter(
                            LocalDateTime.class,
                            new dao.LocalDateTimeAdapter()
                    )

                    .setPrettyPrinting()

                    .create();

    @Override
    public void salvar(List<DesignacaoArbitroPartida> DesignacaoArbitroPartidas) {

        try(FileWriter writer =
                    new FileWriter(ARQUIVO)) {

            gson.toJson(DesignacaoArbitroPartidas, writer);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public List<DesignacaoArbitroPartida> carregar() {

        try {

            File arquivo = new File(ARQUIVO);

            if(!arquivo.exists()) {
                return new ArrayList<>();
            }

            FileReader reader =
                    new FileReader(arquivo);

            Type tipo =
                    new TypeToken<List<DesignacaoArbitroPartida>>(){}.getType();

            List<DesignacaoArbitroPartida> lista =
                    gson.fromJson(reader, tipo);

            return lista == null ?
                    new ArrayList<>() :
                    lista;

        } catch(Exception e) {

            return new ArrayList<>();
        }
    }
}