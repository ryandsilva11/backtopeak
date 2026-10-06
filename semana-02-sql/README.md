# 🗄️ Semana 02: Banco de Dados Relacional (SQL) Sem Medo

Bem-vindo à Semana 02! O objetivo aqui é eliminar qualquer trava com SQL, dominando modelagem relacional, operações DML/DDL e consultas complexas com `JOINs`.

---

## 🎯 Desafio 01: O Banco de Dados do RPG (`rpg_db`)

### 📌 Conceitos a Praticar:
- [x] Criação de tabelas com chaves primárias e auto-incremento (`CREATE TABLE`, `PRIMARY KEY`, `AUTO_INCREMENT`)
- [ ] Inserção de dados (`INSERT INTO`)
- [ ] Consultas e filtros básicos (`SELECT`, `WHERE`, `ORDER BY`)
- [ ] Relacionamento 1:N com Chave Estrangeira (`FOREIGN KEY`)
- [ ] Cruzamento de dados (`INNER JOIN`, `LEFT JOIN`)
- [ ] Agregações (`COUNT`, `AVG`, `GROUP BY`)

---

## 📜 Histórico de Scripts

### 1. Tabela `herois`
Criada para mapear os personagens que criamos na Semana 1 em Java:
```sql
CREATE TABLE herois (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    classe VARCHAR(30) NOT NULL,
    vida INT NOT NULL,
    pontos_de_ataque INT NOT NULL
);
```
