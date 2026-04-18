from flask import Flask, request, Response, jsonify
import cv2
import numpy as np

app = Flask (__name__)


def load_image_from_request ():
    """Загрузка и декодирование изображения из POST-запроса"""
    if 'image' not in request.files:
        return None, jsonify ({"error": "Поле 'image' отсутствует в запросе"}), 400

    file = request.files['image']
    if not file.filename:
        return None, jsonify ({"error": "Файл не выбран"}), 400

    try:
        file_bytes = file.read ()
        nparr = np.frombuffer (file_bytes, np.uint8)
        # IMREAD_COLOR гарантирует 3-канальный формат (BGR), даже если исходник был grayscale
        img = cv2.imdecode (nparr, cv2.IMREAD_COLOR)
        if img is None:
            return None, jsonify ({"error": "Не удалось декодировать изображение"}), 400
        return img, None, None
    except Exception as e:
        return None, jsonify ({"error": f"Ошибка обработки: {str (e)}"}), 500


def encode_image_to_jpeg (img, quality=90):
    """Кодирование numpy-массива в JPEG и возврат в виде HTTP-ответа"""
    _, buffer = cv2.imencode ('.jpg', img, [cv2.IMWRITE_JPEG_QUALITY, quality])
    return Response (buffer.tobytes (), mimetype='image/jpeg')


@app.route ('/grayscale', methods=['POST'])
def grayscale ():
    img, error, status = load_image_from_request ()
    if error:
        return error, status

    # Преобразование в градации серого
    gray = cv2.cvtColor (img, cv2.COLOR_BGR2GRAY)
    return encode_image_to_jpeg (gray)


@app.route ('/resize', methods=['POST'])
def resize ():
    img, error, status = load_image_from_request ()
    if error:
        return error, status

    h, w = img.shape[:2]
    if w == 0:
        return jsonify ({"error": "Ширина изображения равна 0"}), 400

    # Пропорциональное масштабирование до ширины 100px
    new_w = 100
    new_h = int (h * (new_w / w))
    # INTER_AREA оптимален для уменьшения изображений
    resized = cv2.resize (img, (new_w, new_h), interpolation=cv2.INTER_AREA)
    return encode_image_to_jpeg (resized)


if __name__ == '__main__':
    app.run (host='0.0.0.0', port=5000, debug=True)
