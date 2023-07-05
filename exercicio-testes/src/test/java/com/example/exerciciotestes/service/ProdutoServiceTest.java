package com.example.exerciciotestes.service;

import com.example.exerciciotestes.controller.request.ProdutoRequest;
import com.example.exerciciotestes.model.Produto;
import com.example.exerciciotestes.repository.ProdutoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoService produtoService;

    //[M3S01] Ex 5 - Crie os testes unitários para os métodos buscaTodosProdutos e buscaProdutoPorId
    @Test
    void buscaTodosProdutos() {

        List<Produto> produtos = List.of(new Produto(1L,"produto", 2.0));

        when(produtoRepository.findAll()).thenReturn(produtos);

        List<Produto> produtoList = produtoService.buscaTodosProdutos();

        assertEquals("produto",produtoList.get(0).getNomeProduto());
    }

    @Test
    void buscaProdutoPorId() {

        Produto produto = new Produto(1L,"produto", 2.0);

        when(produtoRepository.findById(anyLong())).thenReturn(Optional.of(produto));

        Produto produtoResultado = produtoService.buscaProdutoPorId(1L);

        Assertions.assertEquals(2.0, produtoResultado.getValorProduto());
    }

    // [M3S01] Ex 6 - Crie os testes unitários para os métodos salvarProduto
    @Test
    void salvarProduto() {
        Produto produto = new Produto(1L,"produto", 2.0);

        when(produtoRepository.save(any())).thenReturn(produto);

        Produto novoProduto = produtoService.salvarProduto(new ProdutoRequest("produto", 5.0));

        Assertions.assertNotNull(novoProduto);
        Assertions.assertEquals("produto", novoProduto.getNomeProduto());
        Assertions.assertEquals(2.0, novoProduto.getValorProduto());
    }

    // Produto produto = new Produto(1L,"produto", 2.0);

    @Test
    void atualizarProduto() {

        Produto produto = new Produto(1L,"produto", 2.0);

        when(produtoRepository.findById(anyLong())).thenReturn(Optional.of(produto));

        produtoService.atualizarProduto(1L,new ProdutoRequest("sabonete", 3.0));

        Assertions.assertEquals("sabonete",produto.getNomeProduto());
        Assertions.assertEquals(3.0,produto.getValorProduto());
    }


    // [M3S01] Ex 8 - Crie os testes unitários para os métodos detelaProdutoPorId

    @Test
    void detelaProdutoPorId() {

    Produto produto = new Produto(1L,"produto", 2.0);

    //when(produtoRepository.findById(anyLong())).thenReturn(Optional.of(produto));

    produtoService.detelaProdutoPorId(produto.getId());

    Assertions.assertEquals(1L,produto.getId());
    }
}

