export default function Navbar({ carregando }) {
    return (
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
    );
}
