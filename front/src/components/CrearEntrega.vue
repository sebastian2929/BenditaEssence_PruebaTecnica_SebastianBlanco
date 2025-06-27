<template>
  <div>
    <h2>📬 Crear Entrega</h2>
    <form @submit.prevent="crearEntrega">
      <input v-model="entrega.nombreEntrega" placeholder="Nombre de entrega" required />
      <input type="date" v-model="entrega.fechaEntrega" required />
      <input v-model="entrega.clienteCorreo" placeholder="Correo del Cliente" required />
      <button type="submit">Guardar Entrega</button>
    </form>
  </div>
</template>

<script setup>
import { reactive } from 'vue'
import axios from 'axios'

const entrega = reactive({
  nombreEntrega: '',
  fechaEntrega: '',
  clienteCorreo: ''
})

const crearEntrega = async () => {
  try {
    await axios.post('http://localhost:8080/api/entregas', entrega)
    alert('✅ Entrega registrada')
    Object.keys(entrega).forEach(k => entrega[k] = '')
  } catch (err) {
    alert('❌ Error al guardar entrega: ' + err.response?.data?.message)
  }
}
</script>
