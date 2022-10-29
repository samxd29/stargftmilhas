package stargftmilhas.model;

public enum Status  {
    ENTREGUE("Entregue"),
    NAOENTREGUE("Não Entregue");

    private final String nome;
    private Status(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
