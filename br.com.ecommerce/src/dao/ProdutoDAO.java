package dao;

import model.Produto;

import java.util.List;

public interface ProdutoDAO {
    void adicionarEstoque(Long produtoId, int quantidade);
    void removerEstoque(Long produtoId, int quantidade);
    void adicionarProduto(Produto produto);
    Produto buscarPorId(Long id);
}
