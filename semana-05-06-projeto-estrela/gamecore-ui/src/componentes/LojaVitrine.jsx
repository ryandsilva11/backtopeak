import CardItem from './CardItem';

export default function LojaVitrine({
    itens,
    filtroTipo,
    onTrocarFiltro,
    quantidades,
    saldoJogador,
    onAlterarQtd,
    onComprar
}) {
    const itensFiltrados = filtroTipo === 'TODOS'
        ? itens
        : itens.filter(i => i.tipo.toLowerCase() === filtroTipo.toLowerCase());

    return (
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
                        onClick={() => onTrocarFiltro(tipo)}
                    >
                        {tipo.toUpperCase()}
                    </button>
                ))}
            </div>

            {/* GRID DE CARDS */}
            <div className="itens-grid">
                {itensFiltrados.map((item) => (
                    <CardItem
                        key={item.id}
                        item={item}
                        qtd={quantidades[item.id] || 1}
                        saldoJogador={saldoJogador}
                        onAlterarQtd={onAlterarQtd}
                        onComprar={onComprar}
                    />
                ))}
            </div>
        </section>
    );
}
