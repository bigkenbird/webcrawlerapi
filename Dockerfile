FROM openjdk:21-jdk-slim

# 設定環境變數
ENV CHROME_VERSION=114.0.5735.90-1

# 安裝所需工具和 Chrome，下載安裝 ChromeDriver
RUN apt-get update && \
    apt-get install -y wget curl gnupg2 unzip ca-certificates && \
    # 安裝 Google Chrome
    wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb && \
    dpkg -i google-chrome-stable_current_amd64.deb || apt-get install -f -y && \
    # 清理不需要的檔案
    rm google-chrome-stable_current_amd64.deb && \
    # 安裝 ChromeDriver
    CHROMEDRIVER_VERSION=$(curl -sS chromedriver.storage.googleapis.com/LATEST_RELEASE) && \
    wget https://chromedriver.storage.googleapis.com/$CHROMEDRIVER_VERSION/chromedriver_linux64.zip && \
    unzip chromedriver_linux64.zip && \
    mv chromedriver /usr/local/bin/ && \
    chmod +x /usr/local/bin/chromedriver && \
    rm chromedriver_linux64.zip && \
    # 安裝其它必要的庫
    apt-get install -y libx11-dev libxcomposite-dev libxrandr-dev libglu1-mesa libxi6 libgconf-2-4


ARG JAR_FILE
COPY ${JAR_FILE} app.

ENTRYPOINT ["java", "-jar", "app.jar"]
