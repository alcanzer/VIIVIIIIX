onmousemove = (e) => setXY(e.clientX, e.clientY);

var globalX, globalY, session = Math.floor(Math.random() * 100000 + Date.now());

setInterval(demoFunc, 1000)
function demoFunc() {
    var obj = {
        session: session,
        link: window.location.href,
        width: window.innerWidth,
        height: window.innerHeight,
        xAxis: globalX,
        yAxis: globalY,
        timestamp: Date.now()

    }
    var url = 'http://localhost:8080/events'
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("POST", url, false);
    xmlHttp.setRequestHeader('Content-Type', 'application/json')
    xmlHttp.send(JSON.stringify(obj))

    console.log(obj)
}

function setXY(x, y){
    globalX = x
    globalY = y
}