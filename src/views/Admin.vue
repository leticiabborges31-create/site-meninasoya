<template>

<div class="admin-container">

<h2>Cadastro</h2>

<input v-model="nome" placeholder="Nome completo">
<input v-model="email" placeholder="Email">
<input type="password" v-model="senha" placeholder="Senha">

<button @click="cadastrar">Cadastrar</button>

<hr>

<h2>Login</h2>

<input v-model="loginEmail" placeholder="Email">
<input type="password" v-model="loginSenha" placeholder="Senha">

<button @click="login">Entrar</button>

</div>

</template>

<script>
export default {

data(){
return{
nome:'',
email:'',
senha:'',
loginEmail:'',
loginSenha:''
}
},

methods:{

cadastrar(){

const usuario={
nome:this.nome,
email:this.email,
senha:this.senha
}

localStorage.setItem("usuario",JSON.stringify(usuario))

alert("Cadastro realizado!")

},

login(){

const usuario=JSON.parse(localStorage.getItem("usuario"))

if(
usuario &&
usuario.email === this.loginEmail &&
usuario.senha === this.loginSenha
){

localStorage.setItem("logado",true)

this.$router.push("/painel")

}else{

alert("Email ou senha incorretos")

}

}

}

}
</script>