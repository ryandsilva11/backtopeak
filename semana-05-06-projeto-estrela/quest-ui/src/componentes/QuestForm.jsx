import { useState } from "react";
import { Box, Heading, Input, Button } from '@chakra-ui/react'

function QuestForm({ onQuestCriada }) {

    const [titulo, setTitulo] = useState('');
    const [xp, setXp] = useState(0);
    const [loot, setLoot] = useState('');
    const [lootQnt, setLootQnt] = useState(0);
    const [salvando, setSalvando] = useState(false);

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (!titulo.trim() || salvando) return;

        setSalvando(true);

        const payload = {
            titulo: titulo,
            xp: Number(xp),
            loot: loot.trim() ? loot.trim() : null,
            lootQnt: loot.trim() && lootQnt > 0 ? Number(lootQnt) : null
        };

        try {
            const resposta = await fetch('http://localhost:8080/quests', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(payload)
            });

            if (!resposta.ok) {
                alert('Erro ao cadastrar quest na API');
                return;
            }

            const novaQuest = await resposta.json();
            onQuestCriada(novaQuest);

            setTitulo('');
            setXp(0);
            setLoot('');
            setLootQnt(0);
        }

        catch (erro) {
            console.error('Falha de rede:', erro);
        }

        finally {
            setSalvando(false);
        }
    }

    return (
        <Box p="5" borderWidth="1px" borderRadius="md" bg="white" shadow="sm" mb="6">
            <Heading size="md" mb="4" color="purple.600">
                📜 Criar Nova Quest
            </Heading>

            <form onSubmit={handleSubmit} style={{ display: 'flex', gap: '12px', alignItems: 'center', flexWrap: 'wrap' }}>
                <Input
                    placeholder="Título da Quest"
                    value={titulo}
                    onChange={(e) => setTitulo(e.target.value)}
                    required
                    style={{ flex: 2, minWidth: '180px' }}
                />

                <Input
                    type="number"
                    placeholder="XP"
                    value={xp}
                    onChange={(e) => setXp(e.target.value)}
                    required
                    min="0"
                    style={{ flex: 1, minWidth: '80px' }}
                />

                <Input
                    placeholder="Loot (Opcional)"
                    value={loot}
                    onChange={(e) => setLoot(e.target.value)}
                    style={{ flex: 2, minWidth: '150px' }}
                />

                <Input
                    type="number"
                    placeholder="Qtd (Opcional)"
                    value={lootQnt}
                    onChange={(e) => setLootQnt(e.target.value)}
                    min="1"
                    style={{ flex: 1, minWidth: '80px' }}
                />

                <Button type="submit" colorPalette="purple" disabled={salvando}>
                    {salvando ? 'Salvando...' : 'Cadastrar Quest'}
                </Button>
            </form>
        </Box>
    );
}

export default QuestForm;