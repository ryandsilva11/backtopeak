
// O componente recebe 'heroi' via PROPS (parâmetro da função)
function HeroiCard({ heroi }) {
    return (
        <div className="card-heroi">
            <h3>{heroi.nome}</h3>
            <p className="info-item">
                <strong>Classe:</strong> <span className="badge-classe">{heroi.classe}</span>
            </p>
            <p className="info-item">
                <strong>Nível:</strong> {heroi.nivel}
            </p>
            <p className="info-item">
                <strong>XP:</strong> {heroi.xp}
            </p>
        </div>
    );
}

export default HeroiCard;