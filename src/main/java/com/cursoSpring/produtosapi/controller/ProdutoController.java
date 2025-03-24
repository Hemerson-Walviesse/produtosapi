package com.cursoSpring.produtosapi.controller;

import com.cursoSpring.produtosapi.model.Produto;
import com.cursoSpring.produtosapi.repository.ProdutoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    private ProdutoRepository repo;

    public ProdutoController(ProdutoRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public Produto salvar(@RequestBody  Produto produto) {
        var id = UUID.randomUUID().toString();
        produto.setId(id);
        System.out.println("Produto recebido" + produto);
        repo.save(produto);
        return produto;
    }

    @GetMapping("/{id}")
    public  Produto obterPorId(@PathVariable("id") String id){
        /*Optional<Produto> produto = repo.findById(id);
        return produto.isPresent() ? produto.get() : null;*/

        return repo.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable("id") String id) {
        repo.deleteById(id);
    }

    @PutMapping("/{id}")
    public Produto atualizar(@PathVariable("id") String id, @RequestBody Produto produto) {
        produto.setId(id);
        repo.save(produto);
        return produto;
    }

    @GetMapping("/todosProdutos")
    public List<Produto> listar() {
        return repo.findAll();
    }

    @GetMapping
    public List<Produto> buscarPorNome(@RequestParam("nome") String nome) {

       return repo.findByNome(nome);

    }
}
