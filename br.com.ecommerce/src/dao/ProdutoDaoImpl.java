package dao;

import exceptions.EstoqueException;
import model.Produto;
import util.ConexaoDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDaoImpl implements ProdutoDAO{

    List<Produto> listaProdutos = new ArrayList<>();
    Connection conexaoDB = ConexaoDB.getConnection();


    public ProdutoDaoImpl() {
        criarTabela();
    }

    private void criarTabela() {
        String sql = """
                BEGIN
                    EXECUTE IMMEDIATE '
                        CREATE TABLE produtos (
                            id NUMBER GENERATED ALWAYS AS IDENTITY,
                            nome VARCHAR2(100) NOT NULL,
                            preco NUMBER(10,2) NOT NULL,
                            quantidade NUMBER NOT NULL,
                            categoria VARCHAR2(100),
                            PRIMARY KEY (id)
                        )
                    ';
                EXCEPTION
                    WHEN OTHERS THEN
                        IF SQLCODE != -955 THEN
                            RAISE;
                        END IF;
                END;
                """;

        try (Connection conn = ConexaoDB.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
            System.out.println("Tabela 'produtos' verificada/criada com sucesso!");

        } catch (SQLException e) {
            throw new EstoqueException("Erro ao criar tabela (DAO): " + e.getMessage());
        }
    }

    @Override
    public void adicionarEstoque(Long produtoId, int quantidade) {
        String sql = "UPDATE produtos SET quantidade = quantidade + ? WHERE id = ?";

        try (Connection conn = ConexaoDB.getConnection()) {
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
        String sql = "UPDATE produtos SET quantidade = quantidade - ? WHERE id = ?";

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
        String sql = "INSERT INTO produtos (nome, preco, quantidade, categoria) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexaoDB.getConnection()) {

            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getQuantidadeEstoque());
            stmt.setString(4, produto.getCategoria());

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
        String sql = "SELECT * FROM produtos WHERE id = ?";
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