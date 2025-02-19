package br.com.fiap.apiRest.Service;

import br.com.fiap.apiRest.DTO.LivroRequest;
import br.com.fiap.apiRest.DTO.LivroRequestDTO;
import br.com.fiap.apiRest.DTO.LivroResponse;
import br.com.fiap.apiRest.model.Livro;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LivroService
{
    public Livro requestToLivro(LivroRequest livroRequest)
    {
        Livro livro = new Livro();
        livro.setAutor(livroRequest.getAutor());
        livro.setTitulo(livroRequest.getTitulo());
        livro.setPreco(livroRequest.getPreco());
        livro.setCategoria(livroRequest.getCategoria());
        livro.setIsbn(livroRequest.getIsbn());
        return livro;
    }

    public Livro recordToLivro(LivroRequestDTO livroRecord)
    {
        Livro livro = new Livro();
        livro.setTitulo(livroRecord.titulo());
        livro.setAutor(livroRecord.autor());
        return livro;
    }

    public LivroResponse livroToResponse(Livro livro)
    {
        LivroResponse LivroResponse = new LivroResponse(livro.getTitulo()+" - "+livro.getAutor());
        return LivroResponse;

    }

    public List<LivroResponse> livrosToResponse(List<Livro> livros)
    {
        List<LivroResponse> listaLivros = new ArrayList<>();
        for(Livro livro: livros)
        {
            listaLivros.add(livroToResponse(livro));
        }
        return listaLivros;

    }


}
