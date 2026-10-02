public abstract class Hero {

    private String nome;
    private int vida;
    private int pontosDeAtaque;

    public Hero(String nome, int vida, int pontosDeAtaque) {
        this.nome = nome;
        this.vida = vida;
        this.pontosDeAtaque = pontosDeAtaque;
    }

    public String getNome() {
        return this.nome;
    }

    public int getVida() {
        return this.vida;
    }

    public int getPontosDeAtaque() {
        return this.pontosDeAtaque;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setVida(int dano) {
        if (dano > this.vida) {
            this.vida = 0;
        } else {
            this.vida -= dano;
        }
    }

    public void receberDano(int dano) {
        this.setVida(dano);
    }

    public abstract void executarAtaque(Hero alvo);
}