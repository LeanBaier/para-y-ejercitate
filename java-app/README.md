# Para y Ejercítate - Aplicación de Escritorio

Esta versión implementa la funcionalidad de recordatorio de ejercicios en Java usando Swing. Al ejecutar la aplicación se mostrará un temporizador, la lista de ejercicios y controles para marcar como hechos o posponer el recordatorio. Las preferencias (intervalo y lista de ejercicios) se almacenan usando la clase `Preferences` del JDK.

## Compilación y ejecución

```bash
javac -d out src/ParaYEjercitate.java
java -cp out ParaYEjercitate
```

El temporizador por defecto es de 40 minutos. Se puede ajustar introduciendo un nuevo valor en el campo "Intervalo" y pulsando en **Guardar intervalo**.
