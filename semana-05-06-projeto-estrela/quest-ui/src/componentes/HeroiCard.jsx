import { Box, Heading, Text, Badge, VStack } from '@chakra-ui/react';

function HeroiCard({ heroi }) {
    return (
        <Box
            p="5"
            borderWidth="1px"
            borderRadius="md"
            bg="white"
            shadow="sm"
            _hover={{ shadow: 'md', transform: 'translateY(-2px)' }}
        >
            <VStack align="start" gap="2">
                <Heading size="md" color="blue.600">
                    {heroi.nome}
                </Heading>

                <Text fontSize="sm" color="gray.600">
                    <Badge colorPalette="teal" variant="subtle">
                        {heroi.classe}
                    </Badge>
                </Text>
                <Text fontSize="sm" color="gray.600">
                    <strong>Nível:</strong> {heroi.nivel}
                </Text>
                <Text fontSize="sm" color="gray.600">
                    <strong>XP:</strong> {heroi.xp}
                </Text>
            </VStack>
        </Box>
    )
}

export default HeroiCard; 
