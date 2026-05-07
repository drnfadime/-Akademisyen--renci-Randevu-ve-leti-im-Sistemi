const bcrypt = require('bcryptjs');
const jwt = require('jsonwebtoken');
const userRepository = require('../repositories/UserRepository');

class AuthService {
    async register(userData) {
        const { email, password, name, role } = userData;

        if (!email.endsWith('@firat.edu.tr')) {
            throw new Error('Sadece @firat.edu.tr uzantılı e-postalar kabul edilmektedir.');
        }

        const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
        if (!passwordRegex.test(password)) {
            throw new Error('Şifre en az 8 karakter olmalı; bir büyük harf, bir küçük harf, bir rakam ve bir özel karakter içermelidir.');
        }

        const existingUser = await userRepository.findByEmail(email);
        if (existingUser) {
            throw new Error('Bu e-posta adresi zaten kullanımda.');
        }

        const hashedPassword = await bcrypt.hash(password, 10);

        return await userRepository.create({
            email,
            password: hashedPassword,
            name,
            role: role || 'STUDENT'
        });
    }

    async login(email, password) {
        const user = await userRepository.findByEmail(email);
        if (!user) {
            throw new Error('Hatalı e-posta veya şifre.');
        }

        const isPasswordValid = await bcrypt.compare(password, user.password);
        if (!isPasswordValid) {
            throw new Error('Hatalı e-posta veya şifre.');
        }

        const token = jwt.sign(
            { id: user.id, email: user.email, role: user.role },
            process.env.JWT_SECRET || 'gizli-anahtar-123',
            { expiresIn: '24h' }
        );

        return { user, token };
    }
}

module.exports = new AuthService();
