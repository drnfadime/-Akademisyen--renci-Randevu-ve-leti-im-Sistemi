const authService = require('../services/AuthService');

class AuthController {
    register = async (req, res) => {
        try {
            const user = await authService.register(req.body);
            
            // Hassas bilgileri temizle
            const { password, ...userWithoutPassword } = user;

            return res.status(201).json({
                success: true,
                message: 'Kullanıcı başarıyla kaydedildi.',
                data: userWithoutPassword
            });
        } catch (error) {
            return res.status(400).json({
                success: false,
                message: error.message
            });
        }
    }

    login = async (req, res) => {
        try {
            const { email, password } = req.body;
            const { user, token } = await authService.login(email, password);

            // Hassas bilgileri temizle
            const { password: _, ...userWithoutPassword } = user;

            return res.status(200).json({
                success: true,
                message: 'Giriş başarılı.',
                data: {
                    user: userWithoutPassword,
                    token
                }
            });
        } catch (error) {
            return res.status(401).json({
                success: false,
                message: error.message
            });
        }
    }
}

module.exports = new AuthController();
