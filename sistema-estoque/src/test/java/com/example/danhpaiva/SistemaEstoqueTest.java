package com.example.danhpaiva;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class SistemaEstoqueTest {

    private SistemaEstoque sistemaEstoque;

    @BeforeEach
    void setUp() {
        sistemaEstoque = new SistemaEstoque();
    }

    @Test
    void testAdicionarProdutoComSucesso() {
        sistemaEstoque.adicionarProduto("Caneta", 10);
        assertEquals(10, sistemaEstoque.consultarEstoque("Caneta"));
    }

    @Test
    void testAdicionarProdutoNomeInvalido() {
        assertThrows(IllegalArgumentException.class, () -> sistemaEstoque.adicionarProduto("", 10));
        assertThrows(IllegalArgumentException.class, () -> sistemaEstoque.adicionarProduto(null, 10));
    }

    @Test
    void testAdicionarProdutoQuantidadeInvalida() {
        assertThrows(IllegalArgumentException.class, () -> sistemaEstoque.adicionarProduto("Caneta", 0));
        assertThrows(IllegalArgumentException.class, () -> sistemaEstoque.adicionarProduto("Caneta", -5));
    }

    @Test
    void testRemoverProdutoComSucesso() {
        sistemaEstoque.adicionarProduto("Lápis", 20);
        sistemaEstoque.removerProduto("Lápis", 5);
        assertEquals(15, sistemaEstoque.consultarEstoque("Lápis"));
    }

    @Test
    void testRemoverProdutoQuantidadeExcedente() {
        sistemaEstoque.adicionarProduto("Borracha", 5);
        assertThrows(IllegalArgumentException.class, () -> sistemaEstoque.removerProduto("Borracha", 10));
    }

    @Test
    void testRemoverProdutoInexistente() {
        assertThrows(IllegalArgumentException.class, () -> sistemaEstoque.removerProduto("Caderno", 1));
    }

    @Test
    void testRemoverProdutoNomeInvalido() {
        assertThrows(IllegalArgumentException.class, () -> sistemaEstoque.removerProduto("", 1));
        assertThrows(IllegalArgumentException.class, () -> sistemaEstoque.removerProduto(null, 1));
    }

    @Test
    void testConsultarEstoqueProdutoInexistente() {
        assertEquals(0, sistemaEstoque.consultarEstoque("Livro"));
    }

    @Test
    void testObterHistoricoTransacoes() {
        sistemaEstoque.adicionarProduto("Apontador", 3);
        sistemaEstoque.removerProduto("Apontador", 1);
        List<String> historico = sistemaEstoque.obterHistoricoTransacoes();
        assertEquals(2, historico.size());
        assertTrue(historico.get(0).contains("Adicionado"));
        assertTrue(historico.get(1).contains("Removido"));
    }

    @Test
    void testVerificarDisponibilidadeComSucesso() {
        sistemaEstoque.adicionarProduto("Marca-texto", 10);
        assertTrue(sistemaEstoque.verificarDisponibilidade("Marca-texto", 5));
    }

    @Test
    void testVerificarDisponibilidadeComFalha() {
        sistemaEstoque.adicionarProduto("Grampeador", 2);
        assertFalse(sistemaEstoque.verificarDisponibilidade("Grampeador", 5));
    }

    @Test
    void testVerificarDisponibilidadeProdutoInvalido() {
        assertThrows(IllegalArgumentException.class, () -> sistemaEstoque.verificarDisponibilidade("", 1));
        assertThrows(IllegalArgumentException.class, () -> sistemaEstoque.verificarDisponibilidade(null, 1));
        assertThrows(IllegalArgumentException.class, () -> sistemaEstoque.verificarDisponibilidade("Grampeador", 0));
    }
}
