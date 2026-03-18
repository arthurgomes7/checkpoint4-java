package dao;

import model.Produto;

import java.util.List;

public interface ProdutoDAO {
    void inserir(Produto produto);
    Produto buscarPorId(Long id);
    List<Produto> listarTodos();
    void atualizar(Produto produto);
    void deletar(Long id);
}
