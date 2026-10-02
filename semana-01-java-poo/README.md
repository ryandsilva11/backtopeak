# ☕ Semana 01: Java Puro & Orientação a Objetos (POO)

Bem-vindo à primeira semana do seu retorno ao auge! Aqui vamos reativar a memória muscular de sintaxe, boas práticas de POO e coleções em Java.

---

## 🎯 Desafio 01: RPG Mini-Core (Heróis e Batalha)

### 📌 Conceitos a Praticar:

- [ ] Modificadores de Acesso (`private`, `protected`, `public`)
- [ ] Classes e Métodos Abstratos (`abstract`)
- [ ] Construtores com `super()`
- [ ] Herança (`extends`) e Polimorfismo (`@Override`)
- [ ] Encapsulamento (Getters, Setters e validações de estado)

---

### 📝 Estrutura Esperada dos Arquivos:

Crie os seguintes arquivos dentro desta pasta (`semana-01-java-poo/`):

1. **`Heroi.java`** (Classe Abstrata)

   - **Atributos privados:**

     - `nome` (String)
     - `vida` (int)
     - `pontosDeAtaque` (int)
   - **Construtor:** Que recebe `(String nome, int vida, int pontosDeAtaque)`.
   - **Métodos:**

     - Getters e Setters (com regra: a vida não pode ficar menor que 0).
     - `public void receberDano(int dano)`: Subtrai a vida e avisa no console quanto de dano tomou e a vida restante.
     - `public abstract void executarAtaque(Heroi alvo)`: Método abstrato que cada subclasse implementará do seu jeito.
     - `public boolean estaVivo()`: Retorna `true` se `vida > 0`.
2. **`Guerreiro.java`** (Subclasse de `Heroi`)

   - Herda de `Heroi`.
   - Construtor que chama `super(nome, vida, pontosDeAtaque)`.
   - Sobrescreve `executarAtaque(Heroi alvo)`:
     - Causa dano físico crítico (ex: `pontosDeAtaque + 5`).
     - Chama `alvo.receberDano(...)`.
3. **`Mago.java`** (Subclasse de `Heroi`)

   - Herda de `Heroi`.
   - Atributo extra privado: `mana` (int).
   - Construtor que recebe `(nome, vida, pontosDeAtaque, mana)`.
   - Sobrescreve `executarAtaque(Heroi alvo)`:
     - Se `mana >= 10`: gasta 10 de mana e causa o dobro de dano (`pontosDeAtaque * 2`).
     - Senão: dá um ataque básico fraco por falta de mana.
     - Chama `alvo.receberDano(...)`.
4. **`Main.java`** (Ponto de Entrada)

   - Cria uma instância de `Guerreiro` (ex: "Arthur", vida: 100, ataque: 15).
   - Cria uma instância de `Mago` (ex: "Merlin", vida: 80, ataque: 12, mana: 30).
   - Simula alguns turnos de ataque entre eles até exibir os resultados no console.

---

## 💻 Como Compilar e Rodar no Terminal

Abra o terminal dentro desta pasta (`semana-01-java-poo`) e rode:

```bash
# Compilar todos os arquivos Java:
javac *.java

# Executar a classe principal:
java Main
```

---

> 💡 **Dica de Ouro:** Tente escrever cada linha de código **sem pedir para a IA autocompletar**. Se bater dúvida em alguma sintaxe específica (ex: como faz o construtor com herança), tente lembrar ou pesquise pontualmente. Quando terminar, me avise para fazermos o **Code Review**!
