# 🛒 Baratito - Sistema de Ventas

> **Sistema de gestión de ventas para minimarket/supermercado con reportes de facturas**

---

## 📋 Descripción del Proyecto

**Baratito** es un sistema integral de ventas diseñado específicamente para minimarkets y supermercados pequeños. Permite gestionar transacciones de compra y venta con generación automática de reportes y facturas, facilitando el control del inventario y la visualización de movimientos comerciales.

---

## 👥 Integrantes del Equipo

| Nombre | Rol |
|--------|-----|
| **Mara Saribel Herrera Acuña** | 👤 |
| **Dulce Maria Aguilar Martinez** | 👤 |
| **Angel Damian Vega Gonzalez** | 👤 |

---

## ✨ Tareas Realizadas

- ✅ **Implementación de comentarios intuitivos** en los controladores para mejor comprensión del código
- ✅ **Rediseño de la interfaz de inicio de sesión** con paleta de colores más viva y atractiva
- ⏳ *Pendiente: Documentación de tareas de Mara Saribel*

---

## 🚀 Instalación y Ejecución

### 📌 Requisitos Previos

Asegúrate de tener instalados los siguientes programas:

| Componente | Versión | Detalles |
|-----------|---------|----------|
| **JDK** | 21+ | Java Development Kit |
| **Apache NetBeans** | 26+ | IDE para desarrollo |
| **XAMPP** | Última | Suite de servidores |

#### Configuración de XAMPP

```
Tomcat:
  Usuario: root
  Contraseña: root

Apache + MySQL:
  Usuario: root
  Contraseña: (sin contraseña)
```

---

### 📥 Pasos de Instalación

#### 1️⃣ Clonar el Repositorio
```bash
git clone <url-del-repositorio>
cd proyecto_tp_sistema_de_ventas
```

#### 2️⃣ Abrir en Apache NetBeans
- Abre **Apache NetBeans 26+**
- Selecciona `Archivo → Abrir Proyecto`
- Elige la carpeta clonada como **Proyecto Web**

#### 3️⃣ Configurar Servidor Tomcat
- Ve a `Herramientas → Servidores`
- Configura **Tomcat** con las credenciales:
  - Usuario: `root`
  - Contraseña: `root`

#### 4️⃣ Importar Base de Datos
- Abre **phpMyAdmin** (desde XAMPP)
- Importa el backup SQL del repositorio:
  ```
  📁 proyecto_tp_sistema_de_ventas/
  └── backup_bd.sql
  ```
- **Credenciales predefinidas en la BD:**
  - Admin: `admin/admin`
  - Root: `root/root`

#### 5️⃣ Resolver Errores de Librerías
Si aparecen errores de localización de archivos, las librerías están en:
```
proyecto_tp_sistema_de_ventas/src/java/librerias/
```
Verifica que estén correctamente enlazadas en las configuraciones del proyecto.

#### 6️⃣ Iniciar Servidores
- Asegúrate que **Tomcat** y **MySQL** estén en ejecución
- Desde XAMPP, activa ambos servicios

#### 7️⃣ Ejecutar el Sistema
- En NetBeans, haz clic en ▶️ **Ejecutar Proyecto**
- El sistema se abrirá en tu navegador predeterminado

---

## 🎯 Características Principales

- 💳 Gestión de ventas y compras
- 📊 Reportes detallados de facturas
- 🗂️ Control de inventario
- 👤 Sistema de autenticación seguro
- 🎨 Interfaz intuitiva y moderna

---

## 📂 Estructura del Proyecto

```
proyecto_tp_sistema_de_ventas/
├── src/
│   ├── java/
│   │   ├── controladores/
│   │   ├── modelos/
│   │   └── librerias/
│   └── ...
├── web/
├── backup_bd.sql
└── README.md
```

---

## 🛠️ Tecnologías Utilizadas

- **Backend:** Java (JDK 21)
- **Frontend:** JSP / HTML / CSS / JavaScript
- **Servidor Web:** Apache Tomcat
- **Base de Datos:** MySQL
- **IDE:** Apache NetBeans

---

## 📝 Notas Importantes

> ⚠️ **Asegúrate de:**
> - Tener XAMPP completamente instalado y configurado
> - Usar la versión correcta de JDK (21+)
> - Importar el backup de BD antes de ejecutar el proyecto
> - Tener permisos de administrador para instalar servicios

---

## 🤝 Contribuciones

Para contribuir al proyecto:
1. Crea una rama nueva (`git checkout -b feature/mejora`)
2. Realiza tus cambios
3. Haz commit (`git commit -am 'Agrega mejora'`)
4. Envía un pull request

---

## 📞 Soporte

Si tienes dudas o encuentras problemas, contacta con algún integrante del equipo o abre un **Issue** en el repositorio.

---

<div align="center">

**Hecho con ❤️ por el equipo de Baratito**

</div>
