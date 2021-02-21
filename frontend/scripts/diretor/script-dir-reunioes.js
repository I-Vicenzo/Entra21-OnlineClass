// Pegando id do usuário que logou 
var idUsuario = sessionStorage.getItem("idUsuario");
var userEmail;
$('.alert').hide();
//Verifica se o idUsuario é válido 
if(idUsuario != 0 && idUsuario != null){
    //Busca dos dados do usuário
    var xhr = new XMLHttpRequest(); 

        xhr.open("GET", "http://localhost:8080/usuarios/"+idUsuario);

        xhr.addEventListener("load", function(){
            var resposta = xhr.responseText; 
            dadosUsuario = JSON.parse(resposta);
            userEmail = dadosUsuario.email;
            //Adiciona o nome
            document.getElementById("idNomeUsuario").textContent = dadosUsuario.nome;
            $("#idDestinatario").val(dadosUsuario.nome).prop("disabled", true);
            var dataAgora = new Date();
            var dia  = String(dataAgora.getDate()).padStart(2, '0');
            var mes  = String(dataAgora.getMonth() + 1).padStart(2, '0');
            var ano  = dataAgora.getFullYear();
            var hora = String(dataAgora.getHours()).padStart(2, '0');
            var min  = String(dataAgora.getMinutes()).padStart(2, '0');;
            var dataAgora = ano+'-'+mes+'-'+dia+'T'+hora+':'+min;
            $("#idDateTime").attr("min",dataAgora);
            //Adiciona a foto de perfil do usuario
            var img = document.querySelector("#idFotoPerfil");
            if(dadosUsuario.fotoUsuario != null){
                img.setAttribute('src', "/imagens-usuarios/"+dadosUsuario.fotoUsuario);
                img.style.borderRadius = "80%";
            }
        })

    xhr.send();
    
}else{
    alert('Sessão expirada - Erro (0002)')
    window.location = "/frontend/";
}
var convidados = [];
async function criarReuniao(){
    var data;
    var nome;
    var valido = true;
    if ($("#idDateTime").val()=="") {
        valido = false;
        $('#erroData').text('Insira uma data').show(300);
        setTimeout(function(){$('#erroData').hide(300)},1500);
    }
    if($("#idNomeReu").val()==""){
        valido = false;
        $('#erroNome').text('Insira um nome').show(300);
        setTimeout(function(){$('#erroNome').hide(300)},1500);
    }
    if (convidados.length==0) {
        valido = false;
        $('#erroConvidado').text('Insira um participante').show(300);
        setTimeout(function(){$('#erroConvidado').hide(300)},1500);
    }
    if (valido) {
        data = new Date($("#idDateTime").val());
        nome = $('#idNomeReu').val();

        
        var sala = {
            nome:"Reuniao: "+nome,
            descricao: "",
            situacaoAcesso: true,
            tipoSala: true,
            link: nome.replace(" ","_")
        }
        sala = JSON.stringify(sala);
        sala = await usarApi('POST','http://localhost:8080/salas/return',sala);
        sala = JSON.parse(sala);
        var reuniao = {
            descricao : nome,
            dataInicio : data,
            dono: idUsuario,
            fk_sala: sala.idSala
        };
        reuniao = JSON.stringify(reuniao);
        console.log(reuniao);
        var idReuniao = await usarApi('POST','http://localhost:8080/reunioes/personalizada/return',reuniao);

        $('#reuniaoCriada').text('Reunião criada com sucesso').show(300);
        for (var i = 0; i < convidados.length; i++) {
            const element = convidados[i];
            console.log(element);
            var convite = {
                fk_reuniao: idReuniao,
                fk_usuario: element.idUsuario
            }
            convite = JSON.stringify(convite);
            usarApi('POST','http://localhost:8080/reuniaousuarios',convite);
            
        }
    }
    
}

$('#btnCriarReuniao').click(criarReuniao);
$('#btnAddConvite').click(convidar);
$('#inConvidado').bind("enterKey",convidar);

async function convidar(){
    var email = $('#inConvidado').val();
    var form = $('form');
    if(!form[0].checkValidity()) {
        $('<input type="submit">').hide().appendTo(form).click().remove();
    }else{
        if (email=='') {
            $('#erroEmail').text('Insira um email').show(300);
            setTimeout(function(){$('#erroEmail').hide(300)},1500);
        }else if (email==userEmail) {
            $('#erroEmail').text('Não insira o seu email').show(300);
            setTimeout(function(){$('#erroEmail').hide(300)},1500);
        }else{
            var user = JSON.parse(await usarApi('GET','http://localhost:8080/usuarios/cosnultaremail/'+email));
            if (user.idUsuario==0) {
                $('#erroEmail').text('usuario nao existe').show(300);
                setTimeout(function(){$('#erroEmail').hide(300)},1500)
            }else{
                var jaExiste = false;
                for (var i = 0; i < convidados.length; i++) {
                    const convidado = convidados[i];
                    if (convidado.email == user.email) {
                        jaExiste = true;
                    }
                }
                if (!jaExiste) {
                    convidados.push(user)
                    $('#tbConvidados').append('<tr><th><img src="/imagens-usuarios/'+user.fotoUsuario+'" alt="" width="32" style="border-radius: 80%;">'+user.email+'</th></tr>')

                    $('#emailCerto').text('Email adicionado com sucesso').show(300);
                    setTimeout(function(){$('#emailCerto').hide(300)},1500);
                }else{
                    $('#erroEmail').text('Email já inserido').show(300);
                    setTimeout(function(){$('#erroEmail').hide(300)},1500);
                }
            }
        }
    }
}

$('#inConvidado').keyup(function(e){
    if(e.keyCode == 13)
    {
        $(this).trigger("enterKey");
    }
});

//Evento de abertura do menu 
document.getElementById("mostrar").addEventListener("mouseover", function(){
    abrirMenu();
})
document.getElementById("idImgMenu").addEventListener("mouseover", function(){
    abrirMenu();
})

//Abertura do menu
function abrirMenu(){
    document.getElementById("menu").style.display = "block";
}

//Evento de fechamento do menu 
document.getElementById("menu").addEventListener("mouseleave", function(){
    document.getElementById("menu").style.display = "none";
})