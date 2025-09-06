# 📝 GESTION DE BLOGS

API REST para la gestión de blogs, autores y comentarios desarrollada en Java con Spring Boot.

## 🎯 Descripción del Proyecto

Este sistema permite la gestión completa de un blog con las siguientes funcionalidades:
- ✅ Crear, consultar, actualizar y eliminar blogs
- ✅ Gestión de autores con información personal completa
- ✅ Sistema de comentarios con puntuaciones (0-10)
- ✅ Histórico de cambios en blogs
- ✅ Añadir comentarios a blogs
- ✅ Resumen estadístico de puntuaciones

## 🏗️ Arquitectura y Tecnologías

### **Stack Tecnológico**
- **Java 21** - Lenguaje de programación
- **Spring Boot 3.4.9** - Framework principal
- **Spring Data JPA** - Persistencia de datos
- **Hibernate** - ORM
- **H2 Database** - Base de datos en memoria (desarrollo)
- **PostgreSQL** - Base de datos para producción
- **Maven** - Gestión de dependencias
- **Docker & Docker Compose** - Dockerizacion
- **OpenAPI/Swagger** - Documentación de API

### **Patrones de Diseño Implementados**
- **Repository Pattern** - Acceso a datos
- **DTO Pattern** - Transferencia de datos
- **Builder** - Construccion de objetos
- **Service Layer** - Lógica de negocio
- **MVC Pattern** - Arquitectura web
- **Exception Handling** - Manejo centralizado de errores

### **Arquitectura por Capas**
```
┌─────────────────┐
│   Controllers   │  ← API REST Endpoints
├─────────────────┤
│    Services     │  ← Lógica de negocio
├─────────────────┤
│  Repositories   │  ← Acceso a datos
├─────────────────┤
│   Entities      │  ← Modelo de datos
└─────────────────┘
```

## 📊 Modelo de Datos

### **Entidades Principales**

**Autor**
- Información personal: nombres, apellidos, fecha nacimiento
- Datos de contacto: email, país de residencia
- Relación: 1 → N blogs

**Blog**
- Información del blog: título, tema, contenido
- Configuración: periodicidad (DIARIA/SEMANAL/MENSUAL)
- Control: permite comentarios (boolean)
- Relaciones: N ← 1 autor, 1 → N comentarios, 1 → N histórico

**Comentario** 
- Contenido del comentario y puntuación (0-10)
- Relaciones: N ← 1 blog, N ← 1 comentarista

**Comentador**
- Información básica: nombre, email, país
- Relación: 1 → N comentarios

**BlogHistorico** (Histórico)
- Versiones anteriores de blogs
- Control de cambios con número de versión

### **Diagrama de Relaciones**
```
Autor (1) ←──→ (N) Blog (1) ←──→ (N) Comentario (N) ←──→ (1) Comentador
                 │
                 ↓
           BlogHistorico (N)
```

## 🚀 Instalación y Ejecución

### **Prerrequisitos**
- Java 21+
- Maven 3.8+
- Docker y Docker Compose (opcional)
- PostgreSQL 15+ (solo para producción)

### **Opción 1: Ejecución Local**

```bash
# Clonar el repositorio
git clone https://github.com/Davp94/API-Blogs.git

# Compilar el proyecto
mvn clean package -DskipTests

# Ejecutar en modo desarrollo (H2 in-memory)
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

La aplicación estará disponible en: `http://localhost:8080`

### **Opción 2: Docker Compose (SOLO PRODUCCION) **

```bash
# Construir y ejecutar todos los servicios
docker compose up --build -d

# Ver logs
docker compose logs -f blog-api

# Detener servicios
docker compose down
```

### **Opción 3: Solo Dockerfile (RECOMENDADO)**

```bash
# Construir la imagen
docker build -t blog-api .

# Ejecutar el contenedor
docker run -p 8080:8080 \
  -e SPRING_PROFILES_ACTIVE=dev \
  blog-api
```

## 📚 Documentación de la API

### **Swagger UI**
Una vez ejecutada la aplicación, la documentación interactiva está disponible en:
- **Swagger UI**: `http://localhost:8080/swagger-ui/index.html`
- **OpenAPI JSON**: `http://localhost:8080/v3/api-docs`

### **Endpoints Principales**

#### **👤 Autores**
```
POST   /api/autores          - Crear autor
GET    /api/autores          - Listar autores
GET    /api/autores/{id}     - Obtener autor por ID
```

#### **📝 Blogs**
```
POST   /api/blogs                    - Crear blog
GET    /api/blogs/{id}               - Obtener blog por ID
PUT    /api/blogs/{id}               - Actualizar blog
DELETE /api/blogs/{id}               - Eliminar blog
```

#### **💭 Comentarios**
```
POST   /api/comments/blog/{blogId}           - Crear comentario
GET    /api/comments/blog/{blogId}           - Comentarios de un blog
```


