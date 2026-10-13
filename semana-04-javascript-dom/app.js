const botao = document.querySelector("#btn-carregar");
const container = document.querySelector(".cards-container");

botao.addEventListener('click', async () => {
    botao.innerText = 'Carregando aventureiros...'

    const resposta = await
        fetch('https://dummyjson.com/users?limit=4');
    const dados = await resposta.json();

    container.innerHTML = '';
    dados.users.forEach((usuario) => {
        container.innerHTML += `
            <article class="hero-card">
                <h2>${usuario.firstName} ${usuario.lastName}</h2>
                <span class="badge guerreiro">${usuario.role || 'Aventureiro'}</span>
                <p>Idade: ${usuario.age} | Altura: ${usuario.height}cm</p>
                <p class="guilda">E-mail: ${usuario.email}</p>
            </article>
        `;
    });
    botao.innerText = 'Aventureiros Carregados! ✅';
})
