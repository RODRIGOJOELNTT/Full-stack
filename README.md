# springbatch-recharges-poller-no-db-final

Esta versión evita los errores de `dataSource` **sin usar una BD real**.

## Idea clave
- Spring Batch se ejecuta con `ResourcelessJobRepository` (sin persistir metadata).
- Se define un `DataSource` **dummy** solo para satisfacer autoconfiguraciones que lo requieren como bean,
  pero **no se usa** porque el JobRepository real es resourceless.

## Ejecutar
```bash
mvn clean spring-boot:run
```
