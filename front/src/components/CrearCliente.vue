<template>
  <div>
    <h2>Crear Cliente</h2>
    <form @submit.prevent="crearCliente">
      <input v-model="cliente.nombre" placeholder="Nombre" required />
      <input v-model="cliente.apellido" placeholder="Apellido" required />
      <input v-model="cliente.celular" placeholder="Celular" required />
      <input v-model="cliente.correo" placeholder="Correo" required />
      <button type="submit">Crear</button>
    </form>
  </div>
</template>

<script setup>
import { reactive } from 'vue'
import axios from 'axios'

const cliente = reactive({
  nombre: '',
  apellido: '',
  celular: '',
  correo: ''
})

const crearCliente = async () => {
  try {
    await axios.post('http://localhost:8080/api/clientes', cliente)
    alert('Cliente creado exitosamente')
    Object.keys(cliente).forEach(k => cliente[k] = '')
  } catch (e) {
    alert('Error al crear cliente')
  }
}
</script>
