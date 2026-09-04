public class Mage extends Hero {

    private int mana;

    public Mage(String nome, int vida, int pontosDeAtaque) {
        super(nome, vida, pontosDeAtaque);
    }

    @Override
    public void executarAtaque(Hero alvo) {
        if (this.mana >= 10) {
            this.mana -= 10;
            alvo.receberDano(this.getPontosDeAtaque() * 2);
        } else {
            alvo.receberDano(getPontosDeAtaque());
        }
    }
}
