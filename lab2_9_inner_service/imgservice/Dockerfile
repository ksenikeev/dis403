FROM ubuntu:24.04
#FROM python:3.11-slim

ENV PYTHONDONTWRITEBYTECODE=1 \
    PYTHONUNBUFFERED=1 \
    DEBIAN_FRONTEND=noninteractive

WORKDIR /app

# 1. Чистим старый кэш (если остался от предыдущих попыток)
#RUN apt-get clean && rm -rf /var/lib/apt/lists/*

# 2. Обновляем списки пакетов (выполняется отдельно для точной диагностики)
#RUN apt-get update

# 3. Устанавливаем системные зависимости
RUN apt-get install -y --no-install-recommends \
    ca-certificates \
    libgl1 \
    libglib2.0-0 \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

COPY . .

RUN useradd --create-home appuser && chown -R appuser:appuser /app
USER appuser

EXPOSE 5000

CMD ["gunicorn", "--bind", "0.0.0.0:5000", "--workers", "2", "--timeout", "120", "app:app"]