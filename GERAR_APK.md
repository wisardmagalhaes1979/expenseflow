# 🚀 Gerar APK - ExpenseFlow v1.16

## ✅ Pré-requisitos
- [Node.js](https://nodejs.org/) 16+ instalado
- [Android Studio](https://developer.android.com/studio) instalado
- [JDK 11+](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) instalado
- Pasta do projeto em: C:\Users\wizar\Downloads\expennse flow final\expenseflow\

## 📋 Passos para Gerar APK

### 1️⃣ Sincronizar com Android (Terminal)
```bash
cd C:\Users\wizar\Downloads\expennse flow final\expenseflow\
npm install
npx cap sync android
```

### 2️⃣ Abrir no Android Studio
```bash
npx cap open android
```

Isso abre Android Studio automaticamente com o projeto configurado.

### 3️⃣ Gerar APK (Android Studio)
1. Aguarde Gradle sincronizar (pode levar 2-5 min)
2. Menu: **Build** → **Build Bundle(s) / APK(s)** → **Build APK(s)**
3. Escolha: **debug** (para testes) ou **release** (produção)
4. Aguarde compilar (~2-10 minutos)

### 4️⃣ Localizar APK Gerado
```
Local: C:\Users\wizar\Downloads\expennse flow final\expenseflow\android\app\build\outputs\apk\debug\app-debug.apk
```

### 5️⃣ Instalar em Android (Testes)
```bash
adb install C:\Users\wizar\Downloads\expennse flow final\expenseflow\android\app\build\outputs\apk\debug\app-debug.apk
```

## 📱 Features Inclusos no APK v1.16
✅ Dashboard com gráficos gerenciais (Chart.js)
✅ Métricas de kilometragem (último KM - primeiro KM)
✅ Filtro por usuário no Dashboard
✅ Relatório Detalhado com novo design
✅ PDF profissional para cada despesa
✅ Validação: Combustível → KM obrigatório
✅ Sincronização com Supabase

## 🔧 Troubleshooting
- **Gradle error**: Limpar `android/.gradle` e rodar sync novamente
- **Plugin não encontrado**: Verifique internet e npm install
- **APK grande demais**: Normal (~5-10MB com recursos)

## 📞 Mais info
- Capacitor: https://capacitorjs.com
- Android Studio: https://developer.android.com/studio
