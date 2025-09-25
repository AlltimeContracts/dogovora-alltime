const form = document.getElementById('login-form');
const message = document.getElementById('message');

form.addEventListener('submit', async (e) => {
  e.preventDefault();

  const login = form.login.value;     // если поле называется login
  const password = form.password.value;

  console.log('Данные для входа:', { login, password });

  message.textContent = 'Попытка входа...';

  await new Promise((resolve) => setTimeout(resolve, 1000)); // ждем 1 секунду

  // Здесь можно прописать условие для имитации ошибки, например:
  if (login === 'admin' && password === '1234') {
    message.textContent = 'Успешный вход!';
    console.log('Токен: fake-jwt-token');
  } else {
    message.textContent = 'Неверный логин или пароль';
  }
});
