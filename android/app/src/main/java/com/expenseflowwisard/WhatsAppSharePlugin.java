package com.expenseflowwisard;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Base64;

import androidx.core.content.FileProvider;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

import java.io.File;
import java.io.FileOutputStream;

@CapacitorPlugin(name = "WhatsAppShare")
public class WhatsAppSharePlugin extends Plugin {

    private static final String[] WHATSAPP_PACKAGES = {"com.whatsapp", "com.whatsapp.w4b"};

    @PluginMethod
    public void share(PluginCall call) {
        String data = call.getString("data");
        String fileName = call.getString("fileName");
        String mimeType = call.getString("mimeType");

        if (data == null || fileName == null || mimeType == null) {
            call.reject("Parâmetros ausentes (data, fileName, mimeType)");
            return;
        }

        try {
            Context context = getContext();

            byte[] bytes = Base64.decode(data, Base64.DEFAULT);
            File cacheDir = new File(context.getCacheDir(), "shared");
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }
            File file = new File(cacheDir, fileName);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bytes);
            fos.close();

            Uri uri = FileProvider.getUriForFile(
                context,
                context.getPackageName() + ".fileprovider",
                file
            );

            boolean opened = false;
            for (String pkg : WHATSAPP_PACKAGES) {
                Intent waIntent = new Intent(Intent.ACTION_SEND);
                waIntent.setType(mimeType);
                waIntent.putExtra(Intent.EXTRA_STREAM, uri);
                waIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                waIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                waIntent.setPackage(pkg);
                try {
                    context.startActivity(waIntent);
                    opened = true;
                    break;
                } catch (ActivityNotFoundException e) {
                    // tenta o próximo pacote do WhatsApp
                }
            }

            if (!opened) {
                // WhatsApp não instalado: cai para o menu padrão de compartilhamento
                Intent sendIntent = new Intent(Intent.ACTION_SEND);
                sendIntent.setType(mimeType);
                sendIntent.putExtra(Intent.EXTRA_STREAM, uri);
                sendIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                Intent chooser = Intent.createChooser(sendIntent, "Compartilhar");
                chooser.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(chooser);
            }

            JSObject ret = new JSObject();
            ret.put("opened", opened);
            call.resolve(ret);
        } catch (Exception e) {
            call.reject("Erro ao compartilhar: " + e.getMessage(), e);
        }
    }
}
