import { useState, useEffect } from 'react';
import './App.css';

function App() {
  // 1. Estados da nossa aplicação
  const [herois, setHerois] = useState([]);
  const [carregando, setCarregando] = useState(true);

  // 2. Função assíncrona que chama a sua API Spring Boot
  const carregarHerois = async () => {
    try {
      setCarregando(true);
      const resposta = await fetch('http://localhost:8080/herois');

      if (!resposta.ok) {
        if (resposta.status === 404) {
          throw new Error('Recurso não encontrado no servidor (Erro 404)');
        } else if (resposta.status === 500) {
          throw new Error('Erro interno no servidor Java/Banco de Dados (Erro 500)');
        } else if (resposta.status === 400) {
          throw new Error('Dados inválidos enviados para a API (Erro 400)');
        } else {
          throw new Error(`A API respondeu com o erro HTTP ${resposta.status}`);
        }
      }

      const dados = await resposta.json();
      setHerois(dados); // Guarda a lista vinda do banco no estado!
    } catch (erro) {
      console.error('Erro na requisição:', erro);
    } finally {
      setCarregando(false);
    }
  };

  // 3. O useEffect dispara a busca UMA vez assim que a tela abre
  useEffect(() => {
    carregarHerois();
  }, []);

  // 4. O Visual (JSX)
  return (
    <div style={{ maxWidth: '800px', margin: '0 auto', padding: '20px', fontFamily: 'sans-serif' }}>
      <h1>🗡️ Painel de Heróis - Quest API</h1>
      <hr />

      {/* Exibição condicional enquanto busca na API */}
      {carregando && <p>Carregando heróis da guilda...</p>}

      {!carregando && herois.length === 0 && (
        <p>Nenhum herói cadastrado ainda no banco de dados.</p>
      )}

      {/* Renderização da lista de heróis */}
      <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fill, minmax(220px, 1fr))', gap: '15px', marginTop: '20px' }}>
        {herois.map((heroi) => (
          <div
            key={heroi.id}
            style={{ border: '1px solid #ccc', borderRadius: '8px', padding: '15px', backgroundColor: '#f9f9f9', color: '#333' }}
          >
            <h3 style={{ margin: '0 0 10px 0' }}>{heroi.nome}</h3>
            <p style={{ margin: '5px 0' }}><strong>Classe:</strong> {heroi.classe}</p>
            <p style={{ margin: '5px 0' }}><strong>Nível:</strong> {heroi.nivel}</p>
            <p style={{ margin: '5px 0' }}><strong>XP:</strong> {heroi.xp}</p>
          </div>
        ))}
      </div>
    </div>
  );
}

export default App;