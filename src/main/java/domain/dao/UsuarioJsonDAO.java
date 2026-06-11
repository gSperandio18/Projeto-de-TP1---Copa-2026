package domain.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import domain.classes.administracao.Usuario;

import domain.dao.LocalDateTimeAdapter;
import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UsuarioJsonDAO implements UsuarioDAO {
    private static final String ARQUIVO = "usuarios.json";
    private final Gson gson;

    public UsuarioJsonDAO() {
        this.gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .setPrettyPrinting()
                .create();
    }
    @Override
    public void salvar(List<Usuario> usuarios){
        try(FileWriter writer = new FileWriter(ARQUIVO)){
            gson.toJson(usuarios, writer);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public List<Usuario> carregar(){
        try{
            File arquivo = new File(ARQUIVO);
            if(!arquivo.exists()){
                return new ArrayList<>();
            }
            FileReader reader = new FileReader(arquivo);
            Type tipo = new TypeToken<List<Usuario>>(){}.getType();
            List<Usuario> lista = gson.fromJson(reader, tipo);
            return lista == null ? new ArrayList<>() : lista;
        }catch(Exception e){
            return new ArrayList<>();
        }
    }
}
