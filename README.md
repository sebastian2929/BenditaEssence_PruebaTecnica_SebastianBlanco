# 🚀 Prueba Técnica - Sebastian Blanco

### Parte 1: BPMN

#### 🎯 Introducción y Objetivo
El objetivo de este ejercicio es demostrar la capacidad para analizar, interpretar y modelar un proceso de negocio utilizando BPMN 2.0, identificando claramente los actores, tareas, flujos y decisiones.

#### 📌 Descripción del proceso

Proceso de gestión de reclamos de motos, que inicia con el registro del cliente y termina con el envío de un correo formal de cierre. Involucra validación de documentos, evaluación del seguro y coordinación con talleres, incluyendo notificaciones automáticas por correo, SMS y WhatsApp según corresponda.

#### 📷 Diagrama BPMN

![Reclamos de Motos](https://github.com/user-attachments/assets/d3d0ad18-1b1d-4ba7-8604-3b161040baf2)

#### ⚙ Herramientas utilizadas
Bizagi Modeler para el modelado BPMN.

### Parte 2: Desarrollo Full-Stack
Se construyó una solución que:

* Implementa en Salesforce los objetos personalizados Cliente__c y Entrega__c con relación uno a muchos, además de un Flow para actualizar fechas de entrega.

  - Expone una API REST externa que permite:

  - Crear un cliente.

  - Crear una entrega asociada a un cliente.

  - Consultar las entregas de un cliente.

### 📷 pruebas de Salesforce 
![Cliente](https://github.com/user-attachments/assets/5730123e-9189-49ae-9613-82ba665dfcca)
![Entrega](https://github.com/user-attachments/assets/dd40d4f6-056d-43b1-8150-73b45d23d452)
![image](https://github.com/user-attachments/assets/3192130f-7f42-4404-a3f9-56dd75d09178)


#### 🗂 Estructura de carpetas

```
BenditaEssence_PruebaTecnica_SebastianBlanco
├── back/       # Backend Spring Boot
│   ├── src/
│   │   ├── main/java/api/sebastian
│   │   │   ├── config/            # Configuración de CORS y Swagger
│   │   │   ├── controller/        # Controladores REST
│   │   │   ├── dto/               # Objetos de transferencia
│   │   │   ├── exception/         # Excepciones personalizadas
│   │   │   ├── model/             # Modelos de MongoDB
│   │   │   ├── repository/        # Repositorios Mongo
│   │   │   └── service/           # Lógica de negocio + integración Salesforce
│   │   └── resources/
│   │       ├── application.properties
│   │       └── static/index.html  # Landing minimal
│   └── pom.xml
│
└── front/     # Frontend Vue 3 + Vite
    ├── src/
    │   ├── components/            # Componentes Vue: CrearCliente, CrearEntrega, VerEntregas
    │   ├── assets/                # CSS y logos
    │   ├── App.vue
    │   └── main.js
    ├── public/
    ├── package.json
    └── vite.config.js
```

## ⚙ Configuración y ejecución

### 📦 Backend (Spring Boot + Maven)

#### 🔧 Prerrequisitos:
-   Java 17 o superior
- Maven

🚀 Cómo correr
```
cd back
mvn clean install
mvn spring-boot:run
```
Por defecto corre en ```http://localhost:8080.```

#### 🔗 Configuración en application.properties

Agregar los tokens dados por [salesforce](https://www.salesforce.com/mx/) 
```
salesforce.client.id={}
salesforce.client.secret={}
salesforce.username={}
salesforce.password={}
salesforce.token={}
salesforce.login.url=https://login.salesforce.com
```

### 🌐 Frontend (Vue 3 + Vite)

🔧 Prerrequisitos:
- Node.js 16+

🚀 Cómo correr
```
cd front
npm install
npm run dev
```

### 📌 API REST - Documentación rápida

#### ✉ Endpoints principales
| Método | Endpoint                        | Descripción                         |
|--------|---------------------------------|------------------------------------|
| POST   | `/api/clientes`                 | Crea un cliente                     |
| POST   | `/api/clientes/{id}/entregas`   | Crea una entrega asociada a cliente |
| GET    | `/api/clientes/{id}/entregas`   | Obtiene entregas del cliente 

#### 🗄 Identificación
La relación Cliente → Entrega se mantiene por clienteId referenciado en la entrega.

#### 🚀 Swagger UI
Puedes ver y probar todos los endpoints en:
```
http://localhost:8080/swagger-ui.html
```
#### 📷 Resultado
![Front](https://github.com/user-attachments/assets/7bed6113-e543-4369-b3f4-23be236ea2d4)


### ✍ Autor
 - [Sebastián David Blanco Rodriguez](https://github.com/sebastian2929)
