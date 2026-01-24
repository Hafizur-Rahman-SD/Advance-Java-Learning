
For see output in terminal give command like this. 

curl -X POST http://localhost:8090/api/ai/chat -H "Content-Type: application/json" -d "{\"prompt\":\"Say hello in one sentence\"}"



curl -X POST http://localhost:8090/api/ai/personalized -H "Content-Type: application/json" -d "{\"name\":\"Hafizur\",\"topic\":\"Microservices\"}"



curl -X POST http://localhost:8090/api/assistant/ask -H "Content-Type: application/json" -d "{\"question\":\"What is Spring Security?\"}"
