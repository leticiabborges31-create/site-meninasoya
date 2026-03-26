<template>
  <main class="secao clara">
    <h1 class="titulo-secao">Parcerias</h1>

    <div class="grid-parceiros">

      <!-- PARCEIRO -->
      <div class="card-parceiro">
        <a href="https://portal.ifma.edu.br/inicio/" target="_blank">
          <img src="@/assets/ifma.png" alt="IFMA">
          <p class="nome-parceiro">IFMA</p>
        </a>
      </div>

      <div class="card-parceiro">
        <a href="https://ufba.br/" target="_blank">
          <img src="@/assets/ufba.png" alt="UFBA">
          <p class="nome-parceiro">UFBA</p>
        </a>
      </div>

      <div class="card-parceiro">
        <a href="https://www.ufrgs.br/site/" target="_blank">
          <img src="@/assets/ufrgs.png" alt="ufrgs">
          <p class="nome-parceiro">UFRGS</p>
        </a>
      </div>

      <div class="card-parceiro">
        <a href="https://unilins.edu.br/" target="_blank">
          <img src="@/assets/unilins.png" alt="unilins">
          <p class="nome-parceiro">UNILINS</p>
        </a>
      </div>

      <div class="card-parceiro">
        <a href="https://www.instagram.com/casafloreserma/" target="_blank">
          <img src="@/assets/casaflorescer.png" alt="casa florescer">
          <p class="nome-parceiro">CASA FLORESCER</p>
        </a>
      </div>

       <div class="card-parceiro">
        <a href="https://sesicasaraodaindustria.fiema.org.br/" target="_blank">
          <img src="@/assets/casarao.png" alt="casa florescer">
          <p class="nome-parceiro">CASARAO INDUSTRIA</p>
        </a>
      </div>

    </div>
  </main>
</template>

<style>
.grid-parceiros {
  display: grid;
  grid-template-columns: repeat(4, 1fr); /* 👈 4 por linha */
  gap: 25px;
  padding: 20px;
}

/* CARD */
.card-parceiro {
  border: 2px solid #ff8a3d;
  border-radius: 20px;
  padding: 20px;
  text-align: center;
  background: white;
  transition: 0.3s;
}

.card-parceiro:hover {
  transform: translateY(-5px);
  box-shadow: 0 8px 20px rgba(0,0,0,0.1);
}

/* LINK (faz tudo clicável) */
.card-parceiro a {
  text-decoration: none;
  color: inherit;
}

/* IMAGEM */
.card-parceiro img {
  width: 100%;
  height: 100px;
  object-fit: contain;
  margin-bottom: 10px;
}

/* NOME */
.nome-parceiro {
  font-weight: 600;
  color: #333;
}
</style>