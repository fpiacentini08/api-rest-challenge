from http.server import BaseHTTPRequestHandler, HTTPServer
import random
import json

hName = "localhost"
Port = 8081
class PercentageServer (BaseHTTPRequestHandler):
    def do_GET (self):
        percentage = random.randint(0, 100)
        data = {
            "value" : percentage
        }
        json_data = json.dumps(data)
        self.send_response(200)
        self.send_header("Content-type", "application/json")
        self.end_headers()
        self.wfile.write(bytes(json_data, "utf-8"))
        self.log_message(f"Response: {json_data}")
if __name__ == "__main__":
    webServer = HTTPServer ((hName, Port), PercentageServer)
    print("Percentage Web server is running http://%s:%s" % (hName, Port))

    try:
        webServer.serve_forever()
    except KeyboardInterrupt:
        pass

    webServer.server_close()
    print("\nServer stopped.")
