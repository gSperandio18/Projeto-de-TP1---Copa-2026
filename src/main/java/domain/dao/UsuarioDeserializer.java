// domain/dao/UsuarioDeserializer.java
package domain.dao;

import com.google.gson.*;
import domain.classes.administracao.Administrador;
import domain.classes.administracao.Organizador;
import domain.classes.administracao.Usuario;
import domain.classes.estadios.Arbitro;

import java.lang.reflect.Type;

public class UsuarioDeserializer implements JsonDeserializer<Usuario> {

    @Override
    public Usuario deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        JsonObject jsonObject = json.getAsJsonObject();

        String nome = jsonObject.get("nomeCompleto").getAsString();
        String email = jsonObject.get("email").getAsString();
        String senha = jsonObject.get("senha").getAsString();
        String tipoStr = jsonObject.get("personagem").getAsString();

        Usuario.Tipo tipo = Usuario.Tipo.valueOf(tipoStr);

        switch (tipo) {
            case ADMINISTRADOR:
                return new Administrador(nome, email, senha, tipo);
            case ORGANIZADOR:
                return new Organizador(nome, email, senha, tipo);
            case ARBITRO:
                return new Arbitro(nome, email, senha, tipo);
            default:
                throw new JsonParseException("Unknown user type: " + tipoStr);
        }
    }
}