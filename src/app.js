const express = require('express');
const cors = require('cors');
const dotenv = require('dotenv');
const authRoutes = require('./routes/authRoutes');

dotenv.config();

const app = express();

// Middleware
app.use(cors());
app.use(express.json());
app.use(express.static('public')); // Statik dosyaları sun

// Routes
app.use('/api/auth', authRoutes);

// Base Route
app.get('/', (req, res) => {
    res.json({
        message: 'FÜSİS - Akademisyen-Öğrenci Randevu Sistemi API Başlatıldı.',
        status: 'Premium'
    });
});

const PORT = process.env.PORT || 5000;

app.listen(PORT, '127.0.0.1', () => {
    console.log(`Server http://127.0.0.1:${PORT} adresinde çalışıyor.`);
});

module.exports = app;
