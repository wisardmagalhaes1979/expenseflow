# Permissão de Câmera — passo obrigatório após `npx cap add android`

Após rodar `npx cap add android`, abra o arquivo:

```
android/app/src/main/AndroidManifest.xml
```

Adicione estas linhas ANTES de `<application`:

```xml
<uses-permission android:name="android.permission.CAMERA" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-feature android:name="android.hardware.camera" android:required="false" />
<uses-feature android:name="android.hardware.camera.autofocus" android:required="false" />
```

Também dentro de `<application`, adicione:

```xml
<provider
    android:name="androidx.core.content.FileProvider"
    android:authorities="${applicationId}.fileprovider"
    android:exported="false"
    android:grantUriPermissions="true">
    <meta-data
        android:name="android.support.FILE_PROVIDER_PATHS"
        android:resource="@xml/file_paths" />
</provider>
```

Crie o arquivo `android/app/src/main/res/xml/file_paths.xml` com:

```xml
<?xml version="1.0" encoding="utf-8"?>
<paths>
    <external-path name="my_images" path="." />
    <cache-path name="my_cache" path="." />
</paths>
```

Depois rode novamente:
```bash
npx cap sync android
npx cap open android
```

E gere o APK normalmente pelo Android Studio.
