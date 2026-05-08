# 🧩 Rompecabezas 15

Puzzle sliding clásico desarrollado en Java con interfaz gráfica en Swing. El objetivo es ordenar los números del 1 al 15 (o fragmentos de una imagen) en una grilla de 4×4, usando el espacio vacío para deslizar las piezas.

---

## 🚀 Cómo ejecutar

1. Clonar o descargar el repositorio.
2. Abrir el proyecto en **Eclipse IDE**.
3. Ejecutar la clase **`view/FrameMenu.java`** como aplicación Java.

> Requiere Java 11 o superior. Las dependencias MigLayout ya están incluidas como `.jar` en el proyecto.

---

## 🎮 Modos de juego

- **Números** — ordenar del 1 al 15 en la grilla.
- **Imágenes** — elegir entre varias imágenes (Messi, Diego, Loro, Perritos) y reconstruirla como rompecabezas.

---

## 🕹️ Controles

| Acción | Teclas |
|--------|--------|
| Mover pieza | `W` `A` `S` `D` o flechas en pantalla |
| Ayuda (sugerir movimiento) | Botón **Ayuda** |
| Volver al menú | Botón **Volver al menú** |

---

## ✨ Funcionalidades

- Grilla 4×4 generada y mezclada aleatoriamente al iniciar cada partida.
- Contador de movimientos en tiempo real.
- Sistema de **ayuda** que deshace el último movimiento usando un historial en `LinkedList`.
- Límite de 100 ayudas por partida.
- Detección automática de victoria al completar el puzzle.
- Confirmación de salida para evitar cierres accidentales.

---

## 🏗️ Arquitectura

El proyecto se organiza en dos capas principales (**View** y **Service**), donde `Tablero` concentra tanto el estado como la lógica del juego:

```
src/
├── view/
│   ├── FrameMenu.java        # Menú principal
│   ├── FrameNumeros.java     # Vista del modo números
│   ├── FrameImagenes.java    # Vista del modo imágenes
│   ├── Gano.java             # Pantalla de victoria
│   └── Reglas.java           # Pantalla de reglas
├── service/
│   └── Tablero.java          # Estado y lógica del juego (mezcla, movimiento, victoria)
└── Imagenes/                 # Recursos gráficos
```

---

## 🛠️ Tecnologías

- Java 11
- Java Swing (GUI)
- MigLayout 11.3
- Eclipse IDE

---

## 👤 Autor

**Franco Jara** — Estudiante de Licenciatura en Sistemas, UNGS  
[francomjara09@gmail.com](mailto:francomjara09@gmail.com)
