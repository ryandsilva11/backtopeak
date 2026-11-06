import { ChakraProvider, defaultSystem, Theme } from '@chakra-ui/react';

export function Provider(props) {
    return (
        <ChakraProvider value={defaultSystem}>
            <ThemeProvider attribute="class" disableTransitionOnChange>
                {props.children}
            </ThemeProvider>
        </ChakraProvider>
    )
}