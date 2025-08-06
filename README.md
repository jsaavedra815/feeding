# Feeding Microservice

Este microservicio forma parte de un entorno de microservicios de la Universidad Católica del Ecuador (PUCE) y es responsable de proporcionar recomendaciones de alimentación para animales.

## Descripción

El microservicio **Feeding** consume el microservicio **Animals** para obtener información detallada de los animales y proporcionar recomendaciones de alimentación basadas en su tipo de alimentación (carnívoro, omnívoro, herbívoro, etc.).

## Arquitectura

### Dependencias de Microservicios
- **Animals Microservice**: Consumido para obtener información de animales por ID
  - URL base: `http://animal-service:8080/api/animals`
  - Puerto de conexión: 8080

### Tecnologías Utilizadas
- **Kotlin** con Spring Boot 3.5.4
- **Spring Web** para APIs REST
- **Jackson** para serialización JSON
- **RestTemplate** para comunicación entre microservicios
- **Java 21**

## Configuración

### Puertos
- **Puerto del servicio**: 8081
- **Nombre de la aplicación**: feeding

### Variables de entorno
```yaml
spring:
  application:
    name: feeding
server:
  port: 8081
```

## API Endpoints

### Recomendación de Alimentación
```
GET /api/feeding/{id}
```

**Descripción**: Obtiene recomendaciones de alimentación para un animal específico.

**Parámetros**:
- `id` (Long): ID del animal

**Respuesta**:
```json
{
  "preferred_food": "Carne",
  "animal": {
    "id": 1,
    "name": "León",
    "species": "Panthera leo",
    "alimentation": "CARNIVORO",
    "age": 5
  }
}
```

## Lógica de Negocio

El servicio determina el tipo de comida recomendada basándose en el tipo de alimentación del animal:

| Tipo de Alimentación | Comida Recomendada |
|---------------------|-------------------|
| CARNIVORO | Carne |
| OMNIVORO | Carne y vegetales |
| HERBIVORO | Hierba y plantas |
| FRUGIVORO | Frutas |
| GRANIVORO | Semillas y granos |
| Otros | Comida desconocida |

## Estructura del Proyecto

```
src/main/kotlin/com/pucetec/feeding/
├── FeedingApplication.kt          # Clase principal de Spring Boot
├── controllers/
│   └── FeedingController.kt       # Controlador REST
├── services/
│   └── FeedingService.kt          # Lógica de negocio
├── repositories/
│   └── AnimalRestRepository.kt    # Cliente REST para Animals microservice
└── models/responses/
    ├── AnimalResponse.kt          # Modelo de respuesta de Animal
    └── FeedingResponse.kt         # Modelo de respuesta de Feeding
```

## Ejecución

### Prerrequisitos
- Java 21
- Gradle
- Acceso al microservicio Animals en `http://animal-service:8080`

### Comandos

#### Compilar el proyecto
```bash
./gradlew build
```

#### Ejecutar localmente
```bash
./gradlew bootRun
```

#### Ejecutar con Docker
```bash
docker build -t feeding-service .
docker run -p 8081:8081 feeding-service
```

#### Docker Compose
```bash
docker-compose up
```

## Comunicación entre Microservicios

Este microservicio actúa como un **consumidor** del microservicio Animals:

1. Recibe una solicitud con un ID de animal
2. Realiza una llamada HTTP al microservicio Animals para obtener los datos del animal
3. Procesa la información de alimentación del animal
4. Retorna una recomendación de comida junto con los datos del animal

## Consideraciones de Desarrollo

- **Tolerancia a fallos**: El servicio maneja casos donde el animal no existe o el microservicio Animals no está disponible
- **Comunicación síncrona**: Utiliza RestTemplate para comunicación HTTP síncrona
- **Configuración**: Las URLs de servicios externos están configuradas en el repositorio

## Próximas Mejoras

- Implementar circuit breaker para mayor resilencia
- Agregar cache para reducir llamadas al microservicio Animals
- Implementar logging estructurado
- Agregar métricas y health checks
- Migrar a WebClient para comunicación reactiva
