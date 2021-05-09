const fetch = require("node-fetch");

async function getRedditTokens(url) {
    var promesa = fetch(url);
    var respuesta = await promesa;
    var myJson = await respuesta.json();
    var posts = myJson.data.children;
    var mapa = {};
    posts.forEach(post => post.data.title.split(' ').forEach(token => {var cadena = token.trim().toLowerCase(); mapa[cadena] = mapa[cadena] ? (mapa[cadena] + 1) : 1}));
    var arreglo = [];
    Object.keys(mapa).forEach(token => arreglo.push({palabra: token, conteo: mapa[token]}));
    arreglo.sort((objeto1, objeto2) => objeto2.conteo - objeto1.conteo);
    console.log(arreglo);
}

getRedditTokens('https://www.reddit.com/r/AskReddit/hot.json');