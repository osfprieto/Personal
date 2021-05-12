const fetch = require("node-fetch");

function cleanUpToken(token){
    let cleanedUp = token.trim().toLowerCase();
    console.log(cleanedUp);
    cleanedUp = cleanedUp.replaceAll(/[^a-z]/g, ''); // Not sure why replaceAll is not working. This works on the browser.
    return cleanedUp;
}

async function getRedditTokens(url) {
    const promesa = fetch(url);
    const respuesta = await promesa;
    const myJson = await respuesta.json();
    const posts = myJson.data.children;
    const mapa = {};
    posts.forEach(post => {
        // console.log(post);
        // To get the post content:
        // fetch https://www.reddit.com/ + post.data.permalink
        // respuesta.data.children[0].selftext has the description.
        post.data.title.split(' ').forEach(token => {
            const cadena = cleanUpToken(token); 
            mapa[cadena] = mapa[cadena] ? (mapa[cadena] + 1) : 1;
        });
    });
    const arreglo = [];
    Object.keys(mapa).forEach(token => arreglo.push({palabra: token, conteo: mapa[token]}));
    arreglo.sort((objeto1, objeto2) => objeto2.conteo - objeto1.conteo);
    console.log(arreglo);
}

// '/r/AskRedit'
const urlParam = process.argv[2];
getRedditTokens(`https://www.reddit.com${urlParam ? urlParam :  ''}/hot.json`);
