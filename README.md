# -Akademisyen-ogrenci-Randevu-ve iletisim-Sistemi
Akademisyen-öğrenci iletişimini dijitalleştiren bu platformda, öğrencilerin uzun randevu talepleri yapay zeka ile 1-2 cümlelik net özetlere dönüştürülerek iletilir. Amacımız; e-posta karmaşasına ve kapı önü beklemelerine son verip onay süreçlerini çok daha verimli kılmaktır

## 🚀 Proje Açıklaması

Sistem, öğrencilerin akademisyenlerin uygunluk takvimlerini anlık olarak görüntüleyerek randevu talebinde (konu, tarih ve saat seçimi ile) bulunabilmelerini; akademisyenlerin ise kendilerine gelen talepleri kendi panelleri üzerinden onaylama, reddetme veya erteleme gibi işlemlerle dinamik olarak yönetebilmelerini sağlar. Proje, kurumsal standartlara uygun, güvenli ve yüksek performanslı bir hibrit mimariyle geliştirilmiştir.

### ✨ Öne Çıkan Özellikler
* **Rol Tabanlı Yetkilendirme:** Öğrenci ve Akademisyen panelleri için tamamen ayrıştırılmış kullanıcı deneyimi.
* **Akıllı Randevu Yönetimi:** Öğrenciler için esnek talep oluşturma formları; akademisyenler için bekleyen talepleri listeleyen dinamik onay/red paneli.
* **Güvenli Kimlik Doğrulama:** Google Cloud Console üzerinden yapılandırılmış OAuth 2.0 entegrasyonu ile kurumsal ve güvenli giriş altyapısı.
* **Gelişmiş Veritabanı Mimarisi:** Prisma ORM ve ilişkisel veritabanı (SQL) yapısı ile optimize edilmiş veri sorguları ve tam veri tutarlılığı.

---

## 🛠️ Kullanılan Teknolojiler

Proje, hem güçlü ve kurumsal bir backend ekosistemini (Java & Spring Boot) hem de modern veritabanı araçlarını (Prisma ORM) bir arada barındıran hibrit bir teknoloji yığını (stack) kullanmaktadır:

* **Backend / Sunucu Tarafı:** Java, Spring Boot (JPA, Hibernate, Lombok, Spring Context)
* **Veritabanı & ORM:** SQL (PostgreSQL / MySQL), Prisma ORM
* **Paket & Bağımlılık Yönetimi:** Maven (Java bileşenleri için), npm / yarn (Node.js & Prisma katmanı için)
* **Kimlik Doğrulama:** OAuth 2.0 & Google Cloud Console Entegrasyonu
* **Sürüm Kontrolü:** Git & GitHub
