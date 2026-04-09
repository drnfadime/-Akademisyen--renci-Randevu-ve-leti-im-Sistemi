const express = require('express');
const router = express.Router();
const authController = require('../controllers/AuthController');

// @route   POST api/auth/register
// @desc    Kullanıcı kaydı
router.post('/register', authController.register);

// @route   POST api/auth/login
// @desc    Kullanıcı girişi
router.post('/login', authController.login);

module.exports = router;
