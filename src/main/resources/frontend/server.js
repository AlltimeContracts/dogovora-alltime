const http = require('http');

const PORT = 3000;

const server = http.createServer((req, res) => {
  // Разрешаем запросы с любого источника (CORS), чтобы наш фронтенд мог достучаться до сервера
  res.setHeader('Access-Control-Allow-Origin', '*');
  res.setHeader('Access-Control-Allow-Methods', 'POST, GET, OPTIONS');
  res.setHeader('Access-Control-Allow-Headers', 'Content-Type');

  // Ответ на предварительный запрос (Preflight OPTIONS query)
  if (req.method === 'OPTIONS') {
    res.writeHead(204);
    res.end();
    return;
  }

  // Обработка логина
  if (req.url === '/login' && req.method === 'POST') {
    let body = '';
    req.on('data', chunk => {
      body += chunk.toString();
    });
    req.on('end', () => {
      try {
        const data = JSON.parse(body);
        console.log('Получены данные для входа:', data);

        if (data.login === 'admin' && data.password === '1234') {
          res.writeHead(200, { 'Content-Type': 'application/json' });
          res.end(JSON.stringify({ success: true, token: 'fake-jwt-token-123', message: 'Успех!' }));
        } else {
          res.writeHead(401, { 'Content-Type': 'application/json' });
          res.end(JSON.stringify({ success: false, message: 'Неверный логин или пароль' }));
        }
      } catch (e) {
        res.writeHead(400);
        res.end('Bad Request');
      }
    });
    return; // Важно: выходим, так как обработка асинхронная внутри req.on
  }

  // Обработка получения списка договоров
  if (req.url === '/contracts' && req.method === 'GET') {
    const contracts = [
      { id: 1, title: 'Договор с ООО "Ромашка"', date: '2023-10-01', status: 'Активен' },
      { id: 2, title: 'Контракт на поставку кофе', date: '2023-11-15', status: 'В работе' },
      { id: 3, title: 'Аренда офиса (Центр)', date: '2024-01-10', status: 'Завершен' }
    ];
    
    res.writeHead(200, { 'Content-Type': 'application/json' });
    res.end(JSON.stringify(contracts));
    return;
  }

  // Если ничего не подошло
  res.writeHead(404);
  res.end('Not Found');
});

server.listen(PORT, () => {
  console.log(`Сервер-заглушка запущен на http://localhost:${PORT}`);
  console.log('Нажми Ctrl+C, чтобы остановить его.');
});
