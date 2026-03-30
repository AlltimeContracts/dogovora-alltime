const form = document.getElementById('login-form');
const message = document.getElementById('message');

form.addEventListener('submit', async (e) => {
  e.preventDefault();

  const login = form.login.value;
  const password = form.password.value;
  const response = await fetch('http://localhost:3000/login', {
    method: 'POST',
    headers: {
      'Content-type': 'application/json'
    },
    body: JSON.stringify({ login, password })
  });
  const data = await response.json();

  console.log('Данные для входа:', { login, password });

  message.textContent = 'Попытка входа...';

  if (data.success) {
    message.textContent = data.message
    let token = data.token
    localStorage.setItem('token', token);
    window.location.href = 'contracts.html';

  } else {
    message.textContent = data.message
  }

});
