const DEFAULT_INTERVAL = 40; // minutes

function $(id) {
  return document.getElementById(id);
}

const timerEl = $('timer');
const intervalInput = $('interval-input');
const saveIntervalBtn = $('save-interval');
const doneBtn = $('done-btn');
const postponeBtn = $('postpone-btn');
const exerciseListEl = $('exercise-list');
const addExerciseBtn = $('add-exercise-btn');
const themeToggleBtn = $('theme-toggle');

let interval = parseInt(localStorage.getItem('interval'), 10) || DEFAULT_INTERVAL;
let remaining = interval * 60; // seconds
let timerId = null;

intervalInput.value = interval;

function loadExercises() {
  const stored = localStorage.getItem('exercises');
  if (stored) {
    return JSON.parse(stored);
  }
  const initial = [
    'Estiramiento lateral de cuello',
    'Rotación suave de cuello',
    'Retracción cervical',
    'Movilidad de hombros',
    'Estiramiento de brazos',
  ];
  localStorage.setItem('exercises', JSON.stringify(initial));
  return initial;
}

let exercises = loadExercises();

function saveExercises() {
  localStorage.setItem('exercises', JSON.stringify(exercises));
}

function renderExercises() {
  exerciseListEl.innerHTML = '';
  exercises.forEach((ex, index) => {
    const li = document.createElement('li');
    const label = document.createElement('label');
    const checkbox = document.createElement('input');
    checkbox.type = 'checkbox';
    checkbox.id = `ex-${index}`;
    label.htmlFor = checkbox.id;
    label.textContent = ex;
    li.appendChild(checkbox);
    li.appendChild(label);
    exerciseListEl.appendChild(li);
  });
}

function updateTimerDisplay() {
  const minutes = Math.floor(remaining / 60);
  const seconds = remaining % 60;
  timerEl.textContent = `${String(minutes).padStart(2, '0')}:${String(seconds).padStart(2, '0')}`;
}

function tick() {
  remaining -= 1;
  if (remaining <= 0) {
    notify();
    restartTimer();
  }
  updateTimerDisplay();
}

function startTimer() {
  if (timerId) return;
  timerId = setInterval(tick, 1000);
}

function stopTimer() {
  clearInterval(timerId);
  timerId = null;
}

function restartTimer() {
  remaining = interval * 60;
  document.querySelectorAll('#exercise-list input').forEach(cb => (cb.checked = false));
}

function notify() {
  if (Notification.permission === 'granted') {
    new Notification('¡Hora de ejercitar!', { body: 'Realiza tus ejercicios cervicales.' });
  } else {
    alert('¡Hora de ejercitar!');
  }
}

saveIntervalBtn.addEventListener('click', () => {
  const value = parseInt(intervalInput.value, 10);
  if (value > 0) {
    interval = value;
    localStorage.setItem('interval', interval);
    restartTimer();
    updateTimerDisplay();
  }
});

doneBtn.addEventListener('click', () => {
  restartTimer();
  updateTimerDisplay();
});

postponeBtn.addEventListener('click', () => {
  remaining += 300; // 5 minutes
  updateTimerDisplay();
});

addExerciseBtn.addEventListener('click', () => {
  const name = prompt('Nombre del ejercicio:');
  if (name) {
    exercises.push(name);
    saveExercises();
    renderExercises();
  }
});

themeToggleBtn.addEventListener('click', () => {
  document.body.classList.toggle('dark');
  const dark = document.body.classList.contains('dark');
  themeToggleBtn.textContent = dark ? 'Modo claro' : 'Modo oscuro';
  localStorage.setItem('theme', dark ? 'dark' : 'light');
});

function applyTheme() {
  const theme = localStorage.getItem('theme');
  if (theme === 'dark') {
    document.body.classList.add('dark');
    themeToggleBtn.textContent = 'Modo claro';
  }
}

function init() {
  renderExercises();
  applyTheme();
  updateTimerDisplay();
  startTimer();
  if (Notification.permission !== 'granted' && Notification.permission !== 'denied') {
    Notification.requestPermission();
  }
}

document.addEventListener('DOMContentLoaded', init);
