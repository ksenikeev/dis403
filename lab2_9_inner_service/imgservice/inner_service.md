1. Версия Python `python --version`, `python3 --version`
2. Виртуальное окружение `python -m venv имя_окружения` : `python -m venv venv`
3. Активация виртуального окружения (далее вся работа будет с использованием 
этой локальной директории и локального интерпретатора) `source venv/bin/activate` 
(`venv\Scripts\activate.bat`)
4. Установка зависимостей `pip install flask opencv-python numpy requests`
   (`pip install --no-cache-dir -r requirements.txt`)
5. `docker build -t flask-opencv-app .`
6. `docker run -d --name opencv-flask -p 5000:5000 flask-opencv-app`
7. просмотр логов `docker logs -f opencv-flask`


