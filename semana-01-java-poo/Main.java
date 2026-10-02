
public class Main {
    public static void main(String[] args) {

        Warrior warrior = new Warrior("Arthur", 100, 15);
        Mage mage = new Mage("Merlin", 80, 12, 30);
        int cont = 0;

        while (true) {
            cont++;

            System.out.printf("=".repeat(15) + "TURNO %d" + "=".repeat(15) + "\n", cont);

            if (warrior.getVida() <= 0) {
                System.out.printf("A vitória é de %s!\n", mage.getNome());
                break;
            } else {
                warrior.executarAtaque(mage);
                System.out.printf("%s atacou %s, deixando-o com %d de vida!\n",
                        warrior.getNome(), mage.getNome(), mage.getVida());
            }

            System.out.println();

            if (mage.getVida() <= 0) {
                System.out.printf("A vitória é de %s!\n", warrior.getNome());
                break;
            } else {
                mage.executarAtaque(warrior);
                System.out.printf("%s atacou %s, deixando-o com %d de vida!\n",
                        mage.getNome(), warrior.getNome(), warrior.getVida());

            }

        }
    }
}
