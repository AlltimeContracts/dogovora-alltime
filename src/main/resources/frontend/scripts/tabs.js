const tabLogin = document.querySelector('.auth-tab-login');
const tabRegister = document.querySelector('.auth-tab-register');
const authMessage = document.querySelector('#message');

tabLogin.addEventListener('click', () => {
    authMessage.textContent = '';
    document.querySelector('.auth-tab--active').classList.remove('auth-tab--active');
    tabLogin.classList.add('auth-tab--active');
    document.querySelector('#register-form').classList.add('hidden');
    document.querySelector('#login-form').classList.remove('hidden');
    document.querySelector('#auth-title').textContent = 'Вход в Договоры';
})
tabRegister.addEventListener('click', () => {
    authMessage.textContent = '';
    document.querySelector('.auth-tab--active').classList.remove('auth-tab--active');
    tabRegister.classList.add('auth-tab--active');
    document.querySelector('#register-form').classList.remove('hidden');
    document.querySelector('#login-form').classList.add('hidden');
    document.querySelector('#auth-title').textContent = 'Регистрация';
})
