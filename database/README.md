# Base de Datos - Huellas de Asturias

Base de datos MySQL para la plataforma de arte asturiano.

## Estructura de la Base de Datos

### Tablas Principales
1. **epoca** - Épocas históricas del arte
2. **artista** - Artistas asturianos
3. **museo** - Museos de Asturias
4. **obra** - Obras de arte
5. **monumento** - Monumentos prerrománicos

## Scripts Disponibles

### Ejecutar en Orden:

1. **00_database_setup.sql** - Crea la base de datos
2. **01_schema.sql** - Crea las tablas e índices
3. **02_constraints.sql** - Aplica relaciones y restricciones
4. **03_data.sql** - Inserta datos de ejemplo

### Comando para ejecutar todos:
```bash
mysql -u root < scripts/00_database_setup.sql
mysql -u root arte_asturiano < scripts/01_schema.sql
mysql -u root arte_asturiano < scripts/02_constraints.sql
mysql -u root arte_asturiano < scripts/03_data.sql