## 🗄️ Base de Datos

### **Desarrollo (H2)**
- **URL**: `jdbc:h2:mem:blogdb`
- **Usuario**: `sa`
- **Password**: `password`
- **Consola H2**: `http://localhost:8080/h2-console`

### **Producción (PostgreSQL)**
```bash
# Variables de entorno requeridas
DB_HOST=localhost
DB_PORT=5432
DB_NAME=blogdb
DB_USERNAME=bloguser
DB_PASSWORD=blogpass
```

### **Docker Compose incluye:**
- PostgreSQL 16
- Volúmenes persistentes

## ⚙️ Configuración

### **Perfiles de Spring**
- **dev**: Desarrollo con H2, logging DEBUG
- **prod**: Producción con PostgreSQL

### **Variables de Entorno**
```bash
SPRING_PROFILES_ACTIVE=dev
DB_HOST=postgres
DB_USERNAME=bloguser
DB_PASSWORD=blogpass
JAVA_OPTS="-Xms512m -Xmx1024m"
```

### **Configuración de Aplicación**
Archivos principales:
- `application.yml`: Configuración base
- `application-dev.yml`: Desarrollo
- `application-prod.yml`: Producción
- `application-test.yml`: Test

## 🔍 Monitoreo

### **Actuator Endpoints**
- **Health**: `http://localhost:8080/actuator/health`
- **Info**: `http://localhost:8080/actuator/info`
- **Metrics**: `http://localhost:8080/actuator/metrics`


## 📈 Características Avanzadas

### **Funcionalidades Implementadas**
- ✅ **Histórico Automático**: Versiones anteriores de blogs
- ✅ **Validaciones**: Bean Validation en todas las entidades
- ✅ **Manejo de Errores**: Global exception handler
- ✅ **CORS**: Configurado para desarrollo frontend
- ✅ **Resumen de Puntuaciones**: Estadísticas automáticas
- ✅ **Índices de BD**: Optimización de consultas

### **Reglas de Negocio Implementadas**
- 📝 Solo se pueden agregar comentarios a blogs que los permiten
- 🔄 El histórico se guarda automáticamente al actualizar blogs
- 📊 Las puntuaciones deben estar entre 0 y 10
- 👤 Correos únicos para autores
- 🔄 Los comentarios anteriores permanecen aunque se desactiven los comentarios

## 🚨 Manejo de Errores

### **Códigos de Estado HTTP**
- `200` - OK: Operación exitosa
- `201` - Created: Recurso creado
- `400` - Bad Request: Error de validación
- `404` - Not Found: Recurso no encontrado
- `409` - Conflict: Recurso duplicado
- `500` - Internal Server Error: Error interno

### **Estructura de Errores**
```json
{
  "timestamp": "2024-01-15T10:30:00",
  "statusCode": 400,
  "message": "Errores de validación en los campos",
  "path": "/api/blogs"
}
```

## 🌟 Posibles Mejoras

### **Features Pendientes**
- 🔐 **Autenticación y Autorización** (Spring Security + JWT)
- 📧 **Notificaciones por Email** al recibir comentarios
- 🖼️ **Soporte para Imágenes** en contenido de blogs
- 🔍 **Búsqueda Avanzada** (Elasticsearch)
- 📱 **API Versioning** para compatibilidad
- 🚀 **Caché** (Redis) para consultas frecuentes
- 📈 **Métricas Avanzadas** (Micrometer + Prometheus)
- 🔍 **Test Unitarios** (Junit + Mockito)
- 🔍 **CI/CD** (Github-Actions, Kubernetes)

### **Estructura del Proyecto**
```
src/
├── main/
│   ├── java/com/blogapp/
│   │   ├── common/         # Recursos comunes (dto, services, etc.)
│   │   ├── config/         # Configuraciones
│   │   ├── controller/     # REST Controllers
│   │   ├── dto/            # Data Transfer Objects (req & res)
│   │   ├── entity/         # JPA Entities
│   │   ├── enums/          # Java enums
│   │   ├── exceptions/     # Custom Exceptions & Global Handler
│   │   ├── repository/     # Data Access Repository
│   │   ├── service/       # Logica de Negocio
│   │   └── BlogApplication.java
│   └── resources/
│       ├── application.yml
│       ├── application-dev.yml
│       ├── application-prod.yml
│       ├── application-test.yml
│       └── data.sql
```

### **Estándares de Código**
- **Java Conventions**: Google Java Style Guide
- **Naming**: CamelCase para métodos, PascalCase para clases
- **Documentation**: JavaDoc en métodos públicos

## 📞 Contacto y Soporte

- **Documentación**: `http://localhost:8080/swagger-ui.html`
- **Health Check**: `http://localhost:8080/api/health`
- **Issues**: Crear issue en GitHub
- **Logs**: Revisar `logs/blog-management.log`


---
