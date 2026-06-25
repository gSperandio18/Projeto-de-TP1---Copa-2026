package domain.dao.partidas;

import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

import domain.classes.partidas.Partida;
import domain.dao.LocalDateTimeAdapter;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PartidaJsonDAO implements PartidaDAO {
    private static final String ARQUIVO = "partidas.json";

    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .setPrettyPrinting()
            .create();


    @Override
    public void salvar(List<Partida> partidas) {
        try (FileWriter arq = new FileWriter(ARQUIVO)) {
            gson.toJson(partidas, arq);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Partida> carregar() {
        try {
            FileReader arq = new FileReader(ARQUIVO);
            Partida[] vetor = gson.fromJson(arq, Partida[].class);

            if (vetor == null) {
                return new ArrayList<>();
            } else {
                return new ArrayList<>(Arrays.asList(vetor));
            }
        } catch (FileNotFoundException e) {
            System.out.println("O arquivo não existe.");
            return new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
