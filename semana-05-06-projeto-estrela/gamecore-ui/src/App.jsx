import { useState, useEffect } from 'react';
import './App.css';

// URL base da nossa API Spring Boot
const API_URL = 'http://localhost:8080';

function App() {
  // 1. ESTADOS (useState): A memória viva do React
  const [jogador, setJogador] = useState(null);
  const [itens, setItens] = useState([]);
  const [inventario, setInventario] = useState([]);
  const [filtroTipo, setFiltroTipo] = useState('TODOS');
  const [quantidades, setQuantidades] = useState({});
  const [alerta, setAlerta] = useState(null); // { tipo: 'sucesso' | 'erro', texto: '' }
  const [carregando, setCarregando] = useState(true);

  // 2. CICLO DE VIDA (useEffect): Carrega os dados assim que a tela abre
  useEffect(() => {
    carregarDados();
  }, []);

  // Função central para buscar tudo na API
  async function carregarDados() {
    try {
      setCarregando(true);
      // Busca dados do Jogador Arthur (ID 1)
      const resJogador = await fetch(`${API_URL}/jogadores/1`);
      if (resJogador.ok) {
        const dadosJogador = await resJogador.json();
        setJogador(dadosJogador);
      }

      // Busca catálogo de itens com estoque > 0
      const resItens = await fetch(`${API_URL}/itens`);
      if (resItens.ok) {
        const dadosItens = await resItens.json();
        setItens(dadosItens);
      }

      // Busca a mochila do jogador
      const resInventario = await fetch(`${API_URL}/loja/inventario/1`);
      if (resInventario.ok) {
        const dadosInventario = await resInventario.json();
        setInventario(dadosInventario);
      }
    } catch (err) {
      mostrarAlerta('erro', 'Falha ao conectar à API GameCore. O backend está rodando?');
    } finally {
      setCarregando(false);
    }
  }

  // Função auxiliar para exibir notificações temporárias
  function mostrarAlerta(tipo, texto) {
    setAlerta({ tipo, texto });
    setTimeout(() => setAlerta(null), 5000); // Some após 5 segundos
  }

  // Controle do contador de quantidade no card de compra (+ e -)
  function alterarQuantidade(itemId, delta, estoqueMaximo) {
    const atual = quantidades[itemId] || 1;
    const novaQtd = Math.max(1, Math.min(atual + delta, estoqueMaximo));
    setQuantidades({ ...quantidades, [itemId]: novaQtd });
  }

  // 3. CONSUMO DA API: Ação de Comprar Item (POST /loja/comprar)
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
        // Reseta o seletor de quantidade desse item para 1
        setQuantidades({ ...quantidades, [item.id]: 1 });
        // Recarrega os dados para atualizar o ouro e o estoque na tela!
        carregarDados();
      } else {
        const erroJson = await resposta.json().catch(() => null);
        const mensagemErro = erroJson?.message || 'Erro ao realizar compra na loja!';
        mostrarAlerta('erro', mensagemErro);
      }
    } catch (err) {
      mostrarAlerta('erro', 'Não foi possível se comunicar com o servidor!');
    }
  }

  // Filtra itens da vitrine por categoria
  const itensFiltrados = filtroTipo === 'TODOS'
    ? itens
    : itens.filter(i => i.tipo.toLowerCase() === filtroTipo.toLowerCase());

  // Ícones dinâmicos de acordo com o tipo
  function getIconeTipo(tipo) {
    switch (tipo?.toLowerCase()) {
      case 'arma': return '⚔️';
      case 'armadura': return '🛡️';
      case 'consumivel': return '🧪';
      case 'cosmetico': return '👑';
      default: return '📦';
    }
  }

  return (
    <div className="app-container">
      {/* 1. NAVBAR SUPERIOR */}
      <header className="navbar">
        <div className="logo-group">
          <span className="logo-icon">⚔️</span>
          <div className="logo-text">
            <h1>GameCore MMORPG</h1>
            <span>Painel do Aventureiro & Mercado</span>
          </div>
        </div>

        <div className="navbar-status">
          <div className="status-badge">
            <span className="status-dot"></span>
            {carregando ? 'Sincronizando...' : 'Servidor Conectado'}
          </div>
        </div>
      </header>

      {/* ALERTA / TOAST DE NOTIFICAÇÃO */}
      {alerta && (
        <div className={`toast-alert ${alerta.tipo}`}>
          <span>{alerta.tipo === 'sucesso' ? '✨' : '⚠️'}</span>
          <span>{alerta.texto}</span>
        </div>
      )}

      {/* 2. PAINEL DO HERÓI (STATUS BARS & OURO) */}
      <section className="hero-panel">
        <div className="hero-avatar">
          <div className="avatar-circle">🧙‍♂️</div>
          <div className="hero-info">
            <h2>{jogador ? jogador.nome : 'Carregando...'}</h2>
            <div className="meta">
              <span>{jogador?.classe || 'Aventureiro'}</span>
              <span>•</span>
              <span>Nível {jogador?.nivel || 1}</span>
              <span>•</span>
              <span className="guild-badge">
                {jogador?.guilda ? `🛡️ ${jogador.guilda}` : 'Sem Guilda'}
              </span>
            </div>
          </div>
        </div>

        {/* BARRAS DE VIDA E MANA */}
        <div className="bars-container">
          <div className="bar-wrapper">
            <div className="bar-header hp">
              <span>Pontos de Vida</span>
              <span>{jogador ? `${jogador.pontosVida} / 100` : '--/--'}</span>
            </div>
            <div className="bar-track">
              <div
                className="bar-fill hp"
                style={{ width: `${jogador ? (jogador.pontosVida / 100) * 100 : 0}%` }}
              ></div>
            </div>
          </div>

          <div className="bar-wrapper">
            <div className="bar-header mp">
              <span>Pontos de Mana</span>
              <span>{jogador ? `${jogador.pontosMana} / 50` : '--/--'}</span>
            </div>
            <div className="bar-track">
              <div
                className="bar-fill mp"
                style={{ width: `${jogador ? (jogador.pontosMana / 50) * 100 : 0}%` }}
              ></div>
            </div>
          </div>
        </div>

        {/* BADGE DE OURO */}
        <div className="gold-badge">
          <span className="gold-icon">🪙</span>
          <div>
            <div className="gold-amount">
              {jogador ? Number(jogador.moedasOuro).toFixed(1) : '0.0'}
            </div>
            <div className="gold-label">Moedas de Ouro</div>
          </div>
        </div>
      </section>

      {/* 3. GRID PRINCIPAL: LOJA NA ESQUERDA | MOCHILA NA DIREITA */}
      <main className="main-grid">

        {/* COLUNA ESQUERDA: VITRINE DA LOJA */}
        <section className="section-panel">
          <div className="section-header">
            <div className="section-title">
              <span>🏪</span>
              <h2>Vitrine do Mercado</h2>
            </div>
            <span className="section-counter">{itensFiltrados.length} itens disponíveis</span>
          </div>

          {/* BARRA DE FILTROS */}
          <div className="filters-bar">
            {['TODOS', 'arma', 'armadura', 'consumivel', 'cosmetico'].map((tipo) => (
              <button
                key={tipo}
                className={`filter-btn ${filtroTipo === tipo ? 'active' : ''}`}
                onClick={() => setFiltroTipo(tipo)}
              >
                {tipo.toUpperCase()}
              </button>
            ))}
          </div>

          {/* LISTA DE CARDS DA LOJA */}
          <div className="itens-grid">
            {itensFiltrados.map((item) => {
              const qtdDesejada = quantidades[item.id] || 1;
              const custoTotal = item.preco * qtdDesejada;
              const semSaldo = jogador && jogador.moedasOuro < custoTotal;
              const semEstoque = item.quantidade < qtdDesejada;

              return (
                <div key={item.id} className="item-card">
                  <div className="item-card-header">
                    <span className="item-icon">{getIconeTipo(item.tipo)}</span>
                    <span className={`item-tipo-badge ${item.tipo.toLowerCase()}`}>
                      {item.tipo}
                    </span>
                  </div>

                  <div className="item-card-body">
                    <h3>{item.nome}</h3>
                    <div className="item-poder">
                      {item.poder ? `⚡ Poder: +${item.poder}` : '✨ Item Cosmético/Especial'}
                    </div>
                    <div className="item-stock">
                      Estoque: <span>{item.quantidade} unidades</span>
                    </div>
                  </div>

                  <div className="item-card-footer">
                    <div className="item-price">🪙 {custoTotal.toFixed(1)} Ouro</div>

                    <div className="purchase-actions">
                      {/* STEPPER DE QUANTIDADE (- / +) */}
                      <div className="qty-stepper">
                        <button onClick={() => alterarQuantidade(item.id, -1, item.quantidade)}>-</button>
                        <span>{qtdDesejada}</span>
                        <button onClick={() => alterarQuantidade(item.id, +1, item.quantidade)}>+</button>
                      </div>

                      <button
                        className="btn-buy"
                        disabled={semSaldo || semEstoque}
                        onClick={() => handleComprar(item)}
                        title={semSaldo ? 'Saldo insuficiente' : semEstoque ? 'Estoque insuficiente' : 'Comprar agora'}
                      >
                        {semEstoque ? 'Sem Estoque' : semSaldo ? 'Sem Ouro' : 'Comprar'}
                      </button>
                    </div>
                  </div>
                </div>
              );
            })}
          </div>
        </section>

        {/* COLUNA DIREITA: MOCHILA / INVENTÁRIO DO HERÓI */}
        <section className="section-panel">
          <div className="section-header">
            <div className="section-title">
              <span>🎒</span>
              <h2>Mochila do Jogador</h2>
            </div>
            <span className="section-counter">{inventario.length} slots ocupados</span>
          </div>

          <div className="inventory-list">
            {inventario.length === 0 ? (
              <div className="empty-inventory">
                <span className="empty-icon">🕸️</span>
                <p>Sua mochila está vazia!</p>
                <small>Compre equipamentos e consumíveis na vitrine ao lado.</small>
              </div>
            ) : (
              inventario.map((slot) => (
                <div key={slot.id} className="inventory-slot">
                  <div className="slot-left">
                    <span className="slot-icon">{getIconeTipo(slot.tipoItem)}</span>
                    <div className="slot-details">
                      <h4>{slot.nomeItem}</h4>
                      <span>
                        {slot.poderItem ? `+${slot.poderItem} Poder` : 'Item Especial'} • {slot.tipoItem}
                      </span>
                    </div>
                  </div>
                  <div className="slot-count-badge">
                    x{slot.quantidade}
                  </div>
                </div>
              ))
            )}
          </div>
        </section>

      </main>
    </div>
  );
}

export default App;
