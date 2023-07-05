package com.example.exerciciotestes.service;

import com.example.exerciciotestes.controller.request.VendaRequest;
import com.example.exerciciotestes.model.Cliente;
import com.example.exerciciotestes.model.Produto;
import com.example.exerciciotestes.model.Venda;
import com.example.exerciciotestes.repository.VendaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VendaService {

    private final VendaRepository VendaRepository;
    private final ClienteService clienteService;
    private final ProdutoService produtoService;

    public VendaService(VendaRepository VendaRepository, ClienteService clienteService, ProdutoService produtoService) {
        this.VendaRepository = VendaRepository;
        this.clienteService = clienteService;
        this.produtoService = produtoService;
    }


    public List<Venda> buscaTodosVendas(){
        return this.VendaRepository.findAll();
    }

    //-----[M3S01] Ex 9 - Realize o desenvolvimento do endpoint POST /vendas----//
    public Venda realizarVenda(VendaRequest vendaRequest){
        // TODO: código a ser feito no método TDD
        Cliente cliente = clienteService.buscaClientePorId(vendaRequest.getClienteId());
        List<Produto> produtos = new ArrayList<>();
        vendaRequest.getProdutos().forEach(
                id -> produtos.add(produtoService.buscaProdutoPorId(id))
        );

        Venda venda = new Venda(vendaRequest.getValorVenda(), cliente, produtos);

        VendaRepository.save(venda);

        return venda;
    }

    //-----[M3S01] Ex 9 - Realize o desenvolvimento do endpoint POST /vendas----//






    public Venda buscaVendaPorId (Long id){
        return this.VendaRepository.findById(id).orElse(null);
    }

}
