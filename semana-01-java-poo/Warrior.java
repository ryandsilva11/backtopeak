public class Warrior extends Hero {

    private Warrior(String nome, int vida, int pontosDeAtaque) {
        super(nome, vida, pontosDeAtaque);
    }

    @Override
    public void executarAtaque(Hero alvo) {
        alvo.receberDano(5);
    }

}