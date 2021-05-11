const fetch = require("node-fetch");

async function getRedditTokens(url) {
    const promesa = fetch(url);
    const respuesta = await promesa;
    const myJson = await respuesta.json();
    const posts = myJson.data.children;
    const mapa = {};
    posts.forEach(post => post.data.title.split(' ').forEach(token => {var cadena = token.trim().toLowerCase(); mapa[cadena] = mapa[cadena] ? (mapa[cadena] + 1) : 1}));
    const arreglo = [];
    Object.keys(mapa).forEach(token => arreglo.push({palabra: token, conteo: mapa[token]}));
    arreglo.sort((objeto1, objeto2) => objeto2.conteo - objeto1.conteo);
    console.log(arreglo);
}

// '/r/AskRedit'
const urlParam = process.argv[2];
getRedditTokens(`https://www.reddit.com${urlParam ? urlParam :  ''}/hot.json`);