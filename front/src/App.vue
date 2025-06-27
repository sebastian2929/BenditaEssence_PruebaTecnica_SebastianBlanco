<template>
  <div class="container">
    <h1>📦 Gestión de Clientes y Entregas</h1>
    <CrearCliente />
    <hr />
    <CrearEntrega />
    <hr />
    <VerEntregas />
  </div>
</template>

<script setup>
import CrearCliente from './components/CrearCliente.vue'
import CrearEntrega from './components/CrearEntrega.vue'
import VerEntregas from './components/VerEntregas.vue'
</script>
