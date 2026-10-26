export default function CardItem({ item, qtd, saldoJogador, onAlterarQtd, onComprar }) {
    const custoTotal = item.preco * qtd;
    const semSaldo = saldoJogador !== null && saldoJogador < custoTotal;
    const semEstoque = item.quantidade < qtd;

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
        <div className="item-card">
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
                    {/* STEPPER (- / +) */}
                    <div className="qty-stepper">
                        <button onClick={() => onAlterarQtd(item.id, -1, item.quantidade)}>-</button>
                        <span>{qtd}</span>
                        <button onClick={() => onAlterarQtd(item.id, +1, item.quantidade)}>+</button>
                    </div>

                    <button
                        className="btn-buy"
                        disabled={semSaldo || semEstoque}
                        onClick={() => onComprar(item)}
                        title={semSaldo ? 'Saldo insuficiente' : semEstoque ? 'Estoque insuficiente' : 'Comprar agora'}
                    >
                        {semEstoque ? 'Sem Estoque' : semSaldo ? 'Sem Ouro' : 'Comprar'}
                    </button>
                </div>
            </div>
        </div>
    );
}
