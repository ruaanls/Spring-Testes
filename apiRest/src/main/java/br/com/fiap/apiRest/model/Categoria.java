package br.com.fiap.apiRest.model;

public enum Categoria {
    ROMANCE("Romance"),
    FICCAO("Ficção"),
    FANTASIA("Fantasia");

    private String descrição;

    Categoria(String descrição) {
        this.descrição = descrição;
    }

    public String getDescrição() {
        return descrição;
    }

    public void setDescrição(String descrição) {
        this.descrição = descrição;
    }
}
