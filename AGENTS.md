# AGENTS.md

## Objetivo del proyecto

Desarrollar una web app client-side, simple y eficiente, para escritorio, que recuerde cada 40 minutos al usuario la necesidad de realizar ejercicios recomendados para la rehabilitación cervical y prevención del sedentarismo. La app debe ser fácil de usar, rápida, agradable y no requerir backend ni login. Todo debe persistirse localmente en el navegador.

---

## Características principales

1. **Recordatorio cada 40 minutos**
   - Temporizador visible en pantalla.
   - Notificación browser (Web Notifications API) o alerta modal.
   - Botón para "Marcar como hecho" o "Posponer 5 minutos".

2. **Lista de ejercicios**
   - Lista editable por el usuario (añadir, quitar, modificar).
   - Nombre y breve descripción por ejercicio.
   - Permite marcar cada ejercicio como completado en el ciclo actual.

3. **Historial básico (opcional)**
   - Registro de cuántos ciclos/días se cumplieron (guardado en LocalStorage).

4. **Configuraciones mínimas**
   - Editar intervalo de recordatorio.
   - Editar la lista de ejercicios.
   - Modo claro/oscuro (opcional).

---

## Público objetivo

- Personas que pasan muchas horas frente a la computadora y necesitan recordar hacer ejercicios de pausas activas o rehabilitación cervical.

---

## Stack técnico

- **Frontend:** HTML, CSS, JavaScript (puede usarse React, pero debe funcionar sin backend).
- **Persistencia:** LocalStorage del navegador.
- **Notificaciones:** Web Notifications API.
- **No requiere backend ni login.**

---

## Ejemplo de ejercicios iniciales

1. **Estiramiento lateral de cuello:** Inclinar suavemente la cabeza hacia un lado y mantener 15 segundos.
2. **Rotación suave de cuello:** Girar la cabeza de lado a lado sin forzar.
3. **Retracción cervical:** Llevar el mentón hacia atrás, mantener 5 segundos.
4. **Movilidad de hombros:** Encoger los hombros y relajarlos 10 veces.
5. **Estiramiento de brazos:** Extender los brazos hacia adelante y estirar los dedos.

---

## Flujo principal

1. El usuario abre la app.
2. Ve el temporizador y la lista de ejercicios.
3. Cuando el temporizador llega a cero, la app envía una notificación.
4. El usuario realiza y marca los ejercicios como hechos.
5. El temporizador se reinicia.

---

## Palabras clave para Codex

web app, recordatorio, ejercicios cervicales, javascript, localstorage, notificación, timer, html, client-side

---

## Agentes y roles sugeridos para el desarrollo

### 1. Agente de UX/UI

- Propone un diseño claro, minimalista, fácil de usar, adecuado para pausas activas.
- Selecciona colores y tipografía que reduzcan la fatiga visual (idealmente modo oscuro/claro).
- Asegura interacción intuitiva, con botones grandes y navegación simple.
- Verifica accesibilidad: buen contraste, soporte teclado, textos descriptivos.

### 2. Agente de Arquitectura Frontend

- Define la estructura de componentes y organización de archivos.
- Recomienda patrones de diseño (separación de lógica de UI, modularidad).
- Busca código reutilizable y fácil de mantener.
- Evalúa si usar vanilla JS o React según el alcance (mantener todo client-side y portable).

### 3. Agente de Calidad de Código

- Promueve buenas prácticas: nombres descriptivos, modularidad, comentarios claros.
- Sugiere herramientas de linting y formateo automático (eslint, prettier).
- Propone testing básico (unitario sobre funciones críticas, si el scope lo permite).
- Recomienda gestión de errores clara y feedback al usuario.

### 4. Agente Desarrollador Experimentado

- Identifica posibles problemas de performance, UX, compatibilidad de navegador.
- Sugiere atajos de productividad (librerías pequeñas, hooks reutilizables).
- Advierte sobre antipatterns comunes en apps client-side.
- Realiza code review antes de cada entrega.

---

## Forma de trabajo recomendada

1. **Diseño inicial:**  
   - Que el Agente UX/UI proponga wireframes simples y paleta de colores.
   - El Agente de Arquitectura define la estructura base del proyecto.

2. **Desarrollo de funcionalidades:**  
   - Cada feature debe ser revisada por los agentes de Arquitectura y Calidad de Código antes de integrar.
   - El Agente Desarrollador Experimentado revisa la solución final y sugiere mejoras.

3. **Revisión continua:**  
   - Ante cada iteración, los agentes evalúan:
     - UX/UI: facilidad y satisfacción de uso.
     - Arquitectura: escalabilidad y orden.
     - Calidad de código: mantenibilidad.
     - Experiencia: posibles bugs y mejoras.

4. **Checklist antes de lanzar:**  
   - UX: ¿La app es intuitiva y agradable?
   - Arquitectura: ¿El código está bien organizado y es fácilmente editable?
   - Calidad: ¿Está limpio, formateado y libre de errores?
   - Experiencia: ¿Es rápida, compatible y estable en los principales navegadores?

---

## Ejemplo de intervención de los agentes

- **Nueva feature**: “Agregar botón para reiniciar temporizador”
  - **UX/UI:** ¿Dónde se ubica? ¿Es visible y fácil de encontrar?
  - **Arquitectura:** ¿Se agrega en el componente correcto? ¿Afecta el estado global/local?
  - **Calidad:** ¿Está bien nombrado el handler? ¿Tiene comentarios?
  - **Experiencia:** ¿Qué pasa si el usuario lo pulsa muchas veces? ¿Funciona igual en todos los navegadores?

---

## Sugerencias adicionales

- Priorizar la simplicidad y la robustez por sobre la sofisticación.
- Optimizar para usuarios con rutinas intensivas de escritorio.
- Documentar decisiones importantes en comentarios para futuras mejoras.

---
