package com.example.exerciciotestes.service;

import com.example.exerciciotestes.controller.request.ClienteRequest;
import com.example.exerciciotestes.model.Cliente;
import com.example.exerciciotestes.repository.ClienteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks

    private ClienteService clienteService;

    // [M3S01] Ex 1 - Crie os testes unitários para os métodos buscaTodosClientes e buscaClientePorId
    @Test
    void buscaTodosClientes() {
        //declaração dos dados que serão utilizados no teste
        List<Cliente> clientes = List.of(new Cliente(1L,"nome",10.0));

        // falsificar a resposta do findAll
        when(clienteRepository.findAll()).thenReturn(clientes);

        // chamada do método a ser testado
        List<Cliente> clientesResultado = clienteService.buscaTodosClientes();

        // validação da chamada do resultado do método
        assertEquals("nome",clientesResultado.get(0).getNomeCliente());

        // validação do uso do Mock , da falsificaçãi acima criada
        verify(clienteRepository).findAll();
    }

    @Test
    void buscaClientePorId() {

        Cliente cliente = new Cliente(1l,"nome",5.0);

        when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(cliente));

        Cliente clienteResultado = clienteService.buscaClientePorId(1L);

        Assertions.assertEquals("nome",clienteResultado.getNomeCliente());
    }

    // [M3S01] Ex 2 - Crie os testes unitários para os métodos salvarCliente

    @Test
    void salvarCliente() {

    Cliente cliente = new Cliente(1l,"Lenara",5.0);

    when(clienteRepository.save(any())).thenReturn(cliente);

    Cliente novoCliente = clienteService.salvarCliente(new ClienteRequest("Lenara",5.0));

    Assertions.assertNotNull(novoCliente);
    Assertions.assertEquals("Lenara",novoCliente.getNomeCliente());
    Assertions.assertEquals(5.0,novoCliente.getSaldoCliente());

    }

    // [M3S01] Ex 3 - Crie os testes unitários para os métodos atualizarCliente

    @Test
    void atualizarCliente() {

    Cliente cliente = new Cliente(1l,"Lenara",5.0);

    when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(cliente));

   clienteService.atualizarCliente(1L, new ClienteRequest("Lena",5.0));

    Assertions.assertEquals("Lena",cliente.getNomeCliente());

    }

    // [M3S01] Ex 4 - Crie os testes unitários para os métodos detelaClientePorId

    @Test
    void detelaClientePorId() {
    Cliente cliente = new Cliente(1l,"Lenara",5.0);

    when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(cliente));

    clienteService.detelaClientePorId(cliente.getId());

    Assertions.assertEquals(1L, cliente.getId());

    }











}