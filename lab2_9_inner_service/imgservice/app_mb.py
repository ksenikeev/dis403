import asyncio
import json
import nats
from nats.aio.msg import Msg


async def main():
    nc = await nats.connect ("nats://localhost:4222")

    async def request_handler(msg: Msg):
        try:
            print (f"Получен запрос: {msg.data.decode ()}")
            request_data = json.loads(msg.data.decode())

            response = {
                "status": "success",
                "received": request_data,
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
