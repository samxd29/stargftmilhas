package stargftmilhas.model;

public enum PresencaEnum {
    PRESENCA("Presença"),
    AUSENCIA("Ausente");

    private final String nome;
    private PresencaEnum(String nome) {
        this.nome = nome;
    }

    public String getName() {
        return nome;
    }
}
