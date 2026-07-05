# ExpenseFlow — Gerar APK com Capacitor

## O que você precisa instalar (gratuito)
1. **Node.js LTS** → https://nodejs.org
2. **Android Studio** → https://developer.android.com/studio
3. **Java JDK 17** → https://www.oracle.com/java/technologies/downloads/#java17

---

## Estrutura de pastas (monte assim no seu computador)

```
📁 expenseflow/
├── package.json
├── capacitor.config.json
├── manifest.json
├── sw.js
└── 📁 www/
    ├── index.html          ← renomeie expenseflow.html para index.html
    └── 📁 icons/
        ├── icon-192.png
        └── icon-512.png
```

---

## Passo a passo no terminal

### 1. Abra o terminal na pasta `expenseflow/`

### 2. Instale as dependências
```bash
npm install
```

### 3. Adicione a plataforma Android
```bash
npx cap add android
```

### 4. Sincronize os arquivos
```bash
npx cap sync android
```

### 5. Abra no Android Studio
```bash
npx cap open android
```

---

## Gerar o APK no Android Studio

1. Aguarde o Android Studio indexar o projeto (pode demorar alguns minutos)
2. Menu: **Build → Generate Signed Bundle / APK**
3. Selecione **APK**
4. Clique em **Create new** para criar sua chave de assinatura
   - Preencha os campos e **anote bem a senha** — você vai precisar sempre
5. Selecione **release**
6. Clique em **Finish**
7. O APK será gerado em: `android/app/release/app-release.apk`

---

## Publicar na Play Store

1. Acesse: https://play.google.com/console
2. Crie uma conta de desenvolvedor (taxa única de US$25)
3. Crie um novo app → preencha as informações
4. Vá em **Produção → Criar nova versão**
5. Faça upload do arquivo `.apk` gerado
6. Preencha a ficha do app e envie para revisão

---

## Dicas importantes

- O app se conecta ao Supabase normalmente pelo Android
- A câmera funciona nativamente (acesso a fotos e câmera)
- Mantenha o arquivo `capacitor.config.json` com seu `appId` único
- Para atualizar o app: edite o HTML → `npx cap sync` → gere novo APK

---

## Suporte
appId: com.girbtec.expenseflow
