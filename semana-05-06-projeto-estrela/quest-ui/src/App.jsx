import { useState, useEffect } from 'react';
import './app.css';
import HeroiCard from './componentes/HeroiCard';
import HeroiForm from './componentes/HeroiForm';
import QuestForm from './componentes/QuestForm';
import QuestCard from './componentes/QuestCard';

function App() {
  const [herois, setHerois] = useState([]);
  const [quests, setQuests] = useState([])
  const [carregando, setCarregando] = useState(true);

  const carregarQuests = async () => {
    try {
      const resposta = await fetch('http://localhost:8080/quests')
      if (resposta.ok) {
        const dados = await resposta.json();
        setQuests(dados);
      }
    }
    catch (erro) {
      console.error('Eror ao carregar quests', erro);
    }
  }

  const carregarHerois = async () => {
    try {
      setCarregando(true);
      const resposta = await fetch('http://localhost:8080/herois');

      if (!resposta.ok) {
        if (resposta.status === 404) {
          throw new Error('Recurso não encontrado no servidor (Erro 404)');
        } else if (resposta.status === 500) {
          throw new Error('Erro interno no servidor Java/Banco de Dados (Erro 500)');
        } else {
          throw new Error(`A API respondeu com o erro HTTP ${resposta.status}`);
        }
      }

      const dados = await resposta.json();
      setHerois(dados);
    } catch (erro) {
      console.error('Erro na requisição:', erro);
    } finally {
      setCarregando(false);
    }
  };

  useEffect(() => {
    carregarHerois();
    carregarQuests();
  }, []);

  const adicionarHeroiNaLista = (novoHeroi) => {
    setHerois((listaAnterior) => [...listaAnterior, novoHeroi]);
  };

  const adicionarQuestNaLista = (novaQuest) => {
    setQuests((listaAnterior) => [...listaAnterior, novaQuest]);
  }

  const handleConcluirQuest = async (questId, heroiId) => {
    try {
      const resposta = await fetch(`http://localhost:8080/quests/${questId}/concluir/${heroiId}`,
        {
          method: 'PATCH'
        });

      if (resposta.ok) {
        carregarHerois();
        carregarQuests();
      }
      else {
        alert('Não foi possível concluir a quest!');
      }
    }
    catch (erro) {
      console.error('Erro ao conclir quest', erro);
    }
  }

  return (
    <div className="container">
      <h1 className="titulo-principal">🗡️ Painel de Heróis - Quest API</h1>
      <h2 className="subtitulo">Gerenciador de Aventureiros da Guilda</h2>
      <hr className="divisor" />

      <HeroiForm onHeroiCriado={adicionarHeroiNaLista} />

      {carregando && <p className="mensagem-alerta">Carregando heróis da guilda...</p>}

      {!carregando && herois.length === 0 && (
        <p className="mensagem-alerta">Nenhum herói cadastrado ainda no banco de dados.</p>
      )}

      {/* Renderização limpa usando o componente filho HeroiCard */}
      <div className="grid-herois">
        {herois.map((heroi) => (
          <HeroiCard key={heroi.id} heroi={heroi} />
        ))}
      </div>

      <hr className="divisor" style={{ marginTop: '40px' }} />

      <h2 className="titulo-principal">📜 Quests Disponíveis</h2>
      <h3 className="subtitulo">Missões da Guilda</h3>

      <QuestForm onQuestCriada={adicionarQuestNaLista} />

      <div className="grid-herois">
        {quests.map((quest) => (
          <QuestCard
            key={quest.id}
            quest={quest}
            herois={herois}
            onConcluirQuest={handleConcluirQuest}
          />
        ))}
      </div>
    </div>

  );
}

export default App;