package controller.administracao;

import controller.exceptions.Copa2026Exceptions;

public class UsuarioController {

    public void validarNome(String nomeCompleto) throws Copa2026Exceptions{
        if(nomeCompleto == null){
            throw new Copa2026Exceptions("Nome não pode estar vazio"); //incluir mensagem de erro
        }else{
            for(char c: nomeCompleto.toCharArray()){
                if((!Character.isLetterOrDigit(c)) && (!Character.isSpaceChar(c))){
                    throw new Copa2026Exceptions("Nome não deve conter caracteres especiais"); //incluir mensagem de erro
                }
            }
        }
    }

    public void validarSenha(String senha)throws Copa2026Exceptions{
        final int MAIUSCULA = 1;
        final int ESPECIAL = 1;
        final int NUMERAL = 1;
        int contadoraM = 0;
        int contadoraE = 0;
        int contadoraN = 0;
        int contadoraMin= 0;

        if(senha == null){
            throw new Copa2026Exceptions("Senha não pode ser vazia"); //incluir mensagem de erro;
        }else if(senha.length() < 8){
            throw new Copa2026Exceptions("Senha deve conter no mínimo 8 caracteres");
        }else{
            for(char c: senha.toCharArray()){
                if(c >= 33 && c <= 47 || c >= 58 && c <= 64 || c >= 91 && c <= 96 || c >= 123 && c <= 126){//caractere especial
                    //Note que dessa forma não conta espaços, quebras de linhas nem acento.
                    contadoraE++;
                }else if(Character.isDigit(c)){
                    contadoraN++;
                }else if(Character.isUpperCase(c)){
                    contadoraM++;
                }else if(Character.isLowerCase(c)){
                    contadoraMin++;
                }
            }
            if((contadoraN < NUMERAL) || (contadoraE < ESPECIAL) || (contadoraM < MAIUSCULA)){
                throw new Copa2026Exceptions("senha deve conter no mínimo: 1 número, 1 caracter especial, " +
                        "1 letra maiúscula");
            }
        }
    }

}
