export default function HeroPanel({ jogador }) {
    const hpPorcentagem = jogador ? (jogador.pontosVida / 100) * 100 : 0;
    const mpPorcentagem = jogador ? (jogador.pontosMana / 50) * 100 : 0;
    const ouroFormatado = jogador ? Number(jogador.moedasOuro).toFixed(1) : '0.0';

    return (
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

            {/* BARRAS DE STATUS (HP E MP) */}
            <div className="bars-container">
                <div className="bar-wrapper">
                    <div className="bar-header hp">
                        <span>Pontos de Vida</span>
                        <span>{jogador ? `${jogador.pontosVida} / 1000` : '--/--'}</span>
                    </div>
                    <div className="bar-track">
                        <div className="bar-fill hp" style={{ width: `${hpPorcentagem}%` }}></div>
                    </div>
                </div>

                <div className="bar-wrapper">
                    <div className="bar-header mp">
                        <span>Pontos de Mana</span>
                        <span>{jogador ? `${jogador.pontosMana} / 500` : '--/--'}</span>
                    </div>
                    <div className="bar-track">
                        <div className="bar-fill mp" style={{ width: `${mpPorcentagem}%` }}></div>
                    </div>
                </div>
            </div>

            {/* BADGE DE OURO */}
            <div className="gold-badge">
                <span className="gold-icon">🪙</span>
                <div>
                    <div className="gold-amount">{ouroFormatado}</div>
                    <div className="gold-label">Moedas de Ouro</div>
                </div>
            </div>
        </section>
    );
}
