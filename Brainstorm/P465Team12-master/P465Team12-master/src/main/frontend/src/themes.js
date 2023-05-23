import { createGlobalStyle } from "styled-components";

export const lightTheme ={
    body: '#6c89fd',
    fontColor: '#000',
    backgroundColor: '#6c89fd'
};
export const darkTheme = {
    body: '#060b26',
    fontColor: '#000',
    backgroundColor: '#060b26'
};

export const GlobalStyles = createGlobalStyle`
body {
    background-color: ${(props) => props.theme.body};
}
`;