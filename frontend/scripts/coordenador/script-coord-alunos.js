// Pegando id do usuário que logou 
var idUsuario = sessionStorage.getItem("idUsuario");
// Pegando o id da escola que o usuário logou
var fk_escola = sessionStorage.getItem('escolaUsuario');
carregarSelect();

//Verifica se o idUsuario é válido 
if(idUsuario != 0 && idUsuario != null){
    //Busca dos dados do usuário
    var xhr = new XMLHttpRequest(); 

        xhr.open("GET", "http://localhost:8080/api/usuario/"+idUsuario);

        xhr.addEventListener("load", function(){
            var resposta = xhr.responseText; 
            var dadosUsuario = JSON.parse(resposta);
            //Adiciona o nome 
            document.getElementById("idNomeUsuario").textContent = dadosUsuario.nome +" "+dadosUsuario.sobrenome;
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
    window.location = "/frontend/index.html";
}

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


//Mascara dos inputs 
$("#inputTelefone").mask("(00) 0000-0000");
$("#inputCelular").mask("(00) 00000-0000");
$("#inputCpf").mask("000.000.000-00");
$("#inputCep").mask("00000-000");

//Preenchimento de CEP
document.getElementById("inputCep").addEventListener("blur",function(){
    var cep = document.getElementById("inputCep").value;
    cep = cep.replace("-","");
    buscarCep(cep);
})

//Busca o cep altomaticamente
async function buscarCep(cep){
    var resposta = await usarApi("GET", "http://viacep.com.br/ws/"+cep+"/json/");
    var dadosLocalizacao =  JSON.parse(resposta);
    if(resposta != null){
        document.getElementById("inputEstado").value = dadosLocalizacao.uf;
        document.getElementById("inputCidade").value = dadosLocalizacao.localidade;
        document.getElementById("inputBairro").value = dadosLocalizacao.bairro;
        document.getElementById("inputLogradouro").value = dadosLocalizacao.logradouro;
    }else{
        alert("CEP inválido!")
    }
}

//Método onclick botão de cadastrar
var btnCadastrar =  document.getElementById('btnCadastrar');
btnCadastrar.addEventListener("click", function() {
    cadastrar();
})

//Método para cadastrar
async function cadastrar() {
    //Verifica se os campos foram preenchidos 
    //Dados Usuario
    var nome = document.getElementById('inputNome').value;
    var sobrenome = document.getElementById('inputSobrenome').value;
    var telefone = document.getElementById('inputTelefone').value;
    var celular = document.getElementById('inputCelular').value;
    var cpf = document.getElementById('inputCpf').value;
    var horarioInicial = new Date(document.getElementById('inputHorarioInicial').valueAsDate);
    var horarioFinal = new Date(document.getElementById('inputHorarioFinal').valueAsDate);
    var email = document.getElementById('inputEmail').value;
    var senha = document.getElementById('inputSenha').value;
    var confirmarSenha = document.getElementById('inputConfirmSenha').value;
    
    //Dados Endereço
    var estado = $("#inputEstado :selected").val();
    var cidade = document.getElementById('inputCidade').value;
    var bairro = document.getElementById('inputBairro').value;
    var cep = document.getElementById('inputCep').value;
    var logradouro = document.getElementById('inputLogradouro').value;
    var numero = Number(document.getElementById('inputNumero').value);
    var complemento = document.getElementById('inputTipoLogradouro').value;

    //Dados Aluno
    var ra = document.getElementById('inputRa').value;
    var matricula = document.getElementById('inputMatricula').value;
    var deficiencia = $('input[name="nmRadio"]:checked').val();
    var nomeMae = document.getElementById('inputNomeMae').value;
    var nomePai = document.getElementById('inputnomePai').value;
    var nomeResponsavel = document.getElementById('inputNomeResponsavel').value;

    var selectTurma = $('#SelectTurma :selected').val();
    if(nome == '' || sobrenome == '' || telefone == '' || celular == '' || cpf == '' || horarioInicial == '' || email == '' || senha == '' || confirmarSenha == '' ||
    cidade == '' || bairro == '' || cep == '' || logradouro == '' || numero == '' || ra == '' || matricula == '' || nomeMae == '' || nomePai == '' || selectTurma == 'default') {
        alert("Preencha todos os campos!")
    }else{
        //Valida a senha
        if (senha != confirmarSenha) {
            alert("As senhas não coincidem!")
        }else{

            //Verifica o email se já está sendo usado 
            var resp = await usarApi("GET", "http://localhost:8080/api/verificar/"+ email);
            var isExisteEmail = JSON.parse(resp);

            if(!isExisteEmail){

                //Verifica se o cpf é válido
                cpfValida = cpf.replace(".", "");
                cpfValida = cpfValida.replace(".", "");
                cpfValida = cpfValida.replace("-", "");
                var isValido = TestaCPF(cpfValida);


                if(isValido){

                    //Verifica se o cpf já está sendo usado  
                    var resp = await usarApi("GET", "http://localhost:8080/api/verificar/cpf/"+ cpf);
                    var isExisteCpf =  JSON.parse(resp);

                    if(!isExisteCpf){

                        //Verifica se o horário é válido ou não 
                        if(horarioInicial < horarioFinal){
                            
                            //Cria o objeto Endereço
                            var inserirEndereco = {
                                estado: estado,
                                cidade: cidade,
                                bairro: bairro,
                                rua: logradouro,
                                numero: numero,
                                cep: cep,
                                complemento: complemento
                            }

                            //Converte o endereço para JSON
                            var enderecoJson =  JSON.stringify(inserirEndereco);

                            //Chamada da api para registrar o Endereço no banco de dados
                            var insertEndereco = await usarApi("POST", "http://localhost:8080/api/endereco/inserir/return/"+enderecoJson);
                            var idEndereco =  JSON.parse(insertEndereco);

                            if(idEndereco != 0){

                                //Cria o objeto Usuario
                                var inserirUsuario = {
                                    nome: nome,
                                    sobrenome: sobrenome,
                                    cpf: cpf,
                                    telefone: telefone,
                                    celular: celular,
                                    tipoUsuario: 5,
                                    email: email,
                                    senha: senha,
                                    horarioFinalExpediente: horarioFinal,
                                    horarioInicioExpediente: horarioInicial,
                                    fotoUsuario: null,
                                    fk_endereco: idEndereco,
                                    fk_escola: fk_escola
                                }

                                //Converte o coordenador para JSON
                                var UsuarioJson = JSON.stringify(inserirUsuario);

                                //Chamada da api para registrar o Coordenador no banco de dados
                                var insertUsuario = await usarApi("POST", "http://localhost:8080/api/usuario/inserir/return/"+UsuarioJson);
                                var idUsuarioJson = JSON.parse(insertUsuario);
                                if (!insertUsuario || !insertEndereco) {
                                    alert("Ocorreu um erro no cadastro do usuario!")
                                } else {
                                    var idTurma = Number($('#SelectTurma :selected').val());
                                  //Cria o objeto Aluno
                                    var inserirAluno = {
                                      ra: ra,
                                      matricula: matricula,
                                      deficiencia: deficiencia,
                                      nomeMae: nomeMae,
                                      nomePai: nomePai,
                                      nomeResponsavel: nomeResponsavel,
                                      situacaoAnoLetivo: null,
                                      fk_usuario: idUsuarioJson,
                                      fk_turma: idTurma
                                    }

                                    var alunoJson = JSON.stringify(inserirAluno);
                                    var insertAluno = await usarApi("POST", "http://localhost:8080/api/aluno/inserir/"+alunoJson);
                                }
                                if (inserirAluno != false) {
                                  alert("Aluno cadastrado com sucesso.");
                                    document.getElementById("formulario").reset();

                                } else {
                                  alert("Ocorreu um erro no cadastro do aluno!");
                                }
                            }else{
                                alert("Erro ao cadastrar!")
                            }

                        }else{
                            alert("Hora inicial não pode ser maior ou igual ao horario final");
                        }
                    }else{
                        alert("CPF já cadastrado no sistema!")
                    }
                }else{
                    alert("CPF inválido!")
                }
            }else{
                alert("E-mail já cadastrado no sistema!");
            }
        }
    } 
}

//Método para carregar o select com as turmas existentes
async function carregarSelect() {
  //Chama a api e retorna um arrays com as turmas pertencentes à escola
  var resposta = await usarApi("GET", "http://localhost:8080/api/turmas/escola/"+fk_escola);
  var turmas = JSON.parse(resposta);
  var select = document.getElementById('SelectTurma');

  //Cria os options do select
  for (let index = 0; index < turmas.length; index++) {
      
      var option = document.createElement('option')
      option.textContent = turmas[index].ano;
      option.value = turmas[index].idTurma;
      option.classList.add('optionTurmas')

      select.append(option);
  }
}
