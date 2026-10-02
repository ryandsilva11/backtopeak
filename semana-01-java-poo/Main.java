
public class Main {
    public static void main(String[] args) {

        Warrior warrior = new Warrior("Arthur", 100, 15);
        Mage mage = new Mage("Merlin", 80, 12, 30);
        int cont = 0;

        while (true) {
            cont++;

            System.out.printf("=".repeat(15) + "TURNO %d" + "=".repeat(15) + "\n", cont);

            warrior.executarAtaque(mage);
            System.out.printf("%s atacou %s, deixando-o com %d de vida!\n",
                    warrior.getNome(), mage.getNome(), mage.getVida());

            if (!mage.estaVivo()) {
                System.out.printf("\nA vitória é de %s!", warrior.getNome());
                break;
            }

            System.out.println();

            mage.executarAtaque(warrior);
            System.out.printf("%s atacou %s, deixando-o com %d de vida!\n",
                    mage.getNome(), warrior.getNome(), warrior.getVida());

            if (!warrior.estaVivo()) {
                System.out.printf("\nA vitória é de %s!", mage.getNome());
                break;
            }
        }
    }
}
