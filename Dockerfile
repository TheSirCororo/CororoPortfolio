# ---------- сборка ----------
FROM eclipse-temurin:21-jdk-noble AS builder

RUN apt-get update && apt-get install -y --no-install-recommends \
    libnss3 \
    libnspr4 \
    libasound2t64 \
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
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY gradlew gradle.properties settings.gradle.kts ./
COPY gradle gradle
COPY site/build.gradle.kts site/
# conf.yaml обязателен: без него плагин Kobweb не считает каталог своим проектом
# и падает с "This project is not a Kobweb project".
COPY site/.kobweb/conf.yaml site/.kobweb/conf.yaml
RUN chmod +x gradlew && ./gradlew --no-daemon kotlinNpmInstall

COPY . .

RUN chmod +x gradlew && ./gradlew kobwebExport \
    -PkobwebReuseServer=false \
    -PkobwebEnv=DEV \
    -PkobwebRunLayout=FULLSTACK \
    -PkobwebBuildTarget=RELEASE \
    -PkobwebExportLayout=FULLSTACK \
    --stacktrace \
    --no-daemon

# ---------- выполнение ----------
FROM eclipse-temurin:21-jre-noble

WORKDIR /app

COPY --from=builder /app/site/.kobweb/ .kobweb

RUN chmod +x ./.kobweb/server/start.sh

EXPOSE 8080

CMD ["./.kobweb/server/start.sh"]
