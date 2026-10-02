public class Mage extends Hero {

    private int mana;

    public Mage(String nome, int vida, int pontosDeAtaque, int mana) {
        super(nome, vida, pontosDeAtaque);
        this.mana = mana;
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
