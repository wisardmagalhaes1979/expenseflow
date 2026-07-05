# --- Etapa 1: Build (só roda se houver package.json) ---
FROM node:20-alpine AS builder
WORKDIR /app
COPY . .
RUN if [ -f package.json ]; then \
      npm install && \
      (npm run build || echo "Sem script de build, seguindo sem build"); \
    fi

# --- Etapa 2: Servir com nginx ---
FROM nginx:alpine
# Copia a pasta de build se existir (dist ou build), senão copia tudo
COPY --from=builder /app /usr/share/nginx/html
RUN if [ -d /usr/share/nginx/html/dist ]; then \
      rm -rf /tmp/html && mv /usr/share/nginx/html/dist /tmp/html && \
      rm -rf /usr/share/nginx/html && mv /tmp/html /usr/share/nginx/html; \
    elif [ -d /usr/share/nginx/html/build ]; then \
      rm -rf /tmp/html && mv /usr/share/nginx/html/build /tmp/html && \
      rm -rf /usr/share/nginx/html && mv /tmp/html /usr/share/nginx/html; \
    fi
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
