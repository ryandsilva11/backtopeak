import { Box, Heading, Text, Badge, VStack, Button, HStack } from '@chakra-ui/react';
import { useState } from 'react';

function QuestCard({ quest, herois, onConcluirQuest }) {
    // Pegamos o ID do primeiro herói disponível como padrão
    const [heroiSelecionado, setHeroiSelecionado] = useState('');
    const heroiIdParaEnviar = heroiSelecionado || (herois.length > 0 ? herois[0].id : '');

    return (
        <Box
            p="5"
            borderWidth="1px"
            borderRadius="md"
            bg={quest.concluida ? "gray.50" : "white"}
            shadow="sm"
            opacity={quest.concluida ? 0.75 : 1}
            _hover={{ shadow: 'md', transform: 'translateY(-2px)' }}
        >
            <VStack align="start" gap="2">
                <HStack justify="space-between" w="full">
                    <Heading size="md" color={quest.concluida ? "gray.600" : "purple.600"}>
                        {quest.titulo}
                    </Heading>

                    <Badge colorPalette={quest.concluida ? "green" : "yellow"} variant="subtle">
                        {quest.concluida ? "✓ Concluída" : "Pendente"}
                    </Badge>
                </HStack>

                <Text fontSize="sm" color="gray.600">
                    <strong>Recompensa:</strong> +{quest.xp} XP
                </Text>

                {quest.loot && (
                    <Text fontSize="sm" color="gray.600">
                        <strong>Loot:</strong> {quest.lootQnt}x {quest.loot}
                    </Text>
                )}

                {/* Se a quest ainda NÃO foi concluída e tem heróis na guilda */}
                {!quest.concluida && (
                    <Box pt="2" w="full">
                        {/* Se existirem heróis, mostra o menu para escolher qual herói envia para a missão */}
                        {herois.length > 0 && (
                            <select
                                value={heroiIdParaEnviar}
                                onChange={(e) => setHeroiSelecionado(e.target.value)}
                                style={{
                                    width: '100%',
                                    padding: '6px 8px',
                                    marginBottom: '8px',
                                    borderRadius: '6px',
                                    border: '1px solid #cbd5e0',
                                    fontSize: '14px',
                                    backgroundColor: 'white'
                                }}
                            >
                                {herois.map((h) => (
                                    <option key={h.id} value={h.id}>
                                        {h.nome} (Nível {h.nivel})
                                    </option>
                                ))}
                            </select>
                        )}
                        <Button
                            size="sm"
                            colorPalette="green"
                            w="full"
                            disabled={herois.length === 0}
                            onClick={() => onConcluirQuest(quest.id, heroiIdParaEnviar)}
                        >
                            {herois.length === 0 ? "Sem heróis disponíveis" : "Concluir Missão ⚔️"}
                        </Button>
                    </Box>
                )}
            </VStack>
        </Box>
    );
}

export default QuestCard;