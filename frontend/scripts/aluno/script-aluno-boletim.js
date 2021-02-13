
//Evento para gerar boletim 
document.getElementById("gerar-boletim").addEventListener("click", function(){
    gerarBoletim();
})

async function gerarBoletim() {

    var lista = document.getElementById("tabela-notas-boletim");
    $("#tabela-notas-boletim").empty();

    //Primeira parte da tabela - dados importantes

    //Busca os dados do usuario
    var resposta = await usarApi("GET", "http://localhost:8080/api/usuario/"+idUsuario);
    var usuario = JSON.parse(resposta);

    //Busca os dados da escola 
    var resposta = await usarApi("GET", "http://localhost:8080/api/escola/"+usuario.fk_escola);
    var escola = JSON.parse(resposta);

    //Busca os dados da escola 
    var resposta = await usarApi("GET", "http://localhost:8080/api/aluno/usuario/"+idUsuario);
    var aluno = JSON.parse(resposta);

    //Busca os dados da turma
    var resposta = await usarApi("GET", "http://localhost:8080/api/turma/"+aluno.fk_turma);
    var turma = JSON.parse(resposta);

    now = new Date();

    document.getElementById("nome-escola").textContent = escola.nome;
    document.getElementById("turma").textContent = turma.ano;
    document.getElementById("ano").textContent = now.getFullYear();

    //Segunda parte da tabela - periodos 

    //Busca os periodos de avaliacao
    var resposta = await usarApi("GET", "http://localhost:8080/api/periodosAvaliacoes/"+fk_escola);
    var periodosBuscados = JSON.parse(resposta);
    var tipoPeriodo;

    if(periodosBuscados.length == 2){
        tipoPeriodo = 2;
    }else if(periodosBuscados.length == 3){
        tipoPeriodo = 3;
    }else if(periodosBuscados.length == 4){
        tipoPeriodo = 4;
    }else{
        tipoPeriodo = 0;
    }

    var linha = document.createElement("tr");

    var media = document.createElement("th");
    media.textContent = "Matéria";
    linha.append(media)

    for (let i = 0; i < tipoPeriodo; i++) {
        const element = periodosBuscados[i];

        var periodo = document.createElement("th");
        periodo.textContent = element.descricao;
        linha.append(periodo)
    }

    if(tipoPeriodo == 0){
        var periodo = document.createElement("th");
        periodo.textContent = "Período único";
        linha.append(periodo)
    }

    var media = document.createElement("th");
    media.textContent = "Média";
    linha.append(media)
    lista.append(linha);

    //Terceira parte da tabela - notas e materias 
    
    //Busca os as notas do aluno
    var resposta = await usarApi("GET", "http://localhost:8080/api/notas/"+idUsuario);
    var notas = JSON.parse(resposta);

    //Pegar as materias do aluno
    var resposta = await usarApi("GET", "http://localhost:8080/api/disciplinas/turmas/aluno/"+aluno.idAluno);
    var disciplinasAluno = JSON.parse(resposta);

    var medias = [];
    var quantidadeMed = 0;
    //Percorre o vetor de diciplinas uma a uma 
    for (let i = 0; i < disciplinasAluno.length; i++) {
        const disc = disciplinasAluno[i];
        var vetorExibicao = [];
        
        //Verifica se o periodo foi definido 
        if(tipoPeriodo != 0){

            //Percorre periodo por periodo para adicionar a coluna com as medias do periodo 
            for (let j = 0; j < periodosBuscados.length; j++) {
                const per = periodosBuscados[j];

                var dataInicioPeriodo = new Date(per.dataInicioPeriodo);
                var dataFinalPeriodo = new Date(per.dataFinalPeriodo);
                
                //Verifica se a nota é da mesma materia e periodo 
                for (let x = 0; x < notas.length; x++) {
                    const not = notas[x];

                    var dataNota = not.dataNota;
                    
                    if(not.materia == disc.nome && (dataNota >= dataInicioPeriodo && dataNota <= dataFinalPeriodo)){
                        vetorExibicao.push(not.nota);
                    }
                }

                //Boletim com periodos 
            }

        }else{
            //Verifica se a nota é da mesma materia 
            for (let x = 0; x < notas.length; x++) {
                const not = notas[x];
                console.log(not)
                if(not.materia == disc.nome){
                    vetorExibicao.push(not.nota);
                }
            }

            //Verifica se há alguma nota 
            var media;
            if(vetorExibicao.length > 0){
                //Calcula a media das notas da materia 
                var soma = 0;
                var quantidade = vetorExibicao.length;
                for (let x = 0; x < vetorExibicao.length; x++) {
                    const exib = vetorExibicao[x];
                    
                    //Atribui uma a uma a nota na soma 
                    soma = soma + exib;
                }

                media = soma/quantidade; 
                medias.push(media);
                quantidadeMed = quantidadeMed + 1;
                
            }else{
                media = "-";
            }

            //Adiciona a media na tabela de boletim
            var linhaMedia = document.createElement("tr");
            var colunaMateria = document.createElement("td");
            colunaMateria.textContent = disc.nome;
            var colunaMedia = document.createElement("td");
            colunaMedia.textContent = media;
            var colunaMediaFinal = document.createElement("td")
            colunaMediaFinal.textContent = media;

            linhaMedia.append(colunaMateria);
            linhaMedia.append(colunaMedia);
            linhaMedia.append(colunaMediaFinal);
            lista.append(linhaMedia);
        }
    }

    //Adiciona a media final e a situacao 
    var linhaFinal = document.createElement("tr");
    var colunaResultado = document.createElement("td");
    var colunaAprovacao = document.createElement("td");
    var colunaTotal = document.createElement("td");
    
    //Verifica se o ano acabou 
    dataFinalLetivo = new Date (escola.dataFinalLetivo);
    now = new Date()

    var mediaFinalTotal;
    colunaAprovacao.textContent = "Situação Final: Aprovado";

    //Verifica se o aluno passou 
    if(dataFinalLetivo <= now){
        var somaMedia = 0;
        for (let m = 0; m < medias.length; m++) {
            const med = medias[m];
            somaMedia = somaMedia + med;

            if(med < 6){
                colunaAprovacao.textContent = "Situação Final: Reprovado";
            }
        } 

        //Calcula a média final para mostrar 
        mediaFinalTotal = somaMedia/quantidadeMed;

    }else{
        colunaAprovacao.textContent = "Situação Final: -";
        mediaFinalTotal = "-";
    }
    colunaTotal.textContent = "Média Final: "+mediaFinalTotal;
    colunaResultado.textContent = "Resultado: ";
    linhaFinal.append(colunaResultado);
    linhaFinal.append(colunaAprovacao);
    linhaFinal.append(colunaTotal);
    lista.append(linhaFinal)

    var meuBoletim = document.getElementById('idBoletim').innerHTML;
    var style = "<style>";
    style = style + "tr, th, td{    padding: 15px;}";
    style = style + "tr, th, td{     text-transform: uppercase;}";
    style = style + "tr, th, td{    border-top: 1px solid #999;}";
    style = style + "tr, th, td{    border-bottom: 1px solid #111;}";
    style = style + "tr, th, td{    border-right: 1px solid #999;}";
    style = style + "tr, th, td{    border-left: 1px solid #111;}";
    style = style + "tr, th, td{    text-align: left;}";
    style = style + "tr, th, td{    font-size: 100%;}";
    style = style + "tr, th, td{    font-family: cursive;}";
    style = style + "tr, th, td{    letter-spacing: 0.2em;}";
    style = style + "tr, th, td{    width: 100vh;}";
    style = style + "</style>";

    var win = window.open('', '', 'height=700,width=700');
    win.document.write('<html><head>');
    win.document.write('<title>Boletim</title>');   
    win.document.write(style);                                    
    win.document.write('</head>');
    win.document.write('<body>');
    win.document.write(meuBoletim);                         
    win.document.write('</body></html>');
    win.document.close(); 	                                        
    win.print();                                                           
}