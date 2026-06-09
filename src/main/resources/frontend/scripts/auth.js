import { apiFetch } from './api.js';

const form = document.getElementById('login-form');
const message = document.getElementById('message');

form.addEventListener('submit', async (e) => {
  e.preventDefault();

  const login = form.login.value;
  const password = form.password.value;

  message.textContent = 'Попытка входа...';

  try {
    const data = await apiFetch('/login', {
      method: 'POST',
      body: JSON.stringify({ login, password })
    });

    if (data.success) {
      message.textContent = data.message;
      const token = data.token;
      localStorage.setItem('token', token);
      window.location.href = 'home.html';
    } else {
      message.textContent = data.message;
    }

  } catch (error) {
    message.textContent = error.message;
  }
});
