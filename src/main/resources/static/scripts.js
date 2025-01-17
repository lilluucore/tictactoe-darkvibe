let messages = [
    "がんばって！", "アニメクレベティックナリ！", "残念！", "もっと頑張れ！", "次は勝てるぞ！",
    "何をやっているんだ", "もう一度挑戦してみろ！", "次こそは勝つんだ！", "まだまだだな！", "もっと集中しろ！",
    "勉強するでいるんだ", "もっと努力しろ", "次こそは勝つんだ！", "まだまだだな！", "もっと集中しろ！",
    "お前の負けだ！", "もっと頑張れよ！", "次こそは勝つんだ！", "まだまだだな！", "もっと集中しろ！",
    "もう一度挑戦してみろ！", "何をやっているんだ", "残念！", "がんばって！", "アニメクレベティックナリ！",
    "勉強するでいるんだ", "もっと努力しろ", "次こそは勝つんだ！", "まだまだだな！", "もっと集中しろ！"
];
let loseMessages = [
    "勉強するでいるんだ", "もっと努力しろ", "次こそは勝つんだ！", "まだまだだな！", "もっと集中しろ！",
    "もう一度挑戦してみろ！", "何をやっているんだ", "残念！", "がんばって！", "アニメクレベティックナリ！",
    "お前の負けだ！", "もっと頑張れよ！", "次こそは勝つんだ！", "まだまだだな！", "もっと集中しろ！",
    "もう一度挑戦してみろ！", "何をやっているんだ", "残念！", "がんばって！", "アニメクレベティックナリ！",
    "勉強するでいるんだ", "もっと努力しろ", "次こそは勝つんだ！", "まだまだだな！", "もっと集中しろ！"
];
let quotes = [
    "努力は必ず報われる。", "成功は努力の結果である。", "努力は必ず実を結ぶ。", "努力は必ず報われる。", "努力は必ず実を結ぶ。",
    "成功は努力の結果である。", "努力は必ず実を結ぶ。", "努力は必ず報われる。", "努力は必ず実を結ぶ。", "努力は必ず報われる。",
    "成功は努力の結果である。", "努力は必ず実を結ぶ。", "努力は必ず報われる。", "努力は必ず実を結ぶ。", "努力は必ず報われる。"
];
let currentMessageIndex = 0;
let currentQuoteIndex = 0;
let messageElement = document.getElementById('message');
let quoteElement = document.getElementById('quote');
let asciiMessageElement = document.getElementById('asciiMessage');

function changeMessage(messagesArray) {
    let currentMessage = messageElement.textContent;
    let newMessage = messagesArray[currentMessageIndex];
    let i = 0;


    let eraseInterval = setInterval(() => {
        if (i < currentMessage.length) {
            messageElement.textContent = currentMessage.substring(0, currentMessage.length - i - 1);
        } else {
            clearInterval(eraseInterval);
            let typeInterval = setInterval(() => {
                if (i < newMessage.length) {
                    messageElement.textContent += newMessage.charAt(i);
                } else {
                    clearInterval(typeInterval);
                }
                i++;
            }, 100);
        }
        i++;
    }, 100);

    currentMessageIndex = (currentMessageIndex + 1) % messagesArray.length;
}

function changeQuote() {
    let currentQuote = quoteElement.textContent;
    let newQuote = quotes[currentQuoteIndex];
    let i = 0;


    let eraseInterval = setInterval(() => {
        if (i < currentQuote.length) {
            quoteElement.textContent = currentQuote.substring(0, currentQuote.length - i - 1);
        } else {
            clearInterval(eraseInterval);

            let typeInterval = setInterval(() => {
                if (i < newQuote.length) {
                    quoteElement.textContent += newQuote.charAt(i);
                } else {
                    clearInterval(typeInterval);
                }
                i++;
            }, 100);
        }
        i++;
    }, 100);

    currentQuoteIndex = (currentQuoteIndex + 1) % quotes.length;
}

function changeAsciiMessage(messagesArray) {
    let currentMessage = asciiMessageElement.textContent;
    let newMessage = messagesArray[currentMessageIndex];
    let i = 0;


    let eraseInterval = setInterval(() => {
        if (i < currentMessage.length) {
            asciiMessageElement.textContent = currentMessage.substring(0, currentMessage.length - i - 1);
        } else {
            clearInterval(eraseInterval);
            let typeInterval = setInterval(() => {
                if (i < newMessage.length) {
                    asciiMessageElement.textContent += newMessage.charAt(i);
                } else {
                    clearInterval(typeInterval);
                }
                i++;
            }, 100);
        }
        i++;
    }, 100);

    currentMessageIndex = (currentMessageIndex + 1) % messagesArray.length;
}

function updateHearts(lives) {
    let heartsElement = document.getElementById('hearts');
    heartsElement.textContent = '❤'.repeat(lives);
    if (lives < 3) {
        heartsElement.classList.add('grayscale');
    } else {
        heartsElement.classList.remove('grayscale');
    }
}

function updateAsciiArt(art) {
    let asciiArtElement = document.getElementById('asciiArt');
    asciiArtElement.textContent = art;
}

function makeMove(row, col) {
    fetch('/move', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: new URLSearchParams({
            row: row,
            col: col
        })
    })
    .then(response => response.text())
    .then(html => {
        let parser = new DOMParser();
        let doc = parser.parseFromString(html, 'text/html');
        let lives = doc.querySelector('#hearts').textContent.length;
        let message = doc.querySelector('#message').textContent;
        let asciiArt = doc.querySelector('#asciiArt').textContent;
        let asciiMessage = doc.querySelector('#asciiMessage').textContent;
        updateHearts(lives);
        updateAsciiArt(asciiArt);
        messageElement.textContent = message;
        asciiMessageElement.textContent = asciiMessage;
        document.open();
        document.write(html);
        document.close();

        if (lives < 3) {
            setInterval(() => changeMessage(loseMessages), 10000);
            setInterval(() => changeAsciiMessage(loseMessages), 10000);
        } else {
            setInterval(() => changeMessage(messages), 10000);
            setInterval(() => changeAsciiMessage(messages), 10000);
        }

        if (lives === 0) {
            setInterval(changeQuote, 20000);
        }
    });
}


setInterval(() => changeAsciiMessage(messages), 10000);
setInterval(changeQuote, 20000);
