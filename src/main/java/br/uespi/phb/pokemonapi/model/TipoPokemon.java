package br.uespi.phb.pokemonapi.model;

public enum TipoPokemon {
    PLANTA("Planta"),
    FOGO("Fogo"),
    AGUA("Água"),
    INSETO("Inseto"),
    NORMAL("Normal"),
    VENENO("Veneno"),
    ELETRICO("Elétrico"),
    TERRA("Terra"),
    LUTADOR("Lutador"),
    PSIQUICO("Psíquico"),
    PEDRA("Pedra"),
    FANTASMA("Fantasma"),
    DRAGAO("Dragão"),
    GELO("Gelo"),
    VOADOR("Voador");

    private TipoPokemon(String nome) {
        this.nome = nome;
    }

    private String nome;

    public String nome() {
        return this.nome;
    }

    public static TipoPokemon fromPokeapi(String texto) {
        return switch (texto.toLowerCase()) {
            case "grass" -> PLANTA;
            case "fire" -> FOGO;
            case "water" -> AGUA;
            case "bug" -> INSETO;
            case "normal" -> NORMAL;
            case "poison" -> VENENO;
            case "electric" -> ELETRICO;
            case "ground" -> TERRA;
            case "fighting" -> LUTADOR;
            case "psychic" -> PSIQUICO;
            case "rock" -> PEDRA;
            case "ghost" -> FANTASMA;
            case "dragon" -> DRAGAO;
            case "ice" -> GELO;
            case "flying" -> VOADOR;
            default -> NORMAL;
        };
    }
}