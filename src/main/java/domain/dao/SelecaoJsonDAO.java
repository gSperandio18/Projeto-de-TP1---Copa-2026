package domain.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import domain.classes.estadios.Arbitro;
import domain.classes.selecoes.Selecao;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SelecaoJsonDAO implements SelecaoDAO {
    private static final String ARQUIVO = "selecoes.json";
    private final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).setPrettyPrinting().create();

    @Override
    public void salvar(List<Selecao> selecoes) {
        try(FileWriter writer = new FileWriter(ARQUIVO)) {
            gson.toJson(selecoes, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Selecao> carregar() {
        try {
            File arquivo = new File(ARQUIVO);
            if(!arquivo.exists()) {
                return new ArrayList<>();
            }
            FileReader reader = new FileReader(arquivo);
            Type tipo = new TypeToken<List<Selecao>>(){}.getType();
            List<Selecao> lista = gson.fromJson(reader, tipo);
            return lista == null ? new ArrayList<>() : lista;
        } catch(Exception e) {
            return new ArrayList<>();
        }
    }
}
