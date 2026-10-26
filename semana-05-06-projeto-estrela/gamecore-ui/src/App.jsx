import { useState, useEffect } from 'react';
import './App.css';
import Navbar from './componentes/Navbar';
import HeroPanel from './componentes/HeroPanel';
import LojaVitrine from './componentes/LojaVitrine';
import Mochila from './componentes/Mochila';

const API_URL = 'http://localhost:8080';

function App() {
  const [jogador, setJogador] = useState(null);
  const [itens, setItens] = useState([]);
  const [inventario, setInventario] = useState([]);
  const [filtroTipo, setFiltroTipo] = useState('TODOS');
  const [quantidades, setQuantidades] = useState({});
  const [alerta, setAlerta] = useState(null);
  const [carregando, setCarregando] = useState(true);

  useEffect(() => {
    carregarDados();
  }, []);

  async function carregarDados() {
    try {
      setCarregando(true);
      const resJogador = await fetch(`${API_URL}/jogadores/1`);
      if (resJogador.ok) setJogador(await resJogador.json());

      const resItens = await fetch(`${API_URL}/itens`);
      if (resItens.ok) setItens(await resItens.json());

      const resInventario = await fetch(`${API_URL}/loja/inventario/1`);
      if (resInventario.ok) setInventario(await resInventario.json());
    } catch (err) {
      mostrarAlerta('erro', 'Falha ao conectar à API GameCore. O backend está rodando?');
    } finally {
      setCarregando(false);
    }
  }

  function mostrarAlerta(tipo, texto) {
    setAlerta({ tipo, texto });
    setTimeout(() => setAlerta(null), 5000);
  }

  function alterarQuantidade(itemId, delta, estoqueMaximo) {
    const atual = quantidades[itemId] || 1;
    const novaQtd = Math.max(1, Math.min(atual + delta, estoqueMaximo));
    setQuantidades({ ...quantidades, [itemId]: novaQtd });
  }

  async function handleComprar(item) {
    const qtd = quantidades[item.id] || 1;

    try {
      const resposta = await fetch(`${API_URL}/loja/comprar`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          jogadorId: 1,
          itemId: item.id,
          quantidade: qtd
        })
      });

      if (resposta.ok) {
        mostrarAlerta('sucesso', `Comprou ${qtd}x ${item.nome}! Item enviado para a mochila.`);
        setQuantidades({ ...quantidades, [item.id]: 1 });
        carregarDados();
      } else {
        const erroJson = await resposta.json().catch(() => null);
        mostrarAlerta('erro', erroJson?.message || 'Erro ao realizar compra na loja!');
      }
    } catch (err) {
      mostrarAlerta('erro', 'Não foi possível se comunicar com o servidor!');
    }
  }

  return (
    <div className="app-container">
      {/* 1. Navbar isolada */}
      <Navbar carregando={carregando} />

      {/* Alerta / Toast */}
      {alerta && (
        <div className={`toast-alert ${alerta.tipo}`}>
          <span>{alerta.tipo === 'sucesso' ? '✨' : '⚠️'}</span>
          <span>{alerta.texto}</span>
        </div>
      )}

      {/* 2. Painel do Herói isolado */}
      <HeroPanel jogador={jogador} />

      {/* 3. Grid Principal com os componentes filhos */}
      <main className="main-grid">
        <LojaVitrine
          itens={itens}
          filtroTipo={filtroTipo}
          onTrocarFiltro={setFiltroTipo}
          quantidades={quantidades}
          saldoJogador={jogador ? jogador.moedasOuro : 0}
          onAlterarQtd={alterarQuantidade}
          onComprar={handleComprar}
        />

        <Mochila inventario={inventario} />
      </main>
    </div>
  );
}

export default App;
