import { apiFetch } from "./api";

const registerForm = document.querySelector('#register-form');

registerForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    if (document.querySelector('#register-password-input').value !== document.querySelector('#register-password-repeat-input').value) {
        document.querySelector('#message').textContent = 'Пароли не совпадают';
        return;
    }
    const login = registerForm.login.value;
    const password = registerForm.password.value;
    const response = await apiFetch('/register',{
        method: 'POST',
        body: JSON.stringify({ login, password })
    });
});
