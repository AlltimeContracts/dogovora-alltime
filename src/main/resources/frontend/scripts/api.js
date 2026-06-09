const BASE_URL = 'http://localhost:3000'

export async function apiFetch(url, options = {}) {
    const token = localStorage.getItem('token');
    const response = await fetch((BASE_URL + url), {
        headers: { 
        'Content-Type': 'application/json', 
        'Authorization': 'Bearer ' + token,
        ...options.headers
    },
        ...options
    });

    if (response.status === 401) {
        window.location.href = 'index.html';
        return;
    }

    if (!response.ok) {
        const data = await response.json();
        throw new Error(data.message || 'Ошибка сервера')
    }

    return response.json();
}