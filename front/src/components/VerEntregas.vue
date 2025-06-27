<template>
  <div class="container">
    <h2>📄 Ver Entregas</h2>

    <form @submit.prevent="consultarEntregas" class="formulario">
      <input
        v-model="correo"
        type="email"
        placeholder="Correo del Cliente"
        required
      />
      <button type="submit">Buscar Entregas</button>
    </form>

    <div v-if="entregas.length > 0">
      <div
        v-for="entrega in entregas"
        :key="entrega.nombreEntrega"
        class="card"
      >
        <strong>📦 Entrega:</strong> {{ entrega.nombreEntrega }}<br />
        <strong>👤 Cliente:</strong> {{ entrega.nombreCliente }}<br />
        <strong>📅 Fecha de entrega:</strong> {{ formatearFecha(entrega.fechaEntrega) }}<br />
        <strong>🕓 Fecha de creación:</strong> {{ formatearFecha(entrega.fechaCreacion) }}<br />
        <strong>📱 Celular:</strong> {{ entrega.celularCliente }}
      </div>
    </div>

    <p v-else-if="buscado">📭 No se encontraron entregas.</p>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import axios from 'axios'

const correo = ref('')
const entregas = ref([])
const buscado = ref(false)

const consultarEntregas = async () => {
  try {
    const res = await axios.get(`http://localhost:8080/api/clientes/${correo.value}/entregas`)
    entregas.value = res.data || []
  } catch (err) {
    alert('❌ Error consultando entregas: ' + (err.response?.data?.message || 'Error desconocido'))
    entregas.value = []
  } finally {
    buscado.value = true
  }
}

const formatearFecha = (fechaStr) => {
  return fechaStr?.substring(0, 10) || ''
}
</script>

<style scoped>
.container {
  max-width: 800px;
  margin: 2rem auto;
  padding: 2rem;
  background: white;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.05);
  border-radius: 10px;
}

.formulario {
  display: flex;
  flex-direction: row;
  gap: 0.5rem;
  margin-bottom: 1rem;
}

input {
  flex: 1;
  padding: 0.6rem;
  border-radius: 8px;
  border: 1px solid #ccc;
  font-size: 1rem;
}

button {
  background: #3498db;
  color: white;
  border: none;
  padding: 0.6rem 1rem;
  border-radius: 8px;
  cursor: pointer;
  font-size: 1rem;
  transition: background 0.3s;
}

button:hover {
  background: #2980b9;
}

.card {
  background: #ecf0f1;
  padding: 1rem;
  margin-top: 1rem;
  border-radius: 8px;
  transition: transform 0.2s ease;
}

.card:hover {
  transform: scale(1.02);
}
</style>
