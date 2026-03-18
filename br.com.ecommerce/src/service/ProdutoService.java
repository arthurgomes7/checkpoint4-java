package service;

import dao.ProdutoDAO;
import exceptions.EstoqueException;
import model.Produto;

public class ProdutoService {
    private ProdutoDAO produtoDAO;

    public ProdutoService(ProdutoDAO produtoDAO) {
        this.produtoDAO = produtoDAO;
    }

    public void adicionarEstoque(Long id, int quantidade) throws EstoqueException {
        Produto p = produtoDAO.buscarPorId(id);

        if (quantidade <= 0) {
            throw new EstoqueException("Quantidade inválida");
        }

        p.setQuantidadeEstoque(p.getQuantidadeEstoque() + quantidade);
        produtoDAO.atualizar(p);
    }

    public void removerEstoque(Long id, int quantidade) throws EstoqueException {
        Produto p = produtoDAO.buscarPorId(id);

        if (quantidade > p.getQuantidadeEstoque()) {
            throw new EstoqueException("Estoque insuficiente");
        }

        p.setQuantidadeEstoque(p.getQuantidadeEstoque() - quantidade);
        produtoDAO.atualizar(p);
    }
}
