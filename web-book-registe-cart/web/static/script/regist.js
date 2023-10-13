//如果想要发送异步请求，我们需要一个关键的对象XMLHttpRequest
var xmlHttpRequest;

function createXMLHttpRequest(){
    if (window.XMLHttpRequest){
        //符合DOM2标准的浏览器，xmlHttpRequest的创建方式
        xmlHttpRequest = new XMLHttpRequest();
    }else if(window.ActiveXObject){ //IE浏览器
        try {
            xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
        }catch (e){
            xmlHttpRequest = new ActiveXObject("Msxml2.HTTP")
        }

    }
}


function ckUname(uname){
    createXMLHttpRequest();
    //true表示是异步请求，false则表示是同步请求
    xmlHttpRequest.open("user.do?operation=ckUname&uname="+uname, "GET", true);
    //设置回调函数
    xmlHttpRequest.onreadystatechange = ckUnameCB;
    //发送请求
    xmlHttpRequest.send();
}

function ckUnameCB(){
    if (xmlHttpRequest.readyState === 4 && xmlHttpRequest.status === 200){
        //表示服务器端响应给我的文本内容
        var responseText = xmlHttpRequest.responseText;
        //{'uname' : '1'}
        if (responseText === {'uname':'1'}){
            alert("用户名已经被注册！");
        }else {
            alert("用户名可以注册!")
        }
    }
}

