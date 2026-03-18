package dao;

import model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDaoImpl implements ProdutoDAO{

    List<Produto> listaProdutos = new ArrayList<>();

    @Override
    public void inserir(Produto produto) {
        listaProdutos.add(produto);
    }

    @Override
    public Produto buscarPorId(Long id) {

        for (Produto p : listaProdutos){
            if (p.getId().equals(id)){
                return p;
            }
        }
        return null;
    }

    @Override
    public List<Produto> listarTodos() {
        return listaProdutos;
    }

    @Override
    public void atualizar(Produto produto) {
        // Implementar
    }

    @Override
    public void deletar(Long id) {
        for (Produto p : listaProdutos){
            if (p.getId().equals(id)){
                Produto p1 = buscarPorId(id);
                listaProdutos.remove(p1);
            }
        }
    }
}
