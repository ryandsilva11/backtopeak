public class Warrior extends Hero {

    public Warrior(String nome, int vida, int pontosDeAtaque) {
        super(nome, vida, pontosDeAtaque);
    }

    @Override
    public void executarAtaque(Hero alvo) {
        alvo.receberDano(this.getPontosDeAtaque());
    }

}
