import { useState } from 'react';
import { Box, Heading, Input, Button } from '@chakra-ui/react';

function HeroiForm({ onHeroiCriado }) {
    const [nome, setNome] = useState('');
    const [classe, setClasse] = useState('Guerreiro');
    // 1. O guarda-costas contra múltiplos cliques
    const [salvando, setSalvando] = useState(false);

    const handleSubmit = async (e) => {
        e.preventDefault();

        // 2. Proteção: se o nome estiver vazio ou se já estiver salvando, NÃO FAZ NADA!
        if (!nome.trim() || salvando) return;

        setSalvando(true);

        try {
            const resposta = await fetch('http://localhost:8080/herois', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ nome, classe }),
            });

            // 3. Proteção: se o Spring Boot devolver erro (400, 500), aborte! Não desenhe card fantasma!
            if (!resposta.ok) {
                alert('Erro ao cadastrar herói na API!');
                return;
            }

            const heroiCriado = await resposta.json();
            onHeroiCriado(heroiCriado);
            setNome('');

        } catch (erro) {
            console.error('Falha de rede:', erro);
        } finally {
            // 4. Libera o botão novamente
            setSalvando(false);
        }
    };

    return (
        <Box p="5" borderWidth="1px" borderRadius="md" bg="white" shadow="sm" mb="6">
            <Heading size="md" mb="4" color="blue.600">
                🛡️ Recrutar Novo Herói
            </Heading>

            <form onSubmit={handleSubmit} style={{ display: 'flex', gap: '12px', alignItems: 'center' }}>
                <Input
                    placeholder="Nome do Herói"
                    value={nome}
                    onChange={(e) => setNome(e.target.value)}
                    required
                    style={{ flex: 1 }}
                />

                <select
                    value={classe}
                    onChange={(e) => setClasse(e.target.value)}
                    style={{ padding: '8px 12px', borderRadius: '6px', border: '1px solid #cbd5e0', background: 'white' }}
                >
                    <option value="Guerreiro">Guerreiro</option>
                    <option value="Mago">Mago</option>
                    <option value="Arqueiro">Arqueiro</option>
                    <option value="Paladino">Paladino</option>
                    <option value="Ladino">Ladino</option>
                </select>

                {/* Enquanto estiver salvando, o botão fica desabilitado! */}
                <Button type="submit" colorPalette="blue" disabled={salvando}>
                    {salvando ? 'Salvando...' : 'Cadastrar Herói'}
                </Button>
            </form>
        </Box>
    );
}

export default HeroiForm;