const registerForm = document.querySelector('#register-form');

registerForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    if (document.querySelector('#register-password-input').value !== document.querySelector('#register-password-repeat-input').value) {
        document.querySelector('#message').textContent = 'Пароли не совпадают';
        return;
    }
    const login = registerForm.login.value;
    const password = registerForm.password.value;
    const response = await fetch('http://localhost:3000/register',{
        method: 'POST',
        headers: { 'Content-Type': 'application/json'},
        body: JSON.stringify({ login, password })
    });
});
