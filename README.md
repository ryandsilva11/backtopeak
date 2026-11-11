# ⛰️ BackToPeak — Java, Spring Boot & Fullstack Engineering

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.4-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![React](https://img.shields.io/badge/React-19-61DAFB?style=for-the-badge&logo=react&logoColor=black)
![Chakra UI](https://img.shields.io/badge/Chakra_UI-v3-319795?style=for-the-badge&logo=chakraui&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8-4479A1?style=for-the-badge&logo=mysql&logoColor=white)

Repositório prático de engenharia de software criado para consolidar fundamentos de **Programação Orientada a Objetos (Java)**, **Modelagem Relacional e SQL**, **Web/HTTP** e construção de uma **aplicação Fullstack RESTful** com **Java 21 + Spring Boot 3** no backend e **React + Chakra UI** no frontend.

---

## 🌟 Projeto Principal: Quest API & UI (`semana-05-06-projeto-estrela`)

Na pasta [`/semana-05-06-projeto-estrela`](./semana-05-06-projeto-estrela) está o projeto principal deste repositório: o **Quest API & UI**, estruturado em **arquitetura em camadas** (*Controller → Service → Repository → DTOs/Records*), persistência relacional com **Spring Data JPA**, validação de contratos e documentação automática com **Swagger / OpenAPI**.

### ⚔️ Quest API & UI (Sistema RPG de Heróis e Quests)

Aplicação Fullstack para cadastro de heróis, criação de missões (*quests*) com sistema de recompensas (*loot* e XP) e progressão automática de nível (*Level Up*) ao concluir missões.

* **Backend ([`quest-api`](./semana-05-06-projeto-estrela/quest-api)):**
  * **Stack:** Java 21, Spring Boot 3.4.3, Spring Web, Spring Data JPA, Bean Validation (`jakarta.validation`), SpringDoc OpenAPI / Swagger, MySQL e H2 Database.
  * **Destaques técnicos:** Separação estrita entre entidades JPA (`Heroi`, `Quest`) e contratos de API via Java `record` DTOs (`QuestRequestDTO`, `QuestResponseDTO`, `HeroiRequestDTO`, `HeroiResponseDTO`); validação condicional customizada com `@AssertTrue` (exigindo quantidade de *loot* válida quando há recompensa); operações transacionais (`@Transactional`) para concluir missões, somar XP e calcular *Level Up* do herói; CORS configurado para integração com React.
  * **Principais Endpoints:**
    * **Heróis (`/herois`):** `POST /herois`, `GET /herois`, `GET /herois/{id}`, `DELETE /herois/{id}`
    * **Quests (`/quests`):** `POST /quests`, `GET /quests`, `GET /quests/{id}`, `PATCH /quests/{questId}/concluir/{heroiId}`
* **Frontend ([`quest-ui`](./semana-05-06-projeto-estrela/quest-ui)):**
  * **Stack:** React 19, Vite e **Chakra UI v3**.
  * **Destaques técnicos:** Formulários controlados (`HeroiForm`, `QuestForm`), cards interativos (`HeroiCard`, `QuestCard`) com seleção de herói executor e integração assíncrona via `fetch` (`async/await`), além de build de produção compilado para `static/` no backend.

---

## 📂 Estrutura Progressiva do Repositório

| Módulo | Foco Técnico | Principais Arquivos |
| :--- | :--- | :--- |
| [`semana-01-java-poo`](./semana-01-java-poo) | Java Puro & POO (abstração, herança, polimorfismo e encapsulamento) | `Hero.java`, `Warrior.java`, `Mage.java`, `Main.java` |
| [`semana-02-sql`](./semana-02-sql) | Banco de Dados Relacional (DDL, DML, `FOREIGN KEY` e `JOIN`s) | `01_consultas_iniciais.sql`, `02_relacionamento_e_joins.sql` |
| [`semana-03-redes-web`](./semana-03-redes-web) | Fundamentos Web com HTML5 e CSS3 | `index.html`, `style.css` |
| [`semana-04-javascript-dom`](./semana-04-javascript-dom) | JavaScript Moderno, DOM, Eventos e `fetch` assíncrono | `index.html`, `style.css`, `app.js` |
| [`semana-05-06-projeto-estrela`](./semana-05-06-projeto-estrela) | Aplicação Fullstack (API Spring Boot + Frontend React/Chakra UI) | [`quest-api`](./semana-05-06-projeto-estrela/quest-api), [`quest-ui`](./semana-05-06-projeto-estrela/quest-ui) |

---

## 🚀 Como Executar Localmente (`Quest API & UI`)

### Pré-requisitos
* **Java 21 (JDK 21)** instalado
* **Node.js 18+** e **npm**
* *(Opcional)* **MySQL 8** local — caso não queira subir um MySQL, a API possui perfil **H2 em memória** pronto para uso.

### 1. Subindo o Backend (`quest-api`)

```bash
cd semana-05-06-projeto-estrela/quest-api
```

**Opção A — Banco em memória H2 (mais rápida para testar):**
* Linux / macOS / Git Bash:
  ```bash
  ./mvnw spring-boot:run -Dspring-boot.run.arguments=--spring.profiles.active=h2
  ```
* Windows (PowerShell):
  ```powershell
  .\mvnw.cmd spring-boot:run "-Dspring-boot.run.arguments=--spring.profiles.active=h2"
  ```

**Opção B — Banco MySQL local (perfil padrão `mysql`):**
* Com o MySQL rodando na porta `3306` (usuário `root`, sem senha; o banco `quest_db` é criado automaticamente):
  ```powershell
  .\mvnw.cmd spring-boot:run
  ```

Após iniciar, a API estará disponível em `http://localhost:8080`:
* **Documentação Swagger UI:** `http://localhost:8080/swagger-ui.html`
* **Console H2 (quando usar perfil `h2`):** `http://localhost:8080/h2-console` (`JDBC URL: jdbc:h2:mem:quest_db`, usuário `sa`, senha vazia)

### 2. Subindo o Frontend em modo desenvolvimento (`quest-ui`)

Em outro terminal:

```bash
cd semana-05-06-projeto-estrela/quest-ui
npm install
npm run dev
```

Acesse a interface no navegador em `http://localhost:5173`.

---

## 👨‍💻 Autor

**Ryan Dias da Silva**

Graduando em Desenvolvimento de Software Multiplataforma (Fatec Indaiatuba) | Técnico em Informática (IFSP)

* **LinkedIn:** [linkedin.com/in/ryandiasdasilva](https://www.linkedin.com/in/ryandiasdasilva)
* **GitHub:** [github.com/ryandsilva11](https://github.com/ryandsilva11)
* **E-mail:** ryandsilva31.19@gmail.com
