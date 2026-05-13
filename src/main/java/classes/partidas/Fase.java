package classes.partidas;

public enum Fase {
    GRUPOS("Fase de grupos"),
    OITAVAS("Oitavas de final"),
    QUARTAS("Quartas de final"),
    SEMIFINAIS("Semifinais"),
    FINAL("Final");

    final private String descricao;

    Fase(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
