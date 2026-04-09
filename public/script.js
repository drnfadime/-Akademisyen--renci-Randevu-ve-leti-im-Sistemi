const API_URL = window.location.hostname === '127.0.0.1' || window.location.hostname === 'localhost' 
    ? `http://${window.location.hostname}:5000/api/auth` 
    : '/api/auth';

// UI State Management
function showAuthModal(type, role = null) {
    const modal = document.getElementById('auth-modal');
    const loginContainer = document.getElementById('login-container');
    const registerContainer = document.getElementById('register-container');
    const alert = document.getElementById('auth-alert');
    const modalTitle = document.getElementById('modal-title');
    
    alert.classList.add('hidden');
    modal.classList.remove('hidden');
    
    if (type === 'login') {
        loginContainer.classList.remove('hidden');
        registerContainer.classList.add('hidden');
        
        if (role) {
            const roleName = role === 'ACADEMIC' ? 'Akademisyen' : 'Öğrenci';
            modalTitle.innerHTML = `<span class="${role === 'ACADEMIC' ? 'text-purple-400' : 'text-blue-400'}">${roleName}</span> Girişi`;
        } else {
            modalTitle.innerText = 'Giriş Yap';
        }
    } else {
        loginContainer.classList.add('hidden');
        registerContainer.classList.remove('hidden');
    }
}

function hideAuthModal() {
    document.getElementById('auth-modal').classList.add('hidden');
}

// Toast Notifications
function showToast(message, type = 'success') {
    const container = document.getElementById('toast-container');
    const toast = document.createElement('div');
    const bgColor = type === 'success' ? 'bg-green-500/20 border-green-500/50 text-green-500' : 'bg-red-500/20 border-red-500/50 text-red-500';
    const icon = type === 'success' ? 'fa-circle-check' : 'fa-circle-exclamation';

    toast.className = `${bgColor} border p-4 rounded-2xl shadow-xl backdrop-blur-md flex items-center space-x-3 min-w-[300px] animate-fade-in`;
    toast.innerHTML = `
        <i class="fas ${icon} text-lg"></i>
        <div class="flex-1 text-sm font-semibold">${message}</div>
    `;

    container.appendChild(toast);
    setTimeout(() => {
        toast.style.opacity = '0';
        toast.style.transform = 'translateY(10px)';
        toast.style.transition = 'all 0.5s ease';
        setTimeout(() => toast.remove(), 500);
    }, 4000);
}

// API Interactions
async function handleRegister(e) {
    e.preventDefault();
    const alert = document.getElementById('auth-alert');
    const spinner = document.getElementById('reg-spinner');
    
    const userData = {
        name: document.getElementById('reg-name').value,
        email: document.getElementById('reg-email').value,
        password: document.getElementById('reg-password').value,
        role: document.getElementById('reg-role').value
    };
    const confirmPassword = document.getElementById('reg-password-confirm').value;

    // Password Match Validation
    if (userData.password !== confirmPassword) {
        alert.textContent = 'Şifreler birbiriyle eşleşmiyor.';
        alert.className = 'mt-4 p-4 rounded-xl text-sm font-medium animate-shake bg-red-500/10 text-red-500 border border-red-500/30';
        alert.classList.remove('hidden');
        return;
    }

    // Password Complexity Validation
    const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
    if (!passwordRegex.test(userData.password)) {
        alert.textContent = 'Şifre en az 8 karakter olmalı; bir büyük harf, bir küçük harf, bir rakam ve bir özel karakter içermelidir.';
        alert.className = 'mt-4 p-4 rounded-xl text-sm font-medium animate-shake bg-red-500/10 text-red-500 border border-red-500/30';
        alert.classList.remove('hidden');
        return;
    }

    alert.classList.add('hidden');
    spinner.classList.remove('hidden');

    try {
        const res = await fetch(`${API_URL}/register`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(userData)
        });

        const data = await res.json();

        if (data.success) {
            showToast('Kayıt başarılı! Giriş yapılıyor...');
            // Otomatik giriş dene
            await login(userData.email, userData.password);
        } else {
            throw new Error(data.message);
        }
    } catch (error) {
        alert.textContent = error.message;
        alert.className = 'mt-4 p-4 rounded-xl text-sm font-medium animate-shake bg-red-500/10 text-red-500 border border-red-500/30';
        alert.classList.remove('hidden');
    } finally {
        spinner.classList.add('hidden');
    }
}

async function handleLogin(e) {
    e.preventDefault();
    const email = document.getElementById('login-email').value;
    const password = document.getElementById('login-password').value;
    await login(email, password);
}

async function login(email, password) {
    const alert = document.getElementById('auth-alert');
    const spinner = document.getElementById('login-spinner');

    alert.classList.add('hidden');
    spinner.classList.remove('hidden');

    try {
        const res = await fetch(`${API_URL}/login`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ email, password })
        });

        const data = await res.json();

        if (data.success) {
            localStorage.setItem('token', data.data.token);
            localStorage.setItem('user', JSON.stringify(data.data.user));
            
            showToast('Giriş başarılı! Yönlendiriliyorsunuz...');
            updateUI(data.data.user);
            setTimeout(() => hideAuthModal(), 1500);
        } else {
            throw new Error(data.message);
        }
    } catch (error) {
        alert.textContent = error.message;
        alert.className = 'mt-4 p-4 rounded-xl text-sm font-medium animate-shake bg-red-500/10 text-red-500 border border-red-500/30';
        alert.classList.remove('hidden');
    } finally {
        spinner.classList.add('hidden');
    }
}

function updateUI(user) {
    const navUserInfo = document.getElementById('nav-user-info');
    const welcomeText = document.getElementById('welcome-text');
    const heroSection = document.getElementById('hero-section');

    if (user) {
        navUserInfo.classList.remove('hidden');
        welcomeText.innerHTML = `Hoş geldin, <span class="text-white font-bold">${user.name}</span> <span class="text-xs bg-blue-600/20 text-blue-400 px-2 py-1 rounded ml-2 uppercase">${user.role === 'ACADEMIC' ? 'Akademisyen' : 'Öğrenci'}</span>`;
        // Hero kısmını biraz değiştir
        heroSection.querySelector('h1').innerHTML = `Tekrar Hoş Geldin, <br><span class="bg-gradient-to-r from-blue-400 to-purple-500 bg-clip-text text-transparent italic">${user.name.split(' ')[0]}!</span>`;
        heroSection.querySelector('p').textContent = 'Randevu paneliniz şu an hazırlanıyor. Yapay zeka asistanınız aktif hale getirildi.';
        heroSection.querySelector('.flex.space-x-4').innerHTML = `
            <button class="px-8 py-4 bg-gradient-to-r from-blue-600 to-indigo-600 rounded-2xl font-bold shadow-lg shadow-blue-600/30">Randevularımı Yönet</button>
        `;
    } else {
        navUserInfo.classList.add('hidden');
    }
}

function logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    window.location.reload();
}

// Initial session check
window.addEventListener('DOMContentLoaded', () => {
    const user = JSON.parse(localStorage.getItem('user'));
    if (user) {
        updateUI(user);
    }
});

// Event Listeners
document.getElementById('login-form').addEventListener('submit', handleLogin);
document.getElementById('register-form').addEventListener('submit', handleRegister);
