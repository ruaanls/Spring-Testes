package br.com.fiap.apiRest.DTO;

import org.springframework.hateoas.Link;

public class LivroResponse
{
    private String infoLivro;
    private Link link;
    private long id;


    public LivroResponse(long id, String infoLivro) {
        this.infoLivro = infoLivro;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInfoLivro() {
        return infoLivro;
    }

    public void setInfoLivro(String infoLivro) {
        this.infoLivro = infoLivro;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }
}
