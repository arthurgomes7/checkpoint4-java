package dao;

import exceptions.EstoqueException;
import model.Produto;
import util.ConexaoDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDaoImpl implements ProdutoDAO{

    List<Produto> listaProdutos = new ArrayList<>();
    Connection conexaoDB = ConexaoDB.getConnection();

    @Override
    public void adicionarEstoque(Long produtoId, int quantidade) {
        String sql = "UPDATE produtos SET estoque = estoque + ? WHERE id = ?";

        try (Connection conn = ConexaoDB.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, quantidade);
            stmt.setLong(2, produtoId);

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Estoque atualizado (adição) com sucesso!");
            }
        } catch (SQLException e) {
            throw new EstoqueException("ERROR! Quantidade Inválida");
        }
    }

    @Override
    public void removerEstoque(Long produtoId, int quantidade) {
        String sql = "UPDATE produtos SET estoque = estoque - ? WHERE id = ?";

        if (quantidade > 0) {
            try (Connection conn = ConexaoDB.getConnection()) {
                PreparedStatement stmt = conn.prepareStatement(sql);

                stmt.setInt(1, quantidade);
                stmt.setLong(2, produtoId);

                int linhasAfetadas = stmt.executeUpdate();
                if (linhasAfetadas > 0) {
                    System.out.println("Estoque atualizado (remoção) com sucesso!");
                }
            } catch (SQLException e) {
                throw new EstoqueException("ERROR! Quantidade Inválida");
            }
        }
        else {
            System.out.println("Quantidade Inválida");
        }
    }

    @Override
    public void adicionarProduto(Produto produto) {
        String sql = "INSERT INTO produtos (nome, preco, quantidade) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoDB.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getQuantidadeEstoque());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0){
                System.out.println("Produto criado com sucesso!");
            }
        }
        catch (SQLException e){
            throw new EstoqueException("ERROR ao criar Produto");
        }
    }

    @Override
    public Produto buscarPorId(Long id) {
        String sql = "SELECT * FROM estoque WHERE id = ?";
        try (Connection conn = ConexaoDB.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setLong(1, id);
            try(ResultSet rs = stmt.executeQuery()){
                if (rs.next()){
                    Produto produto = new Produto();

                    produto.setId(rs.getLong("id"));
                    produto.setNome(rs.getString("nome"));
                    produto.setPreco(rs.getDouble("preco"));
                    produto.setQuantidadeEstoque(rs.getInt("quantidade"));

                    return produto;
                }
            }
        }
        catch (SQLException e){
            throw new EstoqueException("Produto não encontrado.");
        }
        return null;
    }
}
