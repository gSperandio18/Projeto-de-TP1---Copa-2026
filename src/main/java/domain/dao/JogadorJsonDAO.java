package domain.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import domain.classes.selecoes.Jogador;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JogadorJsonDAO implements JogadorDAO {
    private static final String ARQUIVO = "jogadores.json";
    private final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).setPrettyPrinting().create();

    @Override
    public void salvar(List<Jogador> jogadores) {
        try(FileWriter writer = new FileWriter(ARQUIVO)) {
            gson.toJson(jogadores, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Jogador> carregar() {
        try {
            File arquivo = new File(ARQUIVO);
            if(!arquivo.exists()) {
                return new ArrayList<>();
            }
            FileReader reader = new FileReader(arquivo);
            Type tipo = new TypeToken<List<Jogador>>(){}.getType();
            List<Jogador> lista = gson.fromJson(reader, tipo);
            return lista == null ? new ArrayList<>() : lista;
        } catch(Exception e) {
            return new ArrayList<>();
        }
    }
}
