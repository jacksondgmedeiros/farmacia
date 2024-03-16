package br.com.farmacia.modelo;

public class Produto {
    private int id;
    private double preco;
    private String nome;
    private String fabricante;

    public Produto(int id, double preco, String nome, String fabricante) {
        this.id = id;
        this.preco = preco;
        this.nome = nome;
        this.fabricante = fabricante;
    }

    public int getId() {
        return id;
    }

    public double getPreco() {
        return preco;
    }

    public String getNome() {
        return nome;
    }

    public String getFabricante() {
        return fabricante;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id = " + id +
                ", preço = " + preco +
                ", nome = " + nome +
                ", fabricante " + fabricante + "}";
    }
}
