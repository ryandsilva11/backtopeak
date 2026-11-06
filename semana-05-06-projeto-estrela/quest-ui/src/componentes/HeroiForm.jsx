import { useState } from 'react';

// Recebe a prop 'onHeroiCriado' do Pai (App.jsx)
function HeroiForm({ onHeroiCriado }) {
    // 1. Estados controlados para os campos de digitação
    const [nome, setNome] = useState('');
    const [classe, setClasse] = useState('Guerreiro'); // Padrão
    const [salvando, setSalvando] = useState(false);
    const [mensagemErro, setMensagemErro] = useState('');

    // 2. Função de envio do formulário (POST)
    const handleSubmit = async (e) => {
        // REGRA DE OURO: impede o navegador de dar F5 na página ao submeter!
        e.preventDefault();

        setMensagemErro('');
        setSalvando(true);

        try {
            // Monta o JSON exato esperado pelo HeroiRequestDTO do Java
            const resposta = await fetch('http://localhost:8080/herois', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ nome, classe }),
            });

            if (!resposta.ok) {
                throw new Error('Falha ao cadastrar herói na API');
            }

            // O Spring Boot devolve o HeroiResponseDTO criado (com id, nível 1, xp 0):
            const heroiCriado = await resposta.json();

            // Avisa o Pai (App.jsx) que um novo herói nasceu:
            onHeroiCriado(heroiCriado);

            // Limpa os campos do formulário para o próximo cadastro:
            setNome('');
            setClasse('Guerreiro');
        } catch (erro) {
            setMensagemErro('Erro ao cadastrar herói. Verifique os dados ou a conexão com a API.');
            console.error(erro);
        } finally {
            setSalvando(false);
        }
    };

    return (
        <div className="formulario-container">
            <h2>🛡️ Recrutar Novo Herói</h2>
            <form onSubmit={handleSubmit}>
                <div className="form-linha">
                    {/* Campo Nome */}
                    <div className="campo-grupo">
                        <label htmlFor="nome">Nome do Herói</label>
                        <input
                            id="nome"
                            type="text"
                            placeholder="Ex: Arthur Pendragon"
                            value={nome}
                            onChange={(e) => setNome(e.target.value)} // Atualiza o estado a cada tecla!
                            required
                        />
                    </div>

                    {/* Campo Classe (Select) */}
                    <div className="campo-grupo">
                        <label htmlFor="classe">Classe</label>
                        <select
                            id="classe"
                            value={classe}
                            onChange={(e) => setClasse(e.target.value)}
                        >
                            <option value="Guerreiro">Guerreiro</option>
                            <option value="Mago">Mago</option>
                            <option value="Arqueiro">Arqueiro</option>
                            <option value="Paladino">Paladino</option>
                            <option value="Ladino">Ladino</option>
                        </select>
                    </div>

                    {/* Botão de Envio */}
                    <button type="submit" className="btn-cadastrar" disabled={salvando}>
                        {salvando ? 'Recrutando...' : 'Cadastrar Herói'}
                    </button>
                </div>

                {mensagemErro && <p className="msg-erro-form">{mensagemErro}</p>}
            </form>
        </div>
    );
}

export default HeroiForm;