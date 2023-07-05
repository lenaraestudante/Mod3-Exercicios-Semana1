package com.example.exerciciotestes.service;

import com.example.exerciciotestes.controller.request.VendaRequest;
import com.example.exerciciotestes.model.Cliente;
import com.example.exerciciotestes.model.Produto;
import com.example.exerciciotestes.model.Venda;
import com.example.exerciciotestes.repository.VendaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VendaServiceTest {

    @Mock
    private VendaRepository repository;

    @InjectMocks
    private VendaService service;

    @Mock
    private ClienteService clienteService;

    @Mock
    private ProdutoService produtoService;

    //-----[M3S01] Ex 9 - Realize o desenvolvimento do endpoint POST /vendas----//
    @Test
    void realizarVenda() {

    Cliente cliente = new Cliente(1L, "Nome", 10.0);
    Produto produto = new Produto(1L, "produto", 5.0);

    when(clienteService.buscaClientePorId(anyLong())).thenReturn(cliente);
    when(produtoService.buscaProdutoPorId(anyLong())).thenReturn(produto);

    Venda venda = service.realizarVenda(new VendaRequest(1L, List.of(1L),5.0 ));

    Assertions.assertEquals("Nome",venda.getCliente().getNomeCliente());
    Assertions.assertEquals(5.0,venda.getValorVenda());
    }
    //-----[M3S01] Ex 9 - Realize o desenvolvimento do endpoint POST /vendas----//

}