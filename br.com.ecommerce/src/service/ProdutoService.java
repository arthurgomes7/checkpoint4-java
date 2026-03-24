package service;

import dao.ProdutoDAO;
import dao.ProdutoDaoImpl;
import exceptions.EstoqueException;
import model.Produto;

public class ProdutoService {
    private ProdutoDAO produtoDAO;

    public ProdutoService(ProdutoDaoImpl produtoDAO) {
        this.produtoDAO = produtoDAO;
    }

    public void adicionarEstoque(Long id, int quantidade) {
        Produto produto = produtoDAO.buscarPorId(id);

        if (produto == null)
            throw new EstoqueException("Produto não encontrado!");

        if (quantidade <= 0)
            throw new EstoqueException("Quantidade inválida");

        produtoDAO.adicionarEstoque(id, quantidade);
    }

    public void removerEstoque(Long id, Integer quantidade) {
        Produto produto = produtoDAO.buscarPorId(id);

        if (produto == null)
            throw new EstoqueException("Produto não encontrado!");

        if (quantidade > produto.getQuantidadeEstoque())
            throw new EstoqueException("Estoque insuficiente");

        produtoDAO.removerEstoque(id, quantidade);
    }
}