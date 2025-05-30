# Этап сборки
FROM eclipse-temurin:21-jdk AS builder

# Установка необходимых системных библиотек
RUN apt-get update && apt-get install -y \
    libnss3 \
    libnspr4 \
    libasound2-dev \
    libglib2.0-0t64 \
    libdbus-1-3 \
    libatk1.0-0t64 \
    libatk-bridge2.0-0t64 \
    libatspi2.0-0t64 \
    libxcomposite1 \
    libxdamage1 \
    libxfixes3 \
    libxrandr2 \
    libgbm1 \
    libxkbcommon0 \
    curl unzip git \
    && rm -rf /var/lib/apt/lists/*

# Установка Gradle вручную (если нужен Gradle 8.x)
ENV GRADLE_VERSION=8.14
RUN curl -sSL https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip -o gradle.zip \
    && unzip gradle.zip -d /opt \
    && ln -s /opt/gradle-${GRADLE_VERSION}/bin/gradle /usr/bin/gradle \
    && rm gradle.zip

# Копируем исходники
WORKDIR /app
COPY . .

# Выполняем сборку Kobweb
RUN gradle kobwebExport \
    -PkobwebReuseServer=false \
    -PkobwebEnv=DEV \
    -PkobwebRunLayout=FULLSTACK \
    -PkobwebBuildTarget=RELEASE \
    -PkobwebExportLayout=FULLSTACK \
    --stacktrace \
    --no-daemon

# Этап выполнения
FROM eclipse-temurin:21-jre

WORKDIR /app

# Копируем сгенерированное приложение
COPY --from=builder /app/site/.kobweb/ .kobweb

# Делаем скрипт исполняемым
RUN chmod +x ./.kobweb/server/start.sh

EXPOSE 8080

CMD ["./.kobweb/server/start.sh"]
