import asyncio
import base64
import json
import nats
from nats.aio.msg import Msg
import cv2

async def main():
    nc = await nats.connect ("nats://localhost:4222")

    async def request_handler(msg: Msg):
        try:
            print (f"Получен запрос: {msg.data}")
            request_data = json.loads(msg.data.decode())

            data_array = base64.decode(request_data["image"])
            img = cv2.imread (data_array)
            flipped = cv2.flip (img, 0)
            success, jpeg_bytes = cv2.imencode ('.jpg', flipped, [cv2.IMWRITE_JPEG_QUALITY, 85])

            response = {
                "status": "success",
                "image": base64.b64encode(jpeg_bytes).decode('ascii'),
            }
            response_json = json.dumps(response)

            await msg.respond(response_json.encode ())
            print ("Ответ отправлен")
        except json.JSONDecodeError as e:
            error_response = {"status": "error", "message": f"Invalid JSON: {str (e)}"}
            await msg.respond (json.dumps (error_response).encode ())

    # Подписка на тему
    await nc.subscribe("request.image.mirror", cb=request_handler)
    print ("Python‑сервер запущен, ожидает запросы на 'request.image.mirror'")

    # Держим сервер активным
    try:
        await asyncio.Future()  # бесконечное ожидание
    except KeyboardInterrupt:
        await nc.close ()


if __name__ == "__main__":
    asyncio.run (main ())
