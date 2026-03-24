import dao.ProdutoDaoImpl;
import exceptions.EstoqueException;
import model.Produto;
import service.ProdutoService;

public class TesteProduto {

    public static void main(String[] args) {

        ProdutoDaoImpl produtoDAO = new ProdutoDaoImpl();
        ProdutoService produtoService = new ProdutoService(produtoDAO);

        Produto produto = new Produto();
        produto.setNome("Notebook Dell");
        produto.setCategoria("Informática");
        produto.setPreco(3500.00);
        produto.setQuantidadeEstoque(10);

        System.out.println("Criar produto");
        produtoDAO.adicionarProduto(produto);


        System.out.println("\nBuscar produto por ID");
        Produto buscado = produtoDAO.buscarPorId(1L);

        if (buscado != null) {
            System.out.println("Produto encontrado:");
            System.out.println("ID: " + buscado.getId());
            System.out.println("Nome: " + buscado.getNome());
            System.out.println("Preço: " + buscado.getPreco());
            System.out.println("Estoque: " + buscado.getQuantidadeEstoque());
        }

        System.out.println("\nAdicionar estoque");
        try {
            produtoService.adicionarEstoque(1L, 5);
            Produto atualizado = produtoDAO.buscarPorId(1L);
            System.out.println("Novo estoque: " + atualizado.getQuantidadeEstoque());
        } catch (EstoqueException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nRemover estoque");
        try {
            produtoService.removerEstoque(1L, 3);
            Produto atualizado = produtoDAO.buscarPorId(1L);
            System.out.println("Novo estoque: " + atualizado.getQuantidadeEstoque());
        } catch (EstoqueException e) {
            System.out.println(e.getMessage());
        }

    }
}