export default function Mochila({ inventario }) {
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
    );
}
